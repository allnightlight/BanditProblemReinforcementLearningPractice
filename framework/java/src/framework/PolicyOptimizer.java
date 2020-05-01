package framework;

public class PolicyOptimizer {
	
	private BuildOrder buildOrder;

	public PolicyOptimizer(Agent agent, ValueFunctionApproximator valueFunctionApproximator, BuildOrder buildOrder) {
		// TODO Auto-generated constructor stub
		this.buildOrder = buildOrder;
	}
	
	public void train(MyArray<ObservationSequence> observationSequences) {
		// NOT IMPLEMENTED YET
	}
	
	public void requestUpdate(Trainer trainer) {
		
		if((trainer.getTimeSimulation()+1) % buildOrder.getnIntervalPolicyOptimization() == 0) {
			
			MyArray<ObservationSequence> observationSequences = new MyArray<ObservationSequence>();
			System.out.println("policy optimization was not implemented yet.");
			for(int i = 0; i < buildOrder.getnBatchPolicyOptimization(); i++) {
				observationSequences.add(trainer.getObservationSequence(i));
			}
			train(observationSequences);
			trainer.markPolicyUpdate();			
		}
	}
	
	
	
}
