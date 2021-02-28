import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * readyapi-tnt
 * Created by yu on 2021/2/28.
 */
public class PlanFixExit {

    @Test
    public void fixExitTestByAsmCode() throws IOException {
        String fileCoreName="ConcurrentXmlLoadProcess";

        PlanA.javaToAsmSource("com.smartbear.ready.module.ConcurrentXmlLoadProcess");

        String matchStringA = "methodVisitor.visitLdcInsn(\"ex\")";
        String matchStringB = "methodVisitor.visitLdcInsn(\"it\")";

        String matchString = "methodVisitor.visitMethodInsn(INVOKESTATIC, \"java/lang/Integer\", \"valueOf\", \"(I)Ljava/lang/Integer;\", false);\n" +
                "methodVisitor.visitInsn(AASTORE);\n" +
                "methodVisitor.visitMethodInsn(INVOKESTATIC, \"com/smartbear/ready/utils/ReflectionUtils\", \"callMethod\", \"(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;\", false);\n";
        String targetString = "methodVisitor.visitMethodInsn(INVOKESTATIC, \"java/lang/Integer\", \"valueOf\", \"(I)Ljava/lang/Integer;\", false);\n" +
                "methodVisitor.visitInsn(AASTORE);";
        String oriString = PlanA.readFileToString("src/test/java/ConcurrentXmlLoadProcessDump.java");

        String out = replaceStringAtByBlock(oriString, matchString, targetString, matchStringA);

        saveDumpFixJavaFile(fileCoreName,out);
    }

    @Test
    public void fixLicenseBootstrapTest() throws IOException {
        String fileCoreName="LicenseBootstrap";
        PlanA.javaToAsmSource("com.smartbear.ready.license.LicenseBootstrap");

        String oriString = PlanA.readFileToString("src/test/java/LicenseBootstrapDump.java");

        String out = oriString.replace("methodVisitor.visitMethodInsn(INVOKESTATIC, \"com/eviware/soapui/support/swing/SwingUtils\", \"exit\", \"(I)V\", false);\n","");

        saveDumpFixJavaFile(fileCoreName,out);
    }
    static void saveDumpFixJavaFile(String fileCoreName,String data) throws IOException {
        data = data.replaceAll("package asm.(.*?);", "//");
        data = data.replace(fileCoreName+"Dump", fileCoreName+"DumpFix");
        PlanA.stringToFile(data, "src/test/java/"+fileCoreName+"DumpFix.java");
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


    @Test
    public void fixClassDumpTest() throws Exception {
        byte[] s = (byte[]) Class.forName("ConcurrentXmlLoadProcessDumpFix").getMethod("dump").invoke(null);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("cxp.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }

    @Test
    public void fixLicenseBootstrapTestDump() throws Exception {
        byte[] s = (byte[]) Class.forName("LicenseBootstrapDumpFix").getMethod("dump").invoke(null);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("lbd.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }


    @Test
    public void base64Test() {
        // new String(Base64.decodeBase64(h));

        final byte[] h = new byte[]{89, 50, 57, 116, 76, 109, 86, 50, 97, 88, 100, 104, 99, 109, 85, 117, 99, 50, 49, 104, 99, 110, 82, 105, 90, 87, 70, 121, 76, 110, 74, 108, 89, 87, 82, 53, 76, 109, 49, 118, 90, 72, 86, 115, 90, 83, 53, 85, 98, 50, 57, 115, 89, 109, 70, 121, 81, 87, 78, 48, 97, 87, 57, 117, 81, 88, 78, 122, 97, 88, 78, 48, 89, 87, 53, 48};
        final byte[] i = new byte[]{(byte) 109, (byte) 100, (byte) 53, (byte) 72, (byte) 101, (byte) 120};
        final byte[] j = new byte[]{(byte) 111, (byte) 114, (byte) 103, (byte) 46, (byte) 97, (byte) 112, (byte) 97, (byte) 99, (byte) 104, (byte) 101, (byte) 46, (byte) 99, (byte) 111, (byte) 109, (byte) 109, (byte) 111, (byte) 110, (byte) 115, (byte) 46, (byte) 99, (byte) 111, (byte) 100, (byte) 101, (byte) 99, (byte) 46, (byte) 100, (byte) 105, (byte) 103, (byte) 101, (byte) 115, (byte) 116, (byte) 46, (byte) 68, (byte) 105, (byte) 103, (byte) 101, (byte) 115, (byte) 116, (byte) 85, (byte) 116, (byte) 105, (byte) 108, (byte) 115};
        final byte[] XML_KEYS = new byte[]{(byte) 87, (byte) 87, (byte) 57, (byte) 49, (byte) 99, (byte) 105, (byte) 66, (byte) 83, (byte) 90, (byte) 87, (byte) 70, (byte) 107, (byte) 101, (byte) 83, (byte) 69, (byte) 103, (byte) 81, (byte) 86, (byte) 66, (byte) 74, (byte) 73, (byte) 71, (byte) 108, (byte) 117, (byte) 99, (byte) 51, (byte) 82, (byte) 104, (byte) 98, (byte) 71, (byte) 120, (byte) 104, (byte) 100, (byte) 71, (byte) 108, (byte) 118, (byte) 98, (byte) 105, (byte) 66, (byte) 122, (byte) 90, (byte) 87, (byte) 86, (byte) 116, (byte) 99, (byte) 121, (byte) 66, (byte) 48, (byte) 98, (byte) 121, (byte) 66, (byte) 105, (byte) 90, (byte) 83, (byte) 66, (byte) 106, (byte) 98, (byte) 51, (byte) 74, (byte) 121, (byte) 100, (byte) 88, (byte) 66, (byte) 48, (byte) 90, (byte) 87, (byte) 81, (byte) 117, (byte) 73, (byte) 70, (byte) 74, (byte) 108, (byte) 89, (byte) 87, (byte) 82, (byte) 53, (byte) 73, (byte) 83, (byte) 66, (byte) 66, (byte) 85, (byte) 69, (byte) 107, (byte) 103, (byte) 100, (byte) 50, (byte) 108, (byte) 115, (byte) 98, (byte) 67, (byte) 66, (byte) 122, (byte) 97, (byte) 72, (byte) 86, (byte) 48, (byte) 90, (byte) 71, (byte) 57, (byte) 51, (byte) 98, (byte) 105, (byte) 52, (byte) 61};

        System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(h)));
        System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(i)));
        System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(j)));
        System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(XML_KEYS)));
        // com.eviware.smartbear.ready.module.ToolbarActionAssistant();
        //h= com.eviware.smartbear.ready.module.ToolbarActionAssistant
        //         Your Ready! API installation seems to be corrupted. Ready! API will shutdown.
    }
}
