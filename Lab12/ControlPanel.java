package Lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * A control panel for the application.
 */
public class ControlPanel extends JPanel {
    private DesignPanel designPanel;
    private JButton button;
    private final MainFrame frame;
    private JTextField swingComponent;
    private JTextField componentName;

    /**
     * Creates and configures a control panel.
     *
     * @param frame       the frame in which the control panel resides.
     * @param designPanel a design panel.
     */
    public ControlPanel(MainFrame frame, DesignPanel designPanel) {
        this.frame = frame;
        this.designPanel = designPanel;
        swingComponent = new JTextField(50);
        componentName = new JTextField(50);
        button = new JButton("Click");

        add(swingComponent);
        add(componentName);
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = swingComponent.getText();
                String text = componentName.getText();
                try {
                    Class clazz = Class.forName(name);
                    Class[] signature = new Class[]{String.class};
                    Constructor constructor = clazz.getConstructor(signature);
                    designPanel.add((Component) constructor.newInstance(text));
                    frame.setVisible(true);
                } catch (ClassNotFoundException error) {
                    error.printStackTrace();
                } catch (NoSuchMethodException error) {
                    try {
                        Class clazz = Class.forName(name);
                        designPanel.add((Component) clazz.newInstance());
                        frame.setVisible(true);
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

        Class beanClass = DesignPanel.class;
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
        }

    }
}
