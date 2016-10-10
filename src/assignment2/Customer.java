package assignment2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Root
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
     * 
     * @param date
     * @param comments
     * @param rating
     * @return 
     */
    public Review addReview(String date, String comments, double rating)
    {
        Review r = new Review(date, comments, rating);
        reviews.add(r);
        return r;
    }
    
    @Override
    /**
     * 
     */
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
     * 
     * @return 
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
