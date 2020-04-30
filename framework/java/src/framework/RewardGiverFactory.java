package framework;

public class RewardGiverFactory {
	
	static private RewardGiverFactory rewardGiverFactoryUnique = new RewardGiverFactory();
	
	private RewardGiverFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public RewardGiver create(BuildOrder buildOrder) {
		return new RewardGiver();
	}

	public static RewardGiverFactory getRewardGiverFactoryUnique() {
		return rewardGiverFactoryUnique;
	}
}