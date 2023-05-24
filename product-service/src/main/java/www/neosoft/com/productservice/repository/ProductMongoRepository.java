package www.neosoft.com.productservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import www.neosoft.com.productservice.model.Product;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, Integer> {

	

	@Query("{'Category.name':?0}")
	List<Product> findByCategory(String category);

}
