package org.yingheng.resource;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 17:42
 */
public class FileSystemResource extends AbstractResource{

    private Path path;

    public FileSystemResource(Path path) {
        this.path = path;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }
}
