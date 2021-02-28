import org.junit.jupiter.api.Test;

/**
 * crack
 * Created by yu on 2021/3/1.
 */
public class WithSwingUtil {


    /**
     * 只替换一行代码
     * @throws Exception
     */
    // @Test
    public void fixSwingUtilsTestPlanA() throws Exception {
        /*
         * 使用Asm直接注释一行代码
         */
        String fileCoreName = "SwingUtils";
        WithProtectionPlanA.classToAsmSource("com.eviware.soapui.support.swing.SwingUtils");
        String oriString = WithProtectionPlanA.readFileToString("src/test/java/gen/" + fileCoreName + "Dump.java");

        String gcString = "methodVisitor.visitMethodInsn(INVOKESTATIC, \"java/lang/System\", \"gc\", null, false);";
        String iload = "methodVisitor.visitVarInsn(ILOAD, 0);\n";
        String PopString = "methodVisitor.visitInsn(POP);";
        gcString = PopString;
        iload = "";
        String out = oriString.replace(iload + "methodVisitor.visitMethodInsn(INVOKESTATIC, \"java/lang/System\", \"exit\", \"(I)V\", false);", gcString);

        JarZipUtil.saveDumpFixJavaFile(fileCoreName, out);
        JarZipUtil.loadAsmClassAndRunDump(fileCoreName + "DumpFix");
    }

    /**
     * ,替换直接替换整个代码块,为空方法
     * @throws Exception
     */
    //@Test
    void fixSwingUtilsTestPlanB() throws Exception {
        /*
        直接替换整个代码块,为空方法
         */
        String fileCoreName = "SwingUtils";

        WithProtectionPlanA.classToAsmSource("com.eviware.soapui.support.swing.SwingUtils");
        WithProtectionPlanA.classToAsmSource("com.eviware.soapui.support.swing.SwingUtilsCrack");

        String oriString = WithProtectionPlanA.readFileToString("src/test/java/gen/" + fileCoreName + "Dump.java");
        String craString = WithProtectionPlanA.readFileToString("src/test/java/gen/" + fileCoreName + "CrackDump.java");

        String matchString = "ACC_PUBLIC | ACC_STATIC, \"exit\", \"(I)V\", null, null";

        String out = WithProtectionPlanA.replaceStringAt(oriString, craString, matchString);

        // 修正类型不一致
        // out = out.replace(fileCoreName + "Crack", fileCoreName);
        out = out.replace("classWriter.visitNestMember(\"com/eviware/soapui/support/swing/SwingUtils$1\");", "");
        out = out.replace("classWriter.visitInnerClass(\"com/eviware/soapui/support/swing/SwingUtils$1\", null, null, 0);", "");

        JarZipUtil.saveDumpFixJavaFile(fileCoreName, out);
        JarZipUtil.loadAsmClassAndRunDump(fileCoreName + "DumpFix");
    }

    /**
     * 替换整个文件
     * @throws Exception
     */
    @Test
    void fixSwingUtilsTestPlanC() throws Exception {
        String fileCoreName = "SwingUtilsCrackPlanC";
        WithProtectionPlanA.classToAsmSource("com.eviware.soapui.support.swing.SwingUtilsCrackPlanC");
        String oriString = WithProtectionPlanA.readFileToString("src/test/java/gen/" + fileCoreName + "Dump.java");

        String out = oriString.replace("SwingUtilsCrackPlanC", "SwingUtils");
        out = out.replace("public class SwingUtilsDump", "public class SwingUtilsCrackPlanCDump");
        JarZipUtil.saveDumpFixJavaFile(fileCoreName, out);
        /* 这里有运行时bug. asm并不生成visitFrame语句,导致无法运行.
         *
         */
        JarZipUtil.loadAsmClassAndRunDump(fileCoreName + "DumpFix");
    }
}
