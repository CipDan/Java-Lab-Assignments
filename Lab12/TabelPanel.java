package Lab12;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

/**
 * A panel for the table of properties for a JComponent.
 */
public class TabelPanel extends JPanel {

    private JTable propertiesTable;

    /**
     * Creates a new instance.
     */
    public TabelPanel() {
        initialize();
    }

    /**
     * Initializes the created instance.
     */
    private void initialize() {
        propertiesTable = new JTable(new DefaultTableModel(new String[]{"Name", "Type", "Description"}, 91));
        setLayout(new BorderLayout());
        propertiesTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(propertiesTable);
        add(scrollPane, CENTER);
    }

    /**
     * Returns the panel's properties table.
     *
     * @return a <code>JTable</code>.
     */
    public JTable getPropertiesTable() {
        return propertiesTable;
    }

    /**
     * Sets the panel's properties table.
     *
     * @param propertiesTable the new properties table.
     */
    public void setPropertiesTable(JTable propertiesTable) {
        this.propertiesTable = propertiesTable;
    }

    /**
     * Resets the panel's properties table's row count.
     */
    public void resetRowCount() {
        DefaultTableModel model = (DefaultTableModel) propertiesTable.getModel();
        model.setRowCount(91);
    }
}
