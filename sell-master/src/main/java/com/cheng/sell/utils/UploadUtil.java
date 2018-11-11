package com.cheng.sell.utils;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-11
 * Time: 上午11:37
 */
public class UploadUtil {

    private static final String UPLOADURL = "http://paicrtff0.bkt.clouddn.com/";
    private static final String ACCESSKEY = "ZwAqX8X2mgS_FZbdgUgZON9K8fGI2LOadY2OrI6d";
    private static final String SECRETKEY = "qfOy5e_Fv012NB37Hc0c_wABMq4cPLrVTLPCCLZh";
    private static final String BUCKET = "test";

    public static String uploadFile(MultipartFile file) throws IOException {

        String uploadFileName = KeyUtil.getUUID();
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        String upToken = auth.uploadToken(BUCKET);
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        String newName = uploadFileName + file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        System.out.println(newName);
        uploadManager.put(file.getBytes(), newName, upToken);
        return UPLOADURL + newName;
    }

}
