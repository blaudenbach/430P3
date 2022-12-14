import java.awt.*;
public class Label extends Item {
  private Point startingPoint;
  private String text = "";
  public Label(Point point) {
    startingPoint = point;
  }
  public Label(Point point, String txt){
    startingPoint = point;
    text = txt;
  }
  public void addCharacter(char character) {
    text += character;
  }
  public void removeCharacter() {
    if (text.length() > 0) {
      text = text.substring(0, text.length() - 1);
    }
  }
  public boolean includes(Point point) {
    return distance(point, startingPoint) < 10.0;
  }
 public void render() {
   uiContext.drawLabel(text, startingPoint);
  }
  public String getText() {
    return text;
  }
  public Point getStartingPoint() {
    return startingPoint;
  }
  public void setStartingPoint(Point sPoint){
    startingPoint = sPoint;
  }
  public String toString(){
    return "Label with text '" + text + "'";
  }
  public Label move(double xOffset, double yOffset){
    int newX = (int) (getStartingPoint().getX() + xOffset);
    int newY = (int) (getStartingPoint().getY() + yOffset);
    Label label = new Label(new Point(newX, newY), getText());
    label.addElement(label);

    return label;
  }
}
