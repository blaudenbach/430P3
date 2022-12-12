import java.awt.*;

public class MoveCommand extends Command {
    private Item item;
    private Canvas box;
    private Point location;

    public MoveCommand(Canvas b, Item i, Point l){
        box = b;
        item = i;
        location = new Point((int) l.getX() - 10, (int) l.getY() - 70);
    }

    public void execute(){
        box.setLocation(location);
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
