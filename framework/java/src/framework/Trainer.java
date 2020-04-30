package framework;

public class Trainer {
	
	private int timeSimulation;
		
	public Trainer() {
		// TODO Auto-generated constructor stub
	}
	
	public void train(Builder builder) {
		
		for(int nItr = 0; nItr < 10; nItr++) {
			builder.saveAgent(this);
		}
		
	}
	
	public int getTimeSimulation() {
		return timeSimulation;
	}
	
}
