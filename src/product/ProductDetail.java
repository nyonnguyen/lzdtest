package product;
import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetail {

	private static final DecimalFormat priceFormat = new DecimalFormat("###,###.###");
	
	private String sku;
	private String name;
	private String url;
	private String description;
	private String branch;
	private String max_price;
	private String price;
	private String max_special_price;
	private String special_price;
	private String max_saving_percentage;
	private String is_fast_deliver;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getMax_price() {
		return max_price;
	}
	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMax_special_price() {
		return max_special_price;
	}
	public void setMax_special_price(String max_special_price) {
		this.max_special_price = max_special_price;
	}
	public String getSpecial_price() {
		return special_price;
	}
	public void setSpecial_price(String special_price) {
		this.special_price = special_price;
	}
	public String getMax_saving_percentage() {
		return max_saving_percentage;
	}
	public void setMax_saving_percentage(String max_saving_percentage) {
		this.max_saving_percentage = max_saving_percentage;
	}
	public String getIs_fast_deliver() {
		return is_fast_deliver;
	}
	public void setIs_fast_deliver(String is_fast_deliver) {
		this.is_fast_deliver = is_fast_deliver;
	}
	
	public String toString() {
		String result = "";
		try {
			result = String.format("%s%% \t %s \t %s \t %s \n", getMax_saving_percentage(), getName(),
					getSku(), priceFormat.format(Double.parseDouble(getSpecial_price())));

		} catch (Exception e) {
			System.out.println("Not found Special Price!!!");
			result = String.format("%s%% \t %s \t %s \t %s \n", getMax_saving_percentage(), getName(),
					getSku(), priceFormat.format(Double.parseDouble(getPrice())));
		}
		return result;
	}
	
}
