package org.yingheng.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 17:42
 */
public class FileSystemResource extends AbstractResource {

    private String path;

    private File file;
    private Path filePath;

    public FileSystemResource(Path path) {
        this.filePath = path;
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
        this.filePath = this.file.toPath();
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return Files.newInputStream(this.filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public URL getURL() throws IOException {
        return this.file != null ? this.file.toURI().toURL() : this.filePath.toUri().toURL();
    }

    @Override
    public URI getURI() throws IOException {
        return this.file != null ? this.file.toURI() : this.filePath.toUri();
    }

    @Override
    public String getFileName() {
        return this.file != null ? this.file.getName() : this.filePath.getFileName().toString();
    }
}
