import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	
	
    public static boolean authenticate(String username, String password) {
        // authenticate against user input to verify password.
    	for (User user: User.users)
    	{
    		if (((username.equalsIgnoreCase(user.getUsername())) && ((password.equals(user.getPassword()))))) {
    			return true;
    		}
    	}
        return false;
    }
    
/*    public static User addUser(JTextField tfUsername, JPasswordField pfPassword, JTextField tfName, JTextField tfDateOfBirth,
			JTextField tfEmail)
    {
    	boolean found = false;
    	for (int i = 0; i < User.users.size(); i++)
    	{
    		if((tfUsername.getText().trim().compareToIgnoreCase(User.users.get(i).getUsername())==0)) 
    		{
    			found = true;
    			JOptionPane.showMessageDialog(null,
                            "This user" + User.users.get(i).getUsername() +" already exists! Would you like to Login?",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
    			break;
    		}
    		
    		
    	}
    	if(!found) 
			{
				User user = new User(tfUsername.getText().trim(), pfPassword.getPassword().toString(), tfName.getText().trim(), tfDateOfBirth.getText().trim(), tfEmail.getText().trim());
				User.users.add(user);
				return user;
			}
			else
			{
				return null;
			}
    }*/

	
    

}
