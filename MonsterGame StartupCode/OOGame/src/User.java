import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class User extends RegisterDialog{
	
	protected static ArrayList<User> users = new ArrayList<User>();

	public User(String username, String password, String name, String email, String dateOfBirth) {
		super(username, password, name, email, dateOfBirth);
		
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
    public String getEmail()
    {
    	return email;
    }
    @Override
    public String getDateOfBirth()
    {
    	return dateOfBirth;
    }
	
    public static User addUser(String username, String password, String name,  String email, String dateOfBirth) 
	{
		boolean found = false;
    	for (int i = 0; i < users.size(); i++)
    	{
    		if((username.compareToIgnoreCase(users.get(i).getUsername())==0)) 
    		{
    			found = true;
    			JOptionPane.showMessageDialog(null,
                            "This user" + users.get(i).getUsername() +" already exists! Would you like to Login?",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
    			break;
    		}
    		
    		
    	}
    	if(!found)
    	{
				User user = new User(username, password, name, email, dateOfBirth);
				User.users.add(user);
				for(User user1 : users)
				{
				System.out.println(user1.getUsername());
				}
					JOptionPane.showMessageDialog(null, "The player : "+ user.getUsername()+" has been added",
							"Player :"+user.getEmail(), JOptionPane.INFORMATION_MESSAGE);
				return user;
    		
		}
		return null;
		
	}
    
    public static void removeUser(String username, String email)
    {
    	int idx = 0;
    	for (int i = 0; i < User.users.size(); i++) 
    	{
    	    if (username.compareToIgnoreCase(users.get(i).getUsername())==0 && email.compareToIgnoreCase(users.get(i).getEmail())==0)
    	      	JOptionPane.showMessageDialog(null, "The Player : "+users.get(i).getUsername()+ " has been removed!",
    	    			email,JOptionPane.INFORMATION_MESSAGE);
    	    	idx = i;
    	}
    	User.users.remove(idx);
    }
	
}
