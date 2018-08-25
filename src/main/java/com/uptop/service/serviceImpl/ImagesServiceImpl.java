package com.uptop.service.serviceImpl;

import com.uptop.dao.ImagesDao;
import com.uptop.entity.ImageUrl;
import com.uptop.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("imagesService")
public class ImagesServiceImpl implements ImagesService{

    @Autowired
    private ImagesDao imagesDao;



    @Override
    public void insertImages(ImageUrl images) {
        imagesDao.insertImages(images);
    }
}
