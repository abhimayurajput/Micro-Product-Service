package www.neosoft.com.productservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.neosoft.com.productservice.exception.CurrencyNotValidException;
import www.neosoft.com.productservice.exception.OfferNotValidException;
import www.neosoft.com.productservice.model.Product;
import www.neosoft.com.productservice.repository.ProductMongoRepository;
import www.neosoft.com.productservice.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductMongoRepository productMongoRepository;
	private List<Product> products = new ArrayList<>();
    
	
	@PostConstruct
	public void loadAllProdects() {
		
	}

	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		/*
		 * String status=""; Product prod = productRepository.save(product); if(prod !=
		 * null) { products.add(prod); status=status+"success"; }else {
		 * status=status+"failed"; } return status;
		 */	
		if(product.getPrice()==0 && product.getDiscount()>0) {
			throw new OfferNotValidException("No discount is allowed at 0 price");
		}
		List<String> validCurrencies=Arrays.asList("INR","USD","EUR");
		if(validCurrencies.contains(product.getCurrency().toUpperCase())) {
			throw new CurrencyNotValidException("Invalid currency."+ "Valid currencies"+validCurrencies);
		}
		productMongoRepository.save(product);
		return "saved";
	}

	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return productMongoRepository.findAll();
	}

	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		/*
		 * List<Product> listByCategory=
		 * products.stream().filter(product->product.getCategory().getName().
		 * equalsIgnoreCase(category)).collect(Collectors.toList()); return
		 * listByCategory;
		 */
		return productMongoRepository.findByCategory(category);
	}

	public Product productById(Integer id) {
		// TODO Auto-generated method stub
		/*
		 * return
		 * products.stream().filter(product->product.getId()==id).findAny().get();
		 */
		return productMongoRepository.findById(id).get();
	}

	public String updateProduct(Product product) {
		// TODO Auto-generated method stub
		String status="not updated";
		//for(Product prod:products) {
			//if(prod.getId()==product.getId()) {
				/*
				 * prod.setName(product.getName()); prod.setCategory(product.getCategory());
				 * prod.setPrice(product.getPrice()); prod.setDiscount(product.getDiscount());
				 * prod.setDiscountDescription(product.getDiscountDescription()); Product
				 * prods=productRepository.save(prod);
				 */
				productMongoRepository.save(product);
				status="updated successfully";
			//}
		//}
		return "updated successfully";
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		/*
		 * for(Product prod:products) { if(prod.getId()==id) { products.remove(prod); }
		 * }
		 */
		productMongoRepository.deleteById(id);
	}

}
