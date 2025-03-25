package com.qr.controller;

import com.qr.dao.Box;
import com.qr.exception.NotFoundException;
import com.qr.service.BoxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
@Slf4j
public class BoxController {
    private final BoxService boxService;


    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/box/upload")
    public String saveBox(@RequestParam("image") MultipartFile file,
                          @RequestParam(name = "name", required = false, defaultValue = "box") String name, Model model) throws IOException {
        log.info("Saving box image");
        Box box = new Box();
        box.setImage(file.getBytes());
        box.setName(name);
        Box upBox = boxService.save(box);
        log.info("Box:{} image saved", box.getName());
        final byte[] image = boxService.qrSupplier("/box/" + upBox.getId() + "/image");
        String qrCodeBase64 = Base64.getEncoder().encodeToString(image);
        model.addAttribute("qrCodeBase64", qrCodeBase64);
        model.addAttribute("boxId", upBox.getId());
        return "qr";
    }

    @GetMapping(path = {"/box/{id}/image"})
    public String getImage(@PathVariable("id") Integer id, Model model) throws IOException {
        log.info("Getting box image with id: {}", id);
        final byte[] image = boxService.getBoxImageById(id);
        log.info("Box image with id: {} retrieved", id);
        String imageCodeBase64 = Base64.getEncoder().encodeToString(image);
        model.addAttribute("name", boxService.getBoxNameById(id));
        model.addAttribute("imageBase64", imageCodeBase64);
        return "image";
    }

    @GetMapping(path = {"/box/find/qr"})
    public String getQrByName(@RequestParam("name") String name, Model model) throws IOException, NotFoundException {
        log.info("Getting qr of box with name: {}", name);
        Box box = boxService.getBoxByName(name);
        final byte[] qr = boxService.qrSupplier("/box/" + box.getId() + "/image");
        log.info("Box with name: {} retrieved", name);
        String qrCodeBase64 = Base64.getEncoder().encodeToString(qr);
        model.addAttribute("qrCodeBase64", qrCodeBase64);
        model.addAttribute("boxId", box.getId());
        return "qr";
    }
}
