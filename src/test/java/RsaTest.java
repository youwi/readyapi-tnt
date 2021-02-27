import com.smartbear.ready.license.LicenseState;
import com.smartbear.ready.license.protection.LicensedModule;
import com.smartbear.ready.license.protection.SoapuiProtectionSupport;

/**
 * Created by yu on 15/9/28.
 */
public class RsaTest {
    public static byte[] rsa_key = {-84, -19, 0, 5, 115, 114, 0, 20, 106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121, 82, 101, 112, -67, -7, 79, -77, -120, -102, -91, 67, 2, 0, 4, 76, 0, 9, 97, 108, 103, 111, 114, 105, 116, 104, 109, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 91, 0, 7, 101, 110, 99, 111, 100, 101, 100, 116, 0, 2, 91, 66, 76, 0, 6, 102, 111, 114, 109, 97, 116, 113, 0, 126, 0, 1, 76, 0, 4, 116, 121, 112, 101, 116, 0, 27, 76, 106, 97, 118, 97, 47, 115, 101, 99, 117, 114, 105, 116, 121, 47, 75, 101, 121, 82, 101, 112, 36, 84, 121, 112, 101, 59, 120, 112, 116, 0, 3, 82, 83, 65, 117, 114, 0, 2, 91, 66, -84, -13, 23, -8, 6, 8, 84, -32, 2, 0, 0, 120, 112, 0, 0, 0, 94, 48, 92, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, 75, 0, 48, 72, 2, 65, 0, -108, -70, -46, -115, 69, -114, 94, -36, 9, 62, 66, 124, 93, -26, -28, 57, -103, 33, 47, 77, 68, -71, 117, -40, 76, 81, 85, -122, -75, 66, 7, 36, 61, -115, 31, -19, 19, 43, 28, 127, -120, 50, 33, -62, -7, 0, 111, 97, -42, 37, -26, 122, -92, 46, 120, -84, 17, 11, 11, -89, 109, 67, -11, -45, 2, 3, 1, 0, 1, 116, 0, 5, 88, 46, 53, 48, 57, 126, 114, 0, 25, 106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121, 82, 101, 112, 36, 84, 121, 112, 101, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 120, 114, 0, 14, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 69, 110, 117, 109, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 120, 112, 116, 0, 6, 80, 85, 66, 76, 73, 67};

    public static void main(String[] args) {
        System.out.println("SSF");
//      ConcurrentXmlLoadProcess ;

        byte[] j = new byte[]{(byte) 111, (byte) 114, (byte) 103, (byte) 46, (byte) 97, (byte) 112, (byte) 97, (byte) 99, (byte) 104, (byte) 101, (byte) 46, (byte) 99, (byte) 111, (byte) 109, (byte) 109, (byte) 111, (byte) 110, (byte) 115, (byte) 46, (byte) 99, (byte) 111, (byte) 100, (byte) 101, (byte) 99, (byte) 46, (byte) 100, (byte) 105, (byte) 103, (byte) 101, (byte) 115, (byte) 116, (byte) 46, (byte) 68, (byte) 105, (byte) 103, (byte) 101, (byte) 115, (byte) 116, (byte) 85, (byte) 116, (byte) 105, (byte) 108, (byte) 115};
        byte[] XML_KEYS = new byte[]{(byte) 87, (byte) 87, (byte) 57, (byte) 49, (byte) 99, (byte) 105, (byte) 66, (byte) 83, (byte) 90, (byte) 87, (byte) 70, (byte) 107, (byte) 101, (byte) 83, (byte) 69, (byte) 103, (byte) 81, (byte) 86, (byte) 66, (byte) 74, (byte) 73, (byte) 71, (byte) 108, (byte) 117, (byte) 99, (byte) 51, (byte) 82, (byte) 104, (byte) 98, (byte) 71, (byte) 120, (byte) 104, (byte) 100, (byte) 71, (byte) 108, (byte) 118, (byte) 98, (byte) 105, (byte) 66, (byte) 122, (byte) 90, (byte) 87, (byte) 86, (byte) 116, (byte) 99, (byte) 121, (byte) 66, (byte) 48, (byte) 98, (byte) 121, (byte) 66, (byte) 105, (byte) 90, (byte) 83, (byte) 66, (byte) 106, (byte) 98, (byte) 51, (byte) 74, (byte) 121, (byte) 100, (byte) 88, (byte) 66, (byte) 48, (byte) 90, (byte) 87, (byte) 81, (byte) 117, (byte) 73, (byte) 70, (byte) 74, (byte) 108, (byte) 89, (byte) 87, (byte) 82, (byte) 53, (byte) 73, (byte) 83, (byte) 66, (byte) 66, (byte) 85, (byte) 69, (byte) 107, (byte) 103, (byte) 100, (byte) 50, (byte) 108, (byte) 115, (byte) 98, (byte) 67, (byte) 66, (byte) 122, (byte) 97, (byte) 72, (byte) 86, (byte) 48, (byte) 90, (byte) 71, (byte) 57, (byte) 51, (byte) 98, (byte) 105, (byte) 52, (byte) 61};
        final byte[] h = new byte[]{(byte) 89, (byte) 50, (byte) 57, (byte) 116, (byte) 76, (byte) 109, (byte) 86, (byte) 50, (byte) 97, (byte) 88, (byte) 100, (byte) 104, (byte) 99, (byte) 109, (byte) 85, (byte) 117, (byte) 99, (byte) 50, (byte) 49, (byte) 104, (byte) 99, (byte) 110, (byte) 82, (byte) 105, (byte) 90, (byte) 87, (byte) 70, (byte) 121, (byte) 76, (byte) 110, (byte) 74, (byte) 108, (byte) 89, (byte) 87, (byte) 82, (byte) 53, (byte) 76, (byte) 109, (byte) 49, (byte) 118, (byte) 90, (byte) 72, (byte) 86, (byte) 115, (byte) 90, (byte) 83, (byte) 53, (byte) 85, (byte) 98, (byte) 50, (byte) 57, (byte) 115, (byte) 89, (byte) 109, (byte) 70, (byte) 121, (byte) 81, (byte) 87, (byte) 78, (byte) 48, (byte) 97, (byte) 87, (byte) 57, (byte) 117, (byte) 81, (byte) 88, (byte) 78, (byte) 122, (byte) 97, (byte) 88, (byte) 78, (byte) 48, (byte) 89, (byte) 87, (byte) 53, (byte) 48};


        System.out.println(new String(j));
        System.out.println(new String(XML_KEYS));
        System.out.println(new String(h));
        System.out.println(new String(rsa_key));
        System.out.println((toHexString(new String(rsa_key))));


        SoapuiProtectionSupport soapuiProtectionSupport = new SoapuiProtectionSupport(
                new LicenseState(),
                LicensedModule.loadUI,
                true
        );


        SoapuiProtectionSupport soapuiProtectionSupport2 = new SoapuiProtectionSupport(
                new LicenseState(),
                LicensedModule.loadUI,
                false
        );

        System.out.println("ok");
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
}
