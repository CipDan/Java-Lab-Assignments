package Lab12;

import lombok.Getter;

import javax.swing.JFrame;

import java.io.Serializable;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.CENTER;

/**
 * The Swing graphical user interface's frame and the `main` class.
 */
@Getter
public class MainFrame extends JFrame {

    private ControlPanel controlPanel;
    private DesignPanel designPanel;

    /**
     * Creates a new instance.
     */
    public MainFrame() {
        super("Dynamic Swing Designer");
        setSize(1400, 700);
        initialize();
    }

    /**
     * Initializes the created instance.
     */
    private void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializePanels();
        addElements();
        pack();
    }

    /**
     * Creates the frame's panels.
     */
    private void initializePanels() {
        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this, 1200, 500);
    }

    /**
     * Adds elements to the frame.
     */
    private void addElements() {
        add(controlPanel, NORTH);
        add(designPanel, CENTER);
    }

    /**
     * Updates the frame's <code>DesignPanel</code>.
     *
     * @param designPanel the dynamic panel.
     */
    public void updateDesignPanel(DesignPanel designPanel) {
        remove(this.designPanel);
        this.designPanel = designPanel;
        add(designPanel, CENTER);
        pack();
    }

    /**
     * The `main` method.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
