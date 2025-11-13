package org.example.lesson16.homework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DBRepository extends JpaRepository<Product,Long> {

    List<Product> findTop5ByOrderByViewsDesc();//if you want to get top 5 products by views
}
