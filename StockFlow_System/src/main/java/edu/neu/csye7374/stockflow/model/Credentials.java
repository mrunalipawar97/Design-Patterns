package edu.neu.csye7374.stockflow.model;

/**
 * The Credentials class represents a simple model for user authentication
 * containing a username and a password.
 */
public class Credentials {
	
    private String username;
    private String password;

    public Credentials() {}

    /**
     * Parameterized constructor to initialize username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
