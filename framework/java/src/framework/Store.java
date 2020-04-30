package framework;

public class Store {
	
	static private Store storeUnique = new Store();
	
	private Store() {
		// TODO Auto-generated constructor stub
	}
	
	public static Store getStoreUnique() {
		return storeUnique;
	}
	
	public void save(StoreField storeField) {
	}
	
	public StoreField load(TrainId trainId) {
		return null;
	}
	
}
