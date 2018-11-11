package com.zhiyou.service;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

//服务类
@Component
public class FileService {

    @Value("${qiniu.ak}")
    private String ak;

    @Value("${qiniu.sk}")
    private String sk;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.url}")
    private String url;

    public String upload(MultipartFile file) throws IOException {
        //1.获取上传凭证
        Auth auth = Auth.create(ak, sk);
        //2.获取到i-product上传凭证
        String token = auth.uploadToken(bucket);
        //3.上传文件
        //构造上传的配置信息 指定是华南去的空间
        Configuration cfg = new Configuration(Zone.zone2());
        //4.创建文件上传的管理器
        UploadManager uploadManager = new UploadManager(cfg);

        //5.获取新的文件名称
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String fileName = "皮蛋瘦肉粥.png";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = uuid + suffix;

        // 上传客户端文件到服务器
        //参数1 字节数组  参数2 文件名称   参数3 凭证
        uploadManager.put(file.getBytes(), newFileName, token);
        // 上传后生成的图片地址
        String fileUrl = url + newFileName;
        return fileUrl;
    }

}
