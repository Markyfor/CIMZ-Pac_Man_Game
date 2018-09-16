import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class User extends RegisterDialog{
	
	static ArrayList<User> users = new ArrayList<User>();

	public User(String username, String password, String name, String dateOfBirth, String email) {
		super(username, password, name, dateOfBirth, email);
		
	}

	public void setUsername(String username)
    {
    	this.username = username;
    }
    
    public void setPassword(String password)
    {
    	this.password = password;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public void setEmail(String email)
    {
    	this.email = email;
    }
    
    public void setDateOfBirth(String dateOfBirth)
    {
     	 this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getName()
    {
    	return name;
    }
    @Override
    public String getDateOfBirth()
    {
    	return dateOfBirth;
    }
    @Override
    public String getEmail()
    {
    	return email;
    }
	
	
}
