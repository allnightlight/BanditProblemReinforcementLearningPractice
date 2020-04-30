package framework;

public class StoreField {
	
	private int timeSimulation;
	private AgentMemento agentMemento;
	private BuildOrder buildOrder;
	
	public StoreField(int timeSimulation, AgentMemento agentMemento, BuildOrder buildOrder) {
		// TODO Auto-generated constructor stub
		this.timeSimulation = timeSimulation;
		this.agentMemento = agentMemento;
		this.buildOrder = buildOrder;
	}
	
	public AgentMemento getAgentMemento() {
		return agentMemento;
	}
	
	public int getTimeSimulation() {
		return timeSimulation;
	}
	
	public BuildOrder getBuildOrder() {
		return buildOrder;
	}
}
