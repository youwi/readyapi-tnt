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

    @Test
    public void byJavaCode() throws IOException {
        // ASMifier.main(new String[]{"com.jp.protection.pub.LicenseReader"});
        // ASMifier.main(new String[]{"build/classes/java/main/com/jp/protection/pub/LicenseReader.class"});
        ASMifier afOri = new ASMifier();
        ASMifier afCrack = new ASMifier();

        File genFile=new File("src/test/java/LicenseReaderDump.java");
        if(genFile.exists()){
            genFile.delete();
        }
        genFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(genFile);

        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, (Printer) afOri, new PrintWriter(fileOutputStream, true));

        new ClassReader("com.jp.protection.pub.LicenseReader").accept(traceClassVisitor, 4);

        fileOutputStream.flush();
    }

    @Test
    void byByteCode() throws IOException {
        // 操作字节码,
        ClassReader classReaderOri = new ClassReader("com.jp.protection.pub.LicenseReader");

        ClassReader classReaderCrack = new ClassReader("com.jp.protection.pub.LicenseReaderCrack");

        ClassWriter cw = new ClassWriter(classReaderOri, 0);

        classReaderOri.accept(new InjectMethodVisitor(cw), 0);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("FakePlanB.class"));
        fileOutputStream.write(cw.toByteArray());
        fileOutputStream.flush();

    }

    @Test
    public void classDumpTest() throws Exception {
        byte[] s = LicenseReaderFix.dump();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("Fake.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }
}

class InjectMethodVisitor extends ClassVisitor {
    public InjectMethodVisitor(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals("readLicense")) {
            System.out.println("readLicense1111");
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(V1_5, access, name, signature, superName, interfaces);

    }
}


class ReturnReadLicense extends ClassVisitor {
    public ReturnReadLicense(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name.equals("readLicense")) {
            System.out.println("readLicense1111");
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
