package assignment2;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * A class that is an interface between the system and its users.
 * A class to prompt for user's inputs and generate output as well as for users
 * to access the different functionality provided by this system.
 * @author Root
 */
public class FTTConsole implements Serializable {
    
    static Scanner kbd;
    static FoodTruckTracker ftt;
    static DateFormat dateFormat;
    
    public static void main(String[] args)
    {   
        ftt = new FoodTruckTracker();
        kbd = new Scanner(System.in);
        
        loadData();
        System.out.println("\nWelcome to Food Truck Tracker.");       
        String menuChoice;
        do
        {
            /**
             * menu
             */
            System.out.println("\n--- FOOD TRUCK TRACKER ---");
            
            System.out.println("\nWould you like to:");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Random usage stats");
            System.out.println("0. Quit");
            System.out.print("\nEnter choice: ");
            menuChoice = kbd.nextLine();
            switch(menuChoice)
            {
                case "1": signUp(); break;
                case "2": login(); break;
                case "3": random(); break;
                case "0": System.out.println("\nThank you for using FTT.");break;
                default: System.out.println("\nInvalid choice!");
            }
        } while (!menuChoice.equals("0"));
        
        saveData();
                
    }
    
    public static void random()
    {
        System.out.println("\n--- Stats ---");
        System.out.println("There are " + ftt.getFTTUsers().size() + " users signed up.");
        System.out.println(ftt.countCust() + " are customers, and " + ftt.countOwner() + " are food truck owners.");
        System.out.println("There are a total of " + ftt.getFTTTrucks().size() + " food trucks in the system.");
    }
    
    /** 
     * A method to check that a string that is entered is not blank
     * @param prompt to indicate the value that is prompted for
     * @return the validated input string
     */
    public static String validateString(String prompt)
    {
         String input;
         do 
         {
            System.out.print("\nPlease enter your " + prompt +": ");
            input = kbd.nextLine();
            if(input.equals(""))
                System.out.println("\nSorry, " + prompt + " cannot be blank. Please try again.");
         }while(input.equals(""));
         return input.trim();
    }
    
    /**
     * A method to check that an email that is entered is not blank and is valid
     * @param prompt to indicate the value that is prompted for
     * @return the validated input email string
     */
    public static String validateEmail(String prompt)
    {
         String input;
         do 
         {
            System.out.print("\nPlease enter your " + prompt +": ");
            input = kbd.nextLine();
            if(input.equals("") || !input.contains("@"))
                System.out.println("\nSorry, "+prompt + " invalid. Please re-enter.");
         }while(input.equals("") || !input.contains("@"));
         return input.trim();
    }
    /**
     * allow user to sign up as Customer or FoodTruckOwner
     */
    public static void signUp()
    {
        System.out.println("\n--- Signing Up ---");
        System.out.println("\nYou would like to sign up as: ");
        System.out.println("1. Customer");
        System.out.println("2. Food Truck Owner");
        System.out.print("Enter choice: ");
        String userType = kbd.nextLine();
        if(!userType.equals("1") && !userType.equals("2"))
            System.out.println("\nInvalid choice!");
        else
        {
            User user = null;
            /**
             * request for non-repeated username
             */
            String username;
            do
            {
                username = validateString("user name");
                user = ftt.getUser(username);
                if(user!=null)
                    System.out.println("This username is already taken. Try again.");
               
            }
            while(user!=null);
            
            String password = validateString("password");
            String fullName = validateString("full name");
            String email = validateEmail("email");
                      
            if(userType.equals("1"))
            {
                user = ftt.addCustomer(username, password, fullName, email);
                System.out.println("\nCustomer Account successfully created."); 
            }
            else // food truck owner
            {
                String licenseNo = validateString("licence number");
                user = ftt.addFoodTruckOwner(username, password, fullName, email, licenseNo);
                System.out.println("\nFood Truck Owner Account successfully created.");
            }
             System.out.println(user.toString());
           
        }
    }
      
    /** 
     * login method
     * to find the user and authenticate
     * @return the logged in user
     */
    public static void login()
    {
        if (ftt.getFTTUsers().isEmpty())
            System.out.println("\n--- No users yet. Please sign up! ---");
        else {
                System.out.println("\n--- Logging in ---");
                System.out.print("\nEnter username: ");
                String username = kbd.nextLine();
                User user = ftt.getUser(username);
                if (user == null)
                    System.out.println("\nUsername not found!");

                else {
                    System.out.print("Enter password: ");
                    String password = kbd.nextLine();
                    if (!user.getPassword().equals(password))
                        System.out.println("\nInvalid password!");
                    userMenu(user);
                }
        }
    }
   
    /**
     * Menu for a logged in user
     * @param user 
     */
    public static void userMenu(User user)
    {
        String choice;
        do
        {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("Logged in as " + user.getUsername() + ". "
                    + "Hello " + user.getFullName() + "!");
            System.out.println("Your email is: " + user.getEmail());
            if (user instanceof FoodTruckOwner)
                System.out.println("Your license No is: " + ((FoodTruckOwner) user).getLicenseNo());
            
            System.out.println("\nWould you like to:");
            System.out.println("1. Update profile");
            System.out.println("2. View All Food Trucks & Reviews");
            if (user instanceof FoodTruckOwner)
            {
                System.out.println("3. Add Food Truck");
                System.out.println("4. Maintain Food Truck");
            }
            System.out.println("0. Log Out");
            System.out.println("Enter choice: ");
            choice = kbd.nextLine();
            switch(choice)
            {
                case "1": updateProfile(user); break;
                case "2": viewTruckInformation(user); break;
                case "3": 
                    if (user instanceof FoodTruckOwner)
                        addFoodTruck((FoodTruckOwner) user); 
                    else
                        System.out.println("\nInvalid choice!");
                    break;
                case "4": 
                    if (user instanceof FoodTruckOwner)
                        maintainFoodTruck((FoodTruckOwner) user); 
                    else
                        System.out.println("\nInvalid choice!");
                    break;
                case "0": 
                    System.out.println("\nSuccessfully logged out."); 
                    user = null; 
                    break;
                default: System.out.println("\nInvalid choice!"); break;
            }  // end switch    
        } while (!choice.equals("0"));
    }
    
    /**
     * Allow user to update profile
     * @param user who wants to update the profile
     */
    public static void updateProfile(User user)
    {
        String choice;
        do {
            System.out.println("\n--- Updating profile ---");
            System.out.println("Your info: " + user.toString());
            System.out.println("\nWould you like to update:");
            System.out.println("1. Full Name");
            System.out.println("2. Email");
            System.out.println("3. Password");
            if (user instanceof FoodTruckOwner)
                System.out.println("4. License No");
            System.out.println("0. Return to User Menu");
            System.out.print("Enter choice: ");
            choice = kbd.nextLine();
            switch(choice)
            {
                case "1": 
                    String fullName = validateString("new full Name");
                    user.setFullname(fullName);
                    System.out.println("\nSuccessfully changed full name to '" + fullName + "'");
                    break;
                case "2": 
                    String email = validateEmail("new email");
                    user.setEmail(email);
                    System.out.println("\nSuccessfully changed email to '" + email + "'");
                    break;
                case "3": 
                    String password = validateString("new password");
                    user.setPassword(password);
                    System.out.println("\nSuccessfully changed password");
                    break;
                case "4":
                    if (user instanceof FoodTruckOwner)
                    {   String licenseno = validateString("new license no");
                        ((FoodTruckOwner) user).setLicenseNo(licenseno);
                        System.out.println("\nSuccessfully changed license no to '" + licenseno + "'");
                        break;
                    }
                case "0": System.out.println("\nReturning to main menu..."); break;
                default:
                    System.out.println("\nInvalid choice"); break;
            }
        } while (!choice.equals("0"));
        
    }
    
    public static void viewTruckInformation(User user)
    {
        System.out.println("\n--- Viewing Food Truck Information ---");
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            System.out.println(ftt.allFoodTrucks());
            
            System.out.println("Reviews written by you: \n" + customer.getAllReviews());
            System.out.print("Average rating: " );
            if (user.getAverageRatings() == 0.0)
                System.out.println("None.");
            else
                System.out.println(user.getAverageRatings());
                      
            String choice;
            do {
                System.out.println("\nWould you like to: ");
                System.out.println("1. Enter review for a food truck");
                System.out.println("2. Look at reviews for a food truck");
                System.out.println("0. Return to user menu");
                System.out.println("Enter choice: ");
                choice = kbd.nextLine();
                switch(choice) {
                    case "1": reviewTruck(customer); break;
                    case "2": truckInfo(); break;
                    case "0": System.out.println("\nReturning to main menu..."); break;
                    default: System.out.println("\nInvalid choice!"); break;
                }
            }while (!choice.equals("0"));
        }
            
         else {
            FoodTruckOwner owner = (FoodTruckOwner) user;
            System.out.println("Trucks you owned: ");
            System.out.println(owner.getFoodTrucks());
            
            System.out.print("Your average rating is: ");
            if (owner.getAverageRatings() == -1.0)
                System.out.println("None. No reviews for your food trucks yet.");
            else {
                System.out.println(owner.getAverageRatings());
                System.out.println(owner.getAllReviews());
            }
            
            String choice;
            do {
                System.out.println("\nWould you like to: ");
                System.out.println("1. View all trucks in the system");
                System.out.println("2. Look at reviews for a food truck");
                System.out.println("0. Return to user menu");
                System.out.println("Enter choice: ");
                choice = kbd.nextLine();
                switch(choice) {
                    case "1": System.out.println("\n" + ftt.allFoodTrucks()); break;
                    case "2": truckInfo(); break;
                    case "0": System.out.println("\nReturning to main menu..."); break;
                    default: System.out.println("\nInvalid choice!"); break;
                }
            } while (!choice.equals("0"));   
        }        
    }
    
    public static void reviewTruck(Customer user)
    {
        System.out.println("\n--- Reviewing a Truck ---");      
        System.out.println("Please enter location or food type of truck to be reviewed: ");
        String tosearch = kbd.nextLine();
        String result = ftt.findFoodTruck(tosearch);
        
        if (result.equals("No Trucks Yet") || result.equals(""))
            System.out.println("\nNo truck found with that criteria.");
        
        else {
            System.out.println("\n" + result);
            System.out.println("Enter truck ID of truck you want to review: ");
            String id = kbd.nextLine();
            FoodTruck ft = ftt.findFoodTruckID(id);
            
            if (ft != null && ft.getStatus().equalsIgnoreCase("active")) {
                System.out.println("\n--- Reviewing food truck " + ft.getTruckName() + " ---");

                double drating = -1.0;
                while (drating > 5 || drating < 0) {
                    try
                    {
                        System.out.println("What is your rating for this truck? (0 to 5): ");
                        String rating = kbd.nextLine();
                        drating = Double.parseDouble(rating);
                        if (drating > 5 || drating < 0)
                            System.out.println("\nInvalid rating entered.");
                    }
                    catch (InputMismatchException a)
                    {
                        System.out.println("\nPlease only enter number between 0 to 5.");
                    }
                    catch (Exception a)
                    {
                        System.out.println("\nPlease only enter number between 0 to 5.");
                    }
                }

                String comments = validateString("comments");
                dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String sdate = dateFormat.format(date);
                Review r = ft.addReview(user, sdate, comments, drating);

                System.out.println("\nSuccessfully added review.");
                System.out.println(r.toString());
            }
            
            else if (ft.getStatus().equalsIgnoreCase("inactive"))
                System.out.println("\nYou can only review active food trucks. Please try again.");
            
            else
                System.out.println("\nFood Truck with that ID not found! Please try again.");
        }        
    }
    
    public static void truckInfo()
    {
        System.out.println("\n--- Viewing reviews written ---");
        System.out.println("\nEnter truck ID of truck reviews you want to view: ");
        String id = kbd.nextLine();
        FoodTruck ft = ftt.findFoodTruckID(id);    
        if (ft != null)
            sortList(ft);      
        else
            System.out.println("\nFood Truck with that ID not found! Please try again.");
    }
    
    public static void sortList(FoodTruck ft)
    {
        if (ft.getReviews().isEmpty()) {
            System.out.println("\n--- Reviews for food truck " + ft.getTruckName() + " ---");
            System.out.println("No reviews for this yet.");
        }
        
        else {
            ArrayList<Review> rlist = ft.getReviews();

            System.out.println("\nWould you like to sort by:");
            System.out.println("1. Date");
            System.out.println("2. Rating");
            System.out.print("Enter choice: ");
            String choice = kbd.nextLine();
            if (choice.equals("1"))
                Collections.sort(rlist);
            else 
                Collections.sort(rlist, new RatingComparator());

            System.out.println("\n--- Reviews for food truck " + ft.getTruckName() + " ---");
            for (Review r:rlist)
                System.out.println(r.toString());
        }
    }
    
    /** 
     * Allow a Food truck owner to maintain his or her food trucks
     * @param owner the owner who is currently logged in 
     */
    public static void addFoodTruck(FoodTruckOwner owner)
    {
        System.out.println("\n--- Adding a new food truck ---");
        String foodTruckName = validateString("food truck name");
        String foodType = validateString("food type");
        FoodTruck ft = ftt.addFoodTruck(owner, foodTruckName, foodType);
        if (ft!= null)
            System.out.println("\nFood Truck successfully added: " + ft.toString());
        
    }
    /** 
     * Allow a Food truck owner to maintain his or her food trucks
     * @param user wanting to maintain his/her trucks
     */
    public static void maintainFoodTruck(FoodTruckOwner user)
    {
        if (user.getNumTrucks() == 0)
            System.out.println("\nNo trucks to maintain yet. Please add a new truck first.");
        
        else {
            System.out.println("\n--- Maintaining Food Truck ---");
            System.out.print("\nEnter Food Truck ID to maintain: ");
            String wantedID = kbd.nextLine();
            FoodTruck ft = user.findFoodTruck(wantedID);
            if (ft == null)
                System.out.println("\nYou do not own this food truck");
            else
            {
                String choice;
                 do {
                    System.out.println("\n--- Managing Food Truck ---");
                    System.out.println("Truck info : " + ft.toString());

                    System.out.println("\nWould you like to update:");
                    System.out.println("1. Truck Name");
                    System.out.println("2. Food Type");
                    System.out.println("3. Location");
                    System.out.println("4. Status");
                    System.out.println("0. Return to User Menu");
                    System.out.print("Enter choice: ");
                    choice = kbd.nextLine();
                    switch(choice)
                    {
                        case "1": 
                            String truckName = validateString("new truck name");
                            ft.setTruckName(truckName);
                            System.out.println("\nSuccessfully changed truck name to '" + truckName + "'");
                            break;
                        case "2": 
                            String foodType = validateString("new food type");
                            ft.setFoodType(foodType);
                            System.out.println("\nSuccessfully changed food type to '" + foodType + "'");
                            break;
                        case "3": 
                            String location = validateString("new location");
                            ft.setLocation(location);
                            System.out.println("\nSuccessfully changed location to '" + location + "'");
                            break;
                        case "4":
                            String status = validateString("new status (only active or inactive)");
                            if (!(status.equals("active") || status.equals("inactive")))
                                System.out.println("Status must be 'active' or 'inactive' only");
                            else {
                                ft.setStatus(status); 
                                System.out.println("\nSuccessfully changed status to '" + status + "'");
                            }
                            break;
                        case "0": System.out.println("\nReturning to main menu..."); break;
                        default:
                            System.out.println("\nInvalid choice"); break;

                    }
                } while (!choice.equals("0"));
            }
        }
    }
    
    public static void saveData()
    {
        System.out.println("\n--- Saving the FTT session data to file ---");
        String filename = "fttdata.txt";
        
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try
        {
            File file = new File(filename);
            
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ftt);
            oos.close();
            System.out.println("Success... Goodbye!");
        }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
        
        finally
        {
            try 
            {
                if (fos != null)
                    fos.close();
                if (oos != null)
                    oos.close();
            }
            catch (IOException io)
            {
                System.out.println(io.getMessage());
            }
        }
    }
    
    public static void loadData()
    {
        System.out.println("\n--- Loading existing FTT data into session ---");
        String filename = "fttdata.txt";
        
        File file = new File(filename);
        if (file.exists())
            System.out.println("Success!");
        else
            System.out.println("File not found!");

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try
        {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            
            ftt = (FoodTruckTracker) ois.readObject();
            ftt.updateFTNo();
            ois.close();
        }
        
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        finally
        {
            try
            {
                if (fis != null)
                    fis.close();
                if (ois != null)
                    ois.close();
            }
            catch (IOException io)
            {
                System.out.println(io.getMessage());
            }
        }   
    }
    
}
