package org.yingheng.resource;

import java.io.File;
import java.io.InputStream;

/**
 *
 *  面向接口编程
 *  资源接口
 */
public interface Resource {

    boolean exists();

    boolean isOpen();

    File getFile();

    InputStream getInputStream();

    String getFileName();
}
