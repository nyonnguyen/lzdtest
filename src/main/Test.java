package main;
import product.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Test {
	
	private static final String usstore = "http://www.lazada.vn/mobapi/usa-store/";

	private static final String allproducts = "http://www.lazada.vn/mobapi/search/find/all-products/";
	private static final String brands = "http://www.lazada.vn/mobapi/brands/";
	private static String page = "?page=";
	
	private static PrintWriter outputStream = null;
	
	private static final String fileName = "precious.txt";
	
	public static void main(String[] args) {
		
		Double percentage = Double.parseDouble(args[0]);

		
		Result firstPage = getData(0).getResult();
		
		
//		products = result.getProducts();
		
		Double numberPage = Double.parseDouble(firstPage.getProductCount())/10;
		
		int intNumbPage = numberPage.intValue();
		
		System.out.println("Number of page: " + intNumbPage);
		
		// get 1st list
//		products.addAll(firstPage.getProducts());
		
//		prepareFile(fileName);
		
		for (int i=1; i<=intNumbPage; i++) {
			System.out.println("Getting " + i + "...");
			
//			printFile(getData(i).getResult().getProducts(), percentage);
			printOut(getData(i).getResult().getProducts(), percentage);
			
			System.out.println("===> Done");
		}
		
//		System.out.println(products.size());
		
	
	}
	
	public static void prepareFile(String fileName) {
		try{
            outputStream = new PrintWriter( new FileOutputStream(fileName, true));
        }
        catch (Exception e){
            System.out.println("Error opening the file precious.txt.");
            System.exit(0);
        }
	}
	
	public static void printFile(List<Product> products, double percentage) {
		
		for (Product product : products) {
			ProductDetail pd = product.getData();
			if (Double.parseDouble(pd.getMax_saving_percentage()) > percentage) {
				String result = pd.getMax_saving_percentage() + "\t" + pd.getName() + "\t\t\t" + pd.getSpecial_price();
				System.out.println(result);
				outputStream.println(result);
			}
		}
	}
	
	public static void printOut(List<Product> products, double percentage) {
		for (Product product : products) {
			ProductDetail pd = product.getData();
			if (Double.parseDouble(pd.getMax_saving_percentage()) > percentage)
			
			System.out.println( pd.getMax_saving_percentage() + "\t" + pd.getName() + "\t\t\t" + pd.getSpecial_price() );
		}
	}
	
	public static ProductResponse getData(int pageNum) {
		ProductResponse pr = null;
		try {
//			FileReader fr = new FileReader(file);
			ObjectMapper mapper = new ObjectMapper();
//			pr = mapper.readValue(fr, ProductResponse.class);
			String surfix = "";
			if (pageNum != 0)
				surfix = page + Integer.toString(pageNum);
				
			pr = mapper.readValue(loadProductList(allproducts+surfix), ProductResponse.class);
			
		} catch (Exception e) {
			System.out.println("Failed to map data: " + e.toString());
		}
		return pr;
	}
	
	
	public static InputStreamReader loadProductList(String link) {
		InputStreamReader inputR = null;
		try {
			URL url = new URL(link);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			inputR = new InputStreamReader((InputStream) request.getContent());

		} catch (Exception e) {
			System.out.println("Failed to connect server: " + e.toString());
		}
		return inputR;
	}

}
