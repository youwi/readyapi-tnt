package com.jp.protection.pub;

import com.jp.protection.security.*;
import com.jp.protection.utils.*;
import dev.util.*;

import java.io.*;
import java.util.*;

public class LicenseReader {
    private transient Vector fLicenseReaderListeners;
    protected License fLicense;
    protected String fLicenseFileName;
    protected boolean fUserHomeRelative;
    protected String fLicenseFolder;
    protected String fLicenseResourceFolder;
    protected boolean fLicenseRead;
    protected boolean fVerbose;
    protected boolean fSkipEncryption;
    protected byte[] fDecryptKeyBytes;
    protected LicenseReaderIssueResolver fLicenseReaderIssueResolver;
    protected boolean fSearchLicenseInFile;
    protected boolean fPerformLicenseBackups;
    protected Storage fStorage;
    protected boolean fNeedUpdateLicense;
    protected SecurityProvider fSecurityProvider;
    protected String fSecurityAlgorithm;
    protected Class fClassForLoadLicenseResource;
    public static /* synthetic */ int z;
    private static  /* synthetic */ String[] A;

    public LicenseReader() {
        this.fLicenseFileName = LicenseReader.A[2];
        this.fUserHomeRelative = true;
        this.fLicenseFolder = "";
        this.fLicenseResourceFolder = "";
        this.fLicenseRead = false;
        this.fVerbose = false;
        this.fSkipEncryption = false;
        this.fSearchLicenseInFile = true;
        this.fPerformLicenseBackups = true;
        this.fStorage = new FileStorage(this);
        this.fNeedUpdateLicense = true;
        this.fSecurityAlgorithm = LicenseReader.A[3];
    }

    public String getLicenseResourceFolder() {
        return this.fLicenseResourceFolder;
    }

    public void setLicenseResourceFolder(final String fLicenseResourceFolder) {
        this.fLicenseResourceFolder = fLicenseResourceFolder;
    }

    public byte[] getDecryptKeyBytes() {
        return this.fDecryptKeyBytes;
    }

    public void setDecryptKeyBytes(final byte[] fDecryptKeyBytes) {
        this.fDecryptKeyBytes = fDecryptKeyBytes;
    }

    public void setDecryptKeyBytes(final String s) {
        final int z = LicenseReader.z;
        final String[] split = s.split(",");
        this.fDecryptKeyBytes = new byte[split.length];
        int i = 0;
        while (i < split.length) {
            this.fDecryptKeyBytes[i] = Byte.parseByte(split[i].trim());
            ++i;
            if (z != 0) {
                break;
            }
        }
    }

    @Deprecated
    public byte[] getPrivateKeyBytes() {
        return this.getDecryptKeyBytes();
    }

    @Deprecated
    public void setPrivateKeyBytes(final byte[] decryptKeyBytes) {
        this.setDecryptKeyBytes(decryptKeyBytes);
    }

    @Deprecated
    public void setPrivateKeyBytes(final String decryptKeyBytes) {
        this.setDecryptKeyBytes(decryptKeyBytes);
    }

    public boolean isLicenseRead() {
        return this.fLicenseRead;
    }

    public boolean isSkipEncryption() {
        return this.fSkipEncryption;
    }

    public void setSkipEncryption(final boolean fSkipEncryption) {
        this.fSkipEncryption = fSkipEncryption;
    }

    public boolean isVerbose() {
        return this.fVerbose;
    }

    public void setVerbose(final boolean fVerbose) {
        this.fVerbose = fVerbose;
    }

    protected void error(final String s) {
        if (this.isVerbose()) {
            System.err.println(s);
        }
    }

    protected void error(final Throwable t) {
        if (this.isVerbose()) {
            t.printStackTrace();
        }
    }

    public boolean isUserHomeRelative() {
        return this.fUserHomeRelative;
    }

    public void setUserHomeRelative(final boolean fUserHomeRelative) {
        this.fUserHomeRelative = fUserHomeRelative;
    }

    public String getLicenseFileName() {
        return this.fLicenseFileName;
    }

    public void setLicenseFileName(final String fLicenseFileName) {
        this.fLicenseFileName = fLicenseFileName;
    }

    public String getLicenseFolder() {
        return this.fLicenseFolder;
    }

    public void setLicenseFolder(final String fLicenseFolder) {
        this.fLicenseFolder = fLicenseFolder;
    }

    public License getLicense() {
        return this.getLicense(false);
    }

    public synchronized License getLicense(final boolean b) {
        if (!this.isLicenseRead() || b) {
            this.fNeedUpdateLicense = true;
            this.readLicense();
        }
        return this.fLicense;
    }

    protected synchronized void readLicense() {
        final int z = LicenseReader.z;
        this.fLicenseRead = true;
        this.fLicense = null;
        try {
            InputStream inputStream = this.getLicenseInputStream();
            while (true) {
                try {
                    while (true) {
                        Label_0065:
                        {
                            if (inputStream == null) {
                                if (this.fLicenseReaderIssueResolver != null && this.fLicenseReaderIssueResolver.resolveLicenseMissing(this)) {
                                    inputStream = this.getLicenseFileInputStream();
                                    if (z == 0) {
                                        break ;//Label_0065;
                                    }
                                }
                                this.fireLicenseMissing(this, this.getLicenseFileLocation());
                                if (z == 0) {
                                    break;
                                }
                            }
                        }
                        if (inputStream != null) {
                            this.readLicense(inputStream, (inputStream instanceof FileInputStream) ? this.getLicenseFileLocation() : this.getLicenseResourceLocation());
                            if (z != 0) {
                                continue;
                            }
                            break;
                        }
                    }
                } catch (LicenseOutdatedException ex2) {
                    inputStream = this.getLicenseFileInputStream();
                    if (z == 0) {
                        continue;
                    }
                }
                break;
            }
        } catch (IOException ex) {
            this.error(ex);
        }
    }

    public boolean isSearchLicenseInFile() {
        return this.fSearchLicenseInFile;
    }

    public void setSearchLicenseInFile(final boolean fSearchLicenseInFile) {
        this.fSearchLicenseInFile = fSearchLicenseInFile;
    }

    public InputStream getLicenseInputStream() {
        return this.fStorage.getLicenseInputStream();
    }

    public OutputStream getLicenseOutputStream() {
        return this.fStorage.getLicenseOutputStream();
    }

    public boolean isPerformLicenseBackups() {
        return this.fPerformLicenseBackups;
    }

    public void setPerformLicenseBackups(final boolean fPerformLicenseBackups) {
        this.fPerformLicenseBackups = fPerformLicenseBackups;
    }

    public boolean backupLicense() {
        return this.fStorage.isSupportsLicenseBackup() && this.fStorage.backupLicense();
    }

    public boolean canRestoreLicenseFromBackup() {
        return this.fStorage.isSupportsLicenseBackup() && this.fStorage.canRestoreLicenseFromBackup();
    }

    public boolean restoreLicenseFromBackup() {
        return this.fStorage.isSupportsLicenseBackup() && this.fStorage.restoreLicenseFromBackup();
    }

    public InputStream getBackupLicenseInputStream() {
        return this.fStorage.isSupportsLicenseBackup() ? this.fStorage.getBackupLicenseInputStream() : null;
    }

    public boolean updateLicense(final InputStream inputStream) throws IOException {
        return this.updateLicense(inputStream, this.isPerformLicenseBackups());
    }

    public boolean updateLicense(final InputStream inputStream, final boolean b) throws IOException {
        boolean b2 = false;
        IOException o = null;
        final boolean b3 = b && this.backupLicense();
        final OutputStream licenseOutputStream = this.getLicenseOutputStream();
        if (licenseOutputStream != null) {
            try {
                try {
                    StreamUtil.copy(inputStream, licenseOutputStream);
                    b2 = true;
                    this.fireLicenseUpdated(this, "");
                } finally {
                    licenseOutputStream.close();
                }
            } catch (Exception ex) {
                this.error(ex);
                o = new IOException(ex);
            }
        }
        if (!b2 && b3) {
            this.restoreLicenseFromBackup();
        }
        if (o != null) {
            throw o;
        }
        return b2;
    }

    protected void checkLicenseFile() throws IllegalStateException {
        if (this.fLicenseFileName == null) {
            throw new IllegalStateException(LicenseReader.A[0]);
        }
        if (this.fLicenseFolder == null && this.fLicenseResourceFolder == null) {
            throw new IllegalStateException(LicenseReader.A[1]);
        }
    }

    public Storage getStorage() {
        return this.fStorage;
    }

    public void setStorage(final Storage fStorage) {
        if (fStorage == null) {
            throw new IllegalArgumentException(LicenseReader.A[4]);
        }
        this.fStorage = fStorage;
    }

    public synchronized void readLicense(final InputStream inputStream, final String licenseLocation) throws IOException {
        final int z = LicenseReader.z;
        this.fLicenseRead = true;
        this.fLicense = null;
        byte[] smartGetLicenseBytes = null;
        try {
            smartGetLicenseBytes = this.smartGetLicenseBytes(inputStream);
        } finally {
            inputStream.close();
        }
        if (smartGetLicenseBytes == null) {
            this.fireLicenseCorrupted(this, licenseLocation);
            if (z == 0) {
                return;
            }
        }
        final byte[] smartDecodeLicense = this.smartDecodeLicense(smartGetLicenseBytes);
        if (smartDecodeLicense == null) {
            this.fireLicenseCorrupted(this, licenseLocation);
            if (z == 0) {
                return;
            }
        }
        this.smartReadLicense(smartDecodeLicense);
        if (this.fLicense != null) {
            ((LicenseImpl) this.fLicense).setLicenseLocation(licenseLocation);
            this.fireLicenseAvailable(this, licenseLocation);
            if (!this.fNeedUpdateLicense) {
                return;
            }
            this.fNeedUpdateLicense = false;
            this.updateLicense();
            if (z == 0) {
                return;
            }
        }
        this.fireLicenseCorrupted(this, licenseLocation);
    }

    protected void updateLicense() {
        if (this.fLicenseReaderIssueResolver instanceof LicenseReaderIssueResolverExt && ((LicenseReaderIssueResolverExt) this.fLicenseReaderIssueResolver).updateLicense(this)) {
            throw new LicenseOutdatedException();
        }
    }

    protected byte[] smartDecodeLicense(final byte[] array) {
        final byte[] decodeLicense = this.decodeLicense(array);
        if (decodeLicense == null && this.fLicenseReaderIssueResolver != null && this.fLicenseReaderIssueResolver.resolveLicenseCorrupted(this)) {
            throw new LicenseOutdatedException();
        }
        return decodeLicense;
    }

    protected byte[] decodeLicense(final byte[] array) {
        byte[] decode = array;
        if (!this.isSkipEncryption()) {
            try {
                final SecurityProvider securityProvider = this.getSecurityProvider();
                decode = securityProvider.decode(array, securityProvider.getPublicKey(this.fDecryptKeyBytes));
            } catch (Exception ex) {
                decode = null;
                this.error(ex);
            }
        }
        return decode;
    }

    public String getSecurityAlgorithm() {
        return this.fSecurityAlgorithm;
    }

    public void setSecurityAlgorithm(final String fSecurityAlgorithm) {
        if (fSecurityAlgorithm == null) {
            throw new IllegalArgumentException(LicenseReader.A[6]);
        }
        this.fSecurityAlgorithm = fSecurityAlgorithm;
        this.fSecurityProvider = null;
    }

    protected SecurityProvider getSecurityProvider() {
        if (this.fSecurityProvider == null) {
            this.fSecurityProvider = SecurityProviderFactory.getSecurityProvider(this.fSecurityAlgorithm);
        }
        return this.fSecurityProvider;
    }

    protected void smartReadLicense(final byte[] array) {
        this.readLicense(array);
        if (this.fLicense == null && this.fLicenseReaderIssueResolver != null && this.fLicenseReaderIssueResolver.resolveLicenseCorrupted(this)) {
            throw new LicenseOutdatedException();
        }
    }

    protected void readLicense(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            try {
                final Properties properties = new Properties();
                properties.load(byteArrayInputStream);
                LicenseUtils.load(this.fLicense = new LicenseImpl(), properties);
            } finally {
                byteArrayInputStream.close();
            }
        } catch (Exception ex) {
            this.fLicense = null;
            this.error(ex);
        }
    }

    public String getLicenseProduct(final InputStream inputStream) {
        String utf = null;
        try {
            utf = new DataInputStream(inputStream).readUTF();
        } catch (Exception ex) {
            this.error(ex);
        }
        return utf;
    }

    protected byte[] smartGetLicenseBytes(final InputStream inputStream) {
        final byte[] licenseBytes = this.getLicenseBytes(inputStream);
        if (licenseBytes == null && this.fLicenseReaderIssueResolver != null && this.fLicenseReaderIssueResolver.resolveLicenseCorrupted(this)) {
            throw new LicenseOutdatedException();
        }
        return licenseBytes;
    }

    protected byte[] getLicenseBytes(final InputStream inputStream) {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] array;
        try {
            this.fireLicenseAboutToRead(this, dataInputStream.readUTF());
            final long long1 = dataInputStream.readLong();
            int available = dataInputStream.available();
            array = new byte[available];
            int n = 0;
            int read;
            do {
                read = dataInputStream.read(array, n, available);
                n += read;
                available -= read;
            } while (read != -1 && available > 0);
            final CustomCRC32 customCRC32 = new CustomCRC32();
            customCRC32.proceed(array);
            if (long1 != customCRC32.getCRC() && inputStream instanceof FileInputStream) {
                array = null;
            }
        } catch (IOException ex) {
            this.error(ex);
            array = null;
        }
        return array;
    }

    public String getLicenseFileLocation() {
        return this.getLicenseFile().toString();
    }

    public String getLicenseResourceLocation() {
        String fLicenseFileName = this.fLicenseFileName;
        if (!StringUtils.noValue(this.fLicenseResourceFolder)) {
            fLicenseFileName = (this.fLicenseResourceFolder.endsWith("/") ? (this.fLicenseResourceFolder + fLicenseFileName) : (this.fLicenseResourceFolder + "/" + fLicenseFileName));
        }
        return fLicenseFileName;
    }

    public InputStream getLicenseFileInputStream() {
        this.checkLicenseFile();
        InputStream inputStream = null;
        if (this.fLicenseFolder != null) {
            try {
                final File licenseFile = this.getLicenseFile();
                if (licenseFile.canRead()) {
                    inputStream = new FileInputStream(licenseFile);
                }
            } catch (FileNotFoundException ex) {
                this.error(ex);
            }
        }
        return inputStream;
    }

    public InputStream getLicenseResourceInputStream() {
        return ((this.fClassForLoadLicenseResource != null) ? this.fClassForLoadLicenseResource : LicenseReader.class).getResourceAsStream(this.getLicenseResourceLocation());
    }

    public Class getClassForLoadLicenseResource() {
        return this.fClassForLoadLicenseResource;
    }

    public void setClassForLoadLicenseResource(final Class fClassForLoadLicenseResource) {
        this.fClassForLoadLicenseResource = fClassForLoadLicenseResource;
    }

    public static File getUserHome() {
        File file = null;
        if (file == null) {
            file = new File(System.getProperties().getProperty(LicenseReader.A[5]));
        }
        return file;
    }

    public File getLicenseFile() {
        File file;
        if (this.isUserHomeRelative()) {
            file = new File(new File(getUserHome(), this.fLicenseFolder), this.fLicenseFileName);
        } else {
            file = (StringUtils.noValue(this.fLicenseFolder) ? new File(this.fLicenseFileName) : new File(this.fLicenseFolder, this.fLicenseFileName));
        }
        return file;
    }

    public synchronized void addLicenseReaderListener(final LicenseReaderListener licenseReaderListener) {
        final Vector fLicenseReaderListeners = (Vector) ((this.fLicenseReaderListeners == null) ? new Vector<LicenseReaderListener>(2) : this.fLicenseReaderListeners.clone());
        if (!fLicenseReaderListeners.contains(licenseReaderListener)) {
            fLicenseReaderListeners.addElement(licenseReaderListener);
            this.fLicenseReaderListeners = fLicenseReaderListeners;
        }
    }

    public synchronized void removeLicenseReaderListener(final LicenseReaderListener licenseReaderListener) {
        if (this.fLicenseReaderListeners != null && this.fLicenseReaderListeners.contains(licenseReaderListener)) {
            final Vector fLicenseReaderListeners = (Vector) this.fLicenseReaderListeners.clone();
            fLicenseReaderListeners.removeElement(licenseReaderListener);
            this.fLicenseReaderListeners = fLicenseReaderListeners;
        }
    }

    protected void fireLicenseMissing(final LicenseReader licenseReader, final String s) {
        final int z = LicenseReader.z;
        if (this.fLicenseReaderListeners != null) {
            final Vector fLicenseReaderListeners = this.fLicenseReaderListeners;
            final int size = fLicenseReaderListeners.size();
            int i = 0;
            while (i < size) {
                ((LicenseReaderListener) fLicenseReaderListeners.elementAt(i)).licenseMissing(licenseReader, s);
                ++i;
                if (z != 0) {
                    break;
                }
            }
        }
    }

    protected void fireLicenseCorrupted(final LicenseReader licenseReader, final String s) {
        final int z = LicenseReader.z;
        if (this.fLicenseReaderListeners != null) {
            final Vector fLicenseReaderListeners = this.fLicenseReaderListeners;
            final int size = fLicenseReaderListeners.size();
            int i = 0;
            while (i < size) {
                ((LicenseReaderListener) fLicenseReaderListeners.elementAt(i)).licenseCorrupted(licenseReader, s);
                ++i;
                if (z != 0) {
                    break;
                }
            }
        }
    }

    protected void fireLicenseAvailable(final LicenseReader licenseReader, final String s) {
        final int z = LicenseReader.z;
        if (this.fLicenseReaderListeners != null) {
            final Vector fLicenseReaderListeners = this.fLicenseReaderListeners;
            final int size = fLicenseReaderListeners.size();
            int i = 0;
            while (i < size) {
                ((LicenseReaderListener) fLicenseReaderListeners.elementAt(i)).licenseAvailable(licenseReader, s);
                ++i;
                if (z != 0) {
                    break;
                }
            }
        }
    }

    protected void fireLicenseAboutToRead(final LicenseReader licenseReader, final String s) {
        final int z = LicenseReader.z;
        if (this.fLicenseReaderListeners != null) {
            final Vector fLicenseReaderListeners = this.fLicenseReaderListeners;
            final int size = fLicenseReaderListeners.size();
            int i = 0;
            while (i < size) {
                ((LicenseReaderListener) fLicenseReaderListeners.elementAt(i)).licenseAboutToRead(licenseReader, s);
                ++i;
                if (z != 0) {
                    break;
                }
            }
        }
    }

    public LicenseReaderIssueResolver getIssueResolver() {
        return this.fLicenseReaderIssueResolver;
    }

    public void setIssueResolver(final LicenseReaderIssueResolver fLicenseReaderIssueResolver) {
        this.fLicenseReaderIssueResolver = fLicenseReaderIssueResolver;
    }

    public byte[] getLicenseBytes() {
        byte[] byteArray = null;
        final InputStream licenseInputStream = this.getLicenseInputStream();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                StreamUtil.copy(licenseInputStream, byteArrayOutputStream);
                byteArrayOutputStream.close();
                byteArray = byteArrayOutputStream.toByteArray();
            } finally {
                licenseInputStream.close();
            }
        } catch (Exception ex) {
            this.error(ex);
        }
        return byteArray;
    }

    protected void fireLicenseRemoved(final LicenseReader licenseReader, final String s) {
        final int z = LicenseReader.z;
        if (this.fLicenseReaderListeners != null) {
            final Vector fLicenseReaderListeners = this.fLicenseReaderListeners;
            final int size = fLicenseReaderListeners.size();
            int i = 0;
            while (i < size) {
                if (fLicenseReaderListeners.elementAt(i) instanceof LicenseReaderListenerExt) {
                    ((LicenseReaderListenerExt) fLicenseReaderListeners.elementAt(i)).licenseRemoved(licenseReader, s);
                }
                ++i;
                if (z != 0) {
                    break;
                }
            }
        }
    }

    protected void fireLicenseUpdated(final LicenseReader licenseReader, final String s) {
        final int z = LicenseReader.z;
        if (this.fLicenseReaderListeners != null) {
            final Vector fLicenseReaderListeners = this.fLicenseReaderListeners;
            final int size = fLicenseReaderListeners.size();
            int i = 0;
            while (i < size) {
                if (fLicenseReaderListeners.elementAt(i) instanceof LicenseReaderListenerExt) {
                    ((LicenseReaderListenerExt) fLicenseReaderListeners.elementAt(i)).licenseUpdated(licenseReader, s);
                }
                ++i;
                if (z != 0) {
                    break;
                }
            }
        }
    }

    public boolean removeLicense() {
        final boolean removeLicense = this.fStorage.removeLicense();
        if (removeLicense) {
            this.fireLicenseRemoved(this, "");
        }
        return removeLicense;
    }

    public boolean updateLicense(final byte[] array) throws IOException {
        boolean updateLicense = false;
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        try {
            updateLicense = this.updateLicense(byteArrayInputStream);
        } finally {
            byteArrayInputStream.close();
        }
        return updateLicense;
    }

    static {
        final String[] a = new String[7];
        final int n = 0;
        final char[] charArray = "5\u0003v@O\n\u000f5CH\u0015\u000f5K@\u0014\u000f5LRY\u0004zQ\u0001\n\u001apFH\u001f\u0003pA".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098:
            {
                if (n2 > 1) {
                    break ;
                    //break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'y';
                            break;
                        }
                        case 1: {
                            c2 = 'j';
                            break;
                        }
                        case 2: {
                            c2 = '\u0015';
                            break;
                        }
                        case 3: {
                            c2 = '%';
                            break;
                        }
                        default: {
                            c2 = '!';
                            break;
                        }
                    }
                    charArray[length] = (char) (c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        a[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "5\u0003v@O\n\u000f5IN\u001a\u000baLN\u0017J|V\u0001\u0017\u0005a\u0005R\t\u000fvLG\u0010\u000fq".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214:
            {
                if (n6 > 1) {
                    break ;//Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'y';
                            break;
                        }
                        case 1: {
                            c4 = 'j';
                            break;
                        }
                        case 2: {
                            c4 = '\u0015';
                            break;
                        }
                        case 3: {
                            c4 = '%';
                            break;
                        }
                        default: {
                            c4 = '!';
                            break;
                        }
                    }
                    charArray2[length2] = (char) (c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        a[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0015\u0003v@O\n\u000f;ND\u0000".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330:
            {
                if (n10 > 1) {
                    break ;//Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'y';
                            break;
                        }
                        case 1: {
                            c6 = 'j';
                            break;
                        }
                        case 2: {
                            c6 = '\u0015';
                            break;
                        }
                        case 3: {
                            c6 = '%';
                            break;
                        }
                        default: {
                            c6 = '!';
                            break;
                        }
                    }
                    charArray3[length3] = (char) (c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        a[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "+9T".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446:
            {
                if (n14 > 1) {
                    break ;//Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'y';
                            break;
                        }
                        case 1: {
                            c8 = 'j';
                            break;
                        }
                        case 2: {
                            c8 = '\u0015';
                            break;
                        }
                        case 3: {
                            c8 = '%';
                            break;
                        }
                        default: {
                            c8 = '!';
                            break;
                        }
                    }
                    charArray4[length4] = (char) (c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        a[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "*\u001ezW@\u001e\u000f5HT\n\u001e{\u0002UY\bp\u0005O\f\u0006y".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562:
            {
                if (n18 > 1) {
                    break ;//Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'y';
                            break;
                        }
                        case 1: {
                            c10 = 'j';
                            break;
                        }
                        case 2: {
                            c10 = '\u0015';
                            break;
                        }
                        case 3: {
                            c10 = '%';
                            break;
                        }
                        default: {
                            c10 = '!';
                            break;
                        }
                    }
                    charArray5[length5] = (char) (c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        a[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\f\u0019pW\u000f\u0011\u0005x@".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678:
            {
                if (n22 > 1) {
                    break ;//Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'y';
                            break;
                        }
                        case 1: {
                            c12 = 'j';
                            break;
                        }
                        case 2: {
                            c12 = '\u0015';
                            break;
                        }
                        case 3: {
                            c12 = '%';
                            break;
                        }
                        default: {
                            c12 = '!';
                            break;
                        }
                    }
                    charArray6[length6] = (char) (c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        a[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "*\u000fvPS\u0010\u001el\u0005@\u0015\rzWH\r\u0002x\u0005R\u0011\u0005`IEY\bp\u0005R\t\u000fvLG\u0010\u000fq".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798:
            {
                if (n26 > 1) {
                    break ;//Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'y';
                            break;
                        }
                        case 1: {
                            c14 = 'j';
                            break;
                        }
                        case 2: {
                            c14 = '\u0015';
                            break;
                        }
                        case 3: {
                            c14 = '%';
                            break;
                        }
                        default: {
                            c14 = '!';
                            break;
                        }
                    }
                    charArray7[length7] = (char) (c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 <= n28) {
                a[n25] = new String(charArray7).intern();
                A = a;
                break;
            }
            continue;
        }
    }

    public static class InMemoryStorage implements Storage {
        protected byte[] fLicenseBytes;
        protected LicenseReader fLicenseReader;
        private static  /* synthetic */ String[] z;

        public InMemoryStorage(final LicenseReader fLicenseReader) {
            this.fLicenseReader = fLicenseReader;
        }

        public byte[] getLicenseBytes() {
            return this.fLicenseBytes;
        }

        public void setLicenseBytes(final byte[] fLicenseBytes) {
            this.fLicenseBytes = fLicenseBytes;
        }

        public InputStream getLicenseInputStream() {
            return (this.fLicenseBytes != null) ? new ByteArrayInputStream(this.fLicenseBytes) : null;
        }

        public OutputStream getLicenseOutputStream() {
            return new ByteArrayOutputStream() {
                @Override
                public void close() throws IOException {
                    super.close();
                    InMemoryStorage.this.fLicenseBytes = this.toByteArray();
                }
            };
        }

        public boolean restoreLicenseFromBackup() {
            throw new UnsupportedOperationException(InMemoryStorage.z[1]);
        }

        public boolean backupLicense() {
            throw new UnsupportedOperationException(InMemoryStorage.z[0]);
        }

        public boolean isSupportsLicenseBackup() {
            return false;
        }

        public boolean canRestoreLicenseFromBackup() {
            return false;
        }

        public InputStream getBackupLicenseInputStream() {
            return null;
        }

        public boolean removeLicense() {
            return false;
        }

        static {
            fakeCall();
        }

        static void fakeCall() {
            final String[] z2 = new String[2];
            final int n = 0;
            final char[] charArray = "\u0013V]b_\u0001{WjO\u001fD[".toCharArray();
            int length;
            int n3;
            final int n2 = n3 = (length = charArray.length);
            int n4 = 0;
            while (true) {
                Label_0098:
                {
                    if (n2 > 1) {
                        break ;//Label_0098;
                    }
                    length = (n3 = n4);
                    do {
                        final char c = charArray[n3];
                        char c2 = '\0';
                        switch (n4 % 5) {
                            case 0: {
                                c2 = 'q';
                                break;
                            }
                            case 1: {
                                c2 = '7';
                                break;
                            }
                            case 2: {
                                c2 = '>';
                                break;
                            }
                            case 3: {
                                c2 = '\t';
                                break;
                            }
                            default: {
                                c2 = '*';
                                break;
                            }
                        }
                        charArray[length] = (char) (c ^ c2);
                        ++n4;
                    } while (n2 == 0);
                }
                if (n2 > n4) {
                    continue;
                }
                break;
            }
            z2[n] = new String(charArray).intern();
            final int n5 = 1;
            final char[] charArray2 = "\u0003RM}E\u0003Rr`I\u0014YMll\u0003XSKK\u0012\\Ky".toCharArray();
            int length2;
            int n7;
            final int n6 = n7 = (length2 = charArray2.length);
            int n8 = 0;
            while (true) {
                Label_0214:
                {
                    if (n6 > 1) {
                        break ;//Label_0214;
                    }
                    length2 = (n7 = n8);
                    do {
                        final char c3 = charArray2[n7];
                        char c4 = '\0';
                        switch (n8 % 5) {
                            case 0: {
                                c4 = 'q';
                                break;
                            }
                            case 1: {
                                c4 = '7';
                                break;
                            }
                            case 2: {
                                c4 = '>';
                                break;
                            }
                            case 3: {
                                c4 = '\t';
                                break;
                            }
                            default: {
                                c4 = '*';
                                break;
                            }
                        }
                        charArray2[length2] = (char) (c3 ^ c4);
                        ++n8;
                    } while (n6 == 0);
                }
                if (n6 <= n8) {
                    z2[n5] = new String(charArray2).intern();
                    z = z2;
                    return;
                }
                continue;
            }
        }
    }

    public static class FileStorage implements Storage {
        protected LicenseReader fLicenseReader;
        private static  /* synthetic */ String z;

        public FileStorage(final LicenseReader fLicenseReader) {
            this.fLicenseReader = fLicenseReader;
        }

        public InputStream getLicenseInputStream() {
            InputStream inputStream = this.fLicenseReader.isSearchLicenseInFile() ? this.fLicenseReader.getLicenseFileInputStream() : this.fLicenseReader.getLicenseResourceInputStream();
            if (inputStream == null) {
                inputStream = (this.fLicenseReader.isSearchLicenseInFile() ? this.fLicenseReader.getLicenseResourceInputStream() : this.fLicenseReader.getLicenseFileInputStream());
            }
            return inputStream;
        }

        public OutputStream getLicenseOutputStream() {
            this.fLicenseReader.checkLicenseFile();
            OutputStream outputStream = null;
            if (this.fLicenseReader.fLicenseFolder != null) {
                try {
                    final File licenseFile = this.fLicenseReader.getLicenseFile();
                    if (licenseFile.getParentFile() != null && !licenseFile.getParentFile().exists()) {
                        licenseFile.getParentFile().mkdirs();
                    }
                    if (!licenseFile.exists() || licenseFile.canWrite()) {
                        outputStream = new FileOutputStream(licenseFile);
                    }
                } catch (FileNotFoundException ex) {
                    this.fLicenseReader.error(ex);
                }
            }
            return outputStream;
        }

        protected int getBackupNumber(final File file) {
            int int1 = 0;
            final String name = file.getName();
            if (name.endsWith("~")) {
                final int index = name.indexOf("~");
                final int lastIndex = name.lastIndexOf("~");
                if (index != lastIndex && index != -1 && lastIndex != -1) {
                    try {
                        int1 = Integer.parseInt(name.substring(index + 1, lastIndex));
                    } catch (Exception ex) {
                    }
                }
            }
            return int1;
        }

        public List<File> getLicenseBackups() {
            List<File> list = null;
            if (this.fLicenseReader.fLicenseFolder != null) {
                final File licenseFile = this.fLicenseReader.getLicenseFile();
                if (licenseFile != null) {
                    final File[] listFiles = licenseFile.getParentFile().listFiles(new FileFilter() {
                        final /* synthetic */ String val$licenseFileName = licenseFile.getName();

                        public boolean accept(final File file) {
                            final String name = file.getName();
                            return name.startsWith(this.val$licenseFileName) && name.length() > this.val$licenseFileName.length();
                        }
                    });
                    if (listFiles != null) {
                        list = new ArrayList<File>(Arrays.asList(listFiles));
                        Collections.sort(list, new Comparator<File>() {
                            public int compare(final File file, final File file2) {
                                return FileStorage.this.getBackupNumber(file2) - FileStorage.this.getBackupNumber(file);
                            }
                        });
                    }
                }
            }
            return (List<File>) list;
        }


        public boolean restoreLicenseFromBackup() {
            boolean restoreLicenseFromBackup = false;
            final List<File> licenseBackups = this.getLicenseBackups();
            if (licenseBackups != null && !licenseBackups.isEmpty()) {
                restoreLicenseFromBackup = this.restoreLicenseFromBackup(licenseBackups.get(0));
            }
            return restoreLicenseFromBackup;
        }

        public boolean restoreLicenseFromBackup(final File file) {
            boolean b = false;
            try {
                if (file.canRead()) {
                    final OutputStream licenseOutputStream = this.getLicenseOutputStream();
                    if (licenseOutputStream != null) {
                        final FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            StreamUtil.copy(fileInputStream, licenseOutputStream);
                            b = true;
                        } finally {
                            fileInputStream.close();
                            licenseOutputStream.close();
                        }
                    }
                }
                if (b) {
                    file.delete();
                }
            } catch (Exception ex) {
                this.fLicenseReader.error(ex);
            }
            return b;
        }

        protected File getNewBackupLicenseFile() {
            File file = null;
            if (this.fLicenseReader.fLicenseFolder != null) {
                final File licenseFile = this.fLicenseReader.getLicenseFile();
                if (licenseFile != null) {
                    int n = 0;
                    final List<File> licenseBackups = this.getLicenseBackups();
                    if (licenseBackups != null && !licenseBackups.isEmpty()) {
                        n = this.getBackupNumber(licenseBackups.get(0)) + 1;
                    }
                    file = new File(licenseFile.getParentFile(), licenseFile.getName() + ((n == 0) ? FileStorage.z : ("~" + n + "~")));
                }
            }
            return file;
        }

        public boolean backupLicense() {
            boolean b = false;
            final File newBackupLicenseFile = this.getNewBackupLicenseFile();
            if (newBackupLicenseFile != null) {
                try {
                    final InputStream licenseInputStream = this.getLicenseInputStream();
                    if (licenseInputStream != null) {
                        final FileOutputStream fileOutputStream = new FileOutputStream(newBackupLicenseFile);
                        try {
                            StreamUtil.copy(licenseInputStream, fileOutputStream);
                            b = true;
                        } finally {
                            licenseInputStream.close();
                            fileOutputStream.close();
                        }
                    }
                } catch (Exception ex) {
                    this.fLicenseReader.error(ex);
                }
            }
            return b;
        }

        public boolean isSupportsLicenseBackup() {
            return true;
        }

        public boolean canRestoreLicenseFromBackup() {
            final List<File> licenseBackups = this.getLicenseBackups();
            return licenseBackups != null && !licenseBackups.isEmpty();
        }

        public InputStream getBackupLicenseInputStream() {
            InputStream inputStream = null;
            final List<File> licenseBackups = this.getLicenseBackups();
            if (licenseBackups != null && !licenseBackups.isEmpty() && licenseBackups.get(0).canRead()) {
                try {
                    inputStream = new FileInputStream(licenseBackups.get(0));
                } catch (FileNotFoundException ex) {
                    this.fLicenseReader.error(ex);
                }
            }
            return inputStream;
        }

        public boolean removeLicense() {
            return false;
        }

        static {
            final char[] charArray = "\u001bdS ".toCharArray();
            int length;
            int n2;
            final int n = n2 = (length = charArray.length);
            int n3 = 0;
            while (true) {
                Label_0094:
                {
                    if (n > 1) {
                        break;// Label_0094;
                    }
                    length = (n2 = n3);
                    do {
                        final char c = charArray[n2];
                        char c2 = '\0';
                        switch (n3 % 5) {
                            case 0: {
                                c2 = '5';
                                break;
                            }
                            case 1: {
                                c2 = '\u0006';
                                break;
                            }
                            case 2: {
                                c2 = '2';
                                break;
                            }
                            case 3: {
                                c2 = 'K';
                                break;
                            }
                            default: {
                                c2 = 'n';
                                break;
                            }
                        }
                        charArray[length] = (char) (c ^ c2);
                        ++n3;
                    } while (n == 0);
                }
                if (n <= n3) {
                    z = new String(charArray).intern();
                    break;
                }
                continue;
            }
        }
    }

    public interface Storage {
        InputStream getLicenseInputStream();

        OutputStream getLicenseOutputStream();

        boolean isSupportsLicenseBackup();

        boolean canRestoreLicenseFromBackup();

        boolean restoreLicenseFromBackup();

        boolean backupLicense();

        InputStream getBackupLicenseInputStream();

        boolean removeLicense();
    }
}
