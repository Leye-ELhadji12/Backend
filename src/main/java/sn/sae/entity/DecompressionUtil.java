package sn.sae.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DecompressionUtil {
    public static byte[] decompressFile(byte[] compressedData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
             ZipInputStream zipIn = new ZipInputStream(bais);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            ZipEntry entry = zipIn.getNextEntry();
            if (entry != null) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zipIn.read(buffer)) > 0) {
                    baos.write(buffer, 0, len);
                }
                zipIn.closeEntry();
            }
            return baos.toByteArray();
        }
    }
}

