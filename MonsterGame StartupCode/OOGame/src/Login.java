
public class Login {
	
	private String username;
	private String password;
	private static String usernameInput;
	private static String passwordInput;
	
    public static boolean authenticate(String username, String password) {
        // authenticate against user input to verify password.
        if (username.equals(usernameInput) && password.equals(passwordInput)) {
            return true;
        }
        return false;
    }
    
    public String getUsername()
    {
    	return username;
    }
    
    public String getPassword()
    {
    	return password;
    }

}
