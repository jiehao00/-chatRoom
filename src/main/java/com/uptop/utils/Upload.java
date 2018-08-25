package com.uptop.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Upload {


    public String uploadImage(MultipartFile url,String dir) throws IOException {
        String filename=null;
        String contentType=url.getContentType();
        //获得文件后缀名
        String suffixName=contentType.substring(contentType.indexOf("/")+1);
        //得到 文件名
        filename=System.currentTimeMillis()+"."+suffixName;
        //文件保存路径
        url.transferTo(new File(dir+filename));
        return filename;
    }
}
