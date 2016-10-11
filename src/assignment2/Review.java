package assignment2;
import java.io.Serializable;
/**
 * A class to create reviews to be written by customers in the
 * Food Truck Application
 * Attributes of reviews include date, comments and ratings.
 * @author Wafiy Damit
 */
public class Review implements Serializable, Comparable<Review>, Cloneable {

    private Customer reviewer;    
    private String date;
    private String comments;
    private double rating;
    
    /**
     * A constructor for the reviews with arguments
     * @param reviewer the reviewer that wrote the review
     * @param date the date the review was written
     * @param comment any comment written by the reviewer
     * @param rating the rating, out of 5, given by the customer
     */
    public Review(Customer reviewer, String date, String comment, double rating)
    {
        this.reviewer = reviewer;
        if (date.equals(""))
            this.date = "invalid";
        else
            this.date = date;
        if (!setComments(comment))
            this.comments = "invalid";
        if (!setRating(rating))
            this.rating = 0.0;
    }    
    
    /**
     * equals() method to test whether two reviews are equal
     * Reviews are equal when the dates are the same
     * @param obj to be compared
     * @return true if its the same and false if its different
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Review))
            return false;
        Review rhs = (Review) obj;
        if ((this.date == rhs.date))
            return true;
        else
            return false;
    }
    
    /**
     * hashCode()
     * @return integer represented by the date of the review
     */
    public int hashCode()
    {
        return date.hashCode();
    }
    
    /**
     * compareTo method to compare this review with another
     * @param obj to be compared with
     * @return integer 
     */
    public int compareTo(Review obj)
    {
        if (this == obj)
            return 0;
        Review rhs = (Review) obj;
        return getDate().compareTo(rhs.getDate());
    }
    
    /**
     * override clone() method from Object class
     * @return object
     */
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            return null;
        }
    }
    
    /**
     * A method to set the reviewer that wrote the review
     * @param reviewer that wrote the review
     */
    public void setReviewer (Customer reviewer)
    {
        this.reviewer = reviewer;
    }
    
    /**
     * A method to set the date of when the review was written
     * @param date when it was written
     * @return false if empty, true if okay
     */
    public boolean setDate (String date)
    {
        if (date.equals(""))
            return false;
        this.date = date;
        return true;
    }
    
    /**
     * A method to set the comments for the review
     * @param comment any comments added by the reviewer
     * @return false if empty, true if okay
     */
    public boolean setComments (String comment)
    {
        if (date.equals(""))
            return false;
        this.comments = comment;
        return true;
    }
    
   /**
    * A method to set the rating of the review
    * @param rating the rating of the review
    * @return false if more than 5 or less than 0, true otherwise
    */
    public boolean setRating (double rating)
    {
        if (rating > 5.0 || rating < 0.0)
            return false;
        this.rating = rating;
        return true;
    }
    
    /**
     * A toString() method to return information of a review
     * @return String of information regarding a review
     */
    public String toString()
    {
        return date + ": " + reviewer.getFullName() + " rated: " + rating + "/5. Comments: \"" + comments + "\"";
    }

    /**
     * A method to get the date of the review written
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * A method to get the comments in the review
     * @return the comments in the review
     */
    public String getComments() {
        return comments;
    }

    /**
     * A method to get the rating given in the review
     * @return the rating given by reviewer
     */
    public double getRating() {
        return rating;
    }
    
    /**
     * A method to get the Customer who wrote the review
     * @return the customer who wrote the review
     */
    public Customer getReviewer() {
        return reviewer;
    }
    
}
