import com.jp.protection.pub.License;
import com.jp.protection.pub.LicenseImpl;
import com.jp.protection.utils.LicenseUtils;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.Properties;

/**
 * readapi3.6-tnt
 * Created by yu on 2021/2/26.
 */
public class MethodReadLicense {
    protected License fLicense;

    protected void readLicense(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            try {
                final Properties properties = new Properties();
                properties.load(byteArrayInputStream);
                LicenseImpl license = new LicenseImpl();
                license.getLicenseExpireDate().setYear(2114);
                LicenseUtils.load( license, properties);
                this.fLicense =license;
            } finally {
                byteArrayInputStream.close();
            }
        } catch (Exception ex) {
            this.fLicense = null;
            System.out.println(ex.getCause().toString());
        }
    }

}
