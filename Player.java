/*This class represents the player at any given time, it has a position and a traveled path.
 * 
 * 
 */
import java.awt.*;
public class Player {
	String path;
	Point currentPosition;
	
	public Player(){
		path="";
		currentPosition=new Point();
	}
	
	public Player(String path, Point currentPosition){
		this.path=path;
		this.currentPosition=currentPosition;
	}
	
	public void setCurrentPosition(Point currentPosition){
		this.currentPosition=currentPosition;
	}
	
	public Point getCurrentPosition(){
		return currentPosition;
	}
	
	public void setPath(String path){
		this.path=path;
	}
	
	public String getPath(){
		return path;
	}
}
