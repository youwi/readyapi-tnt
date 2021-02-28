package asm.com.eviware.soapui.support.swing;

import java.util.*;

import org.objectweb.asm.*;

public class SwingUtilsDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "com/eviware/soapui/support/swing/SwingUtils", null, "java/lang/Object", new String[]{"com/eviware/soapui/support/UIUtils"});

        cw.visitSource("SwingUtils.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtils;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "invokeLater", "(Ljava/lang/Runnable;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "invokeLater", "(Ljava/lang/Runnable;)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
 
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtils;", null, l0, l2, 0);
            mv.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, l0, l2, 1);
            mv.visitMaxs(1, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "invokeAndWait", "(Ljava/lang/Runnable;)V", null, new String[]{"java/lang/Exception"});
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "invokeAndWait", "(Ljava/lang/Runnable;)V", false);
            Label l1 = new Label();
            mv.visitLabel(l1);
 
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtils;", null, l0, l2, 0);
            mv.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, l0, l2, 1);
            mv.visitMaxs(1, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "invokeAndWaitIfNotInEDT", "(Ljava/lang/Runnable;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/InterruptedException");
            Label l3 = new Label();
            mv.visitTryCatchBlock(l0, l1, l3, "java/lang/reflect/InvocationTargetException");
            Label l4 = new Label();
            mv.visitLabel(l4);
 
            mv.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "isEventDispatchThread", "()Z", false);
            mv.visitJumpInsn(IFEQ, l0);
            Label l5 = new Label();
            mv.visitLabel(l5);
 
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("------------debug info--------------------");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label l6 = new Label();
            mv.visitLabel(l6);
 
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Runnable", "run", "()V", true);
            Label l7 = new Label();
            mv.visitJumpInsn(GOTO, l7);
            mv.visitLabel(l0);
 
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "javax/swing/SwingUtilities", "invokeAndWait", "(Ljava/lang/Runnable;)V", false);
            mv.visitLabel(l1);
 
            mv.visitJumpInsn(GOTO, l7);
            mv.visitLabel(l2);
 
            mv.visitVarInsn(ASTORE, 2);
            Label l8 = new Label();
            mv.visitLabel(l8);
 
            mv.visitTypeInsn(NEW, "java/lang/RuntimeException");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l3);
 
            mv.visitVarInsn(ASTORE, 2);
            Label l9 = new Label();
            mv.visitLabel(l9);
 
            mv.visitTypeInsn(NEW, "java/lang/RuntimeException");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l7);
 
            mv.visitInsn(RETURN);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLocalVariable("var3", "Ljava/lang/InterruptedException;", null, l8, l3, 2);
            mv.visitLocalVariable("var4", "Ljava/lang/reflect/InvocationTargetException;", null, l9, l7, 2);
            mv.visitLocalVariable("this", "Lcom/eviware/soapui/support/swing/SwingUtils;", null, l4, l10, 0);
            mv.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, l4, l10, 1);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "exit", "(I)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("status", "I", null, l0, l1, 0);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC + ACC_VARARGS, "findAllComponentsRecursively", "(Ljava/awt/Container;[Ljava/lang/Class;)Ljava/util/List;", "(Ljava/awt/Container;[Ljava/lang/Class<+Ljava/awt/Component;>;)Ljava/util/List<Ljava/awt/Component;>;", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn(new Integer(2147483646));
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtils", "findAllComponentsRecursively", "(Ljava/awt/Container;I[Ljava/lang/Class;)Ljava/util/List;", false);
            mv.visitInsn(ARETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("container", "Ljava/awt/Container;", null, l0, l1, 0);
            mv.visitLocalVariable("filter", "[Ljava/lang/Class;", "[Ljava/lang/Class<+Ljava/awt/Component;>;", l0, l1, 1);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC + ACC_VARARGS, "findAllComponentsRecursively", "(Ljava/awt/Container;I[Ljava/lang/Class;)Ljava/util/List;", "(Ljava/awt/Container;I[Ljava/lang/Class<+Ljava/awt/Component;>;)Ljava/util/List<Ljava/awt/Component;>;", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitTypeInsn(NEW, "java/util/ArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 3);
            Label l1 = new Label();
            mv.visitLabel(l1);
 
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtils", "internalFindAllComponentsRecursively", "(Ljava/util/List;Ljava/awt/Container;I[Ljava/lang/Class;)V", false);
            Label l2 = new Label();
            mv.visitLabel(l2);
 
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLocalVariable("container", "Ljava/awt/Container;", null, l0, l3, 0);
            mv.visitLocalVariable("depth", "I", null, l0, l3, 1);
            mv.visitLocalVariable("filter", "[Ljava/lang/Class;", "[Ljava/lang/Class<+Ljava/awt/Component;>;", l0, l3, 2);
            mv.visitLocalVariable("result", "Ljava/util/List;", "Ljava/util/List<Ljava/awt/Component;>;", l1, l3, 3);
            mv.visitMaxs(4, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_VARARGS, "internalFindAllComponentsRecursively", "(Ljava/util/List;Ljava/awt/Container;I[Ljava/lang/Class;)V", "(Ljava/util/List<Ljava/awt/Component;>;Ljava/awt/Container;I[Ljava/lang/Class<+Ljava/awt/Component;>;)V", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
 
            mv.visitVarInsn(ILOAD, 2);
            Label l1 = new Label();
            mv.visitJumpInsn(IFLT, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
 
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Container", "getComponents", "()[Ljava/awt/Component;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ASTORE, 4);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitVarInsn(ISTORE, 5);
            Label l4 = new Label();
            mv.visitLabel(l4);
 
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 6);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitJumpInsn(IF_ICMPGE, l1);
            Label l6 = new Label();
            mv.visitLabel(l6);
 
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 7);
            Label l7 = new Label();
            mv.visitLabel(l7);
 
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ASTORE, 8);
            Label l8 = new Label();
            mv.visitLabel(l8);
 
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitVarInsn(ISTORE, 9);
            Label l9 = new Label();
            mv.visitLabel(l9);
 
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 10);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitVarInsn(ILOAD, 10);
            mv.visitVarInsn(ILOAD, 9);
            Label l11 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l11);
            Label l12 = new Label();
            mv.visitLabel(l12);
 
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ILOAD, 10);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 11);
            Label l13 = new Label();
            mv.visitLabel(l13);
 
            mv.visitVarInsn(ALOAD, 11);
            Label l14 = new Label();
            mv.visitJumpInsn(IFNULL, l14);
            mv.visitVarInsn(ALOAD, 11);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "isInstance", "(Ljava/lang/Object;)Z", false);
            mv.visitJumpInsn(IFEQ, l14);
            Label l15 = new Label();
            mv.visitLabel(l15);
 
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l16 = new Label();
            mv.visitLabel(l16);
 
            mv.visitJumpInsn(GOTO, l11);
            mv.visitLabel(l14);
 
            mv.visitIincInsn(10, 1);
            mv.visitJumpInsn(GOTO, l10);
            mv.visitLabel(l11);
 
            mv.visitVarInsn(ALOAD, 7);
            mv.visitTypeInsn(INSTANCEOF, "java/awt/Container");
            Label l17 = new Label();
            mv.visitJumpInsn(IFEQ, l17);
            Label l18 = new Label();
            mv.visitLabel(l18);
 
            mv.visitVarInsn(ALOAD, 7);
            mv.visitTypeInsn(CHECKCAST, "java/awt/Container");
            mv.visitVarInsn(ASTORE, 10);
            Label l19 = new Label();
            mv.visitLabel(l19);
 
            mv.visitVarInsn(ALOAD, 10);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/awt/Container", "getComponentCount", "()I", false);
            mv.visitJumpInsn(IFLE, l17);
            Label l20 = new Label();
            mv.visitLabel(l20);
 
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 10);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(ISUB);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtils", "internalFindAllComponentsRecursively", "(Ljava/util/List;Ljava/awt/Container;I[Ljava/lang/Class;)V", false);
            mv.visitLabel(l17);
 
            mv.visitIincInsn(6, 1);
            mv.visitJumpInsn(GOTO, l5);
            mv.visitLabel(l1);
 
            mv.visitInsn(RETURN);
            Label l21 = new Label();
            mv.visitLabel(l21);
            mv.visitLocalVariable("aClass", "Ljava/lang/Class;", "Ljava/lang/Class<+Ljava/awt/Component;>;", l13, l14, 11);
            mv.visitLocalVariable("var9", "I", null, l10, l11, 10);
            mv.visitLocalVariable("childContainer", "Ljava/awt/Container;", null, l19, l17, 10);
            mv.visitLocalVariable("component", "Ljava/awt/Component;", null, l7, l17, 7);
            mv.visitLocalVariable("var11", "[Ljava/lang/Class;", null, l8, l17, 8);
            mv.visitLocalVariable("var10", "I", null, l9, l17, 9);
            mv.visitLocalVariable("var5", "I", null, l5, l1, 6);
            mv.visitLocalVariable("var7", "[Ljava/awt/Component;", null, l3, l1, 4);
            mv.visitLocalVariable("var6", "I", null, l4, l1, 5);
            mv.visitLocalVariable("result", "Ljava/util/List;", "Ljava/util/List<Ljava/awt/Component;>;", l0, l21, 0);
            mv.visitLocalVariable("container", "Ljava/awt/Container;", null, l0, l21, 1);
            mv.visitLocalVariable("depth", "I", null, l0, l21, 2);
            mv.visitLocalVariable("filter", "[Ljava/lang/Class;", "[Ljava/lang/Class<+Ljava/awt/Component;>;", l0, l21, 3);
            mv.visitMaxs(4, 12);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
