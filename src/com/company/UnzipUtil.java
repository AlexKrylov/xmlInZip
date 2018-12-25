package com.company;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipUtil {
    public static void unzip(String path) {
        File file = new File(path);
        try {
            ZipFile zip = new ZipFile(path);
            Enumeration entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }
            }
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }
}
