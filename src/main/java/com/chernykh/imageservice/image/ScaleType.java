package com.chernykh.imageservice.image;

public enum ScaleType {

    CROP, // cut of parts of the image that no longer fit the new aspect ratio
    FILL, // fill up the parts of the image that no longer fit the new aspect ration with a background-color specified by the Fill-color property
    SKEW // simply squeeze the image to fit the new height and width, this will make the image look bad in most cases
}
