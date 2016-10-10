package assignment2;
import java.io.Serializable;
import java.util.*;

/**
 * An abstract class to represent a User of the Food Truck Application
 * @author Root
 */
public abstract class User implements Serializable {
    private String username;
    private String password;
    private String fullName;
    private String email;
  
    /**
     * constructor with arguments
     * @param username username must be unique
     * @param password password 
     * @param fullName full name of user
     * @param email email address of user
     */
    public User(String username, String password, String fullName, String email)
    {
        if (username.equals(""))
            this.username = "invalid";
        else
            this.username = username;
        if (!setPassword(password))
            this.password = "invalid";
        if (!setFullname(fullName))
            this.fullName = "unknown";
        if (!setEmail(email))
            this.email = "unknown";
    }
    
    /**
     * getter method for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter method for password
     * @return password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * getter method for full name
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * getter method for email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter method, password cannot be blank
     * @param password new password 
     * @return  true if password set successfully
     */
    public boolean setPassword(String password) {
        if (password.equals(""))
            return false;
        this.password = password;
        return true;
    }

    /**
     * setter method to set a new full name, cannot be blank
     * @param fullName new full name to be set
     * @return true if set successfully
     */
   public boolean setFullname(String fullName) {
        if (fullName.equals(""))
            return false;
        this.fullName = fullName;
        return true;
    }

    /**
     * setter method to set a new email
     * @param email new email address to be set
     */
    public boolean setEmail(String email) {
        if (email.equals(""))
            return false;
        this.email = email;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        User other = (User) obj;
        return this.username.equals(other.username);
    }

    /**
     * 
     * @return 
     */
    public abstract double getAverageRatings();
    
    /**
     * toString method to print out the information of User
     * @return String contains information about User. 
     */
    @Override
    public String toString() {
        return username + ",  full name: " + fullName + ", email: " + email;
    }   

}
