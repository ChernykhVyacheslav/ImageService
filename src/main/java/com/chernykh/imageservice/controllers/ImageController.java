package com.chernykh.imageservice.controllers;

import com.chernykh.imageservice.image.ImageType;
import com.chernykh.imageservice.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = {"/show/{imageTypeName}/{seoName}/", "/show/{imageTypeName}/"})
    public ResponseEntity<Object> getImage(@PathVariable String imageTypeName, @PathVariable(required = false) String seoName, @RequestParam String reference) {
        ImageType imageType = ImageType.getImageTypeByValue(imageTypeName);

        return ResponseEntity.ok(imageService.getImage(imageType, reference));
    }

    @GetMapping("/flush/{imageTypeName}/")
    public ResponseEntity<String> flushImage(@PathVariable String imageTypeName, @RequestParam String reference) {
        ImageType imageType = ImageType.getImageTypeByValue(imageTypeName);

        imageService.flushImage(imageType, reference);
        return ResponseEntity.ok("Image was deleted from the bucket successfully");
    }
}
