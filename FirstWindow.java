import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FirstWindow extends JFrame {
    JTextField tf = new JTextField("Insert Text");
    JTextArea ta = new JTextArea(10, 40);


    public FirstWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //ta.setBorder(new LineBorder(Color.BLACK, 4));
        ta.setBorder(new TitledBorder("Text Area"));
        Container cp = getContentPane();
        //cp.setLayout();
        cp.add(ta, BorderLayout.CENTER);


        cp.add(tf, BorderLayout.NORTH);


        JButton b1 = new JButton("Clear");
        //b1.addActionListener((actionEvent)->{tf.setText("");ta.setText("");});
        b1.addActionListener(this::clear);

        JButton b2 = new JButton("Action");
        b2.addActionListener((actionEvent)->ta.append(tf.getText()+'\n'));

        JPanel south = new JPanel(new BorderLayout());
        //JPanel south = new JPanel();
        //((FlowLayout)south.getLayout()).setAlignment(FlowLayout.LEFT);
        south.add(b1,BorderLayout.WEST); south.add(b2,BorderLayout.EAST);
        cp.add (south, BorderLayout.SOUTH);
        pack();//redimensiona as janelas com a dim dos elementos

    }

    public void clear (ActionEvent e){
        ta.setText("");
        tf.setText("");

    }

    public static void main(String[] args) {
        new FirstWindow().setVisible(true);

    }
}
