package com.example.shoes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "mark")
    private int mark;
    @Column(name = "user_name")
    private String userName;
    @Column(name="allowed")
    private boolean allowed;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Shoes shoes;
}
