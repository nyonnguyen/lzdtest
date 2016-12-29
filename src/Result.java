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
}
