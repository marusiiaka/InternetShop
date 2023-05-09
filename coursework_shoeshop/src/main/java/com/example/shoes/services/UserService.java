package com.example.shoes.services;

import com.example.shoes.models.Bucket;
import com.example.shoes.models.User;
import com.example.shoes.models.enums.BucketStatus;
import com.example.shoes.models.enums.Role;
import com.example.shoes.repositories.BucketRepository;
import com.example.shoes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BucketRepository bucketRepository;

    public boolean createUser(User user){
        String email= user.getEmail();
        if(userRepository.findByEmail(email)!=null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}"+ email);
        userRepository.save(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void addBucket(Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Optional<Bucket> byUserIdAndProductId = bucketRepository.findByUserIdAndProductId(user.getId(), productId);
        Bucket bucketFromRepo=byUserIdAndProductId.orElse(null);
        if(bucketFromRepo==null){
            Bucket bucket=new Bucket(null, user.getId(), productId,1, BucketStatus.IN_PROCESS);
            bucketRepository.save(bucket);
        } else {
            if(bucketFromRepo.getStatus().equals(BucketStatus.IN_PROCESS)){
                bucketFromRepo.setQuantity(bucketFromRepo.getQuantity()+1);
                bucketRepository.save(bucketFromRepo);
            } else {
                bucketFromRepo.setStatus(BucketStatus.IN_PROCESS);
                bucketFromRepo.setQuantity(1);
                bucketRepository.save(bucketFromRepo);
            }
        }


    }
}
