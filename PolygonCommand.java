import java.util.*;

public class PolygonCommand extends Command{
    private Polygon polygon;

    public PolygonCommand(){
        polygon = new Polygon();
    }

    public PolygonCommand(Polygon poly){
        polygon = poly;
    }

    public void addLine(Line line){
        polygon.addLine(line);
    }

    public boolean undo(){
        Enumeration<Line> elements = polygon.getLines();

        while(elements.hasMoreElements()){
            Line line = (Line) elements.nextElement();
            //model.removeItem(line);
            polygon.elements.remove(line);
        }

        model.removeItem(polygon);

        return true;
    }

    public boolean redo(){
        execute();
        return true;
    }

    public void execute(){
        Enumeration<Line> elements = polygon.getLines();

        while(elements.hasMoreElements()){
            Line line = (Line) elements.nextElement();
            polygon.addElement(line);
        }
        model.addItem(polygon);
    }

    public boolean end(){
        return true;
    }
}
