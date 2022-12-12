import java.io.*;
import java.util.*;
import java.awt.*;
public abstract class Item implements Serializable {
  protected static UIContext uiContext;
  protected Vector<Item> elements = new Vector<Item>();
  public static void setUIContext(UIContext uiContext) {
    Item.uiContext = uiContext;
  }
  public abstract boolean includes(Point point);
  protected double distance(Point point1, Point point2) {
    double xDifference = point1.getX() - point2.getX();
    double yDifference = point1.getY() - point2.getY();
    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
  }
  public  void render() {
    //uiContext.draw(this);
  }

  public Enumeration<Item> getElements(){
    return elements.elements();
  }

  public void addElement(Item element){
    elements.add(element);
  }

  public Point getLocation(){
    System.out.println("Number of elements: " + Integer.toString(elements.size()));
    System.out.println("Element 0: " + elements.elementAt(0).toString());
    if(elements.elementAt(0).getClass() == Line.class){
      Line firstLine = (Line) elements.elementAt(0);
      return firstLine.getPoint1();
    }
    else if(elements.elementAt(0).getClass() == Label.class){
      Label label = (Label) elements.elementAt(0);
      return label.getStartingPoint();
    }

    return new Point(0, 0);
  }

  public void move(int xOffset, int yOffset){
    
  }
}
