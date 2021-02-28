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
        byClassFullNamePlanC("com.jp.protection.pub.LicenseReader");

        byClassFullName("com.smartbear.ready.module.ConcurrentXmlLoadProcess");

        byClassFullName("com.smartbear.ready.license.LicenseBootstrap");

        byClassFullName("com.eviware.soapui.support.swing.SwingUtils");

       // byClassFullNameMainClass("com.eviware.soapui.support.swing.SwingUtils");

    }
    public void byClassFullNameMainClass(String classFullName) throws Exception {
        String className = Class.forName(classFullName).getSimpleName();
        JarZipUtil.classToZipFile("lib/ready-api-soapui-3.6.0.jar", PlanA.echoClassInnerPath(classFullName),
                "build/classes/java/main/"+PlanA.echoClassInnerPath(classFullName));
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
