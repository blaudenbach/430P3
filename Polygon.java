import java.awt.*;
import java.util.*;

public class Polygon extends Item {
    private Vector<Line> lines;

    public Polygon(){
        lines = new Vector<Line>();
    }

    public Polygon(Vector<Line> l){
        lines = new Vector<Line>();
        for(int i = 0; i < l.size(); i++){
            lines.add(l.elementAt(i));
        }
    }

    public boolean includes(Point point){
        for(int i = 0; i < lines.size(); i++){
            Line line = (Line) lines.elementAt(i);
            if(line.includes(point)){
                return true;
            }
        }

        return false;
    }

    public void addLine(Line line){
        lines.add(line);
    }

    public void render() {
        for(int i = 0; i < lines.size(); i++){
            Line line = (Line) lines.elementAt(i);
            line.render();
        }
    }

    public Enumeration<Line> getLines(){
        return lines.elements();
    }

    public String toString(){
        return "Polygon with " + Integer.toString(lines.size()) + " sides";
    }

    public Polygon move(double xOffset, double yOffset){
        Polygon polygon = new Polygon();

        for(int i = 0; i < lines.size(); i++){
            int newX1 = (int) (lines.elementAt(i).getPoint1().getX() + xOffset);
            int newY1 = (int) (lines.elementAt(i).getPoint1().getY() + yOffset);
            int newX2 = (int) (lines.elementAt(i).getPoint2().getX() + xOffset);
            int newY2 = (int) (lines.elementAt(i).getPoint2().getY() + yOffset);
            Line line = new Line(new Point(newX1, newY1), new Point(newX2, newY2));
            polygon.addLine(line);
            polygon.addElement(line);
        }

        return polygon;
    }
}
