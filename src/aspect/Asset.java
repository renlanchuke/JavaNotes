package aspect;

public class Asset {
	private final String id;
	private final Number value;
	
	public Asset(Builder builder) {
		this.id=builder.id;
		this.value=builder.value;
	}
	
	public static class Builder{
		private String id;
		private Number value;
		
		public Builder(){
			id="";
			value=0;
		}
		
		public Builder id(String id) {
			this.id=id;
			return this;
		}
		
		public Builder value(Number nb) {
			this.value=nb;
			return this;
		}
		
		public Asset build() {
			Asset asset=new Asset(this);
			return asset;
		}
	}
}
