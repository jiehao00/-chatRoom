package com.uptop.dao;


import com.uptop.entity.ImageUrl;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesDao {

    public void insertImages(ImageUrl images);
}
