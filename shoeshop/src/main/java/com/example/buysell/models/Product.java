package com.example.buysell.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id //указывает, что это поле - первичный ключ таблицы
    @GeneratedValue(strategy = GenerationType.AUTO) //значение ключа будет автоматически генерироваться
    @Column(name = "id") //указывает на имя столбца в бд, где будет храниться первичный ключ
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "price")
    private double price;
    @Column(name = "size")
    private double size;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, /*cascade = CascadeType.ALL - все операции над Product распространены на Image
    *fetch = FetchType.LAZY - изображения загружаются только при необходимости
    */
    mappedBy = "product") //связь между таблицами product и image устанвлена через поле product в классе Image
    private List<Image> images = new ArrayList<>();
    private Long previewImageId; //хранение id главного изображения товара
    private LocalDateTime dateOfCreated; //хранение времени создания товара

    @PrePersist //метод init() должен быть вызван перед сохр объекта в бд
    private void init() {
        dateOfCreated = LocalDateTime.now();
    } //устанавливает текущее время


    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image); //добавляет изображение в список изображений товара
    }
}
