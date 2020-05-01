package framework;

public class TrainerFactory {
	
	private static TrainerFactory trainerFactory = new TrainerFactory();
	
	private TrainerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static TrainerFactory getTrainerFactory() {
		return trainerFactory;
	}
	
	public Trainer create(Agent agent, Environment environment, 
			ValueFunctionApproximator valueFunctionApproximator, 
			RewardGiver rewardGiver, BuildOrder buildOrder) {
		
		ClosedLoopSimulator closedLoopSimulator = new ClosedLoopSimulator(environment, agent, buildOrder);
		ValueFunctionOptimizer valueFunctionOptimizer = new ValueFunctionOptimizer(valueFunctionApproximator, agent, buildOrder);
		PolicyOptimizer policyOptimizer = new PolicyOptimizer(agent, valueFunctionApproximator, buildOrder);
		
		return new Trainer(closedLoopSimulator, valueFunctionOptimizer, policyOptimizer, rewardGiver, buildOrder);
	}

}
