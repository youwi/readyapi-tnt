package asm.com.eviware.soapui.support.swing;
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
public class SwingUtilsCrackPlanCDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V1_7, ACC_PUBLIC | ACC_SUPER, "com/eviware/soapui/support/swing/SwingUtilsCrackPlanC", null, "java/lang/Object", new String[] { "com/eviware/soapui/support/UIUtils" });

classWriter.visitSource("SwingUtilsCrackPlanC.java", null);

{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(21, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
methodVisitor.visitInsn(RETURN);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtilsCrackPlanC;", null, label0, label1, 0);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "invokeLater", "(Ljava/lang/Runnable;)V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(24, label0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "invokeLater", "(Ljava/lang/Runnable;)V", false);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(25, label1);
methodVisitor.visitInsn(RETURN);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtilsCrackPlanC;", null, label0, label2, 0);
methodVisitor.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, label0, label2, 1);
methodVisitor.visitMaxs(1, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "invokeAndWait", "(Ljava/lang/Runnable;)V", null, new String[] { "java/lang/Exception" });
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(28, label0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "invokeAndWait", "(Ljava/lang/Runnable;)V", false);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(29, label1);
methodVisitor.visitInsn(RETURN);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtilsCrackPlanC;", null, label0, label2, 0);
methodVisitor.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, label0, label2, 1);
methodVisitor.visitMaxs(1, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "invokeAndWaitIfNotInEDT", "(Ljava/lang/Runnable;)V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
Label label1 = new Label();
Label label2 = new Label();
methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/InterruptedException");
Label label3 = new Label();
methodVisitor.visitTryCatchBlock(label0, label1, label3, "java/lang/reflect/InvocationTargetException");
Label label4 = new Label();
methodVisitor.visitLabel(label4);
methodVisitor.visitLineNumber(32, label4);
methodVisitor.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "isEventDispatchThread", "()Z", false);
methodVisitor.visitJumpInsn(IFEQ, label0);
Label label5 = new Label();
methodVisitor.visitLabel(label5);
methodVisitor.visitLineNumber(33, label5);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/lang/Runnable", "run", "()V", true);
Label label6 = new Label();
methodVisitor.visitJumpInsn(GOTO, label6);
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(36, label0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "invokeAndWait", "(Ljava/lang/Runnable;)V", false);
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(41, label1);
methodVisitor.visitJumpInsn(GOTO, label6);
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(37, label2);
methodVisitor.visitVarInsn(ASTORE, 2);
Label label7 = new Label();
methodVisitor.visitLabel(label7);
methodVisitor.visitLineNumber(38, label7);
methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V", false);
methodVisitor.visitInsn(ATHROW);
methodVisitor.visitLabel(label3);
methodVisitor.visitLineNumber(39, label3);
methodVisitor.visitVarInsn(ASTORE, 2);
Label label8 = new Label();
methodVisitor.visitLabel(label8);
methodVisitor.visitLineNumber(40, label8);
methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V", false);
methodVisitor.visitInsn(ATHROW);
methodVisitor.visitLabel(label6);
methodVisitor.visitLineNumber(44, label6);
methodVisitor.visitInsn(RETURN);
Label label9 = new Label();
methodVisitor.visitLabel(label9);
methodVisitor.visitLocalVariable("var3", "Ljava/lang/InterruptedException;", null, label7, label3, 2);
methodVisitor.visitLocalVariable("var4", "Ljava/lang/reflect/InvocationTargetException;", null, label8, label6, 2);
methodVisitor.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtilsCrackPlanC;", null, label4, label9, 0);
methodVisitor.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, label4, label9, 1);
methodVisitor.visitMaxs(3, 3);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "exit", "(I)V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(73, label0);
methodVisitor.visitInsn(RETURN);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLocalVariable("status", "I", null, label0, label1, 0);
methodVisitor.visitMaxs(0, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC | ACC_VARARGS, "findAllComponentsRecursively", "(Ljava/awt/Container;[Ljava/lang/Class;)Ljava/util/List;", "(Ljava/awt/Container;[Ljava/lang/Class<+Ljava/awt/Component;>;)Ljava/util/List<Ljava/awt/Component;>;", null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(76, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitLdcInsn(new Integer(2147483646));
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtilsCrackPlanC", "findAllComponentsRecursively", "(Ljava/awt/Container;I[Ljava/lang/Class;)Ljava/util/List;", false);
methodVisitor.visitInsn(ARETURN);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLocalVariable("container", "Ljava/awt/Container;", null, label0, label1, 0);
methodVisitor.visitLocalVariable("filter", "[Ljava/lang/Class;", "[Ljava/lang/Class<+Ljava/awt/Component;>;", label0, label1, 1);
methodVisitor.visitMaxs(3, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC | ACC_VARARGS, "findAllComponentsRecursively", "(Ljava/awt/Container;I[Ljava/lang/Class;)Ljava/util/List;", "(Ljava/awt/Container;I[Ljava/lang/Class<+Ljava/awt/Component;>;)Ljava/util/List<Ljava/awt/Component;>;", null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(80, label0);
methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
methodVisitor.visitInsn(DUP);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
methodVisitor.visitVarInsn(ASTORE, 3);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(81, label1);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 1);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtilsCrackPlanC", "internalFindAllComponentsRecursively", "(Ljava/util/List;Ljava/awt/Container;I[Ljava/lang/Class;)V", false);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(82, label2);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitInsn(ARETURN);
Label label3 = new Label();
methodVisitor.visitLabel(label3);
methodVisitor.visitLocalVariable("container", "Ljava/awt/Container;", null, label0, label3, 0);
methodVisitor.visitLocalVariable("depth", "I", null, label0, label3, 1);
methodVisitor.visitLocalVariable("filter", "[Ljava/lang/Class;", "[Ljava/lang/Class<+Ljava/awt/Component;>;", label0, label3, 2);
methodVisitor.visitLocalVariable("result", "Ljava/util/List;", "Ljava/util/List<Ljava/awt/Component;>;", label1, label3, 3);
methodVisitor.visitMaxs(4, 4);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_VARARGS, "internalFindAllComponentsRecursively", "(Ljava/util/List;Ljava/awt/Container;I[Ljava/lang/Class;)V", "(Ljava/util/List<Ljava/awt/Component;>;Ljava/awt/Container;I[Ljava/lang/Class<+Ljava/awt/Component;>;)V", null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(86, label0);
methodVisitor.visitVarInsn(ILOAD, 2);
Label label1 = new Label();
methodVisitor.visitJumpInsn(IFLT, label1);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(88, label2);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Container", "getComponents", "()[Ljava/awt/Component;", false);
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ASTORE, 4);
Label label3 = new Label();
methodVisitor.visitLabel(label3);
methodVisitor.visitInsn(ARRAYLENGTH);
methodVisitor.visitVarInsn(ISTORE, 5);
Label label4 = new Label();
methodVisitor.visitLabel(label4);
methodVisitor.visitLineNumber(90, label4);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 6);
Label label5 = new Label();
methodVisitor.visitLabel(label5);
methodVisitor.visitVarInsn(ILOAD, 6);
methodVisitor.visitVarInsn(ILOAD, 5);
methodVisitor.visitJumpInsn(IF_ICMPGE, label1);
Label label6 = new Label();
methodVisitor.visitLabel(label6);
methodVisitor.visitLineNumber(91, label6);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitVarInsn(ILOAD, 6);
methodVisitor.visitInsn(AALOAD);
methodVisitor.visitVarInsn(ASTORE, 7);
Label label7 = new Label();
methodVisitor.visitLabel(label7);
methodVisitor.visitLineNumber(92, label7);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ASTORE, 8);
Label label8 = new Label();
methodVisitor.visitLabel(label8);
methodVisitor.visitLineNumber(93, label8);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitInsn(ARRAYLENGTH);
methodVisitor.visitVarInsn(ISTORE, 9);
Label label9 = new Label();
methodVisitor.visitLabel(label9);
methodVisitor.visitLineNumber(95, label9);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 10);
Label label10 = new Label();
methodVisitor.visitLabel(label10);
methodVisitor.visitVarInsn(ILOAD, 10);
methodVisitor.visitVarInsn(ILOAD, 9);
Label label11 = new Label();
methodVisitor.visitJumpInsn(IF_ICMPGE, label11);
Label label12 = new Label();
methodVisitor.visitLabel(label12);
methodVisitor.visitLineNumber(96, label12);
methodVisitor.visitVarInsn(ALOAD, 8);
methodVisitor.visitVarInsn(ILOAD, 10);
methodVisitor.visitInsn(AALOAD);
methodVisitor.visitVarInsn(ASTORE, 11);
Label label13 = new Label();
methodVisitor.visitLabel(label13);
methodVisitor.visitLineNumber(97, label13);
methodVisitor.visitVarInsn(ALOAD, 11);
Label label14 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label14);
methodVisitor.visitVarInsn(ALOAD, 11);
methodVisitor.visitVarInsn(ALOAD, 7);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "isInstance", "(Ljava/lang/Object;)Z", false);
methodVisitor.visitJumpInsn(IFEQ, label14);
Label label15 = new Label();
methodVisitor.visitLabel(label15);
methodVisitor.visitLineNumber(98, label15);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 7);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
methodVisitor.visitInsn(POP);
Label label16 = new Label();
methodVisitor.visitLabel(label16);
methodVisitor.visitLineNumber(99, label16);
methodVisitor.visitJumpInsn(GOTO, label11);
methodVisitor.visitLabel(label14);
methodVisitor.visitLineNumber(95, label14);
methodVisitor.visitIincInsn(10, 1);
methodVisitor.visitJumpInsn(GOTO, label10);
methodVisitor.visitLabel(label11);
methodVisitor.visitLineNumber(103, label11);
methodVisitor.visitVarInsn(ALOAD, 7);
methodVisitor.visitTypeInsn(INSTANCEOF, "java/awt/Container");
Label label17 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label17);
Label label18 = new Label();
methodVisitor.visitLabel(label18);
methodVisitor.visitLineNumber(104, label18);
methodVisitor.visitVarInsn(ALOAD, 7);
methodVisitor.visitTypeInsn(CHECKCAST, "java/awt/Container");
methodVisitor.visitVarInsn(ASTORE, 10);
Label label19 = new Label();
methodVisitor.visitLabel(label19);
methodVisitor.visitLineNumber(105, label19);
methodVisitor.visitVarInsn(ALOAD, 10);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Container", "getComponentCount", "()I", false);
methodVisitor.visitJumpInsn(IFLE, label17);
Label label20 = new Label();
methodVisitor.visitLabel(label20);
methodVisitor.visitLineNumber(106, label20);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 10);
methodVisitor.visitVarInsn(ILOAD, 2);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitInsn(ISUB);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtilsCrackPlanC", "internalFindAllComponentsRecursively", "(Ljava/util/List;Ljava/awt/Container;I[Ljava/lang/Class;)V", false);
methodVisitor.visitLabel(label17);
methodVisitor.visitLineNumber(90, label17);
methodVisitor.visitIincInsn(6, 1);
methodVisitor.visitJumpInsn(GOTO, label5);
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(112, label1);
methodVisitor.visitInsn(RETURN);
Label label21 = new Label();
methodVisitor.visitLabel(label21);
methodVisitor.visitLocalVariable("aClass", "Ljava/lang/Class;", "Ljava/lang/Class<+Ljava/awt/Component;>;", label13, label14, 11);
methodVisitor.visitLocalVariable("var9", "I", null, label10, label11, 10);
methodVisitor.visitLocalVariable("childContainer", "Ljava/awt/Container;", null, label19, label17, 10);
methodVisitor.visitLocalVariable("component", "Ljava/awt/Component;", null, label7, label17, 7);
methodVisitor.visitLocalVariable("var11", "[Ljava/lang/Class;", null, label8, label17, 8);
methodVisitor.visitLocalVariable("var10", "I", null, label9, label17, 9);
methodVisitor.visitLocalVariable("var5", "I", null, label5, label1, 6);
methodVisitor.visitLocalVariable("var7", "[Ljava/awt/Component;", null, label3, label1, 4);
methodVisitor.visitLocalVariable("var6", "I", null, label4, label1, 5);
methodVisitor.visitLocalVariable("result", "Ljava/util/List;", "Ljava/util/List<Ljava/awt/Component;>;", label0, label21, 0);
methodVisitor.visitLocalVariable("container", "Ljava/awt/Container;", null, label0, label21, 1);
methodVisitor.visitLocalVariable("depth", "I", null, label0, label21, 2);
methodVisitor.visitLocalVariable("filter", "[Ljava/lang/Class;", "[Ljava/lang/Class<+Ljava/awt/Component;>;", label0, label21, 3);
methodVisitor.visitMaxs(4, 12);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
}
