

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

	private Result metadata;
	
	public Result getResult() {
		if (metadata == null) {
			metadata = new Result();
		}
		return this.metadata;
	}

}
