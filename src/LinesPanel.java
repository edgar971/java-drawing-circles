import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by edgar971 on 7/13/15.
 */
public class LinesPanel extends JPanel {
    //variables to hold the coordinate points
    private ArrayList<Point> pointAList;
    private ArrayList<Point> pointBList;
    private int counter;
    private Point pointA = null, pointB;

    //constructor
    public LinesPanel() {
        counter = 0;
        //create line history array
        pointAList = new ArrayList<Point>();
        pointBList = new ArrayList<Point>();
        //create new listener
        LineListener listener = new LineListener();
        //add mouse listener
        addMouseListener(listener);
        //add motion listener
        addMouseMotionListener(listener);

        //set bg color
        setBackground(Color.black);
        //set window size
        setPreferredSize(new Dimension(500, 300));

    }

    protected void paintComponent(Graphics page) {
        Point tempA, tempB;
        super.paintComponent(page);
        page.setColor(Color.yellow);
        //if the points are not null
        //draw the line
        if(pointA != null && pointB != null) {
            page.drawLine(pointA.x, pointA.y,pointB.x,pointB.y);
            //System.out.println(pointA.x + " : " + pointB.y);
        }
        if(counter > 0) {

            //System.out.println("Done creating new line");
            for (int index = 0; index < counter;index++) {
                tempA = pointAList.get(index);
                tempB = pointBList.get(index);
                page.drawLine(tempA.x, tempA.y,tempB.x,tempB.y);

            }

        }
        page.drawString("Count: " + counter, 5, 15);
    }

    private class LineListener implements MouseListener, MouseMotionListener {
        //capture the initial click

        public void mousePressed(MouseEvent event) {
            //get the point and assign it
            pointA = event.getPoint();

        }
        //get point b when dragged
        public void mouseDragged(MouseEvent event) {
            pointB = event.getPoint();
            //repaint the screen when dragged to display new line position
            repaint();

        }

        public void mouseClicked(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {
                      //add new line to array list
            pointAList.add(pointA);
            //get the point where the mouse was released
            pointBList.add(event.getPoint());
            //increase counter
            counter ++;
            //reset points
            pointB = null;
            pointA = null;
            //repaint the page
            repaint();

        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        public void mouseMoved(MouseEvent event) {}
    }
}
