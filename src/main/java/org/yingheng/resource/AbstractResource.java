package org.yingheng.resource;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 16:52
 *
 *
 * 对资源Resource标准实现
 */
public abstract class AbstractResource implements Resource {

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public String getFileName() {
        return null;
    }
}
