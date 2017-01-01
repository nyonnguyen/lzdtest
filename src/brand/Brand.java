package brand;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand {

	protected BrandDetail brand;

	public BrandDetail getBrand() {
		return brand;
	}

	public void setBrand(BrandDetail brand) {
		this.brand = brand;
	}
	
	
}
