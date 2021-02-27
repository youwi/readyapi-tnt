
package com.jp.protection.pub;

import com.jp.protection.pub.License;
import com.jp.protection.pub.LicenseBase;
import com.jp.protection.pub.LicenseBaseImpl;
import com.jp.protection.pub.LicenseImpl;
import com.jp.protection.security.SecurityProvider;
import com.jp.protection.utils.CustomCRC32;
import com.jp.protection.utils.LicenseUtils;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.util.Date;
import java.util.Properties;

/**
 * readapi3.6-tnt
 * 这是代码片段,我要的是它的字节码,这里只有核心代码,
 * 还要用字节码来组合
 * Created by yu on 2021/2/26.
 */
public class LicenseReaderCrack {
    protected License fLicense;

    protected void readLicense(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            try {
                final Properties properties = new Properties();
                properties.load(byteArrayInputStream);
                LicenseImpl license = new LicenseImpl();
                license.getLicenseExpireDate().setYear(2114);
                LicenseUtils.load(license, properties);
                this.fLicense = license;
                license.setLicenseNumber("1");
            } finally {
                byteArrayInputStream.close();
            }
        } catch (Exception ex) {
            this.fLicense = null;
            System.out.println(ex.getCause().toString());
        }
    }


}
