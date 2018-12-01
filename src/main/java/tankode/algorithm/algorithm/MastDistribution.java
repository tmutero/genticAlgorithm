package tankode.algorithm.algorithm;

import java.util.Random;

public class MastDistribution {
	
	private long overallCost;
	
	public long getOverallCost() {
		return this.overallCost;
	}
	
	private int numberOfMasts;
	//The number of masts currently used.
	
	public int getNumberOfMasts() {
		return this.numberOfMasts;
	}
	
	private TransmitterMast[][] mastMap =
		new TransmitterMast[CancerMap.mapHeight][CancerMap.mapWidth];
	
	public TransmitterMast[][] getMastMap() {
		return this.mastMap;
	}
	
	private int[][] coverageMap =
		new int[CancerMap.mapHeight][CancerMap.mapWidth];

	
	public int[][] getCoverageMap() {
		return this.coverageMap;
	}
	
	public int coverage() {
	//Tells how many areas are covered with sufficient signal strength (>=3).
		int coverage = 0;
		int[][] coveragemap = this.coverageMap;
		for(int row = 0; row < CancerMap.mapHeight; row++) {
			for(int col = 0; col < CancerMap.mapWidth; col++) {
				if (CancerMap.cancerMap[row][col] == 1)
					coverage += (coveragemap[row][col] >= 3 ? 1 : 0);
			}
		}
		return coverage;
	}
	
	public void addMast(TransmitterMast mast, int posX, int posY)
	throws Exception {
	//Places a mast at the given coordinates.
		if (!(posY<0||posY>=CancerMap.mapHeight||
			posX<0||posX>=CancerMap.mapWidth)) {
				if (!(CancerMap.cancerMap[posY][posX] == 0)) {
					if (this.mastMap[posY][posX] == null) {
						this.mastMap[posY][posX] = mast;
						int[][] coverage = mast.getAreaOfCoverage();
						for (int row = 0; row < 9; row++) {
							for (int col = 0; col < 9; col++) {
								if (!(posY+row-4<0||
									posY+row-4>=CancerMap.mapHeight||
									posX-col+4<0||
									posX-col+4>=CancerMap.mapWidth))
										this.coverageMap
											[posY+row-4][posX-col+4]
											+= coverage[row][col];
							}
						}
						this.overallCost+=mast.getCost();
						this.numberOfMasts++;
					} else {
						throw new Exception(
							"Cannot add another mast in this place."+
							"(X="+posX+", Y="+posY+")");
					}
				} else {
					throw new Exception(
						"Cannot add a mast outside the country."+
						"(X="+posX+", Y="+posY+")");
				}
		} else {
			throw new Exception(
				"Cannot add a mast outside the map."+
				"(X="+posX+", Y="+posY+")");
		}
	}
	
	public void removeMast(int posX, int posY)
	throws Exception {
	//Removes a mast from the given coordinates.
		if (!(posY<0||posY>=CancerMap.mapHeight||
			posX<0||posX>=CancerMap.mapWidth)) {
				if (!(this.mastMap[posY][posX] == null)) {
					this.overallCost-=mastMap[posY][posX].getCost();
					int[][] coverage = mastMap[posY][posX].getAreaOfCoverage();
					for (int row = 0; row < 9; row++) {
						for (int col = 0; col < 9; col++) {
							if (!(posY+row-4<0||
								posY+row-4>=CancerMap.mapHeight||
								posX-col+4<0||
								posX-col+4>=CancerMap.mapWidth))
									this.coverageMap[posY+row-4][posX-col+4] -=
										coverage[row][col];
						}
					}
					this.mastMap[posY][posX] = null;
					this.numberOfMasts--;
				} else {
					throw new Exception(
						"Cannot remove a non-existent mast."+
						"(X="+posX+", Y="+posY+")");
				}
		} else {
			throw new Exception(
				"Cannot remove a mast outside the map."+
				"(X="+posX+", Y="+posY+")");
		}
	}
	
	public TransmitterMast.MastType checkMast(int posX, int posY)
	throws Exception {
	//Tells what type of mast is placed at the given coordinates.
		if (!(posY<0||posY>=CancerMap.mapHeight||
			posX<0||posX>=CancerMap.mapWidth)) {
				if (!(this.mastMap[posY][posX] == null)) {
					return this.mastMap[posY][posX].getMastType();
				} else {
					throw new Exception(
						"Cannot check for a non-existent mast."+
						"(X="+posX+", Y="+posY+")");
				}
		} else {
			throw new Exception(
				"Cannot check for a mast outside the map."+
				"(X="+posX+", Y="+posY+")");
		}
	}
	
	public void clear() {
	//Deletes all entries of masts for this MastDistribution.
		for (int row = 0; row < CancerMap.mapHeight; row++) {
			for (int col = 0; col < CancerMap.mapWidth; col++) {
				this.mastMap[row][col] = null;
				this.coverageMap[row][col] = 0;
				this.overallCost = 0;
				this.numberOfMasts = 0;
			}
		}
	}
	
	public void randomize(int n) {
	//Fills this MastDistribution randomly with up to TransmitterMasts.
		TransmitterMast tma = new TransmitterMast(TransmitterMast.MastType.A);
	    TransmitterMast tmb = new TransmitterMast(TransmitterMast.MastType.B);
	    TransmitterMast tmc = new TransmitterMast(TransmitterMast.MastType.C);
	    this.clear();

	    Random rnd = new Random();
		int masts = rnd.nextInt(100)+1;
		for (int i = 0; i < masts; i++) {
			TransmitterMast mast = tma;
			switch (rnd.nextInt(3)) {
				case 0:
					mast = tma;
					break;
				case 1:
					mast = tmb;
					break;
				default:
					mast = tmc;
					break;
			}
			try {
				this.addMast(mast,
					(int)(Math.random()*CancerMap.mapWidth-1),
					(int)(Math.random()*CancerMap.mapHeight-1));
			} catch (Exception ex) {
			}
		}
	}
}