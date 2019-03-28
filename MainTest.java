import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class MainTest {
	static  int goal_state[][]=new int[3][3];
	static PriorityQueue<Puzzle> q=new PriorityQueue<>();
	static Stack<Puzzle>st=new Stack<>();
	public static void main(String[] args) {
		int state[][] = new int[3][3];
		Puzzle cur_state;
		Scanner in=new Scanner(System.in);
		System.out.println("Enter Initial State: ");
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				state[i][j]=in.nextInt();
			}
		}
		System.out.println("Enter Goal State: ");
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				goal_state[i][j]=in.nextInt();
			}
		}
		cur_state=new Puzzle(state,null);
		cur_state.setG(0);
		cur_state.setH(findHeuristic(cur_state.state, goal_state));
		cur_state.setFn();
		q.add(cur_state);
		while(!q.isEmpty()) {
			Puzzle cur=q.remove();
			//print_state(cur.state);
			if(Arrays.deepEquals(cur.state,goal_state)){
				print_path(cur);
				break;
			}
			else {
				expandParent(cur);
				
			}
		}

	}
	public static void print_path(Puzzle state) {
		while(state!=null) {
			st.add(state);
			state=state.parent;
		}
		while(!st.isEmpty()) {
			Puzzle cur_st=st.pop();
			print_state(cur_st.state);
			System.out.println("Total Cost: "+cur_st.g);
			System.out.println("Total Heuristic Cost: "+cur_st.h);
			
		}
	}
	public static void print_state(int[][] state) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(state[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	public static void expandParent(Puzzle cur_state) {
		int row = 0,column=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(cur_state.state[i][j]==0) {
					row=i;
					column=j;
				}
			}
		}
		if(row-1>=0) {
			moveUp(cur_state);
		}
		if(row+1<=2) {
			moveDown(cur_state);
		}
		if(column-1>=0) {
			moveLeft(cur_state);
		}
		
		if(column+1<=2) {
			moveRight(cur_state);
		}
		
		
	}
	public static int[][] copyArray(int[][] array1,int [][] array2) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				array2[i][j]=array1[i][j];
			}
		}
		return array2;
	}
	public static void moveUp(Puzzle cur_state) {
		int[][] child_state=new int[3][3];
		child_state=copyArray(cur_state.state,child_state);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if((child_state[i][j]==0)) {
					int t=child_state[i][j];
					child_state[i][j]=child_state[i-1][j];
					child_state[i-1][j]=t;
					break;	
						
				}
				
			}
		}
		boolean check;
		try {
			check = Arrays.deepEquals(child_state,cur_state.parent.state);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			check=false;
		}
		
		if(check==false) {
			Puzzle child=new Puzzle(child_state,cur_state);
			child.setG(cur_state.getG()+1);
			child.setH(findHeuristic(child_state, goal_state));
			child.setFn();
//			print_state(child.state);
//			System.out.println(child.fn);
			q.add(child);
		}
		
		
	}
	public static void moveDown(Puzzle cur_state) {
		int[][] child_state=new int[3][3];
		child_state=copyArray(cur_state.state,child_state);		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(child_state[i][j]==0&&i+1<=2) {
					int t=child_state[i][j];
					child_state[i][j]=child_state[i+1][j];
					child_state[i+1][j]=t;
					break;
				}
				
			}
		}
		
		
		boolean check;
		try {
			check = Arrays.deepEquals(child_state,cur_state.parent.state);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			check=false;
		}
		
		if(check==false) {
			Puzzle child=new Puzzle(child_state,cur_state);
			child.setG(cur_state.getG()+1);
			child.setH(findHeuristic(child_state, goal_state));
			child.setFn();
//			print_state(child.state);
//			System.out.println(child.fn);
			q.add(child);
		}
		
	}
	public static void moveLeft(Puzzle cur_state) {
		int[][] child_state=new int[3][3];
		child_state=copyArray(cur_state.state,child_state);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(child_state[i][j]==0) {
					int t=child_state[i][j];
					child_state[i][j]=child_state[i][j-1];
					child_state[i][j-1]=t;
					break;
					
				}
				
			}
		}
		
			boolean check;
			try {
				check = Arrays.deepEquals(child_state,cur_state.parent.state);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				check=false;
			}
			
			if(check==false) {
			Puzzle child=new Puzzle(child_state,cur_state);
			child.setG(cur_state.getG()+1);
			child.setH(findHeuristic(child_state, goal_state));
			child.setFn();
//			print_state(child.state);
//			System.out.println(child.fn);
			q.add(child);
		}

		
		
	}
	public static void moveRight(Puzzle cur_state) {
		int[][] child_state=new int[3][3];
		child_state=copyArray(cur_state.state,child_state);
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(child_state[i][j]==0) {
					int t=child_state[i][j];
					child_state[i][j]=child_state[i][j+1];
					child_state[i][j+1]=t;
					break;	
				}
				
			}
		}
		
		
			boolean check;
			try {
				check = Arrays.deepEquals(child_state,cur_state.parent.state);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				check=false;
			}
			
			if(check==false) {
			Puzzle child=new Puzzle(child_state,cur_state);
			child.setG(cur_state.getG()+1);
			child.setH(findHeuristic(child_state, goal_state));
			child.setFn();
//			print_state(child.state);
//			System.out.println(child.fn);
			q.add(child);
		}
		
	}
	public static int findHeuristic(int[][] state,int[][] goal) {
		int dist=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
					for(int l=0;l<3;l++) {
						if(state[i][j]==goal[k][l]) {
							dist=dist+Math.abs(i-k)+Math.abs(j-l);
							break;
						}
					}
				}
			}
		}
		return dist;
	}
	

}
