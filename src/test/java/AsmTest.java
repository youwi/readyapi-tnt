import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * readyapi-tnt
 * Created by yu on 2021/2/27.
 */
public class AsmTest {

    int fSkipEncryptionInt = 1234;
    boolean fSkipEncryptionTrue = true;
    int abc=12;
    boolean fSkipEncryptionFalse = false;

    @Test
    public void sfe() throws Exception {
        System.out.println(fSkipEncryptionTrue);
        //mv.visitInsn(ICONST_1);
        //mv.visitFieldInsn(PUTFIELD, "AsmTest", "fSkipEncryption", "Z");
        echoClassJarName("com.jp.protection.pub.LicenseReader");
    }


    void echoClassJarName(String classFullName) throws Exception {
        String jarFilePath = Class.forName(classFullName).getProtectionDomain().getCodeSource().getLocation().getFile();
       // jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
        System.out.println(jarFilePath);
    }
    void sfe(String classFullName) throws Exception{
        Class.forName(classFullName).getPackage().getName().replace(".","/");
    }
}
