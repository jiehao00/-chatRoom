package com.uptop.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ImageUrl {
    private Integer id;
    private String name;
    private String password;
    private String imagesUrl;
    private MultipartFile url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public MultipartFile getUrl() {
        return url;
    }

    public void setUrl(MultipartFile url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
