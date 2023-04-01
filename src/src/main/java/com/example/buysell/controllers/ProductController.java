package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller //указывает, что класс явл компонентом, который обрабатывает HTTP запросы
@RequiredArgsConstructor //для автоматической генерации конструктора, который будет внедрять ProductService в класс
public class ProductController {
    private final ProductService productService;

    @GetMapping("/") //обработка гет запроса на адрес "/"
    public String products(@RequestParam(name = "category", required = false) String category, Model model) { //метод обрабатывает запрос на получение списка продуктов, параметр category - для фильтрации, Model - для передачи данных в представление
        model.addAttribute("products", productService.listProducts(category)); //добавляет список продуктов в объект Model
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) { //метод обрабатывает запрос на получение информации о продукте
        Product product = productService.getProductById(id); //получает информацию о продукте
        model.addAttribute("product", product); //добавл информацию о продукте
        model.addAttribute("images", product.getImages()); //добавл список изображений
        return "product-info";
    }

    @PostMapping("/product/create") //для создания товара
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                 @RequestParam("file3") MultipartFile file3, Product product) throws IOException { //опр метода, который принимает параметры file1, file2, file3 (файлы изобр) и product (содерж инф-цию о новом товаре)
        productService.saveProduct(product, file1, file2, file3); //метод сохраняет инф-цию в бд
        return "redirect:/"; //перенаправление на главную страницу после создания товара
    }

    @PostMapping("/product/delete/{id}") //для удаления товара по id
    public String deleteProduct(@PathVariable Long id) { //метод принимает параметр id
        productService.deleteProduct(id); //метод удаляет товар из бд
        return "redirect:/"; //перенаправл на главн стр после удаления товара
    }
}
