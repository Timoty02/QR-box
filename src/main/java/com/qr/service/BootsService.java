package com.qr.service;

import com.qr.dao.Boots;
import com.qr.repository.BootsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootsService {
    private final BootsRepository bootsRepository;
    @Autowired
    public BootsService(BootsRepository bootsRepository) {
        this.bootsRepository = bootsRepository;
    }

    public Boots save(Boots boots) {
        return bootsRepository.save(boots);
    }

    public byte[] getBootsImageById(int id) {
        return bootsRepository.findById(id).orElseThrow( () -> new RuntimeException("Boots not found")).getImage();
    }
}
