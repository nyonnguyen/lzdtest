package main;

import product.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import brand.*;

public class Test {

	private static final String baseUrl = "http://www.lazada.vn/mobapi/";

	private static final String searchUrl = "http://www.lazada.vn/mobapi/search/?q=xiaomi&price=254600-372345&rating=1-3";

	private static final String allproducts = "http://www.lazada.vn/mobapi/search/find/all-products/";

	private static final String categories = "http://www.lazada.vn/mobapi/catalog/categories/";

	private static final String brands = "http://www.lazada.vn/mobapi/brands/";

	// appending tags
	private static final String priceTag = "&price="; // ex: 123-456
	private static final String ratingTag = "&rating="; // ex: 1-5
	private static String pageTag = "?page="; // 5

	private static PrintWriter outputStream = null;

	private static String linkValue;

	static String proxyInfo;

	private static final DecimalFormat priceFormat = new DecimalFormat("###,###.###");

	public static void main(String[] args) {

		// app <option> <>
		try {
			linkValue = args[0];
			double percentage = Double.parseDouble(args[1]);
			boolean isWriteFile = Boolean.valueOf(args[2]);
			boolean isHidePageNum = Boolean.valueOf(args[3]);

			try {
				proxyInfo = args[4];
			} catch (Exception e) {
				proxyInfo = "";
			}

			// print to file
			prepareFile(linkValue, true);

			// list products
			processProduct(baseUrl + linkValue, percentage, isHidePageNum, isWriteFile);

			// list brands
			// getListBrandName();

		} catch (Exception e) {
			showUsage();
		}
	}

	public static void processProduct(String link, double lookupPercentage, boolean isHidePageNum,
			boolean isWriteFile) {
		Result firstPage = getProductResponse(link, 0).getResult();

		Double numberPage = Double.parseDouble(firstPage.getProductCount()) / 10;

		int intNumbPage = numberPage.intValue();

		System.out.println("Number of page: " + intNumbPage);

		List<Product> listProducts = new ArrayList<Product>();

		for (int i = 1; i <= intNumbPage; i++) {
			if (!isHidePageNum) {
				System.out.println("Getting " + i + "/" + intNumbPage);
			}
			listProducts = getProductResponse(link, i).getResult().getProducts();

			printOut(listProducts, lookupPercentage, isWriteFile);
		}
	}

	public static List<Brand> getListBrandName() {
		List<Brand> listBrand = getBrandResponse(brands, 0).getMetadata().getData();

		for (Brand brand : listBrand) {
			System.out.println(brand.getBrand().getApi_url() + "\t" + brand.getBrand().getProduct_count());
		}

		return listBrand;
	}

	public static BrandResponse getBrandResponse(String link, int pageNum) {
		BrandResponse br = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String surfix = "";
			if (pageNum != 0)
				surfix = pageTag + Integer.toString(pageNum);

			br = mapper.readValue(loadProductList(link + surfix, proxyInfo), BrandResponse.class);

		} catch (Exception e) {
			System.out.println("Failed to map data: " + e.toString());
		}
		return br;
	}

	public static void prepareFile(String fileName, boolean isNew) {
		try {

			if (fileName.contains("/")) {
				fileName = fileName.replace("/", "-");
			}

			if (isNew) {
				outputStream = new PrintWriter(new FileOutputStream(fileName + ".txt"));
			} else {
				outputStream = new PrintWriter(new FileOutputStream(fileName + ".txt", true));
			}

		} catch (Exception e) {
			System.out.println("Error opening the file " + fileName + ".txt.");
			System.exit(0);
		}
	}

	public static void printOut(List<Product> products, double percentage, boolean isWriteFile) {

		for (Product product : products) {
			ProductDetail pd = product.getData();
			if (Double.parseDouble(pd.getMax_saving_percentage()) >= percentage) {
				String result = "";
				try {		
					result = String.format("%s%% \t %s \t %s \t %s \n", pd.getMax_saving_percentage(), pd.getName(),
							pd.getSku(), priceFormat.format(Double.parseDouble(pd.getSpecial_price())));
	
				} catch (Exception e) {
					result = String.format("%s%% \t %s \t %s \t %s \n", pd.getMax_saving_percentage(), pd.getName(),
							pd.getSku(), priceFormat.format(Double.parseDouble(pd.getPrice())));
				}
				writeAndPrint(result, isWriteFile);
			}
		}
	}

	private static void writeAndPrint(String value, boolean isWrite) {
		if (isWrite) {
			// print to file
			prepareFile(linkValue, false);
			
			System.out.printf(value);
			
			outputStream.println(value);
			outputStream.close();
		} else {
			System.out.printf(value);
		}
	}
	
	public static ProductResponse getProductResponse(String link, int pageNum) {
		ProductResponse pr = null;
		try {
			// FileReader fr = new FileReader(file);
			ObjectMapper mapper = new ObjectMapper();
			// pr = mapper.readValue(fr, ProductResponse.class);
			String surfix = "";
			if (pageNum != 0)
				surfix = pageTag + Integer.toString(pageNum);

			pr = mapper.readValue(loadProductList(link + surfix, proxyInfo), ProductResponse.class);

		} catch (Exception e) {
			System.out.println("Failed to map data: " + e.toString());
		}
		return pr;
	}

	public static InputStreamReader loadProductList(String link, String proxyInfo) {
		InputStreamReader inputR = null;
		try {

			URL url = new URL(link);
			HttpURLConnection request = null;

			if (!proxyInfo.isEmpty()) {
				String[] proxyInfoExtracted = proxyInfo.split(":");

				String address = proxyInfoExtracted[0];
				String port = proxyInfoExtracted[1];

				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, Integer.parseInt(port)));
				request = (HttpURLConnection) url.openConnection(proxy);
			} else {
				request = (HttpURLConnection) url.openConnection();
			}

			request.connect();

			// System.out.println(request.getResponseCode());
			// System.out.println(request.toString());

			inputR = new InputStreamReader((InputStream) request.getContent());

		} catch (Exception e) {
			System.out.println("Failed to connect server: " + e.toString());
		}
		return inputR;
	}

	public static void showUsage() {
		System.out.println(
				"# java -jar lzdtest.jar <directory> <discount_percentage> <is_write_output_to_file> <is_showing_page> <proxy:port>");

		System.out.println("Example:");

		System.out.println(
				">java -jar lzdtest.jar tv-video-am-thanh-thiet-bi-deo-cong-nghe/xiaomi 49 true false 10.10.10.10:8080");
	}

}
