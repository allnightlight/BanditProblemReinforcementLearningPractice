package framework;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		
		MyArray<BuildOrder> buildOrders = new MyArray<BuildOrder>();
		for(int i=0;i<10;i++) {
			buildOrders.add(new BuildOrder());
		}
		Builder builder = new Builder();
		builder.build(buildOrders);
				
		MyArray<TrainId> trainIds = new MyArray<TrainId>();
		for(int k = 0; k < 20; k++) {
			trainIds.add(new TrainId());
		}
		
		Loader loader = new Loader(trainIds);
		for (Iterator<Object[]> iterator = loader; iterator.hasNext();) {
			Object [] rtn = (Object []) iterator.next();
			
		}
				
	}

}
