package Login.Bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6050463920427421384L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}