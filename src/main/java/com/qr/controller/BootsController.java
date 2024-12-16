package com.qr.controller;

import com.qr.dao.Boots;
import com.qr.service.BootsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@Slf4j
public class BootsController {
    private final BootsService bootsService;
    @Autowired
    public BootsController(BootsService bootsService) {
        this.bootsService = bootsService;
    }

    @PostMapping("/boots")
    public String saveBoots(@RequestParam("image") MultipartFile file) throws IOException {
        log.info("Saving boots image");
        Boots boots = new Boots();
        boots.setImage(file.getBytes());
        Boots upBoots = bootsService.save(boots);
        log.info("Boots image saved");
        return "http://localhost:8080/boots/" + upBoots.getId() + "/image";
    }
    @GetMapping(path = {"/boots/{id}/image"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) throws IOException {
        log.info("Getting boots image with id: {}", id);
        final byte[] dbImage = bootsService.getBootsImageById(id);
        log.info("Boots image with id: {} retrieved", id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(dbImage);
    }
}
