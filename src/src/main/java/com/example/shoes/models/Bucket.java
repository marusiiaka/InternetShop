package com.example.shoes.models;

import com.example.shoes.models.enums.BucketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "buckets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name="product_id")
    private Long product_id;
    @Column(name = "quantity")
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private BucketStatus status;
}
