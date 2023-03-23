package Login.Bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
    /**
	 * used to store the front end information
	 */
	private static final long serialVersionUID = -6050463920427421384L;
    private String username;
    private String password;

    /*
     * gets the username from the front end 
     * @return String Username from the front end
     */
    public String getUsername() {
        return username;
    }
    /*
     * sets the username for login implementations
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /*
     * gets the password from the front end
     * @return String password
     */
    public String getPassword() {
        return password;
    }
    /*
     * sets the password
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }
}