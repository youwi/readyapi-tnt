import org.junit.jupiter.api.Test;

/**
 * crack
 * Created by yu on 2021/3/1.
 */
public class WithConcurrentXmlLoadProcess {

    /**
     *  替换2个反射调用,导致其不能调用 System.exit()
     * @throws Exception
     */
    @Test
    public void fixExitTestByAsmCode() throws Exception {
        String fileCoreName = "ConcurrentXmlLoadProcess";

        WithProtectionPlanA.classToAsmSource("com.smartbear.ready.module.ConcurrentXmlLoadProcess");

        String matchStringA = "methodVisitor.visitLdcInsn(\"ex\")";
        String matchStringB = "methodVisitor.visitLdcInsn(\"it\")";

        String matchString = "methodVisitor.visitMethodInsn(INVOKESTATIC, \"java/lang/Integer\", \"valueOf\", \"(I)Ljava/lang/Integer;\", false);\n" +
                "methodVisitor.visitInsn(AASTORE);\n" +
                "methodVisitor.visitMethodInsn(INVOKESTATIC, \"com/smartbear/ready/utils/ReflectionUtils\", \"callMethod\", \"(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;\", false);\n";
        String targetString = "methodVisitor.visitMethodInsn(INVOKESTATIC, \"java/lang/Integer\", \"valueOf\", \"(I)Ljava/lang/Integer;\", false);\n" +
                "methodVisitor.visitInsn(AASTORE);";
        String oriString = WithProtectionPlanA.readFileToString("src/test/java/gen/" + fileCoreName + "Dump.java");

        String out = JarZipUtil.replaceStringAtByBlock(oriString, matchString, targetString, matchStringA);

         JarZipUtil.saveDumpFixJavaFile(fileCoreName, out);

         JarZipUtil.loadAsmClassAndRunDump(fileCoreName + "DumpFix");
    }


}
