package framework;

public class Main {
	public static void main(String[] args) {
		
		MyArray<BuildOrder> buildOrders = new MyArray<BuildOrder>();
		for(int i=0;i<10;i++) {
			buildOrders.add(new BuildOrder(3,1,1,10,32));
		}
		Builder builder = new Builder();
		builder.build(buildOrders);
				
		MyArray<TrainId> trainIds = new MyArray<TrainId>();
		for(int k = 0; k < 20; k++) {
			trainIds.add(new TrainId());
		}
		
		Loader loader = new Loader(trainIds);
		while(true) {
			if(loader.hasNext()) {
				loader.next();
			}else {
				break;
			}			
		}
							
	}

}
