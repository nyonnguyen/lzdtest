package main;
import product.*;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Test {
	

	
	
	public static void main(String[] args) {
	
//		private Product product;
		ProductResponse pr = null;	
		
		File file = new File("c:/products.json");

		

	    
		try {
//			FileReader fr = new FileReader(file);
			ObjectMapper mapper = new ObjectMapper();
//			pr = mapper.readValue(fr, ProductResponse.class);
			
			pr = mapper.readValue(loadProductList(), ProductResponse.class);
			
		} catch (Exception e) {
			System.out.println("Failed to map data: " + e.toString());
		}
		
//		System.out.println(pr.getResult());
		
		Result result = pr.getResult();
		
		
		List<Product> products = result.getProducts();
		
		System.out.println(products.size());
		
		for (Product product : products) {
			ProductDetail pd = product.getData();
			if (Double.parseDouble(pd.getMax_saving_percentage()) > 20)
			
			System.out.println( pd.getMax_saving_percentage() + "\t" + pd.getName() + "\t" + pd.getSpecial_price() );
//			System.out.println(StringEscapeUtils.unescapeXml(pd.getName()));
		}
		
	
	}
	
	
	public static InputStreamReader loadProductList() {
		InputStreamReader inputR = null;
		try {
			URL url = new URL("http://www.lazada.vn/mobapi/search/find/all-products/");
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			inputR = new InputStreamReader((InputStream) request.getContent());

			System.out.println(inputR.toString());
		} catch (Exception e) {
			System.out.println("Failed to connect server: " + e.toString());
		}
		return inputR;
	}

}
