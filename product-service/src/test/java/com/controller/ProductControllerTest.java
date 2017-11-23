package com.controller;

import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.mart.controller.ProductController;
import com.mart.dto.ProductDto;
import com.mart.service.ProductService;

public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
	@Mock
	HttpSession httpSession;
	
	 @Before
	    public void setUp(){
	        MockitoAnnotations.initMocks(this);
	       
	    }

	@Test
	public void addNewProduct() throws Exception{
		
		ProductDto productDto = new ProductDto();
		productDto.setId(1);
		productDto.setQuantity(4);
		
		ProductDto productOut = new ProductDto();
		productOut.setId(1);
		productOut.setQuantity(4);
		productOut.setAmount(110);
		productOut.setName("A");
		
		
		when(productService.addProductToOrder(productDto)).thenReturn(productOut);
		
		ResponseEntity<ProductDto> response = productController.addProductToOrder(productDto, httpSession);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
		
	}
	
	@Test
	public void testRemoveProduct() throws HibernateException, Exception{
		ProductDto productOut = new ProductDto();
		productOut.setId(1);
		productOut.setSid(1);
		productOut.setQuantity(4);
		productOut.setAmount(110);
		productOut.setName("A");
		
		ProductDto productOut2 = new ProductDto();
		productOut2.setSid(1);
		productOut2.setId(2);
		productOut2.setQuantity(5);
		productOut2.setAmount(40);
		productOut2.setName("b");
		
		Map<Integer,ProductDto> mapList = new HashMap<Integer,ProductDto>();
		mapList.put(1,productOut);
		mapList.put(2, productOut2);	
		when(httpSession.getAttribute("productList")).thenReturn(mapList);
		ResponseEntity<Boolean> response =productController.removeProductFromOrder(1, httpSession);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		Assert.assertEquals(new Boolean(true), response.getBody());
	}
	
	
}
