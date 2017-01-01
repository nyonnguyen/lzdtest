package brand;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandResult {

	protected List<Brand> data;

	public List<Brand> getData() {
		return data;
	}

	public void setData(List<Brand> data) {
		this.data = data;
	}
	
	
}
