package framework;

public class BuildOrder {
	
	private int nEpoch;
	private int nSeq;
	private int nHorizonValueOptimization;
	private int nIntervalPolicyOptimization;
	private int nBatchPolicyOptimization;
	
	
	public BuildOrder(int nEpoch, int nSeq, int nHorizonValueOptimization, int nIntervalPolicyOptimization, int nBatchPolicyOptimization) {
		// TODO Auto-generated constructor stub
		this.nEpoch                      = nEpoch;
		this.nSeq                        = nSeq;
		this.nHorizonValueOptimization   = nHorizonValueOptimization;
		this.nIntervalPolicyOptimization = nIntervalPolicyOptimization;
		this.nBatchPolicyOptimization    = nBatchPolicyOptimization;
	}	
	
	public int getnEpoch() {
		return nEpoch;
	}
	
	public int getnSeq() {
		return nSeq;
	}
	
	public int getnHorizonValueOptimization() {
		return nHorizonValueOptimization;
	}
	
	public int getnIntervalPolicyOptimization() {
		return nIntervalPolicyOptimization;
	}
	
	public int getnBatchPolicyOptimization() {
		return nBatchPolicyOptimization;
	}
	
}
