package product;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	private String id;
	private ProductDetail data;

	public Product() {}

	public String getId() {
		return id;
	}

	public ProductDetail getData() {
		if (data == null) {
			data = new ProductDetail();
		}
		return this.data;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setData(ProductDetail data) {
		this.data = data;
	}

}
