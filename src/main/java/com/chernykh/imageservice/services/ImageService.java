package com.chernykh.imageservice.services;

import com.chernykh.imageservice.image.ImageType;

public interface ImageService {

    public Object getImage(ImageType imageType, String reference);

    void flushImage(ImageType imageType, String reference);
}
