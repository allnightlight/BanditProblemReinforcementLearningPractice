package framework;

public class TrainerFactory {
	
	private static TrainerFactory trainerFactory = new TrainerFactory();
	
	private TrainerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static TrainerFactory getTrainerFactory() {
		return trainerFactory;
	}
	
	public Trainer create(ClosedLoopSimulator closedLoopSimulator, 
			ValueFunctionOptimizer valueFunctionOptimizer, 
			PolicyOptimizer policyOptimizer, RewardGiver rewardGiver, BuildOrder buildOrder) {
		return new Trainer();
	}

}
