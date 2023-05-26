package org.yingheng.resource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.yingheng.util.ResourceUtils;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 17:04
 */
public class PathMatchingResourcePatternResolver implements ResourceLoader {

    private Path path;

    private ResourceLoader resourceLoader;

    public PathMatchingResourcePatternResolver() {
        this.resourceLoader = new DefaultResourceLoader();
    }

    @Override
    public Resource getResource(String location) {
        return null;
    }

    @Override
    public Resource[] getResources(String locationPattern) {
        Set<Resource> result = new HashSet<>();
        if (locationPattern.startsWith(CLASSPATH_ALL_URL_PREFIX)) {
            String locationPatternWithoutPrefix = locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length());
            // todo 加载模板的配置文件 应该是根目录
            // Search the module path first.
//            Set<Resource> resources = findAllModulePathResources(locationPatternWithoutPrefix);
            // Search the class path next.
            if (isPattern(locationPatternWithoutPrefix)) {
                Resource[] pathMatchingResources = findPathMatchingResources(locationPattern);
                result.addAll(List.of(pathMatchingResources));
            } else {
                Resource[] allClassPathResources = findAllClassPathResources(locationPatternWithoutPrefix);
                result.addAll(List.of(allClassPathResources));
            }
        }
        return result.toArray(new Resource[0]);
    }


    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver pmrpr =
                new PathMatchingResourcePatternResolver();
        String locationPattern = "classpath*:*.properties";
        Resource[] resources = pmrpr.getResources(locationPattern);
        System.out.println();
    }

    private Resource[] findAllClassPathResources(String locationPatternWithoutPrefix) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> classLoaderResources = null;
        try {
            classLoaderResources = contextClassLoader.getResources(locationPatternWithoutPrefix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Set<Resource> result = new LinkedHashSet<>(16);
        while (classLoaderResources.hasMoreElements()) {
            URL url = classLoaderResources.nextElement();
            result.add(convertClassLoaderURL(url));
        }
        return result.toArray(new Resource[result.size()]);
    }

    private Resource[] findPathMatchingResources(String locationPattern) {

//        String rootDirPath = determineRootDir(locationPattern);
        Set<Resource> result = new HashSet<>();
        try {
            String rootDirPath = locationPattern.substring(0, CLASSPATH_ALL_URL_PREFIX.length());
            String subPattern = locationPattern.substring(rootDirPath.length());
            // 加载根目录配置文件
            Resource[] resources = getResources(rootDirPath);
            for (Resource rootDirResource : resources) {
                URL rootDirResourceURL = rootDirResource.getURL();
                Collections.addAll(result, doFindPathMatchingFileResources(rootDirResource, subPattern));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result.toArray(new Resource[0]);
    }

    private static Resource[] doFindPathMatchingFileResources(Resource rootDirResource, String subPattern) {
        try {
            URI rootDirResourceURI = rootDirResource.getURI();
            Path of = Path.of(rootDirResourceURI);
            Set<Resource> result = new HashSet<>();
            Predicate<Path> isMatchingFile = new MyPathPredicate(of, null, of.toString() + subPattern, subPattern);
            try {
                Stream<Path> pathStream = Files.walk(of);
                pathStream.filter(isMatchingFile).sorted().map(FileSystemResource::new).forEach(result::add);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return result.toArray(new Resource[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyPathPredicate implements Predicate<Path> {
        private final Path rootPathForPattern;
        private final PathMatcher pathMatcher;
        private final String resourcePattern;
        private final String subPattern;

        public MyPathPredicate(Path rootPathForPattern, PathMatcher pathMatcher, String resourcePattern, String subPattern) {
            this.rootPathForPattern = rootPathForPattern;
            this.pathMatcher = pathMatcher;
            this.resourcePattern = resourcePattern;
            String str1 = subPattern.substring(0, subPattern.indexOf("."));
            String str2 = subPattern.substring(str1.length()+1, subPattern.length());
            this.subPattern = str2;
        }

        @Override
        public boolean test(Path path) {
            String cleanPath = path.toString();
            return !path.equals(rootPathForPattern) && cleanPath.endsWith(this.subPattern);
        }
    }

    public static boolean isPattern(String path) {
        if (path == null) {
            return false;
        }
        boolean uriVar = false;
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '*' || c == '?') {
                return true;
            }
            if (c == '{') {
                uriVar = true;
                continue;
            }
            if (c == '}' && uriVar) {
                return true;
            }
        }
        return false;
    }

//    protected String determineRootDir(String location) {
//        int prefixEnd = location.indexOf(':') + 1;
//        int rootDirEnd = location.length();
//        while (rootDirEnd > prefixEnd && getPathMatcher().isPattern(location.substring(prefixEnd, rootDirEnd))) {
//            rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
//        }
//        if (rootDirEnd == 0) {
//            rootDirEnd = prefixEnd;
//        }
//        return location.substring(0, rootDirEnd);
//    }
    protected static Resource convertClassLoaderURL(URL url) {
        if (ResourceUtils.URL_PROTOCOL_FILE.equals(url.getProtocol())) {
            try {
                // URI decoding for special characters such as spaces.
                return new FileSystemResource(ResourceUtils.toURI(url).getSchemeSpecificPart());
            } catch (URISyntaxException ex) {
                // Fallback for URLs that are not valid URIs (should hardly ever happen).
                return new FileSystemResource(url.getFile());
            }
        } else {
//            return new UrlResource(url);
            return null;
        }
    }
}
