package com.example.shopping_file_service.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.itbaizhan.shopping_common.exception.BusException;
import com.itbaizhan.shopping_common.result.CodeEnum;
import com.itbaizhan.shopping_common.service.FileService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;

@DubboService
public class FileServiceImpl implements FileService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("${fdfs.fileUrl}")
    private String fileUrl; //nginx访问FastDFS的文件路径

    @Override
    public String uploadImage(byte[] fileBytes, String fileName) {
        if(fileBytes.length !=0){
           try {
               //我就按字节转字符流
               ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
               //获得后缀名
               String fileSuffix  = fileName.substring(fileName.lastIndexOf(".") + 1);
               //上传文件
               StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), fileSuffix, null);
               //放回照片路径
               String imageUrl = fileUrl + "/" + storePath.getFullPath();
               return imageUrl;
           }catch (Exception e){
               throw new BusException(CodeEnum.UPLOAD_FILE_ERROR);
           }
        }else {
            throw new BusException(CodeEnum.UPLOAD_FILE_ERROR);
        }
    }

    @Override
    public void delete(String filePath) {
        fastFileStorageClient.deleteFile(filePath);
    }
}
