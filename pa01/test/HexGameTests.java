import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexGameTests {

    @ParameterizedTest
    @CsvSource({
            "moves01.txt, BLUE",
            "moves02.txt, RED",
            "moves03.txt, NONE",
            "moves04.txt, BLUE",
            "moves05.txt, BLUE",
            "moves06.txt, RED",
            "moves07.txt, NONE"
    })
    public void testHexGame(String inputFile, HexGame.Player winner ) throws Exception {
        int n;
        List<Integer> moves = new ArrayList<>();
        Path path = Path.of( "input", inputFile );
        try( Scanner scan = new Scanner(new File(path.toString())) ) {
            n = scan.nextInt();
            while( scan.hasNextInt() ) {
                moves.add( scan.nextInt() );
            }
        }
        assertEquals(winner, HexGame.simulateGame(moves, n));
    }
}
