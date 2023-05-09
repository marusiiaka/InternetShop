package com.example.shoes.services;

import com.example.shoes.dto.ProductsFromBucket;
import com.example.shoes.models.Bucket;
import com.example.shoes.models.Shoes;
import com.example.shoes.models.User;
import com.example.shoes.models.enums.BucketStatus;
import com.example.shoes.repositories.BucketRepository;
import com.example.shoes.repositories.ShoesRepository;
import com.example.shoes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository bucketRepository;
    private final UserRepository userRepository;
    private final ShoesRepository shoesRepository;

    public List<ProductsFromBucket> getAllProductsByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        List<Bucket> byUserId = bucketRepository.findByUserId(user.getId());
        List<ProductsFromBucket> buckets = new ArrayList<>();
        for (Bucket b : byUserId) {
            Shoes byId = shoesRepository.findById(b.getProduct_id()).orElse(null);
            if (byId != null) {
                ProductsFromBucket productsFromBucket = new ProductsFromBucket(byId.getId(), byId.getCategory(), byId.getDescription(), byId.getManufacturer(), byId.getPrice(), byId.getSize(), b.getQuantity());
                buckets.add(productsFromBucket);
            }
        }
        return buckets;

    }

    public void deleteProductFromBucket(Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Optional<Bucket> byUserIdAndProductId = bucketRepository.findByUserIdAndProductId(user.getId(), productId);
        Bucket bucket = byUserIdAndProductId.orElse(null);
        if (bucket != null) {
            bucket.setStatus(BucketStatus.PAID);
            bucketRepository.save(bucket);
        }
    }

    public void paid(Long userId) {
        List<Bucket> byUserId = bucketRepository.findByUserId(userId);
        for (Bucket bsk : byUserId) {
            bsk.setStatus(BucketStatus.PAID);
            bucketRepository.save(bsk);
        }
    }
}
