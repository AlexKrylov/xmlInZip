package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static void zip(String path, String archiveName, String outDir) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outDir + archiveName));
        FileInputStream fis = new FileInputStream(path);
        int index = path.indexOf("r");
        ZipEntry zipEntry = new ZipEntry(path.substring(index));
        zipOutputStream.putNextEntry(zipEntry);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        zipOutputStream.write(buffer);
        zipOutputStream.closeEntry();
    }
}
