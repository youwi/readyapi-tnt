/**
 * readyapi-tnt
 * Created by yu on 2021/2/27.
 */
public class AsmTest {

    int fSkipEncryptionInt = 1234;
    boolean fSkipEncryptionTrue = true;
    int abc=12;
    boolean fSkipEncryptionFalse = false;

    public void sfe() {
        System.out.println(fSkipEncryptionTrue);
        //mv.visitInsn(ICONST_1);
        //mv.visitFieldInsn(PUTFIELD, "AsmTest", "fSkipEncryption", "Z");
    }
}
