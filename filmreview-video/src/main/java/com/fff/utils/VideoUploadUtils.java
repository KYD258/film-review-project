package com.fff.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class VideoUploadUtils {

    @Value("${endpoint}")
    private String endpoint;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${bucketName}")
    private String bucketName;

    private Logger logger = LoggerFactory.getLogger(VideoUploadUtils.class);

    public String videoUpload(MultipartFile file) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 2);
        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String dir = "video/";
//        File file = new File(path);
//        String suffix = filename.substring(filename.lastIndexOf("."));
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        PutObjectResult result = ossClient.putObject(bucketName, dir + uuid + suffix, file);
        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest(bucketName, dir + filename, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("text/plain");
        metadata.setObjectAcl(CannedAccessControlList.PublicRead);
        putObjectRequest.setMetadata(metadata);
        PutObjectResult result = ossClient.putObject(putObjectRequest);
//        String eTag = result.getETag();
        logger.info("--------------->上传成功, eTag = " + result.getETag());
        ossClient.shutdown();
        String url = "http://" + bucketName + "." + endpoint + "/" + dir + filename;
        return url;
    }
}
