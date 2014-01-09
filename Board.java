import java.util.*;
import java.awt.*;
public class Board {
	ArrayList<Point> boxes;
	ArrayList<Point> goals;
	Player currentPlayer;
	Point maximumReach;
	
	public Board() {
		currentPlayer=new Player();
		boxes=new ArrayList<Point>();
		maximumReach=new Point();
	}
	
	public Board(ArrayList<Point> box,ArrayList<Point> goals,Point maxReach,Player currentPlayer) {
		boxes=box;
		this.goals=goals;
		maximumReach=maxReach;
		this.currentPlayer=currentPlayer;
	}
	
	public String returnPath(){
		return currentPlayer.getPath();
	}
}