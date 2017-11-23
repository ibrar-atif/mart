package com.mart.service.impl;


import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

import com.mart.config.AppContext;
import com.mart.dto.ProductDetails;
import com.mart.dto.ProductDto;
import com.mart.service.ProductService;

@Service

public class ProductServiceImpl implements ProductService{


	public ProductDto addProductToOrder(ProductDto productDto) throws Exception {
		ProductDetails product = AppContext.getProductDetailsMap().get(new Integer((int) productDto.getId()));
		
		if(product!=null){
			productDto.setName(product.getName());
			if(product.isSpecialFlag()){
				if(productDto.getQuantity()< product.getSpecialQuantity()){
					productDto.setAmount(product.getPrice()*productDto.getQuantity());
				}else{
					long specialcount = productDto.getQuantity()/product.getSpecialQuantity();
					long nonSpeacial = productDto.getQuantity()%product.getSpecialQuantity();
					long amount = product.getSpecialPrice()*specialcount+product.getPrice()*nonSpeacial;
					productDto.setAmount(amount);
				}
			}else{
				productDto.setAmount(product.getPrice()*productDto.getQuantity());
			}
		}
		return productDto;
	}



	public Long getTotal(Map<Integer, ProductDto> productList) {
		long total=0;
		if(productList!=null){
			for(Entry<Integer, ProductDto> product:productList.entrySet()){
				total = total+product.getValue().getAmount();
			}
		}
		return new Long(total);
	}
	
}
