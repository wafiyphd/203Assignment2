package assignment2;
import java.io.Serializable;
/**
 *
 * @author Root
 */
public class Review implements Serializable, Comparable<Review>, Cloneable {
    
    private String date;
    private String comments;
    private double rating;
    
    public Review(String date, String comment, double rating)
    {
        if (date.equals(""))
            this.date = "invalid";
        else
            this.date = date;
        if (!setComments(comment))
            this.comments = "invalid";
        if (!setRating(rating))
            this.rating = 0.0;
    }    
    
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
    
    public int hashCode()
    {
        return date.hashCode();
    }
    
    public int compareTo(Review obj)
    {
        if (this == obj)
            return 0;
        Review rhs = (Review) obj;
        return getDate().compareTo(rhs.getDate());
    }
    
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
    
    public boolean setDate (String date)
    {
        if (date.equals(""))
            return false;
        this.date = date;
        return true;
    }
    
    public boolean setComments (String comment)
    {
        if (date.equals(""))
            return false;
        this.comments = comment;
        return true;
    }
    
    public boolean setRating (double rating)
    {
        if (rating > 5.0 || rating < 0.0)
            return false;
        this.rating = rating;
        return true;
    }
    
    public String toString()
    {
        return date + ": rated: " + rating + "/5. Comments: \"" + comments + "\"";
    }

    public String getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    public double getRating() {
        return rating;
    }
    
}
