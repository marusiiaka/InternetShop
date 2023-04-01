package com.example.buysell.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //указывает, что класс явл сущностью, которая будет храниться в бд
@Table(name = "images") //указывает на имя таблицы, для этой сущности
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id //указывает, что поле id явл идентификатором сущности
    @GeneratedValue(strategy = GenerationType.AUTO) //значение id будет генерироваться автоматически
    @Column(name = "id") //указывает на имя столбца, кот будет использ для хран значения поля
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFileName")
    private String originalFileName;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;
    @Lob //указывает, что поле bytes будет храниться как объект большого объема данных
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER) /* @ManyToOne - одному изображению может соотв много товаров и наоборот
    *@CascadeType - при обновл Image, Product также обновл
    *@FetchType - Product должна быть загружена сразу при загрузке Image
    */
    private Product product;
}
