  //强制设置过期时间为2114年
  //修改的是com.jp.protection.pub.LicenseReader.readLicense()
  //反编译出来的代码不能直接使用.试过多个反编译工具都无法正常反编译,只有注入字节码来实现

  //先转换为字节码来,再复制字节码再压缩进jar里

  //脚本在build.gradle中

  //核心代码如下:
  protected void readLicense(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            try {
                final Properties properties = new Properties();
                properties.load(byteArrayInputStream);
                LicenseImpl license = new LicenseImpl();
                Date exDate = new Date();
                exDate.setYear(2114);
                license.setLicenseExpireDate(exDate);
                LicenseUtils.load( license, properties);
                this.fLicense =license;
            } finally {
                byteArrayInputStream.close();
            }
        } catch (Exception ex) {
            this.fLicense = null;
            this.error(ex);
        }
    }
