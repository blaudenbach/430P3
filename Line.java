import java.awt.*;
public class Line extends Item {
  private Point point1;
  private Point point2;
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Line(Point point1) {
    this.point1 = point1;
	point2 = null;
  }
  public Line() {
	  point1 = null;
	  point2 = null;
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void render() {
    uiContext.drawLine(point1, point2);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }
  public Line move(double xOffset, double yOffset){
    int newX1 = (int) (getPoint1().getX() + xOffset);
    int newY1 = (int) (getPoint1().getY() + yOffset);
    int newX2 = (int) (getPoint2().getX() + xOffset);
    int newY2 = (int) (getPoint2().getY() + yOffset);
    Line line = new Line(new Point(newX1, newY1), new Point(newX2, newY2));
    line.addElement(line);
    return line;
  }
}

