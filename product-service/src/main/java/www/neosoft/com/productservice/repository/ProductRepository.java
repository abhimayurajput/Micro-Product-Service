package www.neosoft.com.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import www.neosoft.com.productservice.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query("from Product where name =?1")
	public List<Product> productsByName(String name);
	
	
   @Query("from Product where name =:name") 
   public List<Product> findByProdName(String name);
	 
	/*
	 * @Query(value = "SELECT * FROM Product ORDER BY id", nativeQuery = true)
	 * List<Product> getProductsById(Integer id);
	 */
	
	/*
	 * @Query("select name, price,brand from Product prod inner join Category cat")
	 * List<Object> getProdByCategory(String categoryName) ;
	 */

}
