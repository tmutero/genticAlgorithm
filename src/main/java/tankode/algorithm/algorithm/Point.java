package tankode.algorithm.algorithm;

public class Point {
	public int x;
	public int y;
	Point() {
		this(0, 0);
	}
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object rhs) {
		if (!(rhs instanceof Point))
			return false;
		Point p = (Point) rhs;
		return x == p.x && y == p.y;
	}
}
