import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class PolygonButton extends JButton implements ActionListener{
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel){
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();

    }

    public void actionPerformed(ActionEvent event){
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        drawingPanel.addMouseListener(mouseHandler);
        drawingPanel.addMouseMotionListener(mouseHandler);

    }

    private class MouseHandler extends MouseInputAdapter {
        private Point firstPoint;
        private Point lastPoint;
        private Vector lines;
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event){
            if(SwingUtilities.isRightMouseButton(event)){
                Line line = new Line(firstPoint, lastPoint);
                lines.add(line);
                polygonCommand = new PolygonCommand(lines);
            }
            else{
                if(++pointCount == 1){
                    firstPoint = event.getPoint();
                }
                else{

                }
            }

        }

        public void mouseMoved(MouseEvent event){

        }
    }
}
