import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class RegisterDialog extends JDialog {
	 
    protected JTextField tfUsername;
    protected JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JPasswordField pfReconfirmPassword;
    private JLabel lbReconfirmPassword;
    protected JTextField tfName;
    private JLabel lbName;
    protected JTextField tfDateOfBirth;
    private JLabel lbDateOfBirth;
    protected JTextField tfEmail;
    private JLabel lbEmail;
    private JButton btnRegister;
    private JButton btnCancel;
    private boolean succeeded;
	protected String username;
	protected String password;
	protected String name;
	protected String email;
	protected String dateOfBirth;
    
    public RegisterDialog(String username, String password, String name, String dateOfBirth,
    		String email)
    {
		this.username = username;
    	this.password = password;
    	this.name = name;
    	this.email = email;
    	this.dateOfBirth = dateOfBirth;
    }
 
    public RegisterDialog(Frame parent) {
        super(parent, "Register", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        
        lbReconfirmPassword = new JLabel("Reconfirm Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbReconfirmPassword, cs);
 
        pfReconfirmPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(pfReconfirmPassword, cs);
             
        lbName = new JLabel("Name: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(lbName, cs);
 
        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(tfName, cs);
 
        lbEmail = new JLabel("Email: ");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(lbEmail, cs);
 
        tfEmail = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(tfEmail, cs);
        
        lbDateOfBirth = new JLabel("Date of Birth: ");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        panel.add(lbDateOfBirth, cs);
        
        tfDateOfBirth = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 2;
        panel.add(tfDateOfBirth, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnRegister = new JButton("Register");
 
        btnRegister.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (getUsername() != null && getPassword() != null) 
                {
                	if(Register.authenticatePassword(getPassword(), getConfirmPassword())) 
                	{
                		Login.addUser(getUsername(), getPassword(), getName(), getEmail(), getDateOfBirth());
        				JOptionPane.showMessageDialog(RegisterDialog.this,
        						"Hi " + getUsername() + "! You have successfully registered.",
        						"Login",
        						JOptionPane.INFORMATION_MESSAGE);
        				succeeded = true;
        				dispose();
                	}
                	
                	else
                	{
                		JOptionPane.showMessageDialog(RegisterDialog.this,
        						"Hi " + getUsername() + "! Please enter a valid password",
        						"Register",
        						JOptionPane.ERROR_MESSAGE);
                		dispose();
                	}
                }
                else 
                {
                    JOptionPane.showMessageDialog(RegisterDialog.this,
                            "Invalid username or password",
                            "Register",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    pfReconfirmPassword.setText("");
                    tfName.setText("");
                    tfDateOfBirth.setText("");
                    tfEmail.setText("");
                    succeeded = false;
 
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnRegister);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public JTextField setUsername()
    {
    	return this.tfUsername;
    }
    
    public JPasswordField setPassword()
    {
    	return this.pfPassword;
    }
    
    public JTextField setName()
    {
    	return this.tfName;
    }
    
    public JTextField setEmail()
    {
    	return this.tfEmail;
    }
    
    public JTextField setDateOfBirth()
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    	//tfDateOfBirth = dateFormat.format((tfDateOfBirth).validate());
    	return this.tfDateOfBirth;
    }
    
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    
    public String getConfirmPassword() 
    {
    	return new String(pfReconfirmPassword.getPassword());
    }
    
    public String getName()
    {
    	return tfName.getText().trim();
    }
    
    public String getDateOfBirth()
    {
    	return tfDateOfBirth.getText().trim();
    }
    
    public String getEmail()
    {
    	return tfEmail.getText().trim();
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
}
