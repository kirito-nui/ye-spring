package org.yingheng.resource;

import java.io.InputStream;

/**
 * @author: yeyingheng
 * @date: 2023/5/25 19:07
 */
public class EncodedResource implements InputStreamSource{

    private final Resource resource;

    public EncodedResource(Resource resource) {
        this.resource = resource;
    }

    public final Resource getResource() {
        return this.resource;
    }
    @Override
    public InputStream getInputStream() {
        return this.resource.getInputStream();
    }
}
