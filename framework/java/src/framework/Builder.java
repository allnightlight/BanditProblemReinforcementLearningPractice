package framework;

public class Builder {
	
	private Store store;
	private AgentFactory agentFactory;
	private EnvironmentFactory environmentFactory;
	private ValueFunctionApproximatorFactory valueFunctionApproximatorFactory;
	private RewardGiverFactory rewardGiverFactory;
	
	private TrainerFactory trainerFactory;
	
	private Agent currentlyTrainedAgent;
	private BuildOrder currentlyTrainedBuildOrder;
		
	public Builder() {
		// TODO Auto-generated constructor stub
		store = Store.getStoreUnique();
		agentFactory = AgentFactory.getAgentFactoryUnique();
		environmentFactory = EnvironmentFactory.getEnvironmentFactoryUnique();
		trainerFactory = TrainerFactory.getTrainerFactory();
		valueFunctionApproximatorFactory = ValueFunctionApproximatorFactory.getvalueFunctionApproximatorFactoryUnique();
		rewardGiverFactory = RewardGiverFactory.getRewardGiverFactoryUnique();
		
		currentlyTrainedAgent = null;
		currentlyTrainedBuildOrder = null;		
	}
	
	public void build(MyArray<BuildOrder> buildOrders) {
		
		for (BuildOrder buildOrder : buildOrders) {
			
			Agent agent = agentFactory.create(buildOrder);
			Environment environment = environmentFactory.create(buildOrder);
			ValueFunctionApproximator valueFunctionApproximator = valueFunctionApproximatorFactory.create(buildOrder);
			RewardGiver rewardGiver = rewardGiverFactory.create(buildOrder);
			
			currentlyTrainedBuildOrder = buildOrder;	
			currentlyTrainedAgent = agent;
			
			Trainer trainer = trainerFactory.create(agent, environment, valueFunctionApproximator, rewardGiver, buildOrder);
			trainer.train(this);
			
			currentlyTrainedBuildOrder = null;
			currentlyTrainedAgent = null;
		}
		
	}
	
	public void saveAgent(Trainer trainer) {
		AgentMemento agentMemento = currentlyTrainedAgent.createMemento();
		StoreField storeField = new StoreField(trainer.getTimeSimulation(), agentMemento, currentlyTrainedBuildOrder);
		store.save(storeField);
	}

}
