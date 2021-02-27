package asm.com.jp.protection.pub;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;
public class LicenseReaderCrackDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V1_6, ACC_PUBLIC | ACC_SUPER, "com/jp/protection/pub/LicenseReaderCrack", null, "java/lang/Object", null);

classWriter.visitSource("LicenseReaderCrack.java", null);

{
fieldVisitor = classWriter.visitField(ACC_PROTECTED, "fLicense", "Lcom/jp/protection/pub/License;", null, null);
fieldVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(19, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
methodVisitor.visitInsn(RETURN);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLocalVariable("this", "Lcom/jp/protection/pub/LicenseReaderCrack;", null, label0, label1, 0);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PROTECTED, "readLicense", "([B)V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
Label label1 = new Label();
Label label2 = new Label();
methodVisitor.visitTryCatchBlock(label0, label1, label2, null);
Label label3 = new Label();
methodVisitor.visitTryCatchBlock(label2, label3, label2, null);
Label label4 = new Label();
Label label5 = new Label();
Label label6 = new Label();
methodVisitor.visitTryCatchBlock(label4, label5, label6, "java/lang/Exception");
methodVisitor.visitLabel(label4);
methodVisitor.visitLineNumber(24, label4);
methodVisitor.visitTypeInsn(NEW, "java/io/ByteArrayInputStream");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/io/ByteArrayInputStream", "<init>", "([B)V", false);
methodVisitor.visitVarInsn(ASTORE, 2);
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(26, label0);
methodVisitor.visitTypeInsn(NEW, "java/util/Properties");
methodVisitor.visitInsn(DUP);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Properties", "<init>", "()V", false);
methodVisitor.visitVarInsn(ASTORE, 3);
Label label7 = new Label();
methodVisitor.visitLabel(label7);
methodVisitor.visitLineNumber(27, label7);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Properties", "load", "(Ljava/io/InputStream;)V", false);
Label label8 = new Label();
methodVisitor.visitLabel(label8);
methodVisitor.visitLineNumber(28, label8);
methodVisitor.visitTypeInsn(NEW, "com/jp/protection/pub/LicenseImpl");
methodVisitor.visitInsn(DUP);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/jp/protection/pub/LicenseImpl", "<init>", "()V", false);
methodVisitor.visitVarInsn(ASTORE, 4);
Label label9 = new Label();
methodVisitor.visitLabel(label9);
methodVisitor.visitLineNumber(29, label9);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/jp/protection/pub/LicenseImpl", "getLicenseExpireDate", "()Ljava/util/Date;", false);
methodVisitor.visitIntInsn(SIPUSH, 2114);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "setYear", "(I)V", false);
Label label10 = new Label();
methodVisitor.visitLabel(label10);
methodVisitor.visitLineNumber(30, label10);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/jp/protection/utils/LicenseUtils", "load", "(Lcom/jp/protection/pub/License;Ljava/util/Properties;)V", false);
Label label11 = new Label();
methodVisitor.visitLabel(label11);
methodVisitor.visitLineNumber(31, label11);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitFieldInsn(PUTFIELD, "com/jp/protection/pub/LicenseReaderCrack", "fLicense", "Lcom/jp/protection/pub/License;");
Label label12 = new Label();
methodVisitor.visitLabel(label12);
methodVisitor.visitLineNumber(32, label12);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitLdcInsn("1");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/jp/protection/pub/LicenseImpl", "setLicenseNumber", "(Ljava/lang/String;)V", false);
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(34, label1);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/ByteArrayInputStream", "close", "()V", false);
Label label13 = new Label();
methodVisitor.visitLabel(label13);
methodVisitor.visitLineNumber(35, label13);
methodVisitor.visitJumpInsn(GOTO, label5);
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(34, label2);
methodVisitor.visitVarInsn(ASTORE, 5);
methodVisitor.visitLabel(label3);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/ByteArrayInputStream", "close", "()V", false);
Label label14 = new Label();
methodVisitor.visitLabel(label14);
methodVisitor.visitLineNumber(35, label14);
methodVisitor.visitVarInsn(ALOAD, 5);
methodVisitor.visitInsn(ATHROW);
methodVisitor.visitLabel(label5);
methodVisitor.visitLineNumber(39, label5);
Label label15 = new Label();
methodVisitor.visitJumpInsn(GOTO, label15);
methodVisitor.visitLabel(label6);
methodVisitor.visitLineNumber(36, label6);
methodVisitor.visitVarInsn(ASTORE, 2);
Label label16 = new Label();
methodVisitor.visitLabel(label16);
methodVisitor.visitLineNumber(37, label16);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitFieldInsn(PUTFIELD, "com/jp/protection/pub/LicenseReaderCrack", "fLicense", "Lcom/jp/protection/pub/License;");
Label label17 = new Label();
methodVisitor.visitLabel(label17);
methodVisitor.visitLineNumber(38, label17);
methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "getCause", "()Ljava/lang/Throwable;", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Throwable", "toString", "()Ljava/lang/String;", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
methodVisitor.visitLabel(label15);
methodVisitor.visitLineNumber(40, label15);
methodVisitor.visitInsn(RETURN);
Label label18 = new Label();
methodVisitor.visitLabel(label18);
methodVisitor.visitLocalVariable("properties", "Ljava/util/Properties;", null, label7, label1, 3);
methodVisitor.visitLocalVariable("license", "Lcom/jp/protection/pub/LicenseImpl;", null, label9, label1, 4);
methodVisitor.visitLocalVariable("byteArrayInputStream", "Ljava/io/ByteArrayInputStream;", null, label0, label5, 2);
methodVisitor.visitLocalVariable("ex", "Ljava/lang/Exception;", null, label16, label15, 2);
methodVisitor.visitLocalVariable("this", "Lcom/jp/protection/pub/LicenseReaderCrack;", null, label4, label18, 0);
methodVisitor.visitLocalVariable("array", "[B", null, label4, label18, 1);
methodVisitor.visitMaxs(3, 6);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
}
