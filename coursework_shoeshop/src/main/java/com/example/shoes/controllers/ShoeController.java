package com.example.shoes.controllers;

import com.example.shoes.models.Blog;
import com.example.shoes.models.Review;
import com.example.shoes.models.Shoes;
import com.example.shoes.models.User;
import com.example.shoes.models.enums.Role;
import com.example.shoes.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShoeController {
    private final ShoesService shoesService;
    private final UserService userService;
    private final BucketService bucketService;
    private final ReviewService reviewService;
    private final BlogService blogService;

    @GetMapping("/")
    public String products(@RequestParam(name = "category", required = false) String category, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("products", shoesService.listShoes(category));
        model.addAttribute("buckets", bucketService.getAllProductsByUserId());
        if (((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(Role.ROLE_ADMIN)) {
            return "admin/products";
        }
        if (((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(Role.ROLE_USER)) {
            return "products";
        }
        return "redirect:/login";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Shoes shoe = shoesService.getShoeById(id);
        model.addAttribute("shoe", shoe);
        model.addAttribute("images", shoe.getImages());
        return "shoe-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Shoes product) throws IOException {
        shoesService.saveShoe(product, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/product/addBucket/{id}")
    public String addBucket(@PathVariable Long id) {
        userService.addBucket(id);
        return "redirect:/";
    }

    @PostMapping("/bucket/delete/{id}")
    public String deleteFromBucket(@PathVariable Long id) {
        bucketService.deleteProductFromBucket(id);
        return "redirect:/";
    }

    @PostMapping("/bucket/paid")
    public String bucketPaid(Principal principal) {
        Long userId= ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        bucketService.paid(userId);
        return "redirect:/";
    }

        @PostMapping("/product/createReview/{shoeId}")
    public String createReview(@PathVariable Long shoeId,Review review, Principal principal){
            Shoes shoe = shoesService.getShoeById(shoeId);
            review.setUserName(((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getName());
            review.setShoes(shoe);
            review.setAllowed(false);
            List<Review> reviews = shoe.getReviews();
            reviews.add(review);
            shoe.setReviews(reviews);
            shoesService.saveShoe(shoe);
        return "redirect:/";
    }
    @GetMapping("/review/{id}")
    public String review(@PathVariable Long id, Model model) {
        Shoes shoe = shoesService.getShoeById(id);
        model.addAttribute("shoe", shoe);
        return "review-add";
    }
    @GetMapping("/reviewProduct/{id}")
    public String reviewProduct(@PathVariable Long id, Model model) {
        List<Review> reviews= reviewService.findAllowedReviewByProductId(id);
        model.addAttribute("reviews",reviews);
        return "review-product";
    }
    @GetMapping("/blogs")
    public String blogs( Model model) {
        List<Blog> blogs=blogService.findAllBlogs();
        model.addAttribute("blogs",blogs);
        return "blogs";
    }
}
