package product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

	protected String success;
	protected Result metadata;
	
	public String getSuccessCode() {
		return this.success;
	}
	
	public Result getResult() {
		if (metadata == null) {
			metadata = new Result();
		}
		return this.metadata;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setMetadata(Result metadata) {
		this.metadata = metadata;
	}

}
