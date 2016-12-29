import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetail {

	private String pSku;
	private String pName;
	private boolean isNewProduct = false;
	private String pUrl;
	private String pDescription;
	private String pBranch;
	private int pMaxPrice;
	private int pPrice;
	private int pMaxSpecialPrice;
	private int pSpecialPrice;
	private double pMaxSavingPercentage;
	private boolean isFastDeliver;
	private int pTotalRating;
	public String getpSku() {
		return pSku;
	}
	public String getpName() {
		return pName;
	}
	public boolean isNewProduct() {
		return isNewProduct;
	}
	public String getpUrl() {
		return pUrl;
	}
	public String getpDescription() {
		return pDescription;
	}
	public String getpBranch() {
		return pBranch;
	}
	public int getpMaxPrice() {
		return pMaxPrice;
	}
	public int getpPrice() {
		return pPrice;
	}
	public int getpMaxSpecialPrice() {
		return pMaxSpecialPrice;
	}
	public int getpSpecialPrice() {
		return pSpecialPrice;
	}
	public double getpMaxSavingPercentage() {
		return pMaxSavingPercentage;
	}
	public boolean isFastDeliver() {
		return isFastDeliver;
	}
	public int getpTotalRating() {
		return pTotalRating;
	}
	
	
}
