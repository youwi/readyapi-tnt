import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;
import java.util.Properties;

/**
 * readapi3.6-tnt
 * Created by yu on 2021/2/26.
 */
public class PlanC {
    static Object publicObject = null;

    @Test
    public void byJavaCodePlanC() throws IOException {

        PlanA.javaToAsmSource("com.jp.protection.pub.LicenseReader");

        String oriString = PlanA.readFileToString("src/test/java/LicenseReaderDump.java");

        String matchString = "methodVisitor.visitInsn(ICONST_0);\nmethodVisitor.visitFieldInsn(PUTFIELD, \"com/jp/protection/pub/LicenseReader\", \"fSkipEncryption\", \"Z\");";
        String newString = "methodVisitor.visitInsn(ICONST_1);\nmethodVisitor.visitFieldInsn(PUTFIELD, \"com/jp/protection/pub/LicenseReader\", \"fSkipEncryption\", \"Z\");";
        // 修正类型不一致
        String out = oriString.replace(matchString, newString);
        out = out.replace("public class LicenseReaderDump implements", "public class LicenseReaderDumpMixPlanC implements");
        out = out.replace("package asm.com.jp.protection.pub", "");
        out = out.trim();

        File outFile = new File("src/test/java/LicenseReaderDumpMixPlanC.java");
        if (outFile.exists()) outFile.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        fileOutputStream.write(out.getBytes());
        fileOutputStream.flush();
    }

    @Test
    public void classDumpTestPlanC() throws Exception {
        byte[] s = LicenseReaderDumpMixPlanC.dump();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("FakePlanC.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }

    @Test
    public void buildDecodeKey() throws IOException {
        /*
         * 把密文key生成明文key.旧版本破解方式需要这种key
         */
        byte[] bytes = LicenseTest.keyToTextByName("soapui36.key");
        //替换过期时间
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Properties properties = new Properties();
        properties.load(byteArrayInputStream);
        properties.setProperty("exd","9472048779952");
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        properties.store(bo,"");

        LicenseTest.pureKeyToKeyFile(bo.toByteArray(), "soapui36-decode.key");
    }


    @Test
    public void buildDecodeKeyManual() throws IOException {
        /*
         * 如果要手动修改soapui36.key.txt这个文件,用这个单元测试生成明文key.
         * (会添加crc检验)
         * 注意修改文件不能有中文,不能乱加空格和标点符号
         */

        byte[] bytes =PlanA.readFileToString("soapui36.key.txt").getBytes();
        //替换过期时间
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Properties properties = new Properties();
        properties.load(byteArrayInputStream);
        properties.setProperty("exd","8472048779952");
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        properties.store(bo,"");

        LicenseTest.pureKeyToKeyFile(bo.toByteArray(), "soapui36-decode.key");
    }
}
