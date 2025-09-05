import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class containing static methods for simulating a game of Hex.
 */
public class HexGame {

    /** Represents the value of a hex cell on the game board. */
    public enum Player { NONE, RED, BLUE }

    // TODO: Add static variables if needed

    /**
     * Simulate a game of Hex based on the moves provided.
     *
     * @param moves the moves
     * @param size the size of the game board
     * @return the winner of the game (NONE means that the game is not over)
     */
    public static Player simulateGame( List<Integer> moves, int size ) {

        // TODO: Implement this method

        return Player.NONE;
    }


    /**
     * Main method for manual testing.  Pass the input file as the command line argument.
     *
     * @param args the input file path must be included as the first element
     */
    public static void main(String[] args) throws IOException {
        List<Integer> moves = new ArrayList<>();
        int n;
        try( Scanner in = new Scanner(new File(args[0])) ) {
            n = in.nextInt();
            while( in.hasNextInt() ) {
                moves.add( in.nextInt() );
            }
        }

        System.out.println( simulateGame(moves, n) );
    }
}
