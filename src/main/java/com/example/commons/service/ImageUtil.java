package com.example.commons.service;

import com.example.commons.config.Constants;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Component
public class ImageUtil {
    /**
     * 获取图片文件file类型，然后做成uri保存到服务器里面，并且
     * @param file 图片文件对象
     * @return 转储文件名称
     */
    public String upload(MultipartFile file,String storeIP){
        String fileName = file.getOriginalFilename();
        //后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        File fileDirectory = new File(storeIP);
        //文件夹不存在，就先创建文件夹
        if (!fileDirectory.exists()) {
            //创建失败
            if (!fileDirectory.mkdir()) {
                throw new BusinessErrorException(BusinessMsgEnum.FILE_CREATE_FAIL);
            }
        }
        //创建目标文件
        File destFile = new File(storeIP + "/" +newFileName);
        try {
            //传入文件写入
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    //获取当前的ip和端口号
     private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    /**
     * 提供可以之间访问服务器图片的地址
     * @param httpServletRequest 请求对象
     * @param newFileName 转储图片名称
     * @return
     */
    @SneakyThrows
    public String getImageIp(HttpServletRequest httpServletRequest, String newFileName,String storeIp){
        String destPath = storeIp.substring(storeIp.indexOf("/images"));
        return getHost(new URI(httpServletRequest.getRequestURL() + "")) + destPath +"/"
                + newFileName;
    }
}
