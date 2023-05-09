package com.example.shoes.repositories;

import com.example.shoes.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByShoesId(Long id);
}
