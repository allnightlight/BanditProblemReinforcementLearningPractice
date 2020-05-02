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
		
		MyArray<ObservationSequence> observationSequences = null;
		MyArray<Action> actions = null;
		MyArray<Reward> rewards = null;
		int timeBegin = 0;
		int j = 0;
		
		if(timeLastUpdate + this.buildOrder.getnHorizonValueOptimization() <= timeSimulation) {
			observationSequences = new MyArray<ObservationSequence>();
			actions = new MyArray<Action>();
			rewards = new MyArray<Reward>();
			
			timeBegin = timeSimulation - this.buildOrder.getnHorizonValueOptimization() + 1;
			
			for(j = 0; j < this.buildOrder.getnHorizonValueOptimization();j++) {
				observationSequences.add(trainer.getObservationSequence(timeBegin + j));
				actions.add(trainer.getAction(timeBegin + j));
				rewards.add(trainer.getReward(timeBegin + j));				
			}
			observationSequences.add(trainer.getObservationSequence(timeBegin + this.buildOrder.getnHorizonValueOptimization()));
			
			train(observationSequences, actions, rewards);        	
        }
	}
	
}
