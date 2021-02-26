import com.jp.protection.pub.LicenseReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * readapi3.6-tnt
 * Created by yu on 2021/2/26.
 */
public class TestM {

    @Test
    public void sfe(){
        new LicenseReader();
    }

    @Test
    public void classDumpTest() throws Exception {
        byte[] s= LicenseReaderDump.dump();
        FileOutputStream fileOutputStream= new FileOutputStream(new File("tmp.class"));
        fileOutputStream.write(s);
        fileOutputStream.flush();
    }
}
