import org.junit.jupiter.api.Test;

/**
 * crack
 * Created by yu on 2021/3/1.
 */
public class WithLicenseBootstrap {


    @Test
    public void fixLicenseBootstrapTest() throws Exception {
        String fileCoreName = "LicenseBootstrap";
        WithProtectionPlanA.classToAsmSource("com.smartbear.ready.license.LicenseBootstrap");
        String oriString = WithProtectionPlanA.readFileToString("src/test/java/gen/" + fileCoreName + "Dump.java");

        String out = oriString.replace("methodVisitor.visitMethodInsn(INVOKESTATIC, \"com/eviware/soapui/support/swing/SwingUtils\", \"exit\", \"(I)V\", false);\n", "");

        JarZipUtil.saveDumpFixJavaFile(fileCoreName, out);
        JarZipUtil.loadAsmClassAndRunDump(fileCoreName + "DumpFix");
    }

}
