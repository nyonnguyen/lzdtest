import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	
	
	public static void main(String[] args) {
	
//		private Product product;
		ProductResponse pr = null;
		
		ObjectMapper mapper = new ObjectMapper(); // just need one
		
		InputStreamReader inputR = null;
		
		try {
			URL url = new URL("http://www.lazada.vn/mobapi/search/find/all-products/");
		    HttpURLConnection request = (HttpURLConnection) url.openConnection();
		    request.connect();
	    
	    inputR = new InputStreamReader((InputStream)request.getContent());
	    
		} catch (Exception e) {
			System.out.println("Failed to connect server: " + e.toString());
		}
	    
		try {
			
			pr = mapper.readValue(inputR, ProductResponse.class);
		} catch (Exception e) {
			System.out.println("Failed to map data: " + e.toString());
		}
		
		System.out.print(pr.getResult().getage());
		
//		Result result = pr.getResults();
		
		
//		System.out.println(result.getProductCount());
		
//		List<Product> products = result.getResults();
		
//		for (Product product : products) {
//			System.out.println(product.getId());
//		}
		
		

//		for (Product product : products) {
//			System.out.println("Data: " + product.toString());
//		}
	
	}

}
