package framework;

public class ValueFunctionOptimizer {
	
	private BuildOrder buildOrder;

	public ValueFunctionOptimizer(ValueFunctionApproximator valueFunctionApproximator, Agent agent, BuildOrder buildOrder) {
		// TODO Auto-generated constructor stub
		this.buildOrder = buildOrder;
	}
	
	public void train(MyArray<ObservationSequence> observationSequences, MyArray<Action> actions, MyArray<Reward> rewards) {
		// NOT IMPLEMENTED YET
	}
	
	public void requestUpdate(Trainer trainer) {
		int timeLastUpdate = trainer.getTimeLastUpdate();
		int timeSimulation = trainer.getTimeSimulation();
		if(timeLastUpdate + buildOrder.getnHorizonValueOptimization() <= timeSimulation) {
			MyArray<ObservationSequence> observationSequences = new MyArray<ObservationSequence>();
			MyArray<Action> actions = new MyArray<Action>();
			MyArray<Reward> rewards = new MyArray<Reward>();
			
			int timeBegin = timeSimulation - buildOrder.getnHorizonValueOptimization() + 1;
			
			for(int j = 0; j < buildOrder.getnHorizonValueOptimization();j++) {
				observationSequences.add(trainer.getObservationSequence(timeBegin + j));
				actions.add(trainer.getAction(timeBegin + j));
				rewards.add(trainer.getReward(timeBegin + j));				
			}
			observationSequences.add(trainer.getObservationSequence(timeBegin + buildOrder.getnHorizonValueOptimization()));
			
			train(observationSequences, actions, rewards);        	
        }
	}
	
}
