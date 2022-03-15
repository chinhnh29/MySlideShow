package com.photoeditor.slideshow.utils;

import java.io.File;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void deleteRecursive(File file) {
        try {
            if (file.isDirectory()) {
                for (File deleteRecursive : file.listFiles()) {
                    deleteRecursive(deleteRecursive);
                }
            }
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
