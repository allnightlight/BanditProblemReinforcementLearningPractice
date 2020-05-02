package framework;

public class Trainer {
	
	
	private ClosedLoopSimulator closedLoopSimulator;
	private ValueFunctionOptimizer valueFunctionOptimizer;
	private PolicyOptimizer policyOptimizer;
	
	private int timeSimulation;
	private int timeLastUpdate;
	
	private MyArray<Action> historyActions;
	private MyArray<Reward> historyRewards;
	private MyArray<ObservationSequence> historyObservationSequences;
	
	private RewardGiver rewardGiver;
	
	private BuildOrder buildOrder;
		
	public Trainer(ClosedLoopSimulator closedLoopSimulator, ValueFunctionOptimizer valueFunctionOptimizer, PolicyOptimizer policyOptimizer, RewardGiver rewardGiver, BuildOrder buildOrder) {
		// TODO Auto-generated constructor stub
		
		this.buildOrder = buildOrder;
		this.closedLoopSimulator = closedLoopSimulator;
		this.valueFunctionOptimizer = valueFunctionOptimizer;
		this.policyOptimizer = policyOptimizer;
		this.rewardGiver = rewardGiver;
		
		historyActions = new MyArray<Action>();
		historyObservationSequences = new MyArray<ObservationSequence>();
		historyRewards = new MyArray<Reward>();
		
	}
	
	public void train(Builder builder) {
				
		historyActions.clear();
		historyObservationSequences.clear();
		historyRewards.clear();
		
		closedLoopSimulator.requestInit(this);

		for(int i=0; i < this.buildOrder.getnEpoch();i ++) {
			closedLoopSimulator.requestUpdate(this);
			valueFunctionOptimizer.requestUpdate(this);
			policyOptimizer.requestUpdate(this);
		}
		
	}

	
	public void addObservationSequence(ObservationSequence observationSequence){
	    historyObservationSequences.add(observationSequence);
	}

	public void addAction(Action action){
	    historyActions.add(action);
	}

	public void addReward(Reward reward){
	    historyRewards.add(reward);
	}

	public ObservationSequence getObservationSequence(int idx){
	    return historyObservationSequences.get(idx);
	}

	public Action getAction(int idx){
	    return historyActions.get(idx);
	}

	public Reward getReward(int idx){
	    return historyRewards.get(idx);
	}

	public Reward reward(ObservationSequence observationSequence, Action action){
	    return rewardGiver.evaluate(observationSequence, action);
	}

	public void addTimeSimulation(){
		timeSimulation += 1;
	}

	public void markPolicyUpdate(){
	    timeLastUpdate = this.timeSimulation;
	}
	
	public int getTimeSimulation() {
		return timeSimulation;
	}
	public int getTimeLastUpdate() {
		return timeLastUpdate;
	}

	public void setTimeSimulation(int timeSimulation) {
		this.timeSimulation = timeSimulation;
	}

	public void setTimeLastUpdate(int timeLastUpdate) {
		this.timeLastUpdate = timeLastUpdate;
	}
	
	
}
