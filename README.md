# react-native-aliyun-oss

react-native aliyun oss

# 安装
```
npm install git+https://github.com/lesonli/react-native-aliyun-oss.git --save
react-native link
```

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
const config = {
  AccessKey: '',
  SecretKey: '',
};
const endPoint = '';
AliyunOSS.initWithKey(config, endPoint);

...

// upload
const uploadConfig = {
  bucketName: '',
  sourceFile: '', // local file path
  ossFile: '' // the file path uploaded to oss
};
const uploadProgress = p => console.log(p.currentSize / p.totalSize);
AliyunOSS.addEventListener('uploadProgress', uploadProgress);
await AliyunOSS.uploadObjectAsync(uploadConfig)
  .then((resp) => {
    console.log(resp);
    AliyunOSS.removeEventListener('uploadProgress', uploadProgress);
  });

...

// download
const downloadConfig = {
      bucketName: '',
      ossFile: '' // the file path on the oss
    };
const downloadProgress = p => console.log(p.currentSize / p.totalSize);
AliyunOSS.addEventListener('downloadProgress', downloadProgress);
await AliyunOSS.downloadObjectAsync(downloadConfig).then(path => {
  console.log(path); // the local file path downloaded from oss
  AliyunOSS.removeEventListener('downloadProgress', downloadProgress);
}).catch((error) => {
  console.error(error);
});
```


# 注意
AliyunOSS.initWithKey 只用于测试时方便，正式app中建议不要使用

正式环境请使用服务器签名，app调用AliyunOSS.initWithSigner

详细用法参考index.js中的注释

