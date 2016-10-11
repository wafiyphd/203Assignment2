package assignment2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class of customers which is a subclass of Users of the Food Truck Application
 * Attributes is same as User, with an addition of an array list of reviews
 * @author Wafiy Damit
 */
public class Customer extends User implements Serializable  {
    
    private ArrayList<Review> reviews;
    
    /**
     * constructor with arguments to create a Customer object with
     * parameters passed in
     * @param userName userName, must be unique
     * @param password password of the Customer
     * @param fullName fullName of the Customer
     * @param email email of the Customer
     */
    public Customer(String userName, String password, String fullName, String email)
    {
        super(userName, password, fullName, email);
        reviews = new ArrayList<>();
    }
    
    /**
     * A method to add a review written by the customer into their own array list
     * of reviews written
     * @param reviewer the reviewer that wrote the review
     * @param date the date when the reviews was written
     * @param comments the comments or description by the customer regarding a food truck
     * @param rating the rating, out of 5, given by the customer 
     * @return the review when it is successfully added to the array list 
     */
    public Review addReview(Customer reviewer, String date, String comments, double rating)
    {
        Review r = new Review(reviewer, date, comments, rating);
        reviews.add(r);
        return r;
    }
    
    /**
     * A method to get the average ratings of all the 
     * ratings given by the customer
     * @return the average rating when it is available, or 0
     * when it is empty
     */
    @Override
    public double getAverageRatings()
    {
        if (reviews.isEmpty())
            return 0.0;
        else {
            double rating = 0.0;
            for (Review r:reviews)
                rating += r.getRating();
            return rating/((double)reviews.size());
        }
    }
    
    /**
     * A method to get all the reviews written by the customer 
     * @return all the reviews written by the customer when it is available
     * and returns no reviews written yet when empty
     */
    public String getAllReviews()
    {
        if (reviews.isEmpty())
            return "No reviews written yet.\n";
        else {
            String all = "";
            for (Review r:reviews)
                all += r.toString() + "\n";
            return all;
        }
    }
}
