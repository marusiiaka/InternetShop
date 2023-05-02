package com.example.shoes.controllers;

import com.example.shoes.models.Shoes;
import com.example.shoes.services.ShoesService;
import com.example.shoes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ShoesService shoesService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "admin";
    }
    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/products")
    public String products(@RequestParam(name = "category", required = false) String category, Model model) {
        model.addAttribute("products", shoesService.listShoes(category));
        return "admin-products";
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        shoesService.deleteShoe(id);
        return "redirect:/admin/products";
    }
    @GetMapping("admin/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Shoes shoe = shoesService.getShoeById(id);
        model.addAttribute("shoe", shoe);
        model.addAttribute("images", shoe.getImages());
        return "admin-shoe-info";
    }
}
