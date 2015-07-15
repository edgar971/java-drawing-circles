import javax.swing.*;

/**
 * Created by edgar971 on 7/13/15.
 */
public class RubberLines {
    public static void main(String[] args) {
        JFrame frame =  new JFrame("Rubber Lines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new LinesPanel());
        frame.pack();
        frame.setVisible(true);


    }
}
