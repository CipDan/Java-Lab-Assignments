package Lab12;

import javax.swing.JFrame;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.CENTER;

/**
 * The Swing graphical user interface's frame and the `main` class.
 */
public class MainFrame extends JFrame {

    /**
     * Creates a new frame.
     *
     * @param message the frame's title.
     */
    public MainFrame(String message) {
        super(message);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 700);
    }

    /**
     * The `main` method.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame("Testing");
        DesignPanel designPanel = new DesignPanel(frame);
        ControlPanel controlPanel = new ControlPanel(frame, designPanel);


        frame.add(controlPanel, NORTH);
        frame.add(designPanel, CENTER);
        frame.setVisible(true);//making the frame visible
    }
}
