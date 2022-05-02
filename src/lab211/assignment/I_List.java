package lab211.assignment;

/* Interface for a group of objects
 */

/**
 *
 * @author Hoa Doan
 */
public interface I_List {
  // your code here
  public abstract void loadDataFromFile();
  public abstract void addVehicle();
  public abstract void updateVehicle();
  public abstract void deleteVehicle();
  public abstract void searchVehicle();
  public abstract void showVehicleList();
  public abstract void saveToFile();
}
