package com.mart.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mart.dto.ErrorMsg;
import com.mart.dto.ProductDto;
import com.mart.service.ProductService;


@RestController
@RequestMapping("/product")
//@SessionAttributes("productList")
public class ProductController {

	@Autowired
	ProductService productService;
	
	/**
	 * this method is to add new product to the order
	 * @param productDto
	 * @param httpSession
	 * @return
	 * @throws Exception
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addnew", method=RequestMethod.POST)
	public ResponseEntity<ProductDto> addProductToOrder(@RequestBody  ProductDto productDto,HttpSession httpSession
			)throws Exception,HibernateException{
		//Get price for product Detail
		productDto = productService.addProductToOrder(productDto);	
		
		//Maintaining product in session
		Map<Integer, ProductDto> productList = (Map<Integer, ProductDto> ) httpSession.getAttribute("productList");	
		Integer sid = (Integer) httpSession.getAttribute("sid");
		
		//cart to add product in session
		if(productList ==null){
			productList = new HashMap<Integer,ProductDto>();		
		}
		//custom product id to maintain in session for adding and removing
		if(sid ==null){
			sid = new Integer(0);		
		}
		
		productList.put(++sid, productDto);
		productDto.setSid(sid);
		//setting values in session
		httpSession.setAttribute("productList", productList);	
		httpSession.setAttribute("sid", sid);
		
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
	}
	
	/**
	 * This method is to remove product from order
	 * @param id
	 * @param httpSession
	 * @return
	 * @throws Exception
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/removeproduct/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeProductFromOrder(@PathVariable("id") long id,HttpSession httpSession
			)throws Exception,HibernateException{		
		Map<Integer, ProductDto> productList = (Map<Integer, ProductDto> ) httpSession.getAttribute("productList");	
		productList.remove(new Integer((int) id));
		httpSession.setAttribute("productList", productList);		
		return new ResponseEntity<Boolean>(new Boolean(true),HttpStatus.OK);
	}
	
	/**
	 * This method generate the total for the order
	 * @param httpSession
	 * @return
	 * @throws Exception
	 * @throws HibernateException
	 */
	@RequestMapping(value="/gettotal", method=RequestMethod.GET)
	public ResponseEntity<Long> getTotal(HttpSession httpSession)throws Exception,HibernateException{
		@SuppressWarnings("unchecked")
		Map<Integer, ProductDto> productList = (Map<Integer, ProductDto>) httpSession.getAttribute("productList");
		Long total = productService.getTotal(productList);
		return new ResponseEntity<Long>(total,HttpStatus.OK);
	}
	
	/**
	 * This method initialize the session for new order
	 * @param httpSession
	 * @return
	 * @throws Exception
	 * @throws HibernateException
	 */
	@RequestMapping(value="/neworder", method=RequestMethod.GET)
	public ResponseEntity<Long> intializeOrder(HttpSession httpSession)throws Exception,HibernateException{
		httpSession.setAttribute("productList", null);
		httpSession.setAttribute("sid", null);
		return new ResponseEntity<Long>(new Long(0),HttpStatus.OK);
	}
	
	/**
	 * Handling error
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMsg> handleException(Exception ex) {
		ex.printStackTrace();
		ErrorMsg error = new ErrorMsg();
		error.setStatus("ERROR");
		error.setErrorMessage("Error Occured...");
		return new ResponseEntity<ErrorMsg>(error,HttpStatus.OK);

	}
		 
}
