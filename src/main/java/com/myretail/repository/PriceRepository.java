package com.myretail.repository;

import com.myretail.model.CurrentPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PriceRepository extends MongoRepository<CurrentPrice, Integer> {
    CurrentPrice findById(int id);
}
