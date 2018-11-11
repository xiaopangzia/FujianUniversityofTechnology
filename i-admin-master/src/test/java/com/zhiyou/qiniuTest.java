package com.zhiyou;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.zhiyou.model.User;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class qiniuTest {

    @Test
    public void test() throws QiniuException {
        String ak = "G4nLglkv8U0MO5jOh6h8OGu2PlO824dfRrkoSSNR";
        String sk = "DqO2bUaLgWckpEr6NWaLtSS9GQgT5-HNk2hgOsnR";
        String bucket = "i-product";

        Auth auth = Auth.create(ak, sk);
        String token = auth.uploadToken(bucket);
        System.out.println(token);
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String fileName = "12345.png";
        String suffix = fileName.substring(fileName.lastIndexOf(".") - 1);
        String newFileName = uuid + suffix;
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 1.上传的图片 2.上传到空间的图片名 3.配置
        Response response = uploadManager.put(fileName, newFileName, token);
        String url = "http://p9zrb5mmx.bkt.clouddn.com/";
        String filePath = url + newFileName;
        System.out.println(filePath);

    }

    @Test
    public void test1() {
        String uuid= UUID.randomUUID().toString();
        List<User> list = new ArrayList<>();
    }

}
