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
public class PlanC {
    static Object publicObject = null;

    @Test
    public void byJavaCodePlanC() throws IOException {

        PlanA.javaToAsmSource("com.jp.protection.pub.LicenseReader");

        String oriString = PlanA.readFileToString("src/test/java/LicenseReaderDump.java");

        String matchString = "methodVisitor.visitInsn(ICONST_0);\nmethodVisitor.visitFieldInsn(PUTFIELD, \"com/jp/protection/pub/LicenseReader\", \"fSkipEncryption\", \"Z\");";
        String newString = "methodVisitor.visitInsn(ICONST_1);\nmethodVisitor.visitFieldInsn(PUTFIELD, \"com/jp/protection/pub/LicenseReader\", \"fSkipEncryption\", \"Z\");";
        // 修正类型不一致
        String out=oriString.replace(matchString,newString);
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
}
