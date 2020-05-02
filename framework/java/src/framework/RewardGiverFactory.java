package framework;

public class RewardGiverFactory {
	
	static private RewardGiverFactory rewardGiverFactoryUnique;
	
	private RewardGiverFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public RewardGiver create(BuildOrder buildOrder) {
		return new RewardGiver();
	}

	public static RewardGiverFactory getRewardGiverFactoryUnique() {
		RewardGiverFactory.rewardGiverFactoryUnique = new RewardGiverFactory();
		return rewardGiverFactoryUnique;
	}
}