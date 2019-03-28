import java.util.Arrays;

public class Puzzle implements Comparable<Puzzle>{
	int[][] state;
	int h;
	int g;
	int fn;
	Puzzle parent;
	public Puzzle(int[][] state,Puzzle parent) {
		this.state=state;
		this.parent=parent;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public void setFn() {
		this.fn = this.g+this.h;
	}
	public int getfn() {
		return fn;
	}
	@Override
	public int compareTo(Puzzle p) {
		if(this.fn>p.fn) {
			return 1;
		}
		else if(this.fn<p.fn) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
