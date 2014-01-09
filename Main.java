import java.io.*;
import java.util.*;
import java.awt.Point;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Vector<String> board = new Vector<String>();
		String switcharoo;
		
		char[] switchChars;
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Board tempCheck;
		String movedToBox;

		//my Variables
		int cols=0;
		
		/*
		 * I need these to save the original state's goals and  blocks.
		 */
		Vector<Point> boxes= new Vector<Point>();
		Vector<Point> goals= new Vector<Point>();
		//this is just the basic search, I will add the blocks, goals and initial position
		String line;
		while(br.ready()) {
			line = br.readLine();
			board.add(line);
		} // End while
		// get where is the starting point, goals and 
		for(int i=0;i<board.size();i++){
			cols=board.get(i).length();
			for(int j=0;j<cols;j++){
				switch(board.get(i).charAt(j)){
				case '@':
					switcharoo =board.get(i);
					switchChars = switcharoo.toCharArray();
					switchChars[j] = ' ';
					switcharoo = String.valueOf(switchChars);
					board.setElementAt(switcharoo, i);
					initial=new Node(j,i,"");
					break;
				case '+':
					switcharoo =board.get(i);
					switchChars = switcharoo.toCharArray();
					switchChars[j] = ' ';
					switcharoo = String.valueOf(switchChars);
					board.setElementAt(switcharoo, i);
					goals.add(new Node(j,i,""));
					initial=new Node(j,i,"");
					break;
				case '.':
					switcharoo =board.get(i);
					switchChars = switcharoo.toCharArray();
					switchChars[j] = ' ';
					switcharoo = String.valueOf(switchChars);
					board.setElementAt(switcharoo, i);
					goals.add(new Node(j,i,""));
					break;
				case '$':
					switcharoo =board.get(i);
					switchChars = switcharoo.toCharArray();
					switchChars[j] = ' ';
					switcharoo = String.valueOf(switchChars);
					board.setElementAt(switcharoo, i);
					box.add(new Node(j,i,""));
					break;
				case '*':
					switcharoo =board.get(i);
					switchChars = switcharoo.toCharArray();
					switchChars[j] = ' ';
					switcharoo = String.valueOf(switchChars);
					board.setElementAt(switcharoo, i);
					goals.add(new Node(j,i,""));
					box.add(new Node(j,i,""));
					break;
				}
			}
		}
		//
		//System.out.println("These are the boxes");
		//for(int i=0;i<box.size();i++){
		//	System.out.println(box.get(i).posx+" "+box.get(i).posy);
		//}
		//System.out.println("These are the goals");
		//for(int i=0;i<goals.size();i++){
		//	System.out.println(goals.get(i).posx+" "+goals.get(i).posy);
		//}
		
		
		reachableStates=reachable(initial,board,box);
		//System.out.println("the reachable positions are");
		//for(int i=0;i<reachableStates.size();i++){
		//	System.out.println(reachableStates.get(i).nodePosition());
		//}
		//this is the board
			Queue<Board> openStates= new LinkedList<Board>();
			LinkedList<Board> visitedStates =new LinkedList<Board>();
			
			openStates.add(new Board(box,maxReach(reachableStates),initial));
			while((!openStates.isEmpty())&&(isEndGame==false)){
				Board current=openStates.poll();
				if (isNewBoard(current, visitedStates)){
					visitedStates.add(current);
					if(isEndGame(current.box,goals)){
						isEndGame=true;
						for (int i=0;i<current.box.size();i++)
						results=current.lastPos.path;
						
					}
					else{
						for(int i=0;i<current.box.size();i++){			
							reachableStates=reachable(current.lastPos,board,current.box);
							//check box to push up first;	
							Position movingBoxUp=new Position (current.box.get(i).posx,current.box.get(i).posy-1);
							Position playerPushUp = new Position(current.box.get(i).posx,current.box.get(i).posy+1);
							if(isUp(movingBoxUp.posx,movingBoxUp.posy,board,current.box)){
								movedToBox=isPositionReachable(playerPushUp, reachableStates);
								if (movedToBox==null){
									
									}
								else{
									//the box is indeed movable in this direction; making the board was a bad idea...
									tempBox=newBoxes(current.box,i,movingBoxUp,current.box.get(i).path+"U");
									tempNode=new Node(current.box.get(i).posx,current.box.get(i).posy,movedToBox+"U");
									tempCheck=new Board(tempBox,maxReach(reachable(tempNode,board,tempBox)),tempNode);
									if (isNewBoard(tempCheck,visitedStates)){
										openStates.add(tempCheck);
									}
								}
							
							}
						
							//check box to push down;	
							Position movingBoxDown=new Position (current.box.get(i).posx,current.box.get(i).posy+1);
							Position playerPushDown = new Position(current.box.get(i).posx,current.box.get(i).posy-1);
							if(isDown(movingBoxDown.posx,movingBoxDown.posy,board,current.box)){
								movedToBox=isPositionReachable(playerPushDown, reachableStates);
								if (movedToBox==null){
									
									}
								else{
									//the box is indeed movable in this direction; making the board was a bad idea...
									tempBox=newBoxes(current.box,i,movingBoxDown,current.box.get(i).path+"D");
									tempNode=new Node(current.box.get(i).posx,current.box.get(i).posy,movedToBox+"D");
									tempCheck=new Board(tempBox,maxReach(reachable(tempNode,board,tempBox)),tempNode);
									if (isNewBoard(tempCheck,visitedStates)){
										openStates.add(tempCheck);
									}
								}
							
							}
						
							//check box to push left;	
							Position movingBoxLeft=new Position (current.box.get(i).posx-1,current.box.get(i).posy);
							Position playerPushLeft = new Position(current.box.get(i).posx+1,current.box.get(i).posy);
							if(isLeft(movingBoxLeft.posx,movingBoxLeft.posy,board,current.box)){
								movedToBox=isPositionReachable(playerPushLeft, reachableStates);
								if (movedToBox==null){
									
									}
								else{
									//the box is indeed movable in this direction; making the board was a bad idea...
									tempBox=newBoxes(current.box,i,movingBoxLeft,current.box.get(i).path+"L");
									tempNode=new Node(current.box.get(i).posx,current.box.get(i).posy,movedToBox+"L");
									tempCheck=new Board(tempBox,maxReach(reachable(tempNode,board,tempBox)),tempNode);
									if (isNewBoard(tempCheck,visitedStates)){
										openStates.add(tempCheck);
									}
								}
							
							}
							
							//check box to push right;	
							Position movingBoxRight=new Position (current.box.get(i).posx+1,current.box.get(i).posy);
							Position playerPushRight = new Position(current.box.get(i).posx-1,current.box.get(i).posy);
							if(isRight(movingBoxRight.posx,movingBoxRight.posy,board,current.box)){
								movedToBox=isPositionReachable(playerPushRight, reachableStates);
								if (movedToBox==null){
									
									}
								else{
									//the box is indeed movable in this direction; making the board was a bad idea...
									tempBox=newBoxes(current.box,i,movingBoxRight,current.box.get(i).path+"R");
									tempNode=new Node(current.box.get(i).posx,current.box.get(i).posy,movedToBox+"R");
									tempCheck=new Board(tempBox,maxReach(reachable(tempNode,board,tempBox)),tempNode);
									if (isNewBoard(tempCheck,visitedStates)){
										openStates.add(tempCheck);
									}
								}
							
							}
							
						}//end of for loops
					}
				}
			}
			System.out.println(results);
		} //end of Main
		
	
	//System.out.println(initial.posx+" "+initial.posy);//used for debugging, will get rid of it eventually.
		
		public static LinkedList<Node> reachable(Node initPos,Vector<String> board,LinkedList<Node>boxes){
			Queue<Node> openStates= new LinkedList<Node>();
			LinkedList<Node> visitedStates =new LinkedList<Node>();
			openStates.add(new Node(initPos.posx,initPos.posy,initPos.path));
			while(!openStates.isEmpty()){
				Node current=openStates.poll();
				if (isNewState(current, visitedStates)){
					visitedStates.add(current);
						
					//check up first;			
						Position movingUp=new Position (current.posx,current.posy-1);
						if(isUp(movingUp.posx,movingUp.posy,board,boxes)){
							if (isNewState(movingUp, visitedStates)){
								openStates.add(new Node(movingUp.posx,movingUp.posy,current.path+"U"));
							}
						}
					//then check down;
						Position movingDown=new Position (current.posx,current.posy+1);
						if(isDown(movingDown.posx,movingDown.posy,board,boxes)){
							if (isNewState(movingDown, visitedStates)){
								openStates.add(new Node(movingDown.posx,movingDown.posy,current.path+"D"));
							}
						}
					//then check left;
						Position movingLeft=new Position (current.posx-1,current.posy);
						if(isLeft(movingLeft.posx,movingLeft.posy,board,boxes)){
							if (isNewState(movingLeft, visitedStates)){
								openStates.add(new Node(movingLeft.posx,movingLeft.posy,current.path+"L"));
							}
						}
					//finally check right
						Position movingRight=new Position (current.posx+1,current.posy);
						if(isRight(movingRight.posx,movingRight.posy,board,boxes)){
							if (isNewState(movingRight, visitedStates)){
								openStates.add(new Node(movingRight.posx,movingRight.posy,current.path+"R"));
							}
						}
				}
			}
			return visitedStates;
		} 
		
		public static Boolean isNewState(Position a, LinkedList<Node> visitedStates){
			Boolean isThisNew=true;
			for(int i=visitedStates.size();i>0;){
				i--;
				if ((a.posx==visitedStates.get(i).posx)&&(a.posy==visitedStates.get(i).posy)){
					isThisNew=false;
					break;
				}
			}
			return isThisNew;
		}
		
		public static String isPositionReachable(Position a, LinkedList<Node> visitedStates){
			 String isThisNew=null;
			for(int i=visitedStates.size();i>0;){
				i--;
				if ((a.posx==visitedStates.get(i).posx)&&(a.posy==visitedStates.get(i).posy)){
					isThisNew=visitedStates.get(i).path;
					break;
				}
			}
			return isThisNew;
		}
		
		
		public static Boolean isNewBoard(Board a, LinkedList<Board> visitedStates){
			if(visitedStates.size()==0){
				return true;
			}
			Boolean isThisNew=true;
			int cont;
			for(int i=0;i<visitedStates.size();i++){
				cont=0;
				for(int j=0;j<visitedStates.get(i).box.size();j++){
					for(int k=0;k<a.box.size();k++){
						if ((a.box.get(k).posx==visitedStates.get(i).box.get(j).posx)&&(a.box.get(k).posy==visitedStates.get(i).box.get(j).posy)){
							cont++;
							break;
						}
					}
				}
				if((cont==a.box.size())&&(a.maxReach.posx==visitedStates.get(i).maxReach.posx)&&(a.maxReach.posy==visitedStates.get(i).maxReach.posy)){
					isThisNew=false;
					break;
				}
			}
			return isThisNew;
		}

		
		
		//check up
		public static Boolean isUp(int posx, int posy,Vector<String> board,LinkedList<Node> boxes){
				if((posy)<0){
					return false;
				}
				else{
					if(posx>=board.get(posy).length()){
						return false;
					}
					else{
						if((board.get(posy).charAt(posx)==' ')){
							boolean flag=true;
							for(int i=0;i<boxes.size();i++){
								if ((boxes.get(i).posx==posx)&&(boxes.get(i).posy==posy)){
									flag=false;
									break;
								}
							}
							return flag;
						}
						else{
								return false;
						}
					}
				}
		}// end of isUp
		
		public static Node maxReach(LinkedList<Node> allPositions){
			Node max=new Node(allPositions.getFirst().posx,allPositions.getFirst().posy,allPositions.getFirst().path);
			for (int i=1;i<allPositions.size();i++){
				if((allPositions.get(i).posy<=max.posy)&&(allPositions.get(i).posx<=max.posx)){
					max=new Node(allPositions.get(i).posx,allPositions.get(i).posy,allPositions.get(i).path);
				}
			}
			return max;
	}// end of isUp
		
		//check down
		public static Boolean isDown(int posx, int posy,Vector<String> board, LinkedList<Node> boxes){
				if(posy>=board.size()){
					return false;
				}
				else{
					if(posx>=board.get(posy).length()){
						return false;
					}
					else{
						if((board.get(posy).charAt(posx)==' ')){
							boolean flag=true;
							for(int i=0;i<boxes.size();i++){
								if ((boxes.get(i).posx==posx)&&(boxes.get(i).posy==posy)){
									flag=false;
									break;
								}
							}
							return flag;
						}
						else{
								return false;
						}
					}
				}
		}// end of isDown
		
		//check left
		public static Boolean isLeft(int posx, int posy,Vector<String> board, LinkedList<Node> boxes){
				if((posx)<0){
					return false;
				}
				else{
					if((board.get(posy).charAt(posx)==' ')){
						boolean flag=true;
						for(int i=0;i<boxes.size();i++){
							if ((boxes.get(i).posx==posx)&&(boxes.get(i).posy==posy)){
								flag=false;
								break;
							}
						}
						return flag;
					}
					else{
							return false;
					}
						
				}
		}//end is left
		
		public static Boolean isRight(int posx, int posy,Vector<String> board, LinkedList<Node> boxes){
				if((posx)>=board.get(posy).length()){
					return false;
				}
				else{
					if((board.get(posy).charAt(posx)==' ')){
						boolean flag=true;
						for(int i=0;i<boxes.size();i++){
							if ((boxes.get(i).posx==posx)&&(boxes.get(i).posy==posy)){
								flag=false;
								break;
							}
						}
						return flag;
					}
					else{
							return false;
					}
				}
		}//end is Right
		
		public static Boolean isEndGame(LinkedList<Node> boxes,LinkedList<Node> goals){
			int cont=0;
			for (int i=0;i<goals.size();i++){
				for (int j=0;j<boxes.size();j++){
					if ((boxes.get(j).posx==goals.get(i).posx)&&(boxes.get(j).posy==goals.get(i).posy)){
					cont++;
					break;
					}
				}	
			}
			if (cont==goals.size()){
				return true;
			}
			else{
				return false;
			}
	}//end of isEndGame
	
	
		public static LinkedList<Node> newBoxes(LinkedList<Node> boxes,int index, Position newRockPos,String newRockPath){
			LinkedList<Node> returnList=new LinkedList<Node>();
			for (int i=0;i<boxes.size();i++){
				if(index==i){
					returnList.add(new Node(newRockPos.posx,newRockPos.posy,newRockPath));
				}
				else{
					returnList.add(new Node(boxes.get(i).posx,boxes.get(i).posy,boxes.get(i).path));
				}
				
			}
			return returnList;
	}//end of isEndGame
	
	
} // End Main