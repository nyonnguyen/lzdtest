package brand;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BrandResponse {

	protected String success;
	protected BrandResult metadata;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public BrandResult getMetadata() {
		return metadata;
	}
	public void setMetadata(BrandResult metadata) {
		this.metadata = metadata;
	}
	
	
}
