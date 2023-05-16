package org.yingheng.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 17:04
 */
public class PathMatchingResourcePatternResolver implements ResourceLoader{

    private Path path;

    @Override
    public Resource getResource(String location) {
        return null;
    }

    @Override
    public Resource[] getResources(String locationPattern) {
        Path of = Path.of(locationPattern);
        Set<Resource> result = new HashSet<>();
        try {
            Stream<Path> pathStream = Files.walk(of);
            pathStream.sorted().map(FileSystemResource::new).forEach(result::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toArray(new Resource[result.size()]);
    }


    public static void main(String[] args) {
        PathMatchingResourcePatternResolver pmrpr =
                new PathMatchingResourcePatternResolver();
        String locationPattern = "E:\\ideaProject\\ye-spring\\build\\classes\\java\\main\\org\\yingheng\\useTest";
        Resource[] resources = pmrpr.getResources(locationPattern);
        for (Resource resource : resources) {
            System.out.println(resource);
        }
    }
}
