package framework;

public class Store {
	
	static private Store storeUnique;
	
	private Store() {
		// TODO Auto-generated constructor stub
	}
	
	public static Store getStoreUnique() {
		Store.storeUnique = new Store();
		return storeUnique;
	}
	
	public void save(StoreField storeField) {
	}
	
	public StoreField load(TrainId trainId) {
		return null;
	}
	
}
