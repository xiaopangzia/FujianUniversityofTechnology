package com.zhiyou.util;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class QiniuUtil {

    private static String ak = "G4nLglkv8U0MO5jOh6h8OGu2PlO824dfRrkoSSNR";
    private static String sk = "DqO2bUaLgWckpEr6NWaLtSS9GQgT5-HNk2hgOsnR";
    private static String bucket = "i-product";
    private static String url = "http://p9zrb5mmx.bkt.clouddn.com/";

    public static String upload(MultipartFile file) throws IOException {

        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        Auth auth = Auth.create(ak, sk);
        String token = auth.uploadToken(bucket);
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        String fileName = "皮蛋瘦肉粥.png";

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = uuid + suffix;

        // 1.上传的图片 2.上传到空间的图片名 3.配置
        uploadManager.put(file.getBytes(), newFileName, token);

        String filePath = url + newFileName;
        return filePath;
    }
}
