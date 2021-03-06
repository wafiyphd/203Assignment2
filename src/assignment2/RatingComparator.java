package assignment2;
import java.util.*;
/**
 * A method to compare the ratings of reviews 
 * @author Wafiy Damit
 */
public class RatingComparator implements Comparator<Review> {
    
    public int compare(Review lhs, Review rhs)
    {
        if (lhs.getClass().equals(rhs.getClass()))
        {
            double rating = lhs.getRating() - rhs.getRating();
            if (rating > 0)
                return 1;
            else if (rating < 0)
                return -1;
            return 0;
        }  
        else
            return 1;
    }

}
