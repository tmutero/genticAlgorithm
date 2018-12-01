package tankode.algorithm.algorithm;


public class TransmitterMast {
	
	public enum MastType { A, B, C }

	private MastType type;
	//The kind of mast you want to place. There are three available.
	
	public MastType getMastType() {
		return this.type;
	}
	
	private int[][] areaOfCoverage;
	//The area the masts covers and the prospective signal strength.
	
	public int[][] getAreaOfCoverage() {
		return this.areaOfCoverage;
	}
	
	private int cost;
	//The price for the transmitter mast in a hypothetical currency.
	
	public int getCost() {
		return this.cost;
	}
	
	public TransmitterMast(MastType t) {
		this.type = t;
		switch (t) {
			case A:	//A simple mast - not very strong, but inexpensive.
				this.areaOfCoverage = new int[][] {
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,0,0,0,0},
					{0,0,0,1,1,1,0,0,0},
					{0,0,1,1,2,1,1,0,0},
					{0,0,0,1,1,1,0,0,0},
					{0,0,0,0,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0}
				};
				this.cost = 500000;
				break;
			case B:	//An advanced model - middle range, relatively costly.
				this.areaOfCoverage = new int[][] {
					{0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,0,0,0,0},
					{0,0,1,1,2,1,1,0,0},
					{0,0,1,2,3,2,1,0,0},
					{0,1,2,3,4,3,2,1,0},
					{0,0,1,2,3,2,1,0,0},
					{0,0,1,1,2,1,1,0,0},
					{0,0,0,0,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0}
				};
				this.cost = 2000000;
				break;
			case C:	//The largest mast - has the greatest range of all.
				this.areaOfCoverage = new int[][] {
					{0,0,0,0,1,0,0,0,0},
					{0,0,1,1,1,1,1,0,0},
					{0,1,1,1,1,1,1,1,0},
					{0,1,1,1,2,1,1,1,0},
					{1,1,1,2,3,2,1,1,1},
					{0,1,1,1,2,1,1,1,0},
					{0,1,1,1,1,1,1,1,0},
					{0,0,1,1,1,1,1,0,0},
					{0,0,0,0,1,0,0,0,0}
				};
				this.cost = 2500000;
				break;
			default:
				break;
		}
	}
	
}