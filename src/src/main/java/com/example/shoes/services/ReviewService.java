package com.example.shoes.services;

import com.example.shoes.models.Review;
import com.example.shoes.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> findAllowedReviewByProductId(Long shoesId) {
        return reviewRepository.findAllByShoesId(shoesId)
                .stream()
                .filter(review -> review.isAllowed())
                .collect(Collectors.toList());
    }

    public List<Review> findAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .filter(review -> !review.isAllowed())
                .collect(Collectors.toList());
    }

    public void allowedReview(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review!=null){
            review.setAllowed(true);
            reviewRepository.save(review);
        }
    }
}
