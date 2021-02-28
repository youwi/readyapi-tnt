##  注意: 代码只为学习和分享,切不要以盈利为目的发布
    本仓库不直接提供破解后的jar包(Protection-5.1.5-crack.jar已经删除)

### 对应版本 readApi3.6
    soapui36.key  加密key(官方获取) 可以配合A方案
    soapui36.key.txt 明文txt  (调试使用
    soapui36-decode.key 明文Key(有crc检验),使用代码生成,配合C方案使用.

    可以手动替换:com/jp/protection/pub/LicenseReader.class
    Fake.class 使用A方案
    FakePlanC.class 使用C方案.

### 原理
  强制设置过期时间为2114年  
  //修改的是com.jp.protection.pub.LicenseReader.readLicense()  
  //反编译出来的代码不能直接使用.试过多个反编译工具都无法正常反编译,只有注入字节码来实现  

  //先转换为asm或字节码来,再复制字节码再压缩进jar里,详细看单元测试

  //脚本在build.gradle中

### 方案A 无限试用,无需解密key.
    用asm操作来替换readLicense,无限试用
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

### 方案B 
    直接修改字节码,难度太大

### 方案C 解密key,然后设置为不加密
    使用以前破解soapui4.0一样的方案.  
    把com.jp.protection.pub.LicenseReader.fSkipEncryption=true
    然后把key解密,使用明文key

### build
    gradle build
    gradle test        # 用的测试代码生成class文件
    gradle zipTheFile   #zipTheFilePlanC #使用C方案.
    gradle copyToSystem

### 使用.
  1 按官方步骤获取试用key  
  2 使用代码生成fake.class  
  3 用脚本替换jar包中的class文件
  
### 远程调试
    在readapi.sh 中添加参数 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005,  
    然后打开idea可以远程调试

## 闪退解决方案:
    使用了反射来调用了System.exit()
    readyapi-api-ui.jar:com.smartbear.ready.module.ConcurrentXmlLoadProcess

    直接调用了:SwingUtils.exit(9)
    readyapi-soapui-pro.jar:com.smartbear.ready.module.ToolbarActionAssistant;