package com.example.shoes.repositories;

import com.example.shoes.models.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BucketRepository extends JpaRepository<Bucket,Long> {
@Query(
        value = "SELECT * FROM buckets where user_id=?1 and status like 'IN_PROCESS'",
        nativeQuery = true)
    List <Bucket> findByUserId(@Param("userId") long userId);

    @Query(
            value = "SELECT * FROM buckets where user_id=?1 and product_id=?2 ",
            nativeQuery = true)
    Optional<Bucket> findByUserIdAndProductId(@Param("userId") long userId, @Param("productId") long  productId);
}
