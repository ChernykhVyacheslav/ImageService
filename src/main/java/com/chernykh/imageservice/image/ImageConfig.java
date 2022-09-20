package com.chernykh.imageservice.image;

import lombok.Data;

@Data
public class ImageConfig {

    private int height;
    private int width;
    private int quality;
    private ScaleType scaleType;
    private int fillColor;
    private ImageFormat imageFormat;
    private String sourceName;
}
