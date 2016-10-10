package assignment2;
import java.io.Serializable;
import java.util.*;

/**
 * A facade controller that contains a collection of Users and 
 * a collection of Food Trucks
 * @author Root
 */
public class FoodTruckTracker implements Serializable {

    private ArrayList<User> FTTUsers;
    private ArrayList<FoodTruck> FTTFoodTrucks;
    
    /**
     * constructor without arguments
     */
    public FoodTruckTracker()
    {
        FTTUsers = new ArrayList<>();
        FTTFoodTrucks = new ArrayList<>();
    }
    
    /**
     * A method to save the number of trucks in the array list
     * for when loading data from file
     */
    public void updateFTNo()
    {	
	// current number of employees can be found from ArrayList
        int numTruck = 1000 + FTTFoodTrucks.size(); 	
        FoodTruck.setNextNum(numTruck);	                             
    }

    /**
     * A method to add a Customer object to the list of User objects
     * @param userName username of the Customer to logIn, unique
     * @param password password to login
     * @param fullName full name of the Customer
     * @param email email address of the Customer
     * @return a Customer object if it's successfully created, null otherwise
     */
   public Customer addCustomer(String userName, String password, String fullName, String email)
    {
        Customer c = new Customer(userName, password, fullName, email);
        if(FTTUsers.contains(c))    // same username already exists
            return null;
        if(FTTUsers.add(c))
            return c;
        return null;
    }
    
   /**
    * a method to add FoodTruckOwner object to the list of User objects
    * @param userName username of the FoodTruckOwner to login, unique
    * @param password password to login
    * @param fullName full name of the FoodTruckOwner
    * @param email FoodTruckOwner's email address
    * @param licenseNo FoodTruckOwner's licenseNo
    * @return a foodTruckOwner object if it is successfully created, null otherwise
    */
    public FoodTruckOwner addFoodTruckOwner(String userName, String password, String fullName, String email, String licenseNo)
    {
        FoodTruckOwner o = new FoodTruckOwner(userName, password, fullName, email, licenseNo);
        if(FTTUsers.contains(o))
            return null;
        if(FTTUsers.add(o))
            return o;
        return null;
    }
    
    /**
     * A method to ensure an username doesn't repeat 
     * @param userName username which an User is trying to use
     * @return the availability of that username 
     */
    public User getUser(String userName)
    {
        for(User u:FTTUsers)
        {
           if(u.getUsername().equalsIgnoreCase(userName))
                return u;
        }
        return null;
    }
    
    /**
     * a method to add new FoodTruck object owned by a FoodTruckOwner
     * @param owner owner of the new FoodTruck
     * @param truckName name of the new FoodTruck
     * @param foodType type of cuisine
     * @return FoodTruck after adding to owner's list and FTT's list of FoodTruck object
     */
    public FoodTruck addFoodTruck(FoodTruckOwner owner, String truckName, String foodType)
    {
        FoodTruck ft = owner.addFoodTruck(truckName, foodType);
        FTTFoodTrucks.add(ft);
        return ft;
    }
        
    public String allFoodTrucks()
    {
        if (FTTFoodTrucks.isEmpty())
            return "No Trucks Yet";
        String allTrucks = "All trucks\n";
        for(FoodTruck ft : FTTFoodTrucks)
            allTrucks += ft.toString()+"\n";
        return allTrucks;
    }

    public String findFoodTruck(String string) {
        if (FTTFoodTrucks.isEmpty())
            return "No Trucks Yet";
        else {
            String results = "";
            for (FoodTruck ft : FTTFoodTrucks) {
                if (ft.getFoodType().equalsIgnoreCase(string) ||
                        ft.getLocation().equalsIgnoreCase(string)) 
                    results += ft.toString() + "\n";
            }
            return results;
        }
    }
    
    public FoodTruck findFoodTruckID (String id) {
        for (FoodTruck ft : FTTFoodTrucks)
            if (ft.getTruckID().equalsIgnoreCase(id)) 
                    return ft;
        return null;
    }
    
    /**
     * a method to get the ArrayList of users
     * @return the ArrayList of users
     */
    public ArrayList<User> getFTTUsers() {
        return FTTUsers;
    }
    
    public ArrayList<FoodTruck> getFTTTrucks() {
        return FTTFoodTrucks;
    }
    
    public int countCust()
    {
        int cust = 0;
        for (User u:FTTUsers)
            if (u instanceof Customer)
                cust++;
        return cust;                
    }
    
    public int countOwner()
    {
        int own = 0;
        for (User u:FTTUsers)
            if (u instanceof Customer)
                own++;
        return own;                
    }
}
