package framework;

import java.util.Iterator;

public class Loader{
	
	private Store store;
	private AgentFactory agentFactory;
	
	Iterator<TrainId> iterator;
		
	public Loader(MyArray<TrainId> trainIds) {
		// TODO Auto-generated constructor stub
		store = Store.getStoreUnique();
		agentFactory = AgentFactory.getAgentFactoryUnique();
		iterator = trainIds.iterator();		
	}
	
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}
	
	public Object[] next() {
		// TODO Auto-generated method stub

		TrainId trainId = iterator.next();
		
		StoreField storeField = store.load(trainId);
		
		int timeSimulation = storeField.getTimeSimulation();
		BuildOrder buildOrder = storeField.getBuildOrder();
		AgentMemento agentMemento = storeField.getAgentMemento();
		
		Agent agent = agentFactory.create(buildOrder);
		agent.loadFromMemento(agentMemento);
		
		// yield {timeSimulation, agent, buildOrder};
		return null;
	}
	
}
