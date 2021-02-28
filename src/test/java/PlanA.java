import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;

/**
 * readapi3.6-tnt
 * Created by yu on 2021/2/26.
 */
public class PlanA {
    static Object publicObject = null;

    @Test
    public void byJavaCode() throws Exception {
        // 操作asm码
        String fileCoreName = "LicenseReader";

        classToAsmSource("com.jp.protection.pub.LicenseReader");
        classToAsmSource("com.jp.protection.pub.LicenseReaderCrack");

        String oriString = readFileToString("src/test/java/gen/LicenseReaderDump.java");
        String craString = readFileToString("src/test/java/gen/LicenseReaderCrackDump.java");

        String matchString = "ACC_PROTECTED, \"readLicense\", \"([B)V\", null, null";

        String out = replaceStringAt(oriString, craString, matchString);

        // 修正类型不一致
        out = out.replace("LicenseReaderCrack", "LicenseReader");

        PlanFixExit.saveDumpFixJavaFile(fileCoreName, out);
        PlanFixExit.loadAsmClassAndRunDump(fileCoreName + "DumpFix");

    }

    static void stringToFile(String out, String fileName) throws IOException {
        File outFile = new File(fileName);
        if (outFile.exists()) outFile.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        fileOutputStream.write(out.getBytes());
        fileOutputStream.flush();
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
     * 把类名,转换为路径名
     *
     * @param classFullName
     * @return
     * @throws Exception
     */
    public static String echoClassInnerPath(String classFullName) throws Exception {
        Class clazz = Class.forName(classFullName);
        String dir = clazz.getPackage().getName().replace(".", "/");
        return dir + "/" + clazz.getSimpleName() + ".class";
    }


    /**
     * 搜索并替换asm代码中的函数
     *
     * @param oriString   代码源
     * @param craString   替换源
     * @param matchString 搜索要替换的函数
     */
    static String replaceStringAt(String oriString, String craString, String matchString) {
        //oriString+" "添加空字符原因是最后一个会出bug
        String[] oriStrings = (oriString+" ").split("\\}");
        String[] craStrings = (craString+" ").split("\\}");
        StringBuilder outString = new StringBuilder();

        for (int i = 0; i < oriStrings.length; i++) {
            String tmpString = oriStrings[i];
            if (tmpString.contains(matchString)) {
                for (String repString : craStrings) {
                    if (repString.contains(matchString)) {
                        oriStrings[i] = repString;
                        //System.out.println("这是第x行" + i);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < oriStrings.length; i++) {
            String tmpString = oriStrings[i];
            outString.append(tmpString);
            if(i==oriStrings.length-1) break;
            outString.append("}");
        }
        return outString.toString().trim();
    }

    @Test
    void replaceStringAtTest() {
        String out;
        out = replaceStringAt("{A}{B}{C}", "{XB}", "B");
        System.out.println(out);
        assert out.equals("{A}{XB}{C}");

        out = replaceStringAt("{A}{B}{C}}\n\n", "{XB}", "B");
        System.out.println(out);
        assert out.equals("{A}{XB}{C}}");
    }

    /**
     * 把字节码转换为asm源码,文件保存到 src/test/java目录下.
     *
     * @param className
     * @throws IOException
     */
    static void classToAsmSource(String className) throws IOException {
        ASMifier sourcePrinter = new ASMifier();//ASM7,"vi",4);


        String[] nameList = className.split("\\.");
        File genFile = new File("src/test/java/gen/" + nameList[nameList.length - 1] + "Dump.java");

        if (genFile.exists()) {
            genFile.delete();
        }
        genFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(genFile);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, (Printer) sourcePrinter, new PrintWriter(fileOutputStream, true));
        new ClassReader(className).accept(traceClassVisitor, 0);
        fileOutputStream.flush();

    }

    /**
     * 把文件读取为字符串,效果和 FileUtils.readFileToString一样
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFileToString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }


}
