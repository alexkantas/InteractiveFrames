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
public class MyForm2 extends JFrame implements ChangeListener {

    //JPannels
    private JPanel p1; // Panel for PAGE UP
    private JPanel p2; // Central Panel with gridl for buttons
    private JPanel p3; // Panel for PAGE END
    //
    private GridLayout g1; // for p2
    private JButton[] buttons; // for initialize grid
    private MyForm myForm; // to communicate with form2

    private int mark; // Marks position to khow what button change state
    private boolean active;//

    public MyForm2() {
        this(null);
    }

    public MyForm2(MyForm myForm) {

        // JPanels
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        //

        // Form2 
        if (myForm != null) {
            this.myForm = myForm;
        }

        //
        initializeGrid(5, 5); //rows,columns

        //
        add(p1, BorderLayout.PAGE_START);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.PAGE_END);

        //
        p1.add(new JLabel("Hello !"));
        p3.add(new JButton("OK"));

        //
        active = true;

        //
        setTitle("My Form 2");
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /*
     Initialize array and grid
     */
    private void initializeGrid(int i, int j) {
        buttons = new JButton[i * j];
        g1 = new GridLayout(i, j);
        p2.setLayout(g1);
        for (int k = 0; k < buttons.length; k++) {
            buttons[k] = new JButton("" + 2 * k);
            buttons[k].addChangeListener(this);
            p2.add(buttons[k]);
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
            System.out.println("IS ON 2 !!!");
            JButton aButton = (JButton) e.getSource();
            ButtonModel aModel = aButton.getModel();
            boolean armed = aModel.isArmed();
            boolean pressed = aModel.isPressed();
            boolean rooled = aModel.isRollover();
            JPanel np2 = myForm.getButtonsPanel();
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

            //Press - Unpress
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
            System.out.println("IS OFF 2 !!!");
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
