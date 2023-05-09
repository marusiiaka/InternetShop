package com.example.shoes.services;

import com.example.shoes.models.Blog;
import com.example.shoes.repositories.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    public void save(Blog blog) {
        blogRepository.save(blog);
    }
}
