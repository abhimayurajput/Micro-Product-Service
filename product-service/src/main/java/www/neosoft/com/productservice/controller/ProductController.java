package www.neosoft.com.productservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.neosoft.com.productservice.model.Product;
import www.neosoft.com.productservice.service.ProductService;


@RestController
@RequestMapping("/product/v1/api")
public class ProductController {
	@Autowired
	private ProductService productService;
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/addProduct")
	ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){
		String status=	productService.addProduct(product);
		logger.info("product stored successfully: ",status);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@GetMapping("/productList")
	ResponseEntity<List<Product>> allProduct(){
		List<Product> productList=	productService.getProducts();
		return new ResponseEntity<List<Product>>(productList,HttpStatus.ACCEPTED); 
	}
	
	@GetMapping("/productList/{category}")
	ResponseEntity<List<Product>>  productListByCategory(@PathVariable String category){
		List<Product> productList= productService.getProductsByCategory(category);
		return new ResponseEntity<List<Product>>(productList,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{id}")
	ResponseEntity<Product> productById(@PathVariable Integer id){
	Product product =	productService.productById(id);
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateProduct")
	String updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
		}
	@DeleteMapping("/productDelete/{id}")
	String deleteById(@PathVariable Integer id) {
		productService.deleteById(id);
	return "deleted successfully";	
	}
	
	

}
