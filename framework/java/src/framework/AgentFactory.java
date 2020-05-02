package framework;

public class AgentFactory {
	
	static private AgentFactory agentFactoryUnique;
	
	private AgentFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Agent create(BuildOrder buildOrder) {
		return new Agent();
	}

	public static AgentFactory getAgentFactoryUnique() {
		AgentFactory.agentFactoryUnique  = new AgentFactory();
		return agentFactoryUnique;
	}
}
