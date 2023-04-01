package com.example.buysell.controllers;

import com.example.buysell.models.Image;
import com.example.buysell.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

//опр REST контроллера ImageController
@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) { /*метод getImageById обрабатывает гет запросы на адрес /images/{id}
    аннот @PathVariable - знач пременной id будет получено из адреса запроса*/
        Image image = imageRepository.findById(id).orElse(null); //поиск изобр в бд по id
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName()) //уст имя файла изобр
                .contentType(MediaType.valueOf(image.getContentType())) //опр тип содержимого
                .contentLength(image.getSize()) //опр кол-во байтов
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
