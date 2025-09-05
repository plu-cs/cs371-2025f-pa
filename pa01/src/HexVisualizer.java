import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * A simple application for visualizing an input file.  Pass the input file name as the command
 * line argument.
 */
public class HexVisualizer {

    private Set<Integer> blueCells, redCells;
    private int n;
    private int margin;
    private Color redColor = new Color(248, 50, 50);
    private Color blueColor = new Color(100, 100, 255);

    public HexVisualizer(List<Integer> moves, int n) {
        this.n = n;
        margin = 10;
        blueCells = new HashSet<>();
        redCells = new HashSet<>();
        for( int i = 0 ; i < moves.size() ; i++ ) {
            int cell = moves.get(i);
            if( i % 2 == 0 ) redCells.add(cell);
            else blueCells.add(cell);
        }
        buildGui();
    }

    private class Hex {
        Path2D.Float path;
        float radius = 20;
        public Hex() {
            path = new Path2D.Float();
        }

        public void draw(Graphics2D g, float x, float y, Color fillColor) {
            float stoneRadius = this.radius * 0.65f;
            path.reset();
            path.moveTo(0, 0);
            float angle = (float)(Math.PI / 6.0f);
            path.moveTo( radius * Math.cos(angle) + x, radius * Math.sin(angle) + y );
            angle = (float)( Math.PI / 2.0f );
            for( int i = 0; i < 5; i++ ) {
                path.lineTo( radius * Math.cos(angle) + x, radius * Math.sin(angle) + y );
                angle += (float)(Math.PI / 3.0f);
            }
            path.closePath();

            if( fillColor != null ) {
                g.setColor(fillColor);
                Ellipse2D.Float ellipse = new Ellipse2D.Float(
                        x - stoneRadius, y - stoneRadius, stoneRadius * 2, stoneRadius * 2
                );
                g.fill(ellipse);
            }
            g.setColor(Color.BLACK);
            g.draw(path);
        }

        public float getWidth() {
            return 2.0f * radius * (float)Math.cos(Math.PI / 6.0f);
        }
    }

    private class GraphicsPanel extends JPanel {
        Hex hex;
        private float yStep;

        public GraphicsPanel() {
            hex = new Hex();
            yStep = (float)( 3.0f * Math.sin(Math.PI / 6.0f) * hex.radius );
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2.5f));

            for(int row = 0; row < n; row++) {
                for( int col = 0; col < n; col++) {
                    int cell = row * n + col;
                    if( redCells.contains(cell) ) {
                        drawHex(g2d, row, col, redColor);
                    } else if( blueCells.contains(cell) ) {
                        drawHex(g2d, row, col, blueColor);
                    } else {
                        drawHex(g2d, row, col, null);
                    }
                }
            }
        }

        private void drawHex(Graphics2D g, int row, int col, Color color) {
            float w = hex.getWidth();
            float w2 = w / 2.0f;
            float x = w2 * (row + 1) + margin + (col * w);
            float y = margin + hex.radius + yStep * row;
            hex.draw(g, x, y, color);
        }

    }

    private void buildGui() {
        JFrame frame = new JFrame("Hex Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new GraphicsPanel();
        frame.add(panel);

        panel.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        List<Integer> moves = new ArrayList<>();
        int n;
        try( Scanner in = new Scanner(new File(args[0])) ) {
            n = in.nextInt();
            while( in.hasNextInt() ) {
                moves.add( in.nextInt() );
            }
        }

        new HexVisualizer(moves, n);
    }
}
