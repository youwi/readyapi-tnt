package asm.com.smartbear.ready.license;
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
public class LicenseBootstrapDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V11, ACC_PUBLIC | ACC_SUPER, "com/smartbear/ready/license/LicenseBootstrap", null, "java/lang/Object", null);

classWriter.visitSource("LicenseBootstrap.java", null);

{
annotationVisitor0 = classWriter.visitAnnotation("Lcom/google/inject/Singleton;", true);
annotationVisitor0.visitEnd();
}
classWriter.visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC | ACC_FINAL | ACC_STATIC);

{
fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC, "a", "Ljava/lang/String;", null, "_");
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "b", "Lcom/smartbear/ready/license/action/LicenseManagerAction;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "c", "Lcom/smartbear/ready/license/action/StartTrialAction;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC, "d", "Lcom/eviware/soapui/support/MessageSupport;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_STATIC, "e", "Lorg/slf4j/Logger;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_STATIC, "f", "Ljava/lang/String;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "g", "Lcom/smartbear/ready/license/protection/BackupLicense;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "h", "Lcom/smartbear/ready/core/UniqueUserIdentifier;", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "i", "Z", null, null);
fieldVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(39, label0);
methodVisitor.visitLdcInsn(Type.getType("Lcom/smartbear/ready/license/LicenseBootstrap;"));
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/MessageSupport", "getMessages", "(Ljava/lang/Class;)Lcom/eviware/soapui/support/MessageSupport;", false);
methodVisitor.visitFieldInsn(PUTSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "d", "Lcom/eviware/soapui/support/MessageSupport;");
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(40, label1);
methodVisitor.visitLdcInsn(Type.getType("Lcom/smartbear/ready/license/LicenseBootstrap;"));
methodVisitor.visitMethodInsn(INVOKESTATIC, "org/slf4j/LoggerFactory", "getLogger", "(Ljava/lang/Class;)Lorg/slf4j/Logger;", false);
methodVisitor.visitFieldInsn(PUTSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(41, label2);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(1, 0);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(33, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(45, label1);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitFieldInsn(PUTFIELD, "com/smartbear/ready/license/LicenseBootstrap", "i", "Z");
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(33, label2);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "setUserIdentifier", "(Lcom/smartbear/ready/core/UniqueUserIdentifier;)V", null, null);
{
annotationVisitor0 = methodVisitor.visitAnnotation("Lcom/google/inject/Inject;", true);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(49, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/smartbear/ready/license/LicenseBootstrap", "h", "Lcom/smartbear/ready/core/UniqueUserIdentifier;");
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(50, label1);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "setLicenseManagerAction", "(Lcom/smartbear/ready/license/action/LicenseManagerAction;)V", null, null);
{
annotationVisitor0 = methodVisitor.visitAnnotation("Lcom/google/inject/Inject;", true);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(54, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/smartbear/ready/license/LicenseBootstrap", "b", "Lcom/smartbear/ready/license/action/LicenseManagerAction;");
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(55, label1);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "setBackupLicense", "(Lcom/smartbear/ready/license/protection/BackupLicense;)V", null, null);
{
annotationVisitor0 = methodVisitor.visitAnnotation("Lcom/google/inject/Inject;", true);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(59, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/smartbear/ready/license/LicenseBootstrap", "g", "Lcom/smartbear/ready/license/protection/BackupLicense;");
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(60, label1);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "setStartTrialAction", "(Lcom/smartbear/ready/license/action/StartTrialAction;)V", null, null);
{
annotationVisitor0 = methodVisitor.visitAnnotation("Lcom/google/inject/Inject;", true);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(64, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/smartbear/ready/license/LicenseBootstrap", "c", "Lcom/smartbear/ready/license/action/StartTrialAction;");
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(65, label1);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "isValidLicenseAvailableForAtLeastOneModule", "()Z", null, new String[] { "java/io/IOException", "com/smartbear/ready/license/exception/InvalidLicenseException" });
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(68, label0);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/license/LicenseManagerServiceFactory", "isValidLicensePresentForAtLeastOneReadyApiModule", "()Z", false);
methodVisitor.visitInsn(IRETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "installTrialLicenseForDownloadKey", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
Label label1 = new Label();
Label label2 = new Label();
methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/util/prefs/BackingStoreException");
Label label3 = new Label();
Label label4 = new Label();
methodVisitor.visitTryCatchBlock(label3, label0, label4, "java/io/IOException");
Label label5 = new Label();
Label label6 = new Label();
methodVisitor.visitTryCatchBlock(label5, label6, label4, "java/io/IOException");
Label label7 = new Label();
Label label8 = new Label();
Label label9 = new Label();
methodVisitor.visitTryCatchBlock(label7, label8, label9, "java/util/prefs/BackingStoreException");
Label label10 = new Label();
methodVisitor.visitTryCatchBlock(label3, label0, label10, "com/smartbear/ready/license/exception/InvalidLicenseException");
methodVisitor.visitTryCatchBlock(label5, label6, label10, "com/smartbear/ready/license/exception/InvalidLicenseException");
Label label11 = new Label();
Label label12 = new Label();
Label label13 = new Label();
methodVisitor.visitTryCatchBlock(label11, label12, label13, "java/util/prefs/BackingStoreException");
Label label14 = new Label();
methodVisitor.visitTryCatchBlock(label3, label0, label14, null);
methodVisitor.visitTryCatchBlock(label5, label7, label14, null);
methodVisitor.visitTryCatchBlock(label10, label11, label14, null);
Label label15 = new Label();
Label label16 = new Label();
Label label17 = new Label();
methodVisitor.visitTryCatchBlock(label15, label16, label17, "java/util/prefs/BackingStoreException");
Label label18 = new Label();
Label label19 = new Label();
Label label20 = new Label();
methodVisitor.visitTryCatchBlock(label18, label19, label20, "java/util/prefs/BackingStoreException");
methodVisitor.visitLabel(label3);
methodVisitor.visitLineNumber(73, label3);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/license/LicenseBootstrap", "getDownloadKey", "()Ljava/lang/String;", false);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/license/util/LicenseUtil", "getURLToDownloadTrial", "(Ljava/lang/String;)Ljava/net/URL;", false);
methodVisitor.visitVarInsn(ASTORE, 1);
Label label21 = new Label();
methodVisitor.visitLabel(label21);
methodVisitor.visitLineNumber(74, label21);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitJumpInsn(IFNONNULL, label5);
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(89, label0);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/core/Install4JPreferences", "setInstallerFileToEmpty", "()V", false);
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(90, label1);
Label label22 = new Label();
methodVisitor.visitJumpInsn(GOTO, label22);
methodVisitor.visitLabel(label2);
methodVisitor.visitInsn(POP);
Label label23 = new Label();
methodVisitor.visitLabel(label23);
methodVisitor.visitLineNumber(91, label23);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to update the trial installation status for download key");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;)V", true);
methodVisitor.visitLabel(label22);
methodVisitor.visitLineNumber(75, label22);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitLabel(label5);
methodVisitor.visitLineNumber(77, label5);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "h", "Lcom/smartbear/ready/core/UniqueUserIdentifier;");
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/license/util/LicenseFileUtil", "getTrialLicenseAndUserSpecificInformation", "(Ljava/net/URL;Lcom/smartbear/analytics/api/UserIdentificationInformation;)Ljava/io/File;", false);
methodVisitor.visitVarInsn(ASTORE, 2);
Label label24 = new Label();
methodVisitor.visitLabel(label24);
methodVisitor.visitLineNumber(78, label24);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "c", "Lcom/smartbear/ready/license/action/StartTrialAction;");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "g", "Lcom/smartbear/ready/license/protection/BackupLicense;");
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitTypeInsn(ANEWARRAY, "com/smartbear/ready/license/protection/LicensedModule");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/smartbear/ready/license/action/StartTrialAction", "installLicensesFromFile", "(Lcom/smartbear/ready/license/protection/BackupLicense;Ljava/io/File;Ljava/lang/String;[Lcom/smartbear/ready/license/protection/LicensedModule;)Lcom/smartbear/ready/license/util/InstallationResult;", false);
methodVisitor.visitVarInsn(ASTORE, 3);
Label label25 = new Label();
methodVisitor.visitLabel(label25);
methodVisitor.visitLineNumber(79, label25);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "c", "Lcom/smartbear/ready/license/action/StartTrialAction;");
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/smartbear/ready/license/action/StartTrialAction", "isLicenseInstalled", "(Lcom/smartbear/ready/license/util/InstallationResult;)Z", false);
methodVisitor.visitJumpInsn(IFEQ, label18);
Label label26 = new Label();
methodVisitor.visitLabel(label26);
methodVisitor.visitLineNumber(80, label26);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitFieldInsn(PUTSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
methodVisitor.visitLabel(label6);
methodVisitor.visitLineNumber(82, label6);
methodVisitor.visitJumpInsn(GOTO, label18);
methodVisitor.visitLabel(label4);
methodVisitor.visitVarInsn(ASTORE, 1);
Label label27 = new Label();
methodVisitor.visitLabel(label27);
methodVisitor.visitLineNumber(83, label27);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to install trial license.");
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;Ljava/lang/Throwable;)V", true);
methodVisitor.visitLabel(label7);
methodVisitor.visitLineNumber(89, label7);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/core/Install4JPreferences", "setInstallerFileToEmpty", "()V", false);
methodVisitor.visitLabel(label8);
methodVisitor.visitLineNumber(90, label8);
Label label28 = new Label();
methodVisitor.visitJumpInsn(GOTO, label28);
methodVisitor.visitLabel(label9);
methodVisitor.visitInsn(POP);
Label label29 = new Label();
methodVisitor.visitLabel(label29);
methodVisitor.visitLineNumber(91, label29);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to update the trial installation status for download key");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;)V", true);
methodVisitor.visitJumpInsn(GOTO, label28);
methodVisitor.visitLabel(label10);
methodVisitor.visitLineNumber(84, label10);
methodVisitor.visitVarInsn(ASTORE, 1);
Label label30 = new Label();
methodVisitor.visitLabel(label30);
methodVisitor.visitLineNumber(85, label30);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/smartbear/ready/license/exception/InvalidLicenseException", "getMessage", "()Ljava/lang/String;", false);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/UISupport", "showLicenseError", "(Ljava/lang/String;)V", false);
Label label31 = new Label();
methodVisitor.visitLabel(label31);
methodVisitor.visitLineNumber(86, label31);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to install trial license.");
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;Ljava/lang/Throwable;)V", true);
methodVisitor.visitLabel(label11);
methodVisitor.visitLineNumber(89, label11);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/core/Install4JPreferences", "setInstallerFileToEmpty", "()V", false);
methodVisitor.visitLabel(label12);
methodVisitor.visitLineNumber(90, label12);
methodVisitor.visitJumpInsn(GOTO, label28);
methodVisitor.visitLabel(label13);
methodVisitor.visitInsn(POP);
Label label32 = new Label();
methodVisitor.visitLabel(label32);
methodVisitor.visitLineNumber(91, label32);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to update the trial installation status for download key");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;)V", true);
methodVisitor.visitJumpInsn(GOTO, label28);
methodVisitor.visitLabel(label14);
methodVisitor.visitLineNumber(87, label14);
methodVisitor.visitVarInsn(ASTORE, 4);
methodVisitor.visitLabel(label15);
methodVisitor.visitLineNumber(89, label15);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/core/Install4JPreferences", "setInstallerFileToEmpty", "()V", false);
methodVisitor.visitLabel(label16);
methodVisitor.visitLineNumber(90, label16);
Label label33 = new Label();
methodVisitor.visitJumpInsn(GOTO, label33);
methodVisitor.visitLabel(label17);
methodVisitor.visitInsn(POP);
Label label34 = new Label();
methodVisitor.visitLabel(label34);
methodVisitor.visitLineNumber(91, label34);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to update the trial installation status for download key");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;)V", true);
methodVisitor.visitLabel(label33);
methodVisitor.visitLineNumber(93, label33);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitInsn(ATHROW);
methodVisitor.visitLabel(label18);
methodVisitor.visitLineNumber(89, label18);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/core/Install4JPreferences", "setInstallerFileToEmpty", "()V", false);
methodVisitor.visitLabel(label19);
methodVisitor.visitLineNumber(90, label19);
methodVisitor.visitJumpInsn(GOTO, label28);
methodVisitor.visitLabel(label20);
methodVisitor.visitInsn(POP);
Label label35 = new Label();
methodVisitor.visitLabel(label35);
methodVisitor.visitLineNumber(91, label35);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "e", "Lorg/slf4j/Logger;");
methodVisitor.visitLdcInsn("Failed to update the trial installation status for download key");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/slf4j/Logger", "error", "(Ljava/lang/String;)V", true);
methodVisitor.visitLabel(label28);
methodVisitor.visitLineNumber(94, label28);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(5, 5);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC, "a", "(Ljava/lang/String;)Z", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(97, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/StringUtils", "hasContent", "(Ljava/lang/String;)Z", false);
Label label1 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label1);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "toUpperCase", "()Ljava/lang/String;", false);
methodVisitor.visitLdcInsn("R");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
methodVisitor.visitJumpInsn(IFEQ, label1);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitInsn(IRETURN);
methodVisitor.visitLabel(label1);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitInsn(IRETURN);
methodVisitor.visitMaxs(2, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "promptForLicenseAndExitIfNoLicenseProvided", "(Ljava/lang/String;)V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(101, label0);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/license/action/LicenseManagerAction", "isLicenseManagerOpen", "()Z", false);
Label label1 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label1);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(102, label2);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(104, label1);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/StringUtils", "hasContent", "(Ljava/lang/String;)Z", false);
Label label3 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label3);
Label label4 = new Label();
methodVisitor.visitLabel(label4);
methodVisitor.visitLineNumber(105, label4);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "d", "Lcom/eviware/soapui/support/MessageSupport;");
methodVisitor.visitLdcInsn("LicenseBootstrap.LicenseLostDialog.Message");
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/Object");
methodVisitor.visitInsn(DUP);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitInsn(AASTORE);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/eviware/soapui/support/MessageSupport", "get", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", false);
methodVisitor.visitVarInsn(ASTORE, 2);
Label label5 = new Label();
methodVisitor.visitLabel(label5);
methodVisitor.visitLineNumber(106, label5);
methodVisitor.visitTypeInsn(NEW, "com/smartbear/ready/license/ui/NoLicensePresentDialog");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "b", "Lcom/smartbear/ready/license/action/LicenseManagerAction;");
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/smartbear/ready/license/ui/NoLicensePresentDialog", "<init>", "(Lcom/smartbear/ready/license/action/LicenseManagerAction;Ljava/lang/String;)V", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/smartbear/ready/license/ui/NoLicensePresentDialog", "showNoLicensePresentDialog", "()V", false);
Label label6 = new Label();
methodVisitor.visitLabel(label6);
methodVisitor.visitLineNumber(107, label6);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitLabel(label3);
methodVisitor.visitLineNumber(109, label3);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "i", "Z");
Label label7 = new Label();
methodVisitor.visitJumpInsn(IFNE, label7);
Label label8 = new Label();
methodVisitor.visitLabel(label8);
methodVisitor.visitLineNumber(110, label8);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/smartbear/ready/license/LicenseBootstrap", "i", "Z");
Label label9 = new Label();
methodVisitor.visitLabel(label9);
methodVisitor.visitLineNumber(111, label9);
methodVisitor.visitTypeInsn(NEW, "java/lang/Thread");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInvokeDynamicInsn("run", "(Lcom/smartbear/ready/license/LicenseBootstrap;)Ljava/lang/Runnable;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", false), new Object[]{Type.getType("()V"), new Handle(Opcodes.H_INVOKESPECIAL, "com/smartbear/ready/license/LicenseBootstrap", "a", "()V", false), Type.getType("()V")});
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Thread", "<init>", "(Ljava/lang/Runnable;)V", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "start", "()V", false);
Label label10 = new Label();
methodVisitor.visitLabel(label10);
methodVisitor.visitLineNumber(112, label10);
Label label11 = new Label();
methodVisitor.visitJumpInsn(GOTO, label11);
methodVisitor.visitLabel(label7);
methodVisitor.visitLineNumber(113, label7);
methodVisitor.visitIntInsn(BIPUSH, 9);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/swing/SwingUtils", "exit", "(I)V", false);
methodVisitor.visitLabel(label11);
methodVisitor.visitLineNumber(115, label11);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(6, 3);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "getDownloadKey", "()Ljava/lang/String;", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(118, label0);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
Label label1 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label1);
Label label2 = new Label();
methodVisitor.visitLabel(label2);
methodVisitor.visitLineNumber(119, label2);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(122, label1);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/core/Install4JPreferences", "getInstallerFile", "()Ljava/lang/String;", false);
methodVisitor.visitVarInsn(ASTORE, 0);
Label label3 = new Label();
methodVisitor.visitLabel(label3);
methodVisitor.visitLineNumber(123, label3);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/eviware/soapui/support/StringUtils", "hasContent", "(Ljava/lang/String;)Z", false);
Label label4 = new Label();
methodVisitor.visitJumpInsn(IFNE, label4);
Label label5 = new Label();
methodVisitor.visitLabel(label5);
methodVisitor.visitLineNumber(124, label5);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitLabel(label4);
methodVisitor.visitLineNumber(127, label4);
methodVisitor.visitTypeInsn(NEW, "java/io/File");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/io/File", "<init>", "(Ljava/lang/String;)V", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false);
methodVisitor.visitVarInsn(ASTORE, 1);
Label label6 = new Label();
methodVisitor.visitLabel(label6);
methodVisitor.visitLineNumber(128, label6);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitLdcInsn("_");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "contains", "(Ljava/lang/CharSequence;)Z", false);
Label label7 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label7);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitLdcInsn(".");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "contains", "(Ljava/lang/CharSequence;)Z", false);
methodVisitor.visitJumpInsn(IFEQ, label7);
methodVisitor.visitInsn(ICONST_1);
Label label8 = new Label();
methodVisitor.visitJumpInsn(GOTO, label8);
methodVisitor.visitLabel(label7);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitLabel(label8);
methodVisitor.visitVarInsn(ISTORE, 2);
Label label9 = new Label();
methodVisitor.visitLabel(label9);
methodVisitor.visitLineNumber(129, label9);
methodVisitor.visitVarInsn(ILOAD, 2);
Label label10 = new Label();
methodVisitor.visitJumpInsn(IFNE, label10);
Label label11 = new Label();
methodVisitor.visitLabel(label11);
methodVisitor.visitLineNumber(130, label11);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitLabel(label10);
methodVisitor.visitLineNumber(133, label10);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitLdcInsn("_");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "lastIndexOf", "(Ljava/lang/String;)I", false);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitInsn(IADD);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitLdcInsn(".");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "lastIndexOf", "(Ljava/lang/String;)I", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "substring", "(II)Ljava/lang/String;", false);
methodVisitor.visitVarInsn(ASTORE, 3);
Label label12 = new Label();
methodVisitor.visitLabel(label12);
methodVisitor.visitLineNumber(134, label12);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/smartbear/ready/license/LicenseBootstrap", "a", "(Ljava/lang/String;)Z", false);
Label label13 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label13);
Label label14 = new Label();
methodVisitor.visitLabel(label14);
methodVisitor.visitLineNumber(135, label14);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitFieldInsn(PUTSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
methodVisitor.visitLabel(label13);
methodVisitor.visitLineNumber(137, label13);
methodVisitor.visitFieldInsn(GETSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitMaxs(4, 4);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "eraseDownloadKey", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(141, label0);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitFieldInsn(PUTSTATIC, "com/smartbear/ready/license/LicenseBootstrap", "f", "Ljava/lang/String;");
Label label1 = new Label();
methodVisitor.visitLabel(label1);
methodVisitor.visitLineNumber(142, label1);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(1, 0);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_SYNTHETIC, "a", "()V", null, null);
methodVisitor.visitCode();
Label label0 = new Label();
methodVisitor.visitLabel(label0);
methodVisitor.visitLineNumber(111, label0);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/smartbear/ready/license/LicenseBootstrap", "b", "Lcom/smartbear/ready/license/action/LicenseManagerAction;");
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/smartbear/ready/license/action/LicenseManagerAction", "perform", "(Lcom/eviware/soapui/model/workspace/Workspace;Ljava/lang/Object;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(3, 1);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
}
