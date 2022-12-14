import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.Cursor;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class MoveButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private UndoManager undoManager;
    private MoveCommand moveCommand;
    private Vector<Item> itemList;
    private Vector<Canvas> boxes;

    public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel){
        super("Move");
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        this.undoManager = undoManager;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event){
        //Change cursore
        view.setCursor(new Cursor(Cursor.MOVE_CURSOR));

        //Create boxes next to objects
        itemList = new Vector<Item>();
        boxes = new Vector<Canvas>();
        Enumeration<Item> enumeration = Command.model.getItems();

        while(enumeration.hasMoreElements()){
            Item item = (Item) (enumeration.nextElement());
            itemList.add(item);
        }

        for(int i = 0; i < itemList.size(); i++){
            Item curItem = (Item) itemList.elementAt(i);
            Canvas box = new Canvas();
            box.setSize(10, 10);
            box.setBackground(Color.BLACK);
            box.setLocation(curItem.getLocation());
            box.setVisible(true);
            drawingPanel.add(box);
            box.addMouseListener(mouseHandler);
            box.addMouseMotionListener(mouseHandler);
            boxes.addElement(box);
        }
    }

    private class MouseHandler extends MouseInputAdapter {
        public void mouseReleased(MouseEvent event){
            undoManager.endCommand(moveCommand);
            for(int i = 0; i < boxes.size(); i++){
                drawingPanel.remove((Canvas) boxes.elementAt(i));
            }
            boxes.removeAllElements();
            view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        public void mouseDragged(MouseEvent event){
            if(event.getComponent().getClass() == Canvas.class){                
                //Get item next to box
                for(int i = 0; i < boxes.size(); i++){
                    if(boxes.elementAt(i) == event.getComponent()){
                        itemList = new Vector<Item>();
                        Enumeration<Item> enumeration = Command.model.getItems();

                        while(enumeration.hasMoreElements()){
                            Item item = (Item) (enumeration.nextElement());
                            itemList.add(item);
                        }
                        moveCommand = new MoveCommand((Canvas) boxes.elementAt(i), (Item) itemList.elementAt(i), event.getLocationOnScreen(), i);
                        undoManager.beginCommand(moveCommand);
                    }
                }
            }
        }

    }
}
