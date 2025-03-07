package com.qr.service;

import com.qr.dao.Box;
import com.qr.exception.NotFoundException;
import com.qr.repository.BoxRepository;
import io.nayuki.qrcodegen.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class BoxService {
    private final BoxRepository boxRepository;
    private final String baseUrl;

    @Autowired
    public BoxService(BoxRepository boxRepository, Environment environment) {
        this.boxRepository = boxRepository;
        String port = environment.getProperty("server.port");
        String host = environment.getProperty("server.host");
        String protocol = environment.getProperty("server.protocol");
        if (host == null) {
            host = "localhost";
        }
        if (port == null) {
            port = "8080";
        }
        if (protocol == null) {
            protocol = "http";
        }
        baseUrl = protocol.toLowerCase() + "://" + host + ":" + port;
    }

    public byte[] qrSupplier(String url) throws IOException {
        QrCode qr = QrCode.encodeText(baseUrl + url, QrCode.Ecc.MEDIUM);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(toImage(qr, 10, 4, 0xFFFFFFFF, 0xFF000000), "PNG", baos);
        return baos.toByteArray();
    }

    public Box save(Box box) {
        return boxRepository.save(box);
    }

    public byte[] getBoxImageById(int id) {
        return boxRepository.findById(id).orElseThrow(() -> new RuntimeException("Box not found")).getImage();
    }

    private BufferedImage toImage(QrCode qr, int scale, int border, int lightColor, int darkColor) {
        Objects.requireNonNull(qr);
        if (scale <= 0 || border < 0)
            throw new IllegalArgumentException("Value out of range");
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
            throw new IllegalArgumentException("Scale or border too large");

        BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }
        return result;
    }

    public Box getBoxByName(String name) {
        return boxRepository.findByName(name).orElseThrow(() -> new NotFoundException("Box not found"));
    }
}
