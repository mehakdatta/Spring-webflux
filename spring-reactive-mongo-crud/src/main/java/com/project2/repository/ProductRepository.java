package com.project2.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.project2.dto.ProductDto;
import com.project2.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

	Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);

	

}
