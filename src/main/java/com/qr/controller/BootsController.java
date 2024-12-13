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
    public void saveBoots(@RequestParam("image") MultipartFile file) throws IOException {
        Boots boots = new Boots();
        boots.setName("123");
        boots.setImage(file.getBytes());
        bootsService.save(boots);
    }
    @GetMapping(path = {"/boots/{id}/image"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) throws IOException {

        final byte[] dbImage = bootsService.getBootsImageById(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(dbImage);
    }
}
