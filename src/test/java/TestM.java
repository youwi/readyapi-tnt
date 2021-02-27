import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

/**
 * readapi3.6-tnt
 * Created by yu on 2021/2/26.
 */
public class TestM {
    static Object publicObject = null;

    @Test
    public void byJavaCode() throws IOException {
        // 操作asm码
        ASMifier afOri = new ASMifier();
        ASMifier afCrack = new ASMifier();
        // ASMifier.main(new String[]{"com.jp.protection.pub.LicenseReader"});

        javaToAsmSource("com.jp.protection.pub.LicenseReader");
        javaToAsmSource("com.jp.protection.pub.LicenseReaderCrack");

        String oriString= readFileToString("src/test/java/LicenseReaderDump.java");
        String craString= readFileToString("src/test/java/LicenseReaderCrackDump.java");

        String[] oriStrings=oriString.split("\\}");
        String[] craStrings=craString.split("\\}");
        String matchString="ACC_PROTECTED, \"readLicense\", \"([B)V\", null, null";
        StringBuilder outString=new StringBuilder();

        for(int i=0;i<oriStrings.length;i++){
            String tmpString=oriStrings[i];
            if(tmpString.contains(matchString)){
                for(String repString:craStrings){
                    if(repString.contains(matchString)){
                        oriStrings[i]=repString;
                        System.out.println("这是第x行"+i);
                        break;
                    }
                }
            }
        }
        for(int i=0;i<oriStrings.length;i++){
            String tmpString=oriStrings[i];
            outString.append(tmpString);
            if(i>0){
                outString.append("}");
            };
        }
        String out=outString.toString().replace("LicenseReaderCrack","LicenseReader");
        out=out.replace("public class LicenseReaderDump implements","public class LicenseReaderDumpMix implements");
        out=out.replace("package asm.com.jp.protection.pub","");
        out=out.trim();
        File outFile=new File("src/test/java/LicenseReaderDumpMix.java");
        if(outFile.exists()) outFile.delete();
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        fileOutputStream.write(out.getBytes());
        fileOutputStream.flush();
    }

    /**
     * 把字节码转换为asm源码,文件保存到 src/test/java目录下.
     *
     * @param className
     * @throws IOException
     */
    void javaToAsmSource(String className) throws IOException {
        ASMifier sourcePrinter = new ASMifier();
        String[] nameList = className.split("\\.");
        File genFile = new File("src/test/java/" + nameList[nameList.length - 1] + "Dump.java");

        if (genFile.exists()) {
            genFile.delete();
        }
        genFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(genFile);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, (Printer) sourcePrinter, new PrintWriter(fileOutputStream, true));
        new ClassReader(className).accept(traceClassVisitor, 4);
        fileOutputStream.flush();

    }

    /**
     * 把文件读取为字符串,效果和 FileUtils.readFileToString一样
     * @param filePath
     * @return
     * @throws IOException
     */
     public static String readFileToString(String filePath) throws IOException {
         StringBuffer fileData = new StringBuffer();
         BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    @Test
    void byByteCode() throws IOException {
        // 操作字节码,
        ClassReader classReaderOri = new ClassReader("com.jp.protection.pub.LicenseReader");

        ClassReader classReaderCrack = new ClassReader("com.jp.protection.pub.LicenseReaderCrack");

        ClassWriter cw = new ClassWriter(classReaderOri, 0);

        InjectMethodVisitor injectMethodVisitor = new InjectMethodVisitor(cw);
        classReaderOri.accept(injectMethodVisitor, 0);
        classReaderCrack.accept(injectMethodVisitor, 0);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("FakePlanB.class"));
        fileOutputStream.write(cw.toByteArray());
        fileOutputStream.flush();

    }

    @Test
    public void classDumpTest() throws Exception {
        byte[] s = LicenseReaderDumpMix.dump();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("Fake.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }
}

class InjectMethodVisitor extends ClassVisitor {

    boolean isOri = false;

    public InjectMethodVisitor(ClassVisitor classVisitor) {
        //super(V1_5,classVisitor);
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals("readLicense") && descriptor.equals("([B)V")) {
            if (isOri) {
                TestM.publicObject = cv.visitMethod(access, name, descriptor, signature, exceptions);
            } else if (TestM.publicObject != null) {
                return (MethodVisitor) TestM.publicObject;
            } else {
                System.out.println("没有初始化设置isOri");
            }
            return null;
        }
        return cv.visitMethod(access, name, descriptor, signature, exceptions);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (name.equals("com/jp/protection/pub/LicenseReader")) { // className
            isOri = true;
        }
        cv.visit(V1_5, access, name, signature, superName, interfaces);

    }
}

