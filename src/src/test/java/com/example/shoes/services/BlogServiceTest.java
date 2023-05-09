package com.example.shoes.services;

import com.example.shoes.models.Blog;
import com.example.shoes.repositories.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {
    @Mock
    BlogRepository blogRepository;

    @Test
    void findAllBlogs() {
        List<Blog> blogList = new ArrayList<>();
        blogList.add(new Blog(1L,"test", LocalDate.now()));
        when(blogRepository.findAll()).thenReturn(blogList);
        BlogService blogService = new BlogService(blogRepository);
        List<Blog> allBlogs = blogService.findAllBlogs();
        assertEquals(allBlogs.get(0).getInfo(),"test");
    }
}