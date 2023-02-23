package com.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.project2.dto.ProductDto;
import com.project2.repository.ProductRepository;
import com.project2.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	//GET
	public Flux<ProductDto> getProducts(){
		return repository.findAll().map(AppUtils::entityToDtO);
	}
	
	public Mono<ProductDto> getProduct(String id){
		return repository.findById(id).map(AppUtils::entityToDtO);
	}
	
	public Flux<ProductDto> getProductInRange(double min,double max){
		return repository.findByPriceBetween(Range.closed(min, max));
	}
	
	//POST 
	public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
		return productDtoMono.map(AppUtils::dtoToEntity)
		.flatMap(repository::insert)
		.map(AppUtils::entityToDtO);
	}
	
	//UPDATE(PUT)
	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id){
		 return repository.findById(id)
				.flatMap(p ->productDtoMono.map(AppUtils::dtoToEntity))
				.doOnNext(e ->e.setId(id))
				.map(AppUtils::entityToDtO);
	}
	
	//DELETE
	
	public Mono<Void> deleteProduct(String id){
		return repository.deleteById(id);
	}

	
	

}
