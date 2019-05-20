import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class PigEvent implements Serializable {
	
	private static final long serialVersionUID = 7287739081412064959L;

	long gameID;
	long playerID;
	int score;
	int opponentScore;
	int turnTotal;
	int rollResult;
	public static final int HOLD = 0;

	public PigEvent(long gameID, long playerID, int score,
			int opponentScore, int turnTotal, int rollResult) {
		this.gameID = gameID;
		this.playerID = playerID;
		this.score = score;
		this.opponentScore = opponentScore;
		this.turnTotal = turnTotal;
		this.rollResult = rollResult;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException 
	{
		out.writeLong(gameID);
		out.writeLong(playerID);
		out.writeInt(score);
		out.writeInt(opponentScore);
		out.writeInt(turnTotal);
		out.writeInt(rollResult);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		gameID = in.readLong();
		playerID = in.readLong();
		score = in.readInt();
		opponentScore = in.readInt();
		turnTotal = in.readInt();
		rollResult = in.readInt();
	}
	
	static private void convert() {
		try {
			Scanner in = new Scanner(new File("PigPlay.txt"));
			long playerID = in.nextLong();
			ArrayList<PigEvent> history = new ArrayList<PigEvent>();
			while (in.hasNext()) {
				history.add(new PigEvent(in.nextLong(), in.nextLong(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
			}
			in.close();
			System.out.print("Saving history ... ");
			try {
				File f = new File("PigPlay.dat");
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeLong(playerID);
				out.writeObject(history);
				out.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				System.exit(5);
			}
			System.out.println("Done.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		convert();
	}

}
