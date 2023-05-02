package com.example.shoes.services;

import com.example.shoes.models.Image;
import com.example.shoes.models.Shoes;
import com.example.shoes.repositories.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoesService {
    private final ShoesRepository shoesRepository;

    public List<Shoes> listShoes(String category) {
        if (category == null || category.equals("Все")) return shoesRepository.findAll();
        else return shoesRepository.findByCategory(category);
    }

    public void saveShoe(Shoes shoes1, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            shoes1.addImageShoes(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            shoes1.addImageShoes(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            shoes1.addImageShoes(image3);
        }
        log.info("Saving new Shoe. Title: {}; Author: {}", shoes1.getCategory(), shoes1.getManufacturer());
        Shoes shoes = shoesRepository.save(shoes1);
        shoes.setPreviewImageId(shoes.getImages().get(0).getId());
        shoesRepository.save(shoes1);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteShoe(Long id) {
        shoesRepository.deleteById(id);
    }

    public Shoes getShoeById(Long id) {
        return shoesRepository.findById(id).orElse(null);
    }
}
