package com.project2.utils;

import org.springframework.beans.BeanUtils;

import com.project2.dto.ProductDto;
import com.project2.entity.Product;

public class AppUtils {
	public static ProductDto entityToDtO(Product product) {
		ProductDto productDto=new ProductDto();
		BeanUtils.copyProperties(product,productDto);
		return productDto;
	}
	
	public static Product dtoToEntity(ProductDto productDto) {
		Product product=new Product();
		BeanUtils.copyProperties(productDto,product);
		return product;
	}

}
