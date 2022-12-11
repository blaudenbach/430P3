import java.awt.*;
import java.util.*;

public class Polygon extends Item {
    private Vector lines;

    public Polygon(){
        lines = new Vector();
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

    public Enumeration getLines(){
        return lines.elements();
    }
}
