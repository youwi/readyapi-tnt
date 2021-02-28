import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

/**
 * readapi3.6-tnt
 * Created by yu on 2021/2/27.
 */
public class PlanB {

    @Test
    void byByteCode() throws Exception {
        // 操作字节码,
        String fileCoreName = "LicenseReaderPlanC";

        ClassReader classReaderOri = new ClassReader("com.jp.protection.pub.LicenseReader");

        ClassReader classReaderCrack = new ClassReader("com.jp.protection.pub.LicenseReaderCrack");

        ClassWriter cw = new ClassWriter(classReaderOri, 0);

        InjectMethodVisitor injectMethodVisitor = new InjectMethodVisitor(cw);
        classReaderOri.accept(injectMethodVisitor, 0);
        classReaderCrack.accept(injectMethodVisitor, 0);


        //PlanA.javaToAsmSource("com.jp.protection.pub.LicenseReader");
        //
        //String out=ori.replace("LicenseReaderDump","LicenseReaderPlanCDump");
        //
        //PlanFixExit.saveDumpFixJavaFile(fileCoreName, out);
        //
        //PlanFixExit.loadAsmClassAndRunDump(fileCoreName + "DumpFix");

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
                PlanA.publicObject = cv.visitMethod(access, name, descriptor, signature, exceptions);
            } else if (PlanA.publicObject != null) {
                return (MethodVisitor) PlanA.publicObject;
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

