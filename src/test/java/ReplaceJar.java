import org.junit.jupiter.api.Test;

/**
 * readyapi-tnt
 * Created by yu on 2021/2/28.
 */
public class ReplaceJar {

    @Test
    void buildAllJars() throws Exception {

        byClassFullName("com.jp.protection.pub.LicenseReader");
        //byClassFullNamePlanB("com.jp.protection.pub.LicenseReader");
        //byClassFullNamePlanC("com.jp.protection.pub.LicenseReader");

        byClassFullName("com.smartbear.ready.module.ConcurrentXmlLoadProcess");

        byClassFullName("com.smartbear.ready.license.LicenseBootstrap");

        // byClassFullName("com.eviware.soapui.support.swing.SwingUtils");
        // byClassFullNameCrackPlanC("com.eviware.soapui.support.swing.SwingUtils");

    }

    public void byClassFullNameCrackPlanB(String classFullName) throws Exception {
        String className = Class.forName(classFullName).getSimpleName();
        JarZipUtil.classToZipFile("lib/", PlanA.echoClassInnerPath(classFullName)
                , className + "CrackPlanCDumpFix.class");
    }

    public void byClassFullNameCrackPlanC(String classFullName) throws Exception {
        String className = Class.forName(classFullName).getSimpleName();
        JarZipUtil.classToZipFile(PlanA.echoClassJarName(classFullName), PlanA.echoClassInnerPath(classFullName)
                , className + "CrackPlanCDumpFix.class");
    }

    public void byClassFullName(String classFullName) throws Exception {
        String className = Class.forName(classFullName).getSimpleName();
        JarZipUtil.classToZipFile(PlanA.echoClassJarName(classFullName), PlanA.echoClassInnerPath(classFullName), className + "DumpFix.class");
    }

    public void byClassFullNamePlanB(String classFullName) throws Exception {
        String className = Class.forName(classFullName).getSimpleName();
        JarZipUtil.classToZipFile(PlanA.echoClassJarName(classFullName), PlanA.echoClassInnerPath(classFullName), className + "PlanBDumpFix.class");
    }

    public void byClassFullNamePlanC(String classFullName) throws Exception {
        String className = Class.forName(classFullName).getSimpleName();
        JarZipUtil.classToZipFile(PlanA.echoClassJarName(classFullName), PlanA.echoClassInnerPath(classFullName), className + "PlanCDumpFix.class");
    }
}
