package framework;

public class ValueFunctionApproximatorFactory {
	
	static private ValueFunctionApproximatorFactory valueFunctionApproximatorFactoryUnique = new ValueFunctionApproximatorFactory();
	
	private ValueFunctionApproximatorFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public ValueFunctionApproximator create(BuildOrder buildOrder) {
		return new ValueFunctionApproximator();
	}

	public static ValueFunctionApproximatorFactory getvalueFunctionApproximatorFactoryUnique() {
		return valueFunctionApproximatorFactoryUnique;
	}
}