/** MancalaNode - a mancala game node */

import java.util.ArrayList;

public abstract class MancalaNode extends GameNode
{
	static int MAX_SCORE_PIT = 6, MIN_SCORE_PIT = 13, PLAY_PITS = 6, TOTAL_PITS = PLAY_PITS * 2 + 2, 
			INIT_SEEDS_PER_PIT = 4, TOTAL_SEEDS = INIT_SEEDS_PER_PIT * PLAY_PITS * 2;

	/** 
<pre> 
How to interpret the Mancala state variable:

Let the mancala pits be notated thus:

  f e d c b a
g             G
  A B C D E F

where 
A-F are the first player's (MAX's) pits,
G is the first player's (MAX's) scoring pit,
a-f are the second player's (MIN's) pits, and 
g is the second player's (MIN's) scoring pit.

The numbers of pieces in each pit are stored in an array asa follows:
state[0] ... state[6] store the number of pieces in A ... G.
state[7] ... state[13] store the number of pieces in a ... g.

Each player's goal is to end the game with more pieces in one's own
scoring pit.  Thus a simple measure of utility would be (G - g).
</pre> */
	public int[] state = new int[14];

	public MancalaNode() {
		this(INIT_SEEDS_PER_PIT);
	}

	public MancalaNode(int initSeedsPerPit)
	{
		// Set the initial game state.
		INIT_SEEDS_PER_PIT = initSeedsPerPit;
		TOTAL_SEEDS = initSeedsPerPit * PLAY_PITS * 2;
		// See general class comments to interpret Mancala state
		state = new int[TOTAL_PITS];
		// four pieces initially in each pit...
		for (int i = 0; i < TOTAL_PITS; i++) 
			state[i] = INIT_SEEDS_PER_PIT;
		// ...except scoring pits.
		state[MAX_SCORE_PIT] = state[MIN_SCORE_PIT] = 0;
		// First player is MAX by default.
		player = MAX;
	}

	public MancalaNode(MancalaNode node) {
		this.state = (int[]) node.state.clone();
		this.player = node.player;
		this.prevMove = node.prevMove;
		this.parent = node.parent;
	}


	/**
	 * <code>clone</code> - return a deep clone of the
	 * MancalaNode.
	 *
	 * @return an <code>Object</code> value - a deep clone of the
	 * MancalaNode.*/
	public Object clone() {
		MancalaNode newNode = (MancalaNode) super.clone();
		newNode.state = (int[]) state.clone();
		return newNode;
	}        

	/**
	 * <code>gameOver</code> - return true if no pieces left in
	 * play pits.
	 *
	 * @return a <code>boolean</code> value */
	public boolean gameOver() {
		boolean noPiecesLeft = true;
		for (int position = MAX_SCORE_PIT - PLAY_PITS; noPiecesLeft && (position < MAX_SCORE_PIT); position++)
			noPiecesLeft = noPiecesLeft && (state[position] == 0);
		for (int position = MIN_SCORE_PIT - PLAY_PITS; noPiecesLeft && (position < MIN_SCORE_PIT); position++)
			noPiecesLeft = noPiecesLeft && (state[position] == 0);
		return noPiecesLeft;
	}


	public ArrayList<GameNode> expand() {
		ArrayList<GameNode> children = new ArrayList<GameNode>();
		for (int move : getLegalMoves()) {
			MancalaNode child = (MancalaNode) childClone();
			child.makeMove(move);
			children.add(child);
		}
		return children;
	}

	public ArrayList<Integer> getLegalMoves() 
	{
		ArrayList<Integer> legalMoves = new ArrayList<Integer>();
		final int PLAYER_OFFSET = (player == MAX) ? 0 : MAX_SCORE_PIT + 1;
		for (int i = PLAYER_OFFSET; i < PLAYER_OFFSET + PLAY_PITS; i++)
			if (state[i] > 0)
				legalMoves.add(i);
		return legalMoves;
	}


	/**
	 * Make the designated move, redistributing pieces from the indicated position and
	 * updating player accordingly.
	 * Creation date: (10/6/00 1:51:59 PM)
	 * @param move int
	 */
	public void makeMove(int move) {
		int position = move, scorePit, oppositePit;

		prevMove = move;

		// Take the pieces from the indicated pit.
		int pieces = state[position];
		state[position] = 0;

		// Redistribute them around the pits, skipping the opponent's scoring pit.
		while (pieces > 0) {
			position = (position + 1) % TOTAL_PITS;

			// Skip over opponent's scoring pit
			if (position == ((player == MAX) ? MIN_SCORE_PIT : MAX_SCORE_PIT))
				continue;

			// Distribute piece
			state[position] ++;
			pieces--;
		}

		// If the last piece distributed landed in an empty pit on
		// one's side opposite a non-empty pit, capture both the last
		// piece and the pieces opposite.
		scorePit = (player == MAX) ? MAX_SCORE_PIT : MIN_SCORE_PIT;
		// if last piece distributed in empty pit on own side
		if (state[position] == 1 && (scorePit - position) > 0 
				&& (scorePit - position <= PLAY_PITS)) {
			oppositePit = MIN_SCORE_PIT - position - 1;
			if (state[oppositePit] > 0) { // not in score pit and
				// opposite pit not empty
				// capture own pit
				state[scorePit] ++;
				state[position] --;
				// capture opposite pit
				state[scorePit] += state[oppositePit];
				state[oppositePit] = 0;
			}
		}

		// If the last piece did not land in one's scoring pit, then
		// the player changes.
		if (position != scorePit)
			player = (player == MAX) ? MIN : MAX;

		// Check for starvation.  If next player has no moves (is
		// starved), t1eir opponent scores all remaining pieces.
		boolean starved = true; // assume next player starved
		scorePit = (player == MAX) ? MAX_SCORE_PIT : MIN_SCORE_PIT; // set scoring pit of next player
		for (position = scorePit - PLAY_PITS; starved && position < scorePit; position++)
			if (state[position] > 0)
				starved = false; // flag starved false if any next player pits contain anything
		if (starved) { // if next player starved, then opponent scores all remaining pieces.
			scorePit = (scorePit + PLAY_PITS + 1) % TOTAL_PITS;
			for (position = scorePit - PLAY_PITS; starved && position < scorePit; position++) {
				state[scorePit] += state[position];
				state[position] = 0;
			}
		}
	}

	/**
	 * Translates move integer to a String.
	 * Creation date: (10/11/00 8:50:12 PM)
	 * @return java.lang.String
	 */
	public static String moveToString(int move) {
		final String[] moveString = { "A", "B", "C", "D", "E", "F", "INVALID MOVE", "a", "b", "c", "d", "e", "f" };
		if ((move < 0) && (move >= MIN_SCORE_PIT)) move = MAX_SCORE_PIT;
		return moveString[move];
	}

	/**
	 * String representation of current game state.
	 * Example (initial state):
	 *      f  e  d  c  b  a
	 * -------------------------
	 * |  | 4| 4| 4| 4| 4| 4|  |
	 * | 0|-----------------| 0|
	 * |  | 4| 4| 4| 4| 4| 4|  | <--
	 * -------------------------
	 *      A  B  C  D  E  F
	 *
	 * Creation date: (10/7/00 4:03:47 PM)
	 * @return java.lang.String
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("     f  e  d  c  b  a\n-------------------------\n|  ");
		for (int i = MIN_SCORE_PIT - 1; i > MAX_SCORE_PIT; i--)
			if (state[i] > 9)
				sb.append("|" + state[i]);
			else
				sb.append("| " + state[i]);
		sb.append("|  |");
		if (player == MIN)
			sb.append(" <--");
		if (state[MIN_SCORE_PIT] > 9)
			sb.append("\n|" + state[MIN_SCORE_PIT]);
		else
			sb.append("\n| " + state[MIN_SCORE_PIT]);
		sb.append("|-----------------|");
		if (state[MAX_SCORE_PIT] > 9)
			sb.append(state[MAX_SCORE_PIT]);
		else
			sb.append(" " + state[MAX_SCORE_PIT]);
		sb.append("|\n|  ");
		for (int i = 0; i < MAX_SCORE_PIT; i++)
			if (state[i] > 9)
				sb.append("|" + state[i]);
			else
				sb.append("| " + state[i]);
		sb.append("|  |");
		if (player == MAX)
			sb.append(" <--");
		sb.append("\n-------------------------\n     A  B  C  D  E  F\n");
		return sb.toString();
	}


	/**
	 * Return an estimation of game node utility, unless game is
	 * over.  If game is over, return actual utility.
	 *
	 * NOTE: In your implementation, you must create your own
	 * subclass of MancalaNode (e.g. UserIDMancalaNode extends
	 * MancalaNode) and implement this utility method (inheriting
	 * all others).  This can be confusing if you don't know Java
	 * well, so see the example I've provided with
	 * ScoreDiffMancalaNode.
	 *
	 * Example implementation:
	 * public double utility() {
	 *   return state[MAX_SCORE_PIT]-state[MIN_SCORE_PIT];
	 * }
	 *
	 * Creation date: (10/6/00 1:22:26 PM)
	 * @return double */
	public abstract double utility();
}


