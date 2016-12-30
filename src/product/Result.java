package product;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

	private String product_count;
	private String age_restriction;
	private List<Product> results;
	
	
	public List<Product> getProducts() {
		if (results == null) {
			results = new ArrayList<Product>();
		}
		return this.results;
	}
	
	public String getage() {
		return this.age_restriction;
	}
	
	public String getProductCount() {
		return this.product_count;
	}

	public void setProduct_count(String product_count) {
		this.product_count = product_count;
	}

	public void setAge_restriction(String age_restriction) {
		this.age_restriction = age_restriction;
	}

	public void setResults(List<Product> results) {
		this.results = results;
	}
	
}
