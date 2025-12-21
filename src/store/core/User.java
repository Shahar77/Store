package store.core;

/**
 * Represents a generic user in the system.
 */
public class User implements StoreEntity{

    private String username;

    /**
     * Creates a new user with a username.
     * @param username unique username
     */
    public User(String username){
        this.username=username;
    }

    /**
     * Returns the username.
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Returns a display name for UI usage.
     * @return display name
     */
    @Override
    public String getDisplayName(){
        return username;
    }

    /**
     * Returns detailed display information.
     * @return display details
     */
    @Override
    public String getDisplayDetails(){
        return "User: "+username;
    }
}
