import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTests {

    private static Path testDataDir = Paths.get("test-data");

    @ParameterizedTest
    @CsvSource({"01", "02", "03", "04"})
    public void test(String baseFileName) throws Exception {
        PuzzleState initialState = load( testDataDir.resolve(baseFileName + ".in" ) );
        PuzzleState goalState = new PuzzleState(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0} );
        PuzzleSolver solver = new PuzzleSolver(initialState, goalState);
        List<PuzzleState> path = solver.solveBfs();
        List<PuzzleState> expectedPath = loadAnswer( testDataDir.resolve(baseFileName + ".ans") );
        assertEquals(expectedPath, path);
    }

    private PuzzleState load(Path filePath) throws IOException {
        try( Scanner scanner = new Scanner( filePath ) ) {
            int[] squares = new int[16];
            for( int i = 0; i < 16; i++ ) {
                squares[i] = scanner.nextInt();
            }
            return new PuzzleState( squares );
        }
    }

    private List<PuzzleState> loadAnswer( Path path ) throws IOException {
        try( Scanner scanner = new Scanner( path ) ) {
            List<PuzzleState> answer = new ArrayList<>();

            while( scanner.hasNextLine() ) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner( line );
                int[] squares = new int[16];
                for( int i = 0; i < 16; i++ ) {
                    squares[i] = lineScanner.nextInt();
                }
                answer.add( new PuzzleState( squares ) );
            }

            return answer;
        }
    }
}
