package aspect;

public class AssetServiceImp implements AssetService{

	@Override
	public void addAsset(Asset asset) {
		// TODO Auto-generated method stub
		System.out.println("Add a new asset");
	}

	@Override
	public Asset getAsset(String id) {
		// TODO Auto-generated method stub
		System.out.println("get a asset by id");
		Asset asset=new Asset.Builder().id("112").value(12324).build();
		return asset;
		
	}

}
