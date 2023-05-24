package www.neosoft.com.productservice.model;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
*/
/*@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_CATEGORY")*/
public class Category {
	//@Id
	/*
	 * @GeneratedValue
	 * 
	 * @Column(name = "ID")
	 */
	private Integer id;
	//@Column(name = "CATEGORY_NAME")
	private String name;
	//@Column(name = "CATEGORY_BRAND")
	private String brand;
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	

}
