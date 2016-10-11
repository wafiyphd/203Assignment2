package assignment2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to create a FoodTruck object that is owned by its owner.
 * FoodTruck has attributes truckID, truckName, location, foodType and status. 
 * @author Wafiy Damit
 */
public class FoodTruck implements Serializable {
    
    private String truckID;
    private String truckName;
    private String location;
    private String foodType;
    private String status;  // status is either active or inactive
    private static int nextNum = 1000;
    private ArrayList<Review> reviews;
    
    /**
     * Constructor with arguments
     * @param truckName name of the truck
     * @param foodType type of cuisine
     */
    public FoodTruck(String truckName, String foodType)
    {
        if(!setTruckName(truckName))
            this.truckName = "unknown";
         if(!setFoodType(foodType))
            this.foodType = "unknown";
        this.status = "inactive";
        this.location = "unknown";
        this.truckID = "FT"+nextNum++;
        this.reviews = new ArrayList<>();
    }

    /**
     * A method to add reviews to the list of reviews received by a food truck
     * @param cust the writer of the review
     * @param date the date the review was written
     * @param comments any comments or description from the writer 
     * @param rating rating out of 5 of the food truck
     * @return returns the review when it is successfully added to the array list
     */
    public Review addReview(Customer cust, String date, String comments, double rating)
    {
        Review r = cust.addReview(cust, date, comments, rating);
        reviews.add(r);
        return r;
    }
    
    /**
     * a method to set the next truck number
     * @param nextnum the current next truck number
     */
    public static void setNextNum(int nextnum)
    { 	
        FoodTruck.nextNum = nextnum;
    }
    
    /**
     * a getter method to retrieve truck id
     * @return truck ID
     */
    public String getTruckID() {
        return truckID;
    }

    /**
     * a getter method to retrieve truck name
     * @return truck name
     */
    public String getTruckName() {
        return truckName;
    }

    /**
     * a getter method to retrieve food truck location
     * @return location of a food truck currently at
     */
    public String getLocation() {
        return location;
    }

    /**
     * a getter method to retrieve food truck food type served
     * @return food type served
     */
    public String getFoodType() {
        return foodType;
    }

    /**
     * a getter method to retrieve the status of the food truck
     * @return status of a food truck
     */
    public String getStatus() {
        return status;
    }

    /**
     * setter method to set new truck name
     * @param truckName new truck name to be set
     * @return false if parameter is blank
     */
    public boolean setTruckName(String truckName) {
        if (truckName.equals(""))
            return false;
        this.truckName = truckName;
        return true;
    }

    /**
     * setter method to set new location
     * @param location new location that a food truck is at.
     * @return false if parameter is blank
     */
    public boolean setLocation(String location) {
        if (location.equals(""))
            return false;
        this.location = location;
        return true;
    }
    
    /**
     * setter method to set new food type that a truck serves
     * @param foodType new food type to be served
     * @return false if foodType is blank
     */
     public boolean setFoodType(String foodType) {
        if (foodType.equals(""))
            return false;
        this.foodType = foodType;
        return true;
    }

    /**
     * setter method to change a food truck current status
     * @param status 
     * @return  false if parameter is not 'active' or 'inactive'
     */
    public boolean setStatus(String status) {
        if (!status.equals("active") && !status.equals("inactive"))
            return false;
        this.status = status;
        return true;
    }

    /**
     * A method to get the average ratings a truck has received
     * @return -1 if it has not received any reviews yet or 
     * returns the average ratings when there are reviews
     */
    public double getAverageRatings()
    {
        if (reviews.isEmpty())
            return -1.0; // a particular truck hasn't been reviewed yet
        else
        {
            double rating = 0.0;
            for (Review r: reviews)
                rating += r.getRating();
            return rating/((double)reviews.size());
        }
    }
    
    /**
     * A method to show all the reviews a food truck has received
     * @return returns no reviews yet if not reviewed yet, and returns
     * the list of reviews it has received when its available
     */
    public String getAllReviews()
    {
        if (reviews.isEmpty())
            return "No reviews yet."; // a particular truck hasn't been reviewed yet
        else
        {
            String all = "";
            for (Review r:reviews)
                all += r.toString() + "\n";
            return all;
        }
    }

    /**
     * A method to get the array list of reviews
     * @return the array list of reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    
    /**
     * toString method to print out information about a FoodTruck
     * @return String that contains the information about a food truck. 
     */
    @Override
    public String toString() {
        return "Truck ID: "+ truckID + ", " + truckName + ", serving food type: " + foodType + ", at location: " + location +  ", current status: " + status;
    }   

}
