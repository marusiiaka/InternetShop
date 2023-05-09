package com.example.shoes.services;

import com.example.shoes.models.Review;
import com.example.shoes.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    @Mock
    ReviewRepository reviewRepository;

    @Test
    void findAllReviews() {
        ReviewService reviewService=new ReviewService(reviewRepository);
        List<Review> reviews=new ArrayList<>();
        reviews.add(new Review(1L,"test",4,"myName",true,null));
        reviews.add(new Review(2L,"test2",4,"myName",false,null));
        when(reviewRepository.findAll()).thenReturn(reviews);
        assertEquals(reviewService.findAllReviews().get(0).getDescription(),"test2");
    }
}