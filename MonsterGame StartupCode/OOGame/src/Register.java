import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.*;
import java.lang.Object;

public class Register{
	
	public static boolean authenticatePassword(String password, String confirmPassword)
	{
		if(password.compareTo(confirmPassword) == 0)
        {
            
            JOptionPane.showMessageDialog(null,"Password Registered Successfully");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Password did not match");
            return false;
        }
	}
	
	
	

}
