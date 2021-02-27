import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * readyapi-tnt
 * Created by yu on 2021/2/28.
 */
public class FixExit {

    @Test
    public void fixExitTest() throws IOException {
        PlanA.javaToAsmSource("com.smartbear.ready.module.ConcurrentXmlLoadProcess");
        //methodVisitor.visitLdcInsn("ex");
        String matchString = "methodVisitor.visitMethodInsn(INVOKESTATIC, \"com/smartbear/ready/utils/ReflectionUtils\", \"callMethod\", \"(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;\", false);";
        String oriString = PlanA.readFileToString("src/test/java/ConcurrentXmlLoadProcessDump.java");

        String out = oriString.replace(matchString, "//------");
        out = out.replace("package asm.com.smartbear.ready.module;", "");
        out= out.replace("ConcurrentXmlLoadProcessDump","ConcurrentXmlLoadProcessDumpFix");

        PlanA.stringToFile(out, "src/test/java/ConcurrentXmlLoadProcessDumpFix.java");


    }


    @Test
    public void fixClassDumpTest() throws Exception {
        byte[] s = ConcurrentXmlLoadProcessDumpFix.dump();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("cxp.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }

    @Test
    public void base64Test(){
        // new String(Base64.decodeBase64(h));

        final byte[] h = new byte[]{89, 50, 57, 116, 76, 109, 86, 50, 97, 88, 100, 104, 99, 109, 85, 117, 99, 50, 49, 104, 99, 110, 82, 105, 90, 87, 70, 121, 76, 110, 74, 108, 89, 87, 82, 53, 76, 109, 49, 118, 90, 72, 86, 115, 90, 83, 53, 85, 98, 50, 57, 115, 89, 109, 70, 121, 81, 87, 78, 48, 97, 87, 57, 117, 81, 88, 78, 122, 97, 88, 78, 48, 89, 87, 53, 48};

        String out=new String( org.apache.commons.codec.binary.Base64.decodeBase64(h));
        System.out.println(out);

    }
}
