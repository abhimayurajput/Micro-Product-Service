package www.neosoft.com.productservice.model;

import java.util.List;
/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;*/

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;



/*@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")*/
@Document(collection = "product")
public class Product {
	@Id
	//@GeneratedValue
	//@Column(name = "ID")
	private Integer id;
	//@Column(name = "PRODUCT_NAME")
	private String name;
	
	@NotNull(message = "category should not be null")
	private Category category;
	//@Column(name = "PRICE")
	@Min(0)
	private Double price;
	//@Column(name = "DISCOUNT")
	@Max(50)
	private Double discount;
	//@Column(name = "DISCOUNT_DESCRIPTION")
	private String discountDescription;
	private String currency;
	private List<String> imageURLS;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getDiscountDescription() {
		return discountDescription;
	}
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<String> getImageURLS() {
		return imageURLS;
	}
	public void setImageURLS(List<String> imageURLS) {
		this.imageURLS = imageURLS;
	}
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer id, String name, Category category, Double price, Double discount,
			String discountDescription, String currency, List<String> imageURLS) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.discount = discount;
		this.discountDescription = discountDescription;
		this.currency = currency;
		this.imageURLS = imageURLS;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", discount="
				+ discount + ", discountDescription=" + discountDescription + ", currency=" + currency + ", imageURLS="
				+ imageURLS + "]";
	}
	
	
	

}
