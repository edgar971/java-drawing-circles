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
    private ArrayList<Integer> radiousList;
    private int counter;
    private int previewRadious;
    private Point pointA = null, pointB;

    //constructor
    public LinesPanel() {
        //initialize variables
        counter = 0;
        pointAList = new ArrayList<Point>();
        pointBList = new ArrayList<Point>();
        radiousList = new ArrayList<Integer>();
        previewRadious = 0;
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
        //create temp point a
        Point tempA;
        //temp radious
        int tempRad;
        //paint page
        super.paintComponent(page);
        //set the color
        page.setColor(Color.yellow);
        //if the points are not null

        if(pointA != null && pointB != null) {
            page.setColor(Color.RED);
            //draw the circle
            page.fillOval(pointA.x - previewRadious,pointA.y - previewRadious,previewRadious * 2, previewRadious * 2);
            page.setColor(Color.white);
            //draw the line
            page.drawLine(pointA.x, pointA.y,pointB.x,pointB.y);


        }
        //if there are existing circles then render them
        if(counter > 0) {
            //for each circle
            for (int index = 0; index < counter;index++) {
                //temp point a
                tempA = pointAList.get(index);
                //temp radious
                tempRad = radiousList.get(index);
                //set the color
                if(index % 2 == 0) {
                    page.setColor(Color.yellow);
                } else  {
                    page.setColor(Color.GREEN);
                }
                //create the circle
                page.fillOval(tempA.x - tempRad, tempA.y - tempRad, tempRad * 2, tempRad * 2);


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
            //get ending point
            pointB = event.getPoint();
            //calculate the diameter using the distance formula
            previewRadious = (int) Math.sqrt(Math.pow((pointB.x - pointA.x), 2) + Math.pow((pointB.y - pointA.y), 2));
            //repaint the screen when dragged to display new line position
            repaint();

        }

        public void mouseClicked(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {
            //add the radious to the list
            radiousList.add(previewRadious);
            //add new line to array list
            pointAList.add(pointA);
            //get the point where the mouse was released
            pointBList.add(pointB);
            //increase counter for the loop
            counter ++;
            //reset points
            pointB = null;
            pointA = null;
            previewRadious = 0;
            //repaint the page
            repaint();

        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        public void mouseMoved(MouseEvent event) {}
    }
}
