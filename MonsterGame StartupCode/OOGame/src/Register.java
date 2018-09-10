import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.*;

public class Register extends RegisterDialog{
	
	private static ArrayList<Register> players = new ArrayList<Register>();

	public Register(JTextField tfUsername, JPasswordField pfPassword, JTextField tfName, JTextField tfDateOfBirth,
			JTextField tfEmail) {
		super(tfUsername, pfPassword, tfName, tfDateOfBirth, tfEmail);
		
		players.add(this);
	}
	
	
	
	

}
