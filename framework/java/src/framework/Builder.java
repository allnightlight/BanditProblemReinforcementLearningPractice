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
			this.buildOneAgent(buildOrder);
		}
		
	}
	
	private void buildOneAgent(BuildOrder buildOrder) {
		this.currentlyTrainedBuildOrder = buildOrder;	
		this.currentlyTrainedAgent = this.agentFactory.create(buildOrder);
		Environment environment = this.environmentFactory.create(buildOrder);
		
		ValueFunctionApproximator valueFunctionApproximator = this.valueFunctionApproximatorFactory.create(buildOrder);
		RewardGiver rewardGiver = this.rewardGiverFactory.create(buildOrder);
		
		Trainer trainer = this.trainerFactory.create(this.currentlyTrainedAgent, 
				environment, 
				valueFunctionApproximator, 
				rewardGiver, 
				buildOrder);
		trainer.train(this);
		
		this.currentlyTrainedBuildOrder = null;
		this.currentlyTrainedAgent = null;		
	}
	
	public void saveAgent(Trainer trainer) {
		AgentMemento agentMemento = this.currentlyTrainedAgent.createMemento();
		StoreField storeField = new StoreField(trainer.getTimeSimulation(), agentMemento, this.currentlyTrainedBuildOrder);
		this.store.save(storeField);
	}

}
