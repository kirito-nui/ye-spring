package org.yingheng.propertyresolver.source;

import org.yingheng.resource.EncodedResource;
import org.yingheng.resource.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 16:02
 */
public class ResourcePropertySource extends PropertiesPropertySource {

    private final String resourceName;

    public ResourcePropertySource(EncodedResource resource) throws IOException {
        super(getNameForResource(resource.getResource()), loadProperties(resource));
        this.resourceName = null;
    }

    public ResourcePropertySource(String name, EncodedResource resource) throws IOException {
        super(name, loadProperties(resource));
        this.resourceName = name;
    }

    private static String getNameForResource(Resource resource) {
        return resource.getClass().getSimpleName() + "@" + System.identityHashCode(resource);
    }

    private static Properties loadProperties(EncodedResource resource) throws IOException {
        Properties props = new Properties();
        InputStream stream = null;
        try {
            String fileName = resource.getResource().getFileName();
            stream = resource.getInputStream();
            props.load(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return props;
    }

    public ResourcePropertySource(String name, Map<String, Object> source, String resourceName) {
        super(name, source);
        this.resourceName = resourceName;
    }
}
