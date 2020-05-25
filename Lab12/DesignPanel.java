package Lab12;

import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * A design panel for generating Swing components.
 */
@NoArgsConstructor
public class DesignPanel extends JPanel implements Serializable {

    private MainFrame frame;
    private int panelWidth;
    private int panelHeight;

    /**
     * Creates a new instance.
     *
     * @param frame       the frame in which the design panel resides.
     * @param panelWidth  the width of the panel.
     * @param panelHeight the height of the panel.
     */
    public DesignPanel(MainFrame frame, int panelWidth, int panelHeight) {
        this.frame = frame;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        initialize();
    }

    /**
     * Initializes the created instance.
     */
    private void initialize() {
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
    }

    /**
     * Adds components to the panel and rebuilds the panel.
     *
     * @param component the component to be added.
     */
    public void addSwingComponentToPanel(JComponent component) {
        add(component);
        revalidate();
        repaint();
    }

    /**
     * Returns the panel's width.
     *
     * @return an <code>int</code>.
     */
    public int getPanelWidth() {
        return panelWidth;
    }

    /**
     * Sets the panel's width.
     *
     * @param panelWidth the new width.
     */
    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    /**
     * Returns the panel's height.
     *
     * @return an <code>int</code>.
     */
    public int getPanelHeight() {
        return panelHeight;
    }

    /**
     * Sets the panel's height.
     *
     * @param panelHeight the new height.
     */
    public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }
}
