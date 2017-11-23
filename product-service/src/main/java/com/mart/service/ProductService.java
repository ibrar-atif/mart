package com.mart.service;

import java.util.Map;

import com.mart.dto.ProductDto;


public interface ProductService {
	
	public ProductDto addProductToOrder(ProductDto product)throws Exception;

	public Long getTotal(Map<Integer, ProductDto> productList);

}
