import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.awt.*;

public class PolygonButton extends JButton implements ActionListener{
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private LineCommand lineCommand;
    private LineCommand lineCommand2;
    private UndoManager undoManager;
    private boolean removeTemp = false;

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
        private int pointCount = 0;
        private int moveCounter = 0;

        public void mouseClicked(MouseEvent event){
            if(SwingUtilities.isRightMouseButton(event)){
                lastPoint = event.getPoint();
                Line line = new Line(firstPoint, lastPoint);
                polygonCommand.addLine(line);
                pointCount = 0;
                drawingPanel.removeMouseListener(this);
                drawingPanel.removeMouseMotionListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(polygonCommand);
            }
            else{
                if(++pointCount == 1){
                    firstPoint = event.getPoint();
                    lastPoint = event.getPoint();
                    polygonCommand = new PolygonCommand();
                    polygonCommand.addLine(new Line(firstPoint, lastPoint));
                    undoManager.beginCommand(polygonCommand);
                }
                else{
                    removeTemp = true;
                    Line line = new Line(lastPoint, event.getPoint());
                    moveCounter = 0;
                    polygonCommand.addLine(line);
                    lastPoint = event.getPoint();
                    polygonCommand.addLine(new Line(firstPoint, lastPoint));
                }
            }
        }

        public void mouseMoved(MouseEvent event){
            if(pointCount > 0){
                if(removeTemp == true){
                    lineCommand2.undo();
                    removeTemp = false;
                }
                moveCounter++;
                if(moveCounter > 1){

                    undoManager.endCommand(lineCommand);
                    undoManager.endCommand(lineCommand2);
                    undoManager.undo();
                    undoManager.undo();
                }
                lineCommand = new LineCommand(lastPoint, event.getPoint());
                undoManager.beginCommand(lineCommand);
                lineCommand2 = new LineCommand(firstPoint, event.getPoint());
                undoManager.beginCommand(lineCommand2);
            }
        }
    }
}
