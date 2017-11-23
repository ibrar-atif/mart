package com.mart.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.mart.dto.ProductDetails;

@Component
public class AppContext implements ApplicationContextAware{
	
	public AppContext(){
		System.out.println("config");
	}
	
	public static Map<Integer, ProductDetails> productDetailMap;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		productDetailMap = new HashMap<Integer,ProductDetails>();
		ProductDetails p1 = new ProductDetails();
		p1.setId(1);
		p1.setName("A");
		p1.setPrice(40);
		p1.setSpecialFlag(true);
		p1.setSpecialPrice(70);
		p1.setSpecialQuantity(3);
		
		ProductDetails p2 = new ProductDetails();
		p2.setId(2);
		p2.setName("B");
		p2.setPrice(10);
		p2.setSpecialFlag(true);
		p2.setSpecialPrice(15);
		p2.setSpecialQuantity(2);
		
		ProductDetails p3 = new ProductDetails();
		p3.setId(3);
		p3.setName("C");
		p3.setPrice(30);
		
		
		ProductDetails p4 = new ProductDetails();
		p4.setId(4);
		p4.setName("D");
		p4.setPrice(25);
		
		productDetailMap.put(1,p1);
		productDetailMap.put(2,p2);
		productDetailMap.put(3,p3);
		productDetailMap.put(4,p4);
		
	}
	
	public static Map<Integer, ProductDetails> getProductDetailsMap(){
		return productDetailMap;
	}

}
