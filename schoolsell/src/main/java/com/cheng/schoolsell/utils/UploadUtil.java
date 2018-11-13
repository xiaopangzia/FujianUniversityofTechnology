package com.cheng.schoolsell.utils;

import com.cheng.schoolsell.config.NetEaseNosConfig;
import com.cheng.schoolsell.config.QiNiuUploadConfig;
import com.netease.cloud.auth.BasicCredentials;
import com.netease.cloud.auth.Credentials;
import com.netease.cloud.services.nos.NosClient;
import com.netease.cloud.services.nos.model.ObjectMetadata;
import com.netease.cloud.services.nos.transfer.TransferManager;
import com.netease.cloud.services.nos.transfer.Upload;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: cheng
 * Date: 2018-07-11
 * Time: 上午11:37
 * 七牛上传图片
 */
@Component
public class UploadUtil {

    @Autowired
    private QiNiuUploadConfig qiNiuUploadConfig;

    @Autowired
    private NetEaseNosConfig netEaseNosConfig;

    /**
     * 七牛云上传文件方法
     * @deprecated
     * @param file
     * @return
     * @throws IOException
     */
    @Deprecated
    public String uploadFile(MultipartFile file) throws IOException {

        String uploadFileName = KeyUtil.getUUID();
        Auth auth = Auth.create(
                qiNiuUploadConfig.getAccessKey(),
                qiNiuUploadConfig.getSecretKey());
        String upToken = auth.uploadToken(qiNiuUploadConfig.getBucket());
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);

        String newName = uploadFileName + file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));

        uploadManager.put(file.getBytes(), newName, upToken);
        return qiNiuUploadConfig.getUploadUrl() + newName;
    }

    /**
     * 七牛云删除文件方法
     * @deprecated
     * @param url
     * @throws QiniuException
     */
    @Deprecated
    public void deleteFile(String url) throws QiniuException {

        String key = url.substring(url.lastIndexOf("/") + 1);
        Auth auth = Auth.create(
                qiNiuUploadConfig.getAccessKey(),
                qiNiuUploadConfig.getSecretKey());
        Configuration configuration = new Configuration(Zone.zone2());
        BucketManager bucketManager = new BucketManager(auth, configuration);
        bucketManager.delete(qiNiuUploadConfig.getBucket(), key);
    }

    /**
     * 网易云对象存储上传文件
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFileNOS(MultipartFile file) throws IOException {

        Credentials credentials = new BasicCredentials(
                netEaseNosConfig.getAccessKey(),
                netEaseNosConfig.getSecretKey());
        NosClient nosClient = new NosClient(credentials);
        nosClient.setEndpoint(netEaseNosConfig.getUploadUrl());
        //然后通过nosClient对象来初始化TransferManager
        TransferManager transferManager = new TransferManager(nosClient);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        String uploadFileName = KeyUtil.getUUID();
        String newName = uploadFileName + file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));

        Upload upload = transferManager.upload(netEaseNosConfig.getBucket(), newName, file.getInputStream(), objectMetadata);
        try {
            upload.waitForUploadResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "https://sell."+netEaseNosConfig.getUploadUrl()+"/"+newName;
    }

    /**
     * 网易云对象存储删除图片
     * @param url
     * @return
     */
    public boolean deleteFileNOS(String url) {
        String key = url.substring(url.lastIndexOf("/") + 1);
        Credentials credentials = new BasicCredentials(
                netEaseNosConfig.getAccessKey(),
                netEaseNosConfig.getSecretKey());
        NosClient nosClient = new NosClient(credentials);
        nosClient.setEndpoint(netEaseNosConfig.getUploadUrl());
        boolean isExist = nosClient.doesObjectExist(
                netEaseNosConfig.getBucket(),key,null);
        if (isExist){
            nosClient.deleteObject(netEaseNosConfig.getBucket(),key);
        }
        return true;
    }

}
