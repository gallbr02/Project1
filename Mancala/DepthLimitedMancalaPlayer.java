/**
 * TnellerMancalaPlayer - My simple implementation of a mancala
 * player with a simple, messy hack for distributing game-play.
 *
 * @author Todd Neller
 * @version 1.1 */

public class DepthLimitedMancalaPlayer implements MancalaPlayer {

	int depthLimit = 1;

	/**
	 * @param depthLimit depth limit for search
	 */
	public DepthLimitedMancalaPlayer(int depthLimit) {
		super();
		this.depthLimit = depthLimit;
	}


	/**
	 * Choose a move for the given game situation given play time
	 * remaining.  */
	public int chooseMove(MancalaNode node, long timeRemaining) {
		MinimaxSearcher searcher 
		= new MinimaxSearcher(depthLimit);

		// Create a new copy of the input node in my own node
		// type (with my own evaluation function)
		ScoreDiffMancalaNode searchNode 
		= new ScoreDiffMancalaNode(node);

		searcher.eval(searchNode);
		return searcher.getBestMove();
	}


	/**
	 * Returns the number of pieces not yet captured.
	 * @return int - uncaptured pieces
	 * @param node MancalaNode - node to check
	 */
	public int piecesRemaining(MancalaNode node) {
		int pieces = 0;
		for (int i = 0; i < 6; i++) pieces += node.state[i];
		for (int i = 7; i < 13; i++) pieces += node.state[i];
		return pieces;
	}

}
