import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class JarZipUtil {


    /**
     * 复制class文件到zip文件中,替换LicenseReader.class
     * 默认路径为:lib/Protection-5.1.5.jar
     * 默认文件名:Protection-5.1.5-crack.jar
     */
    public static void classToZipFile(String zipFileName, String classFullName, String tmpClassFileName) {
        try {
            File zipIn = new File(zipFileName);
            if (!zipIn.exists()) {
                System.out.println("文件不存在");
                System.out.println();
            }
            if (!new File(tmpClassFileName).exists()) {
                System.out.println(tmpClassFileName + "文件不存在");
                System.out.println("class文件先由测试代码生成");
                return;
            }
            ZipFile zip = new ZipFile(zipIn);
            File zipTemp = File.createTempFile("tmp", ".jar", new File("./"));
            zipTemp.deleteOnExit();
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipTemp));

            final Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry thisEntry = entries.nextElement();
                if (!thisEntry.getName().equalsIgnoreCase(classFullName)) {
                    InputStream input = zip.getInputStream(thisEntry);
                    zos.putNextEntry(new ZipEntry(thisEntry.getName()));
                    zos.write(inputToByteArray(input));
                } else {
                    System.out.println(zipFileName+":中找到匹配文件-替换中");
                    zos.putNextEntry(new ZipEntry(thisEntry.getName()));
                    zos.write(fileToBytes(tmpClassFileName));
                }
                try {
                    zos.closeEntry();
                } catch (Exception e) {
                    System.err.println(thisEntry.getName());
                    System.err.println(e.getMessage());
                }
            }

            zos.close();
            zip.close();

            zipTemp.renameTo(new File(zipFileName.replace(".jar", "-crack.jar")));

        } catch (Exception e) {
            System.err.println(" " + classFullName + e.getCause() + " " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static byte[] inputToByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    public static byte[] fileToBytes(String filePath) {
        byte[] buffer = null;
        File file = new File(filePath);

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();

            byte[] b = new byte[1024];

            int n;

            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

            buffer = bos.toByteArray();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException ex) {
            } finally {
                try {
                    if (null != fis) {
                        fis.close();
                    }
                } catch (IOException ex) {
                }
            }
        }

        return buffer;
    }
}
