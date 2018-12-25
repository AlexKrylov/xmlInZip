package com.company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class XMLHandler {
    private static String dir = "C:/xmlin/";
    private static String outDir = "C:/xmlout/";
    private static String nameXML;

    public static void xmlChanger() throws SAXException, TransformerException, ParserConfigurationException, IOException {
        File file = new File(dir);
        File[] fileList = file.listFiles();
        for (File f : Objects.requireNonNull(fileList)) {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(f));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                nameXML = entry.getName();
            }
            String name = f.getName();
            UnzipUtil.unzip(f.getPath());
            XMLChanger.changeXml(dir + nameXML);
            ZipUtil.zip(dir + nameXML, name, outDir);
        }
    }
}
