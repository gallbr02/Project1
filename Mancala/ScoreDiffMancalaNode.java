// Written by Todd W. Neller.  Copyright (c) 2000 Gettysburg College.
// You may distribute this source code for non-commercial purposes
// only.  You may study, modify, and use this source code for any
// purpose, as long as this notice is retained.  Note that this
// example is provided "as is", WITHOUT WARRANTY of any kind either
// expressed or implied.


/**
 * My simple extension of MancalaNode with a simple utility evaluation.
 * Creation date: (10/6/00 3:26:32 PM)
 * @author: Todd Neller
 */
public class ScoreDiffMancalaNode extends MancalaNode {


	/**
	 * ScoreDiffMancalaNode constructor.
	 */
	public ScoreDiffMancalaNode() {
		super();
	}

	public ScoreDiffMancalaNode(int initSeedsPerHole) {
		super(initSeedsPerHole);
	}

	public ScoreDiffMancalaNode(MancalaNode node) {
		super(node);
	}

	/**
	 * My simple utility method returns different in MAX/MIN score.
	 */
	public double utility() {
		return state[MAX_SCORE_PIT]-state[MIN_SCORE_PIT];
	}

}
