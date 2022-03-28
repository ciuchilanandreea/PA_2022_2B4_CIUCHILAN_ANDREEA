package com.company.Panels;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Class FolderFilter extends FileFilter and accepts only folders*/
public class FolderFilter extends FileFilter {

    @Override
    public boolean accept(File file){
        return file.isDirectory();
    }

    @Override
    public String getDescription(){
        return "Directories only!";
    }
}