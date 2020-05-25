package Lab12;

import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * A control panel for the application.
 */
public class ControlPanel extends JPanel {
    private JButton button;
    private JButton saveBtn;
    private JButton loadBtn;
    private final MainFrame frame;
    private JTextField swingComponent;
    private JTextField componentName;

    /**
     * Creates a new instance.
     *
     * @param frame the frame in which the control panel resides.
     */
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        swingComponent = new JTextField(50);
        componentName = new JTextField(50);
        button = new JButton("Click");
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        initialize();
    }

    /**
     * Initializes the created instance.
     */
    private void initialize() {
        add(swingComponent);
        add(componentName);
        add(button);
        add(saveBtn);
        add(loadBtn);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = swingComponent.getText();
                String text = componentName.getText();
                try {
                    Class clazz = Class.forName(name);
                    Class[] signature = new Class[]{String.class};
                    Constructor constructor = clazz.getConstructor(signature);
                    JComponent component = (JComponent) constructor.newInstance(text);
                    component.setFocusable(true);
                    focusListenerHandler(component);
                    mouseListenerHandler(component);
                    frame.getDesignPanel().addSwingComponentToPanel(component);
                } catch (NoSuchMethodException error) {
                    try {
                        Class clazz = Class.forName(name);
                        JComponent component = (JComponent) clazz.newInstance();
                        component.setFocusable(true);
                        focusListenerHandler(component);
                        mouseListenerHandler(component);
                        frame.getDesignPanel().addSwingComponentToPanel(component);
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                        System.out.println(ex.toString());
                    }
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException error) {
                    System.out.println(error.toString());
                }
            }
        });

        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
    }

    /**
     * Handles the action event for the `save` button.
     *
     * @param e an event.
     */
    private void save(ActionEvent e) {
        try (var encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream("src/Lab12/design_panel.xml")))) {
            encoder.writeObject(this.frame.getDesignPanel());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Handles the action event for the `load` button.
     *
     * @param e an event.
     */
    private void load(ActionEvent e) {
        try (var decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream("src/Lab12/design_panel.xml")))) {
            Object obj = decoder.readObject();
            this.frame.updateDesignPanel((DesignPanel) obj);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Handles the `focus` event for the design panel's components.
     *
     * @param component the component that is to have its `focus` event handled.
     */
    public void focusListenerHandler(JComponent component) {
        Border border = component.getBorder();
        component.addFocusListener(new FocusListener() {
            @SneakyThrows
            @Override
            public void focusGained(FocusEvent e) {
                component.setBorder(new LineBorder(Color.RED, 5));
                Class<?> componentClass = component.getClass();
                BeanInfo componentInfo = Introspector.getBeanInfo(componentClass);

                int index = 0;
                DefaultTableModel tableModel = (DefaultTableModel) frame.getTabelPanel().getPropertiesTable().getModel();
                for (PropertyDescriptor propertyDescriptor : componentInfo.getPropertyDescriptors()) {
                    tableModel.setValueAt(String.valueOf(propertyDescriptor.getName()), index, 0);
                    tableModel.setValueAt(String.valueOf(propertyDescriptor.getPropertyType()), index, 1);
                    tableModel.setValueAt(String.valueOf(propertyDescriptor.getShortDescription()), index, 2);
                    index++;
                }
                JTable table = new JTable(tableModel);
                frame.getTabelPanel().setPropertiesTable(table);
            }

            @SneakyThrows
            @Override
            public void focusLost(FocusEvent e) {
                component.setBorder(border);
                DefaultTableModel tableModel = (DefaultTableModel) frame.getTabelPanel().getPropertiesTable().getModel();
                tableModel.setRowCount(0);
                frame.getTabelPanel().resetRowCount();
            }
        });
    }

    /**
     * Handles mouse events for the design panel's components.
     *
     * @param component the component that is to have its mouse event handled.
     */
    public void mouseListenerHandler(JComponent component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JComponent triggerComponent = (JComponent) e.getSource();
                triggerComponent.requestFocusInWindow();
                customizeComponent(component);
            }
        });
    }

    /**
     * Handles customization of a design panel's selected component.
     *
     * @param component the component to be customized.
     */
    public void customizeComponent(JComponent component) {
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        int componentCode = 0;
        int componentWidth = component.getWidth();
        int componentHeight = component.getHeight();
        SpinnerNumberModel spinnerWidth = new SpinnerNumberModel(componentWidth, 20, componentWidth + 30, 1);
        SpinnerNumberModel spinnerHeight = new SpinnerNumberModel(componentHeight, 10, componentHeight + 30, 1);
        JSpinner spinnerW = new JSpinner(spinnerWidth);
        JSpinner spinnerH = new JSpinner(spinnerHeight);
        JTextField textField = new JTextField(20);
        if (component instanceof JLabel || component instanceof JButton) {
            optionPanel.add(new Label("Change text:"));
            optionPanel.add(textField);
            if (component instanceof JLabel)
                componentCode = 1;
            else
                componentCode = 2;
        }
        optionPanel.add(new Label("Set width:"));
        optionPanel.add(spinnerW);
        optionPanel.add(new Label("Set height:"));
        optionPanel.add(spinnerH);
        int option = JOptionPane.showOptionDialog(null, optionPanel, "Customize selected JComponent", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            switch (componentCode) {
                case 1:
                    String newText1 = textField.getText();
                    if (newText1.length() != 0)
                        ((JLabel) component).setText(newText1);
                    break;
                case 2:
                    String newText2 = textField.getText();
                    if (newText2.length() != 0)
                        ((JButton) component).setText(newText2);
                    break;
                default:
                    break;
            }
            int width = (Integer) spinnerW.getValue();
            int height = (Integer) spinnerH.getValue();
            component.setPreferredSize(new Dimension(width, height));
        }
    }
}
