package org.yingheng.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 *
 *  面向接口编程
 *  资源接口
 */
public interface Resource extends InputStreamSource{

    boolean exists();

    boolean isOpen();

    File getFile();

    InputStream getInputStream();

    String getFileName();

    URL getURL() throws IOException;
    URI getURI() throws IOException;
}
