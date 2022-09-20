package com.chernykh.imageservice.image;

import com.chernykh.imageservice.exceptions.ImageTypeNotFoundException;

public enum ImageType {

    ORIGINAL("original"),
    THUMBNAIL("thumbnail"),
    DETAIL_LARGE("detail-large");

    private final String value;

    ImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ImageType getImageTypeByValue(String value) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.value.equalsIgnoreCase(value)) {
                return imageType;
            }
        }
        throw new ImageTypeNotFoundException("No image type matches the value provided");
    }
}
