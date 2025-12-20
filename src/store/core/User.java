// name : Sarah Gabay
// id : 329185771
// name : Shahar Ezra
// id : 329186118
package store.core;

/**
 * Abstract base class for all users in the store system.
 * Stores basic identifying information such as username and email.
 */
public abstract class User {

    private final String username;
    private final String email;

    /**
     * Creates a new user with the given username and email.
     *
     * @param username unique identifier of the user
     * @param email user's email address
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * Returns the username of the user.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email address of the user.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns a textual representation of the user.
     *
     * @return string describing the user
     */
    @Override
    public String toString() {
        return "User{username='" + username + "', email='" + email + "'}";
    }

    /**
     * Compares two users by their username.
     *
     * @param obj object to compare
     * @return true if usernames are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return username.equals(other.username);
    }

    /**
     * Generates hash code based on username.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
