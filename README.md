# react-native-aliyun-oss

react-native aliyun oss

# 安装
```
npm install git+https://github.com/lesonli/react-native-aliyun-oss.git --save
react-native link
```
# 引入Framework

需要引入OSS iOS SDK framework。
在Xcode中，直接把framework拖入您对应的Target下即可，在弹出框勾选Copy items if needed。

注意，引入Framework后，需要在工程Build Settings的Other Linker Flags中加入-ObjC。如果工程此前已经设置过-force_load选项，那么，需要加入-force_load <framework path>/AliyunOSSiOS。

# 兼容IPv6-Only网络

OSS移动端SDK为了解决无线网络下域名解析容易遭到劫持的问题，已经引入了HTTPDNS进行域名解析，直接使用IP请求OSS服务端。在IPv6-Only的网络下，可能会遇到兼容性问题。而APP官方近期发布了关于IPv6-only网络环境兼容的APP审核要求，为此，SDK从2.5.0版本开始已经做了兼容性处理。在新版本中，除了-ObjC的设置，还需要引入两个系统库：
```
libresolv.tbd
SystemConfiguration.framework
```

# 使用

```

import AliyunOSS from 'react-native-aliyun-oss'

AliyunOSS.enableOSSLog();

    let key_conf = {
      AccessKey:'xxxxxxx',
      SecretKey:'xxxxxxxxxxxxxx',
    };
    let EndPoint = 'https://oss-cn-qingdao.aliyuncs.com'; 
    AliyunOSS.initWithKey(key_conf,EndPoint);
    
   ...
   
   let date = new Date();
      date = date.toGMTString();
 
    let upload_conf = {
bucketName:'xxxx',
sourceFile:'',
ossFile:'test/file1m',
updateDate:date};
    let uploadProgress = data => console.log(data);
  AliyunOSS.addEventListener('uploadProgress',uploadProgress);
  AliyunOSS.uploadObjectAsync(upload_conf)
  .then((resp) => {
    console.log(resp);
    AliyunOSS.removeEventListener('uploadProgress',uploadProgress);
  });
```

# 注意
AliyunOSS.initWithKey 只用于测试时方便，正式app中建议不要使用

正式环境请使用服务器签名，app调用AliyunOSS.initWithSigner

详细用法参考index.ios.js中的注释

当前版本只封装了下载功能，后续更新下载功能

