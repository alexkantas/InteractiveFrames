package gr.alexkantas.oop2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Alex
 */
public class MyForm extends JFrame implements ChangeListener {

    //JPannels
    private JPanel p1; // Panel for PAGE UP
    private JPanel p2; // Central Panel with gridl for buttons
    private JPanel p3; // Panel for PAGE END
    //
    private GridLayout g1; // for p2
    private MyForm myForm2; // to communicate with form2

    int mark; // Marks position to khow what button change state
    boolean active; // define if change listener will execute or not

    public MyForm() {
        this(null);
    }

    public MyForm(MyForm myForm2) {

        // JPanels
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        //

        // Form2
        if (myForm2 != null) {
            this.myForm2 = myForm2;
        } else {
            this.myForm2 = new MyForm(this);
            this.myForm2.setTitle("My Form 2");
        }

        //
        active = true;

        //
        initializeGrid(5, 5); //rows,columns

        //
        add(p1, BorderLayout.PAGE_START);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.PAGE_END);

        //TEST CODE
        p1.add(new JLabel("Hello !"));
        p3.add(new JButton("OK"));
////        buttons[11].getModel().setPressed(true);
////        buttons[18].getModel().setPressed(true);
//        JButton bb1 = (JButton) p2.getComponent(21);
//        bb1.getModel().setArmed(true);
//        bb1.getModel().setPressed(true);
//
//        JButton bb2 = (JButton) p2.getComponent(17);
//        bb2.getModel().setRollover(true);
//        if (p2 == bb2.getParent())
//        System.out.println("Hi!");
////        bb1.setBackground(new Color(238, 238, 238));
        // TEST CODE END

        //
        setTitle("My Form");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
     Initialize array and grid
     */
    private void initializeGrid(int i, int j) {
        g1 = new GridLayout(i, j);
        p2.setLayout(g1);
        for (int k = 0; k < i*j; k++) {
            JButton button = new JButton("" + k);
            button.addChangeListener(this);
            p2.add(button);
        }
    }

    /* Returns Pannel with JButtons */
    public JPanel getButtonsPanel() {
        return p2;
    }

    // Change Listener
    @Override
    public void stateChanged(ChangeEvent e) {
        if (active) {
            myForm2.setActive(false);
            //
            JButton aButton = (JButton) e.getSource();
            ButtonModel aModel = aButton.getModel();
            boolean armed = aModel.isArmed();
            boolean pressed = aModel.isPressed();
            boolean rooled = aModel.isRollover();
            JPanel np2 = myForm2.getButtonsPanel();
            int c = p2.getComponentCount();
            JButton bb, bb2;
            bb2 = (JButton) e.getSource();
            //Mouse Over
            if (rooled) {
                for (int i = 0; i < c; i++) {
                    if (e.getSource() == (JButton) p2.getComponent(i)) {
                        bb = (JButton) np2.getComponent(i);
                        mark = i;
                        bb.getModel().setRollover(true);
                        break;
                    }
                }
            }

            if (!rooled) {
                bb = (JButton) np2.getComponent(mark);
                bb.getModel().setRollover(false);
            }
        //

            //Armed-release
            if (armed) {
                for (int i = 0; i < c; i++) {
                    if (e.getSource() == (JButton) p2.getComponent(i)) {
                        bb = (JButton) np2.getComponent(i);
                        mark = i;
                        bb.getModel().setArmed(true);
                        break;
                    }
                }
            }

            if (!armed) {
                bb = (JButton) np2.getComponent(mark);
                bb.getModel().setArmed(false);
            }
        //

            //Pressed - Unpressed
            if (pressed) {
                for (int i = 0; i < c; i++) {
                    if (e.getSource() == (JButton) p2.getComponent(i)) {
                        bb = (JButton) np2.getComponent(i);
                        mark = i;
                        bb.getModel().setPressed(true);
                        break;
                    }
                }
            }

            if (!pressed) {
                bb = (JButton) np2.getComponent(mark);
                bb.getModel().setPressed(false);
            }
            //
            myForm2.setActive(true);
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static void main(String[] args) {
        MyForm myForm = new MyForm();
    }
}
