/**
 * Full name: Kevin Le
 * Student ID: 2406054 
 * Chapman email: kevle@chapman.edu
 * Course number and section: CPSC 231-04 
 * Mastery Project 1: Part 2 - Profile
*/

/*
Description: A program to simulate a Dating App using the Profile class.
             Has name, age, like array, match array attributes. Can simulate the "swipe right"
             function of dating apps in which  profiles can "swipe right" on each other and if 
             two profiles swipe right, they will match and get added to their match array.
             In the case that one profile wants to revert their swipe right, they can swipe left to
             remove a profile from the like list. The two arrays of a Profile object stores other profiles objects
*/

/**
 * The Profile class is used to simulate a basic Dating Profile
 * @author Kevin Le
 * @version 1.0
 * @see Profile
*/

public class Profile {

    // MEMBER VARIABLES
    /** an String representation of the Profile name*/ 
    private String m_name;

    /** an double representation of the Profile age*/ 
    private double m_age;

    /** an Profile array representing of the Profile likes*/ 
    private Profile[] m_like;

    /** an Profile array representing of the Profile matches*/ 
    private Profile[] m_match;

    // DEFAULT CONSTRUCTOR
    /** 
      * The default constructor for Profile class  
    */     
    public Profile() {
       m_name = "N/A";
       m_age = 0.0;
       m_like = new Profile[10]; 
       m_match = new Profile[10];

    }

    // OVERLOADED CONSTRUCTOR
    /** 
     * Create a Profile instance with attributes specified 
     * @param name The Profile's name
     * @param age The Profile's age 
    */
    public Profile(String name, double age) {
        m_name = name;
        m_age = age;
        m_like = new Profile[10];
        m_match = new Profile[10]; 

    }

    // COPY CONSTRUCTOR
    /**
     * The Profile copy constructor
     * @param p The Profile being copied
     */
    public Profile(Profile p) {
        m_name = p.m_name;
        m_age = p.m_age;
        m_like = new Profile[10];
        m_match = new Profile[10];

        // Fills both like and match arrays with Profile p's values
        for (int i = 0; i < 10; ++i) {
            m_like[i] = p.m_like[i];
            m_match[i] = p.m_match[i];
        } 
        
    }

    // ACCESSORS
    /**
     * Accessor for m_name attribute
     * @return a String representing the Profile's name
     */
    public String getName() {
        return m_name;
    }
    /**
     * Accessor for m_age attribute
     * @return a double representing the Profile's age
     */
    public double getAge() {
        return m_age;
    }
    /**
     * Accessor for m_like attribute
     * @return a String of the Profile's likes
     */
    public String getLike() {
        Profile[] thisLikeArray = this.m_like;
        String s = "";
        s += "Liked: ";
        for (int i = 0; i < (thisLikeArray).length; ++i) {
            if (thisLikeArray[i] != null) {
                s += thisLikeArray[i].getName()+", ";
            }
        }
        return s;
    }
    /**
     * Accessor for m_match attribute
     * @return a String of the Profile's matches
     */
    public String getMatch() {
        String s = "";
        Profile[] thisMatchArray = this.m_match;
        s += "Match: ";
        for (int i = 0; i < (thisMatchArray).length; ++i) {
            if (thisMatchArray[i] != null) {
                s += thisMatchArray[i].getName()+", ";
            }
        }
        return s;
    }

    // MUTATORS
    /**
     * Mutator for m_name attribute
     * @param name The new name for Profile
     */
    public void setName(String name) {
        m_name = name;
    }
    /**
     * Mutator for m_age attribute
     * @param age The new age for Profile
     */
    public void setAge(double age) {
        m_age = age;
    }
    /**
     * Adds Profile "this" to Profile p's like array. Checks if both Profiles liked each other and adds to both match arrays
     * @param p The Profile that Profile this "swiped right" on
     */
    public void swipeRight(Profile p) {
        int i;
        int j;
        Profile[] pLikeArray = p.m_like;
        Profile[] thisLikeArray = this.m_like;
        Profile[] pMatchArray = p.m_match;
        Profile[] thisMatchArray = this.m_match;

        System.out.println(this.getName()+" swiped right on "+p.getName()+".\n");

        // Loops through like array of Profile p
        boolean thisIsLiked = false;
        for (i = 0; i < 10; ++i) {
            // If Profile p is full (does not contain "this" and no empty elements)
            if  ( (i == 9) && (pLikeArray[i] instanceof Profile) ) {
                System.out.println("\t"+p.getName()+" already has 10 likes.\n");
                break;
            }
            // If "this" is already in Profile p's like list
            else if (pLikeArray[i] == this) {
                System.out.println("\t"+this.getName()+" already liked "+p.getName()+"!\n");
                break;
            }

            // If Profile p at [i] has an instance of another Profile already
            else if (pLikeArray[i] instanceof Profile) {
                continue;
            }

            // If Profile p's like list has an empty element
            else if (pLikeArray[i] == null) {
                pLikeArray[i] = this;
                break;
            }
  
        }

        for (Profile currProfile : thisLikeArray) {   
            // If "this" is already in Profile p's like list
            if (p.equals(currProfile)) {
                thisIsLiked = true;
                break;
            }
            // If another Profile
            else {
                continue;
            }
        }
        // If both profiles liked each other
        if (thisIsLiked) {
            /* 
                If the last element of either match array has a Profile.
                Match array cannot remove Profiles once added. A nonempty
                last element indicates a full array 
            */
            if ( (pMatchArray[9] instanceof Profile) || (thisMatchArray[9] instanceof Profile)  ) {
                System.out.println("\n\tSomeone already has 10 matches!\n");
                return; // Skips the nested loops below when either match arrays are full
            }

            for (i = 0; i < 10; ++i) {
                for (j = 0; j < 10; ++j) {
                    // find null elements in both match arrays and add both profiles into arrays
                    if ( (thisMatchArray[i] == null) && (pMatchArray[j] == null) ) {
                        System.out.println("\n\tMatch!\n");
                        thisMatchArray[i] = p;
                        pMatchArray[j] = this;
                        i = 10;
                        break;
                    }


                }
            }
        }
    }

    /**
     * Removes Profile p from "this" like list
     * @param p the Profile to be removed
     */
    public void swipeLeft(Profile p) {
        System.out.println(this.getName()+" swiped left on "+p.getName()+".");
        for (int i = 0; i < 10; ++i) {
            if (this.m_like[i] == p) {
                this.m_like[i] = null;
                System.out.println("\n\t"+p.getName()+" was removed from "+this.getName()+"'s like list.\n");
                break;
            }
        }

    }
    
    // TOSTRING METHOD FOR PRINTING
    /**
     * Returns a string representation of the Profile instance
     * @return a pretty print version of the object
     */
    public String toString() {
        String s = "";
        s += "Name: "+this.getName()+"\n"; // Utilizing Accessors for Demonstration
        s += "Age: "+this.getAge()+"\n";
        s += this.getLike();
        s += "\n";
        s += this.getMatch();
        s += "\n";
        return s;
    }
    
    // EQUALS METHOD FOR COMPARING TWO PROFILE INSTANCES
    /**
     * Determines whether or not two students are equal
     * @param o Object to be compared for equality
     * @return True if the objects are equal and false otherwise
     */
    public boolean equals(Object o) {
        // If same memory address
        if (this == o) {
            return true;
        }
        // If not a Profile
        if (!(o instanceof Profile)) {
            return false;
        }
        Profile p = (Profile)o;
        return ( (this.m_name == p.m_name) && (this.m_age == p.m_age) );

    }
    
    // MAIN METHOD
    public static void main(String[] args) {

        // Create generic profiles to demonstrate when arrays are full
        Profile test1 = new Profile("Test1", 1.0);
        Profile test2 = new Profile("Test2", 2.0);
        Profile test3 = new Profile("Test3", 3.0);
        Profile test4 = new Profile("Test4", 4.0);
        Profile test5 = new Profile("Test5", 5.0);
        Profile test6 = new Profile("Test6", 6.0);
        Profile test7 = new Profile("Test7", 7.0);
        Profile test8 = new Profile("Test8", 8.0);
        Profile test9 = new Profile("Test9", 9.0);
        Profile test10 = new Profile("Test10", 10.0);

        // Creates Sam's Profile with Overloaded Constructor
        Profile sam = new Profile("Sam", 23.0);
        System.out.println("\n"+sam);

        // Creates Alex's Profile with Default Constructor
        Profile alex = new Profile();

        // Uses Mutators to change Alex's name and age attributes
        alex.setName("Alex");
        alex.setAge(25.0);
        System.out.println(alex);

        // Creates Jim's Profile 
        Profile jim = new Profile("Jim", 30.0);
        System.out.println(jim);

        System.out.println("********************************\n");

        // Uses Copy Instructor to create another Jim profile
        Profile jim2 = new Profile(jim);

        // Demonstrates equals method
        System.out.println("Does Jim equal Jim2? "+jim.equals(jim2));
        System.out.println("\nDoes Jim equal Alex? "+jim.equals(alex)+"\n");

        // Demonstrate swipeRight method
        alex.swipeRight(sam);
        alex.swipeRight(sam); // When someone swipes right twice
        sam.swipeRight(alex);
        alex.swipeRight(jim);

        System.out.println("********************************\n");

        System.out.println(alex);
        System.out.println(sam);
        System.out.println(jim);

        // Demonstrate swipeLeft method
        alex.swipeLeft(sam);
        sam.swipeLeft(alex);
        jim.swipeRight(alex);

        // When a like array is full
        test1.swipeRight(alex);
        test2.swipeRight(alex);
        test3.swipeRight(alex);
        test4.swipeRight(alex);
        test5.swipeRight(alex);
        test6.swipeRight(alex);
        test7.swipeRight(alex);
        test8.swipeRight(alex);
        test9.swipeRight(alex);
        test10.swipeRight(alex); // Will not add to Like array because Alex's array is full

        System.out.println("********************************\n");

        // When a match array is full
        alex.swipeRight(test1);
        alex.swipeRight(test2);
        alex.swipeRight(test3);
        alex.swipeRight(test4);
        alex.swipeRight(test5);
        alex.swipeRight(test6);
        alex.swipeRight(test7);
        alex.swipeRight(test8);
        alex.swipeRight(test9); // Will not match because Match array for Alex is full

        System.out.println("********************************\n");

        // Resulting Profiles after method calls
        System.out.println(alex);
        System.out.println(sam);
        System.out.println(jim);
    }
}
