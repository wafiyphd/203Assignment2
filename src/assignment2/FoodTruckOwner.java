package assignment2;
import java.io.Serializable;
import java.util.*;

/**
 * FoodTruckOwner is a User that has a collection of Food Trucks
 * @author Wafiy Damit
 */
public class FoodTruckOwner extends User implements Serializable {

    private String licenseNo;
    private ArrayList<FoodTruck> ownedTrucks;

    /**
     * constructor with arguments
     * @param userName username
     * @param password password
     * @param fullName name of the User
     * @param email email address
     * @param licenseNo FoodTruckOwner's licenseNo
     */
    public FoodTruckOwner(String userName, String password, String fullName, String email, String licenseNo) {
        super(userName, password, fullName, email);
        if (!setLicenseNo(licenseNo)) {
            licenseNo = "unknown license no.";
        }
        ownedTrucks = new ArrayList<>();
    }

    /**
     * A method that returns the number of food trucks that this owner owns
     */
    public int getNumTrucks() {
        return ownedTrucks.size();
    }

    /**
     * getter method to retrieve FoodTruckOwner's license No.
     * @return license number.
     */
    public String getLicenseNo() {
        return licenseNo;
    }

    /**
     * a setter method to set a new licenseNo
     * @param licenseNo new licenseNo of that particular FoodTruckOwner
     * @return true if license set successfully
     */
    public boolean setLicenseNo(String licenseNo) {
        if (licenseNo.equals("")) {
            return false;
        }
        this.licenseNo = licenseNo;
        return true;
    }

    /**
     * toString method to print out the information about a FoodTruckOwner
     * @return String contains FoodTruckOwner's information
     */
    @Override
    public String toString() {
        return super.toString() + ", licenseNo: " + licenseNo;
    }

    /**
     * A method to add new food truck to owner's list of Food Truck objects
     * @param truckName name of the truck
     * @param foodType type of cuisine
     * @return FoodTruck object that has been created
     */
    public FoodTruck addFoodTruck(String truckName, String foodType) {
        FoodTruck newFoodTruck = new FoodTruck(truckName, foodType);
        ownedTrucks.add(newFoodTruck);
        return newFoodTruck;
    }

    /**
     * a method to return all FoodTruck objects an owner has
     * @return the list of food trucks an owner has
     */
    public String getFoodTrucks() {
        if (ownedTrucks.isEmpty())
            return "No trucks owned";
        String all = "All food trucks owned: \n";
        for (FoodTruck ft : ownedTrucks) {
            all += ft.toString() + "\n";
        }
        return all;
    }

    /**
     * A method find a particular FoodTruck using truckID
     * @param truckID truckID of intended FoodTruck
     * @return FoodTruck when it is found
     */
    public FoodTruck findFoodTruck(String truckID) {
        for (FoodTruck ft : ownedTrucks) {
            if (ft.getTruckID().equalsIgnoreCase(truckID)) {
                return ft;
            }
        }
        return null;
    }

    /**
     * A method to get the average ratings of all the food trucks 
     * owned by the owner
     * @return the average ratings of all the food trucks owned by the owner
     */
    @Override
    public double getAverageRatings()
    {
        double rating = 0.0;
        double reviewed = ownedTrucks.size();
        for (FoodTruck ft:ownedTrucks)
            if (ft.getAverageRatings() == -1.0)
                reviewed -= 1.0; //a particular truck hasn't been reviewed yet
            else                        
                rating += ft.getAverageRatings();
        
        if (reviewed == 0.0)
            return -1.0;
        else
            return rating/(reviewed);
    }
    
    /**
     * A method to get a list of all the reviews received by the owner for
     * all his food trucks
     * @return a list of all the reviews received by the owner
     */
    public String getAllReviews()
    {
        String all = "";
        int num = 1;
        for (FoodTruck ft:ownedTrucks) {
            all += num + ". "  + ft.getTruckName() + " reviews: \n" + ft.getAllReviews() + "\n";
            num++;
        }
        return all;
    }
}

