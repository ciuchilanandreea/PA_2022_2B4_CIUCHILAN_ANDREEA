package org.example;

import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoading extends URLClassLoader{
    public ClassLoading() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

}