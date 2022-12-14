import java.awt.*;

public class MoveCommand extends Command {
    private Item item;
    private Item newItem;
    private Canvas box;
    private Point location;
    int index;

    public MoveCommand(Canvas b, Item i, Point l, int ind){
        box = b;
        item = i;
        location = new Point((int) l.getX() - 10, (int) l.getY() - 70);
        index = ind;
    }

    public void execute(){
        box.setLocation(location);

        Point origLoc = item.getLocation();
        double xOffset = location.getX() - origLoc.getX();
        double yOffset = location.getY() - origLoc.getY();

        newItem = item.move(xOffset, yOffset);

        model.replaceItem(index, newItem);
    }

    public boolean undo(){
        
        return true;
    }

    public boolean redo(){
        execute();
        return true;
    }

    public boolean end(){
 
        return true;
    }

}
