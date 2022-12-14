import javax.swing.*;

//import org.w3c.dom.events.MouseEvent;

import java.awt.event.*;
import java.awt.*;

public class LineButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private LineCommand lineCommand;
  private LineCommand lineCommand2;
  private UndoManager undoManager;

  public LineButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Line");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
    drawingPanel.addMouseMotionListener(mouseHandler);
  }

  private class MouseHandler extends MouseAdapter {
    private int pointCount = 0;
    private int moveCounter = 0;
    private Point point1;

    public void mouseClicked(MouseEvent event) {
      if (++pointCount == 1) {
        point1 = event.getPoint();
        lineCommand = new LineCommand(View.mapPoint(point1));
        undoManager.beginCommand(lineCommand);
      }
      else if (pointCount == 2) {
        point1 = new Point();
        pointCount = 0;
        moveCounter = 0;
        lineCommand.setLinePoint(View.mapPoint(event.getPoint()));
        drawingPanel.removeMouseListener(this);
        view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        lineCommand2.undo();
        undoManager.endCommand(lineCommand);
        }
    }

    public void mouseMoved(MouseEvent event){
      
      if(pointCount == 1){
        moveCounter++;
        if(moveCounter > 1){
          lineCommand2.undo();
        }
        lineCommand2 = new LineCommand(point1 , View.mapPoint(event.getPoint()));
        lineCommand2.execute();        
      }
    }
  }
}