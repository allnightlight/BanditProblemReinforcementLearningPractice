package framework;

public class EnvironmentFactory {
	
	static private EnvironmentFactory environmentFactoryUnique;
	
	private EnvironmentFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Environment create(BuildOrder buildOrder) {
		return new Environment();
	}

	public static EnvironmentFactory getEnvironmentFactoryUnique() {
		EnvironmentFactory.environmentFactoryUnique = new EnvironmentFactory(); 
		return environmentFactoryUnique;
	}
}