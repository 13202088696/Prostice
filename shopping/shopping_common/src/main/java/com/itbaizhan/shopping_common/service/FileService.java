package com.itbaizhan.shopping_common.service;

//文件服务
public interface FileService {
    /**
     * 上传文件
     * @param fileBytes 图片转化字节数值
     * @param fileName 文件名字
     * @return 返回路径
     */
    String uploadImage(byte[] fileBytes,String fileName);

    /**
     * 删除文件
     * @param filePath 文件名字
     */
    void delete(String filePath);
}
