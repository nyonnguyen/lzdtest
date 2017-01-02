package link;

public class Link {

	private String baseLink;
	private String mainLink;
	private String price;
	private String rating;
//	private String sort;
	
	private Link(Builder builder) {
		baseLink = builder.baseLink;
		mainLink = builder.mainLink;
		price = builder.price;
		rating = builder.rating;
	}
	
	public static class Builder {
		
		private String baseLink;
		private String mainLink;
		private String price;
		private String rating;
		
		public Builder(String baseLink, String mainLink) {
			this.baseLink = baseLink;
			this.mainLink = mainLink;
		}
		
		public Builder baseLink(String baseLink) {
			this.baseLink = baseLink;
			return this;
		}
		
		public Builder mainLink(String mainLink) {
			this.mainLink = mainLink;
			return this;
		}
		
		public Builder price(String price) {
			this.price = price;
			return this;
		}
		
		public Builder rating(String rating) {
			this.rating = rating;
			return this;
		}
		
		public Link build() {
			return new Link(this);
		}
	}
}
