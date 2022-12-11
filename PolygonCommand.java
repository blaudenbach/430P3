import java.awt.*;
import java.util.*;

public class PolygonCommand extends Command{
    private Polygon polygon;

    public PolygonCommand(Vector lines){
        for(int i = 0; i < lines.size(); i++){
            Line line = (Line) lines.elementAt(i);
            polygon.addLine(line);
        }
    }

    public boolean undo(){
        Enumeration elements = polygon.getLines();

        while(elements.hasMoreElements()){
            Line line = (Line) elements.nextElement();
            model.removeItem(line);
        }

        return true;
    }

    public boolean redo(){
        execute();
        return true;
    }

    public void execute(){
        Enumeration elements = polygon.getLines();

        while(elements.hasMoreElements()){
            Line line = (Line) elements.nextElement();
            model.addItem(line);
            polygon.addElement(line);
        }
    }
}
