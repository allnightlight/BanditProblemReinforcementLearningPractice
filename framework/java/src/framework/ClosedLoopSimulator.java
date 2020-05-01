package framework;

public class ClosedLoopSimulator {
	
	private Environment environment;
	private Agent agent;
	private ObservationSequence observationSequence;
	private BuildOrder buildOrder;
	
	public ClosedLoopSimulator(Environment environment, Agent agent, BuildOrder buildOrder) {
		// TODO Auto-generated constructor stub
		this.agent = agent;
		this.environment = environment;
		this.buildOrder = buildOrder;
		observationSequence = new ObservationSequence();
	}
	
	public ObservationSequence init() {
		observationSequence.clear();
		
		Observation observation = environment.observe();
		observationSequence.add(observation);
		return observationSequence.copy();		
	}
	
	public Object [] update() {
		ObservationSequence observationSequence0 = observationSequence.copy();
		Action action0 = agent.call(observationSequence);
		environment.update(action0);
		Observation observation = environment.observe();
		if (observationSequence.size() >= buildOrder.getnSeq()) {
			observationSequence.remove(0);			
		}
		observationSequence.add(observation);
		ObservationSequence observationSequence1 = observationSequence0.copy();
		
		Object [] rtn = new Object [] {observationSequence0, action0, observationSequence1};
		return rtn;		
	}
	
	public void requestInit(Trainer trainer) {
		init();
		trainer.addObservationSequence(observationSequence);
		trainer.setTimeLastUpdate(-1);
		trainer.setTimeSimulation(-1);
	}
	
	public void requestUpdate(Trainer trainer) {
		Object [] rtn = (Object [])update();
		ObservationSequence observationSequence0 = (ObservationSequence)rtn[0];
		Action action0 = (Action)rtn[1];
		ObservationSequence observationSequence1 = (ObservationSequence)rtn[2];
		
		Reward reward0 = trainer.reward(observationSequence0, action0);
				
		trainer.addObservationSequence(observationSequence1);
		trainer.addAction(action0);
		trainer.addReward(reward0);
		trainer.addTimeSimulation();		
	}

}
