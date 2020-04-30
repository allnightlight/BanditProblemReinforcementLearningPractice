package framework;

public class EnvironmentFactory {
	
	static private EnvironmentFactory environmentFactoryUnique = new EnvironmentFactory();
	
	private EnvironmentFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Environment create(BuildOrder buildOrder) {
		return new Environment();
	}

	public static EnvironmentFactory getEnvironmentFactoryUnique() {
		return environmentFactoryUnique;
	}
}