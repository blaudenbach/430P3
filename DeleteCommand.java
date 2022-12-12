import java.util.*;
class DeleteCommand extends Command {
  private Vector<Item> itemList;
  public DeleteCommand () {
    itemList = new Vector<Item>();
    Enumeration<Item> enumeration = model.getSelectedItems();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      itemList.add(item);
    }
  }
  public boolean undo() {
    Enumeration<Item> enumeration = itemList.elements();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      model.addItem(item);
      model.markSelected(item);
    }
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public void execute() {
    model.deleteSelectedItems();
  }
}
