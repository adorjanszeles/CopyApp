package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

public class Copy {
    public void copyFileOrFolder(File source, File dest, CopyOption...  options) throws IOException {
        if (source.isDirectory()) {
            copyFolder(source, dest, options);
        }
        else {
            ensureParentFolder(dest);
            copyFile(source, dest, options);
        }
    }

    private void copyFolder(File source, File dest, CopyOption... options) throws IOException {
        if (!dest.exists()) {
            dest.mkdirs();
        }
        File[] contents = source.listFiles();
        if (contents != null) {
            for (File f : contents) {
                File newFile = new File(dest.getAbsolutePath() + File.separator + f.getName());
                if (f.isDirectory()) {
                    copyFolder(f, newFile, options);
                }
                else {
                    copyFile(f, newFile, options);
                }
            }
        }
    }

    private void copyFile(File source, File dest, CopyOption... options) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), options);
    }

    private void ensureParentFolder(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }
}
