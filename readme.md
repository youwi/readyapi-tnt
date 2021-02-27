##  注意: 代码只为学习和分享,切不要以盈利为目的发布
    本仓库不直接提供破解后的jar包

### 对应版本 readApi3.6
  

### 原理
  强制设置过期时间为2114年  
  //修改的是com.jp.protection.pub.LicenseReader.readLicense()  
  //反编译出来的代码不能直接使用.试过多个反编译工具都无法正常反编译,只有注入字节码来实现  

  //先转换为字节码来,再复制字节码再压缩进jar里,详细看单元测试

  //脚本在build.gradle中

  //核心代码如下:
```
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
```
### build
    gradle build
    gradle test 
    gradle zipTheFile
    gradle copyToSystem

### 使用.
  1 按官方步骤获取试用key  
  2 使用代码生成fake.class  
  3 用脚本替换jar包中的class文件