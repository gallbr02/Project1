
import java.util.Stack;
/**
 * mouse.java
 * @author Brandon Gallagher
 * representation of a stack
 */
public class Mouse {
	
	private Maze myMaze;
	private Stack<Position> myStack;
	private Position curr;
	private char next[] = {'n', 'e', 's', 'w'};

	public Mouse(Maze maze) {
		myMaze = maze;
		Position mouse =  new Position(1, 0);
		curr = mouse;
		myStack = new Stack<Position>();
	}

	/**
	 * returns position of mouse
	 * @return the current position of mouse
	 */
	public Position getPosition() {
		return curr;
	}

	/**
	 * Returns true is the mouse is at the exit location
	 * @return true if the current position of the mouse is at the exit location
	 */
	public boolean isOut() {
		Position finish = new Position (myMaze.getRows()-2, myMaze.getCols()-1);
		if(curr.equals(finish)) {
			myStack.push(curr);
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * returns the stack of valid Positions the mouse found as path
	 * @return the stack of valid Positions the mouse found as path
	 */
	public Stack<Position> getStack(){
		return myStack;
	}

	/**
	 * moves the mouse once using an algorithm
	 * Check if the mouse can move in any four directions and mark its current position
	 * return false if their is no position open or the stack is empty
	 * mark the current position if the stack is not empty but no adjacent position is open
	 * @return true if you have reached the exit position
	 */
	public boolean makeMove() {
		for(int i = 0; i < next.length; i++) {
			if(myMaze.isOpen(curr, next[i])) {
				myMaze.mark(curr, 3);
				myStack.push(curr);
				curr = curr.getAdjacent(next[i]);
				return true;
			} 	
		}
		if(myStack.empty()) {
			return false;
		}else {
			myMaze.mark(curr, 2);
			curr = myStack.pop();
			return true;
		}

	}

}
