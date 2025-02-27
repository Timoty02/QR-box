package com.qr.controller;

import com.qr.dao.Box;
import com.qr.service.BoxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@Slf4j
public class BoxController {
    private final BoxService boxService;


    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping("/box")
    public ResponseEntity<byte[]> saveBox(@RequestParam("image") MultipartFile file,
                          @RequestParam(name = "name", required = false, defaultValue = "box") String name) throws IOException {
        log.info("Saving box image");
        Box box = new Box();
        box.setImage(file.getBytes());
        box.setName(name);
        Box upBox = boxService.save(box);
        log.info("Box:{} image saved",box.getName());
        byte[] image =boxService.qrSupplier("/box/" + upBox.getId() + "/image");
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }
    @GetMapping(path = {"/box/{id}/image"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) throws IOException {
        log.info("Getting box image with id: {}", id);
        final byte[] dbImage = boxService.getBoxImageById(id);
        log.info("Box image with id: {} retrieved", id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(dbImage);
    }

    @GetMapping(path = {"/box/{name}/qr"})
    public ResponseEntity<byte[]> getQrByName(@PathVariable("name") String name) throws IOException {
        log.info("Getting qr of box with name: {}", name);
        final byte[] qr = boxService.getBoxQrByName(name);
        log.info("Box qr with name: {} retrieved", name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qr);
    }
}
