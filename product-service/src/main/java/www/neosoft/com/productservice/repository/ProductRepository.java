package www.neosoft.com.productservice.repository;

import org.springframework.data.repository.CrudRepository;

import www.neosoft.com.productservice.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
