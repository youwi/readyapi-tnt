import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class JarZipUtil {

    public static void fffxxxx() {

    }

    /**
     * 获取路径命名
     *
     * @param classFullName
     * @throws Exception
     */
    public static String echoClassJarName(String classFullName) throws Exception {
        return Class.forName(classFullName).getProtectionDomain().getCodeSource().getLocation().getFile();
    }


    /**
     * 按默认名字保存为DumpFix.java文件
     *
     * @param fileCoreName 文件名
     * @param data
     * @throws IOException
     */
    static void saveDumpFixJavaFile(String fileCoreName, String data) throws IOException {
        data = data.replaceAll("package asm.(.*?);", "//");
        data = data.replace(fileCoreName + "Dump", fileCoreName + "DumpFix");
        WithProtectionPlanA.stringToFile(data, "src/test/java/gen/" + fileCoreName + "DumpFix.java");
    }

    /**
     * 在大段文本中找到小字符串然后替换掉
     *
     * @param oriString    原始大文本
     * @param craString    要替换的小串
     * @param targetString 替换为
     * @param matchString  定位串
     * @return
     */
    static String replaceStringAtByBlock(String oriString, String craString, String targetString, String matchString) {
        String[] oriStrings = oriString.split("\\}");
        String[] craStrings = craString.split("\\}");
        StringBuilder outString = new StringBuilder();

        for (int i = 0; i < oriStrings.length; i++) {
            String tmpString = oriStrings[i];
            if (tmpString.contains(matchString)) {
                oriStrings[i] = oriStrings[i].replace(craString, targetString);
            }
        }
        for (int i = 0; i < oriStrings.length; i++) {
            String tmpString = oriStrings[i];
            outString.append(tmpString);
            if (i > 0) {
                outString.append("}");
            }
        }
        return outString.toString();
    }


    /**
     * 使用反射运行生成为DumpFix.dump()方法
     *
     * @param fileClassName
     * @throws Exception
     */
    public static void loadAsmClassAndRunDump(String fileClassName) throws Exception {
        byte[] s = (byte[]) Class.forName(fileClassName).getMethod("dump").invoke(null);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileClassName + ".class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }
}
