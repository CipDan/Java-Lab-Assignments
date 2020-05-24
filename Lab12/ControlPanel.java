package Lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        /*Class beanClass = DesignPanel.class;
        BeanInfo info = null;
        try {
            info = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if (info != null) {
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                System.out.println(pd.getName());
            }
        }*/
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
                    frame.getDesignPanel().addSwingComponentToPanel((Component) constructor.newInstance(text));
                } catch (ClassNotFoundException error) {
                    error.printStackTrace();
                } catch (NoSuchMethodException error) {
                    try {
                        Class clazz = Class.forName(name);
                        frame.getDesignPanel().addSwingComponentToPanel((Component) clazz.newInstance());
                    } catch (ClassNotFoundException err) {
                        error.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InstantiationException ex) {
                        ex.printStackTrace();
                    }
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
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
            System.out.println(obj);
            this.frame.updateDesignPanel((DesignPanel) obj);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
}
