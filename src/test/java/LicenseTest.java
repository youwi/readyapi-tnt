import com.jp.protection.pub.License;
import com.jp.protection.pub.LicenseImpl;
import com.jp.protection.pub.LicenseReader;
import com.jp.protection.security.SecurityProvider;
import com.jp.protection.security.SecurityProviderFactory;
import com.jp.protection.utils.CustomCRC32;
import com.smartbear.ready.license.protection.LicensedModule;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.security.PublicKey;
import java.util.Date;

/**
 * Created by yu on 15/9/30.
 */
public class LicenseTest {
    //Users/yu/workspace/soapUICrk/loadui-明文.key

    @Test
    public void checkSS() {
        //  com.smartbear.ready.license.util.LicenseFileUtil.validateAndInstallLicensesFromFile;
        LicensedModule s1 = LicensedModule.loadUI;
        //for(int i=0;i< s1.getDecryptionKeyBytes().length();i++){

        System.out.println(LicensedModule.SoapUIPro.getDecryptionKeyBytes().equals(LicensedModule.loadUI.getDecryptionKeyBytes()));
        // }
        System.out.println(LicensedModule.SoapUIPro.getDecryptionKeyBytes());
        System.out.println(LicensedModule.loadUI.getDecryptionKeyBytes());

        String[] dek = LicensedModule.loadUI.getDecryptionKeyBytes().split(",");
        String[] des = LicensedModule.SoapUIPro.getDecryptionKeyBytes().split(",");
        byte[] dekbyte = new byte[dek.length];
        byte[] desbyte = new byte[des.length];
        for (int i = 0; i < dek.length; i++) {
            dekbyte[i] = (byte) Integer.parseInt(dek[i]);
        }
        System.out.println(new String(dekbyte));


        for (int i = 0; i < des.length; i++) {
            desbyte[i] = (byte) Integer.parseInt(des[i]);
        }
        System.out.println(new String(desbyte));
    }


    /**
     * 旧版本soapUI使用的代码
     *
     * @return
     */
    public static License buildLicese() {
        License l = new LicenseImpl();

        //l.addFeature("organization", "xxxx");
        //l.addFeature("name", "xxxx");
        //l.addFeature("type", LicenseType.PROFESSIONAL.name());
        //Date d = new Date();
        //d.setYear(2114);
        //l.addFeature("expiration", d);
        //l.addFeature("id", "xxxx");
        //  LicenseType.
        return l;
    }


    @Test
    public void ssss() {
        License ss = buildLicese();
        System.out.println(buildLicese());
    }

    @Test
    public void seckey() throws IOException {
        // com.jp.protection.pub.LicenseReader;
        String[] dek = LicensedModule.Secure.getDecryptionKeyBytes().split(",");
        byte[] dekbyte = new byte[dek.length];
        for (int i = 0; i < dek.length; i++) {
            dekbyte[i] = (byte) Integer.parseInt(dek[i]);
        }
        LicenseReader lr = new LicenseReader();
        lr.setDecryptKeyBytes(dekbyte);
        lr.setSkipEncryption(false);
        lr.setLicenseFileName("Secure-trial.key");
        lr.setUserHomeRelative(false);
        lr.setLicenseResourceFolder(".");
        lr.setLicenseFolder(".");
        lr.setSecurityAlgorithm("RSA - SunJCE - 512");
        //lr.getLicense().getLicenseExpireDate().setYear(2118);

        lr.getLicense();//生成中间文件 enc.key

        bbCRC(new FileInputStream(new File("enc.key")),"textC.txt"); //生成out.key

    }

    @Test
    public void secbbb() throws IOException {
        bbCRC(new FileInputStream(new File("enc.key")),"textD.txt"); //生成out.key
        // /Users/yu/workspace/soapUICrk/out.key
    }

    @Test
    public void serviceVkey() throws IOException {
        //
        /*
            前2个字节指明 名称长度,如soapUI长度为6  0x00,0x06
            后一个为字符串 "soapUI"
            后一个为CRC检验,长度为4字节,long型


         */
        // com.jp.protection.pub.LicenseReader;
        String[] dek = LicensedModule.ServiceV.getDecryptionKeyBytes().split(",");
        byte[] dekbyte = new byte[dek.length];
        for (int i = 0; i < dek.length; i++) {
            dekbyte[i] = (byte) Integer.parseInt(dek[i]);
        }
        LicenseReader lr = new LicenseReader();
        lr.setDecryptKeyBytes(dekbyte);
        lr.setSkipEncryption(false);
        lr.setLicenseFileName("ServiceV-trial.key");
        lr.setUserHomeRelative(false);
        lr.setLicenseResourceFolder(".");
        lr.setLicenseFolder(".");
        lr.setSecurityAlgorithm("RSA - SunJCE - 512");

        lr.getLicense();//生成中间文件 enc.key

        bbCRC(new FileInputStream(new File("enc.key")),"testA.txt"); //生成out.key

    }

    @Test
    public void bbbbb() throws IOException {
        bbCRC(new FileInputStream(new File("soapuicp.key")),"testSoapUI.txt"); //生成out.key
        // /Users/yu/workspace/soapUICrk/out.key
    }
    @Test
    public void keyToText36() throws IOException {
        bbCRC(new FileInputStream(new File("soapui3.6.key")),"testSoapUI36.txt");
    }


    @Test
    public void kkkkkk() throws IOException {
        bbCRC(new FileInputStream(new File("loadUI.key")),"testLoadUI.txt"); //生成out.key
        // /Users/yu/workspace/soapUICrk/out.key

      //  com.smartbear.ready.module.ConcurrentXmlLoadProcess concurrentXmlLoadProcess=new ConcurrentXmlLoadProcess();
    }

    @Test
    public void loaduikey() throws IOException {
        // com.jp.protection.pub.LicenseReader;
        String[] dek = LicensedModule.loadUI.getDecryptionKeyBytes().split(",");

        byte[] dekbyte = new byte[dek.length];
        for (int i = 0; i < dek.length; i++) {
            dekbyte[i] = (byte) Integer.parseInt(dek[i]);
        }
        //   System.out.println(new String(dekbyte));
        LicenseReader lr = new LicenseReader();
        lr.setDecryptKeyBytes(dekbyte);
        lr.setSkipEncryption(false);
        lr.setLicenseFileName("loadUI-trial.key");
        lr.setUserHomeRelative(false);
        lr.setLicenseResourceFolder(".");
        lr.setLicenseFolder(".");
        lr.setSecurityAlgorithm("RSA - SunJCE - 512");
        InputStream is = lr.getLicenseInputStream();
        lr.getLicenseFile();

        bbCRC(lr.getLicenseInputStream(),"loadUIb.txt");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        String ko = baos.toString();
        //new String( )
        //byte[]  ss= lr.bbCRC();

        com.jp.protection.pub.License sr = lr.getLicense();
        System.out.println(ko);
    }


    public byte[] bbCRC(InputStream paramInputStream,String txtFileName) {
        byte[] arrayOfByte = null;
        DataInputStream localDataInputStream = new DataInputStream(paramInputStream);
        try {
            String str = localDataInputStream.readUTF();
            // fireLicenseAboutToRead(this, str);

            long l = localDataInputStream.readLong();

            int i = localDataInputStream.available();
            arrayOfByte = new byte[i];
            int j = 0;
            int k;
            do {
                k = localDataInputStream.read(arrayOfByte, j, i);
                j += k;
                i -= k;
            } while ((k != -1) && (i > 0));
            CustomCRC32 localCustomCRC32 = new CustomCRC32();
            localCustomCRC32.proceed(arrayOfByte);

            FileOutputStream lop = new FileOutputStream(new File(txtFileName));
            DataOutputStream dp = new DataOutputStream(lop);
            dp.write(0x00);
            dp.write(0x06);
            dp.write(str.getBytes());
            // dp.writeLong();
            dp.writeLong(localCustomCRC32.getCRC());
            dp.write(arrayOfByte);
            dp.flush();
            //   if ((l != localCustomCRC32.getCRC()) && ((paramInputStream instanceof FileInputStream))) {
            //     arrayOfByte = null;
            // }
        } catch (IOException localIOException) {
            //error(localIOException);
            arrayOfByte = null;
        }
        return arrayOfByte;
    }

    byte[] rsa_key = {-84, -19, 0, 5, 115, 114, 0, 20, 106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121, 82, 101, 112, -67, -7, 79, -77, -120, -102, -91, 67, 2, 0, 4, 76, 0, 9, 97, 108, 103, 111, 114, 105, 116, 104, 109, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 91, 0, 7, 101, 110, 99, 111, 100, 101, 100, 116, 0, 2, 91, 66, 76, 0, 6, 102, 111, 114, 109, 97, 116, 113, 0, 126, 0, 1, 76, 0, 4, 116, 121, 112, 101, 116, 0, 27, 76, 106, 97, 118, 97, 47, 115, 101, 99, 117, 114, 105, 116, 121, 47, 75, 101, 121, 82, 101, 112, 36, 84, 121, 112, 101, 59, 120, 112, 116, 0, 3, 82, 83, 65, 117, 114, 0, 2, 91, 66, -84, -13, 23, -8, 6, 8, 84, -32, 2, 0, 0, 120, 112, 0, 0, 0, 94, 48, 92, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, 75, 0, 48, 72, 2, 65, 0, -108, -70, -46, -115, 69, -114, 94, -36, 9, 62, 66, 124, 93, -26, -28, 57, -103, 33, 47, 77, 68, -71, 117, -40, 76, 81, 85, -122, -75, 66, 7, 36, 61, -115, 31, -19, 19, 43, 28, 127, -120, 50, 33, -62, -7, 0, 111, 97, -42, 37, -26, 122, -92, 46, 120, -84, 17, 11, 11, -89, 109, 67, -11, -45, 2, 3, 1, 0, 1, 116, 0, 5, 88, 46, 53, 48, 57, 126, 114, 0, 25, 106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121, 82, 101, 112, 36, 84, 121, 112, 101, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 120, 114, 0, 14, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 69, 110, 117, 109, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 120, 112, 116, 0, 6, 80, 85, 66, 76, 73, 67};

    /**
     * 把字节保存为文件
     * @param bytes
     * @param fileName
     */
    public void bytesToFile(byte[] bytes,String fileName){

        FileOutputStream lop = null;
        try {
            lop = new FileOutputStream(new File(fileName));
            DataOutputStream dp = new DataOutputStream(lop);
            dp.write(bytes);
            dp.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 导出明文文件
     * @param paramArrayOfByte
     * @return
     */
    protected byte[] decodeLicense(byte[] paramArrayOfByte) {
        byte[] arrayOfByte = paramArrayOfByte;
        if (!fSkipEncryption) {
            try {
                SecurityProvider localSecurityProvider = fSecurityProvider;
                PublicKey localPublicKey = localSecurityProvider.getPublicKey(this.fDecryptKeyBytes);
                arrayOfByte = localSecurityProvider.decode(paramArrayOfByte, localPublicKey);
                /*
                    保存为明文文件
                 */
                CustomCRC32 localCustomCRC32 = new CustomCRC32();
                localCustomCRC32.proceed(arrayOfByte);
                FileOutputStream lop = new FileOutputStream(new File("enc.key"));
                DataOutputStream dp = new DataOutputStream(lop);
                dp.write(0x00);
                dp.write(0x06);
                dp.write("SOAP".getBytes());
                // dp.writeLong();
                dp.writeLong(localCustomCRC32.getCRC());
                dp.write(arrayOfByte);
                dp.flush();
                dp.close();
                // 修改到此.....
            } catch (Exception localException) {
                arrayOfByte = null;
                localException.printStackTrace();
            }
        }
        return arrayOfByte;
    }
    protected String fSecurityAlgorithm="RSA - SunJCE - 512";
    protected SecurityProvider fSecurityProvider=SecurityProviderFactory.getSecurityProvider(this.fSecurityAlgorithm);
    protected byte[] fDecryptKeyBytes=rsa_key;//密钥可以用远程调试连接得到原文
     protected boolean fSkipEncryption = true;


}
