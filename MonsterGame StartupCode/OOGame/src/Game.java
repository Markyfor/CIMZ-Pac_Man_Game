 import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

/* This class is the main System level class which creates all the objects 
 * representing the game logic (model) and the panel for user interaction. 
 * It also implements the main game loop 
 */



public class Game extends JFrame {

   private JComboBox cmbMessageList;

   private final int TIMEALLOWED = Integer.parseInt((String)cmbMessageList.getSelectedItem());

   private JButton up = new JButton("up"); 
   private JButton down = new JButton("down"); 
   private JButton left = new JButton("left"); 
   private JButton right = new JButton("right");
   private JButton start = new JButton("start");
   private JLabel mLabel = new JLabel("Time Remaining : "+TIMEALLOWED);
   
   private Grid grid;
   private Player player;
   private Monster monster;
   private BoardPanel bp;
   
   
   /* This constructor creates the main model objects and the panel used for UI.
    * It throws an exception if an attempt is made to place the player or the 
    * monster in an invalid location.
    */
   public Game() throws Exception
   {
	  grid = new Grid();
	  player = new Player(grid,0,0);
	  monster = new Monster(grid,player,5,5);
      bp = new BoardPanel(grid,player,monster);

      // Create a separate panel and add all the buttons 
      JPanel panel = new JPanel();
      panel.add(up); 
	  panel.add(down); 
	  panel.add(left); 
	  panel.add(right);
	  panel.add(start);
	  panel.add(mLabel);
	  
	  // add Action listeners to all button events
	  up.addActionListener(bp);
	  down.addActionListener(bp);
	  left.addActionListener(bp);
	  right.addActionListener(bp);
	  start.addActionListener(bp);

	  // add panels to frame
	  add (bp, BorderLayout.CENTER);             
      add (panel,BorderLayout.SOUTH);
   }	   
    
   // method to delay by specified time in ms
   public void delay(int time)
   {
   	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	   
   }
   
     
   /* This method waits until play is ready (until start button is pressed) 
    * after which it updates the moves in turn until time runs out (player won) 
    * or player is eaten up (player lost).
    */
   public String play()
   {
	  int time = 0; 
	  String message;
      player.setDirection(' '); // set to no direction
      while (!player.isReady()) 
          delay(100);
	  do {
      
		 Cell newPlayerCell = player.move();
         if (newPlayerCell == monster.getCell())
        	break;
         player.setDirection(' ');   // reset to no direction 
         
         Cell newMonsterCell = monster.move();
         if (newMonsterCell == player.getCell())
         	break;        

         // update time and repaint
         time++;
         mLabel.setText("Time Remaining : "+ (TIMEALLOWED - time));
         delay(1000);
         bp.repaint();      

	  } while (time < TIMEALLOWED);

	  if (  time < TIMEALLOWED)			// players has been eaten up
    	  message =  "Player Lost"; 
      else 			
    	  message =  "Player Won";   

      mLabel.setText(message);
	  return message;
   }
   
   public static void main(String args[]) throws Exception
   {
	   Scanner inputStream = null;
		try {

			inputStream = new Scanner(new File("Users.txt"));

			while (inputStream.hasNextLine()) {

				String line = inputStream.nextLine();

				String[] temp = line.split(":");

				User.addUser(temp[0], temp[1], temp[2], temp[3],temp[4]);
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error opening file" + " Users.txt" ,
					"Load From File", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error opening file" + " Users.txt");
			System.exit(0);

		}

		inputStream.close();
		
	   
	   final JFrame frame = new JFrame("Monster Hunt Pacman Style");
	   final JButton btnRegister = new JButton("Click to Register");
       final JButton btnLogin = new JButton("Click to Login");
       final JButton btnSysAdminLogin = new JButton("System Administrator");
       final JButton btnRemovePlayer = new JButton("Remove User");
       final JButton btnChangeTimer = new JButton("Change Timer");

       btnLogin.addActionListener(
               new ActionListener(){
                   public void actionPerformed(ActionEvent e) {
                       LoginDialog loginDlg = new LoginDialog(frame);
                       loginDlg.setVisible(true);
                       // if logon successfully
                       if(loginDlg.isSucceeded()){
                           btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                           //btnPlay.setText("Hi " + loginDlg.getUsername() + "! Ready to Play!!");
                           new Thread(() -> {
                               try {
								Game.run();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                               SwingUtilities.invokeLater(() -> btnLogin.setText("Ready!!"));
                   }).start();
                          // Game.run();
						
                           
                       }
                   }
               });
       
       btnRegister.addActionListener(
               new ActionListener(){
                   public void actionPerformed(ActionEvent e) {
                       RegisterDialog registerDlg = new RegisterDialog(frame);
                       registerDlg.setVisible(true);
                       // if register successfully
                       if(registerDlg.isSucceeded()){
                    	   for(User user : User.users)
                    	   {
                           btnRegister.setText("Hi " + user.getUsername() + "! You are successfully registered to Play");
                    	   }
                       }
                   }
               });
       
       btnSysAdminLogin.addActionListener(
               new ActionListener(){
                   public void actionPerformed(ActionEvent e) {
                       SysAdminDialog sysAdDlg = new SysAdminDialog(frame);
                       sysAdDlg.setVisible(true);
                       // if logon successfully
                       if(sysAdDlg.isSucceeded()){
                    	   new Thread(() -> {
                               
                    	   frame.getContentPane().add(btnRemovePlayer);
                    	   frame.getContentPane().add(btnChangeTimer);
                    	   frame.setVisible(true);
                    	   btnRemovePlayer.addActionListener(new ActionListener()
                    			   {
                    		   			public void actionPerformed(ActionEvent e) {
                    		   				
                    		   				JTextField tfUsername = new JTextField(20);
                    		   				JTextField tfEmail = new JTextField(20);
                    		   				JPanel removeUser = new JPanel();
                    		   				removeUser.add(new JLabel("Username :"));
                    		   				removeUser.add(tfUsername);
                    		   				removeUser.add(Box.createHorizontalStrut(15));
                    		   				removeUser.add(new JLabel("Email :"));
                    		   				removeUser.add(tfEmail);
                    		   				JOptionPane.showConfirmDialog(null, removeUser, "Please enter Username and Email",
                    		   						JOptionPane.OK_CANCEL_OPTION);
                    		   				User.removeUser(tfUsername.getText().trim(), tfEmail.getText().trim());
                    		   				
                    		   				
                    		   			}
                    			   });
                    	   btnChangeTimer.addActionListener(new ActionListener()
                    			   {
                    		   			public void actionPerformed(ActionEvent e) 
                    		   			{
                    		   				String [] timesStrings = {"Change Time to 50", "Change Time to 100",
                    		   						"Change Time to 150", "Change Time to 200"};
                    		   				JComboBox cmbMessageList = new JComboBox(timesStrings);
                    		   				JLabel lblText = new JLabel("Change Timer");
                    		   				JPanel changeTimer = new JPanel();
                    		   				final JFrame frame = new JFrame();
                    		   		        frame.setPreferredSize(new Dimension(200, 200));
                    		   		        final JToolBar toolBar = new JToolBar();

                    		   		        //Create the Menu Bar
                    		   		        final JMenuBar menuBar = new JMenuBar();
                    		   		        final JMenu menu = new JMenu("Options");
                    		   		        menu.setMnemonic(KeyEvent.VK_A);
                    		   		        menu.getAccessibleContext().setAccessibleDescription(
                    		   		          "Option 1");
                    		   		        menuBar.add(menu);
                    		   		        JMenuItem menuItem = new JMenuItem("Option 2",
                    	                         KeyEvent.VK_T);
                    		   		        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    		   		        		KeyEvent.VK_1, ActionEvent.ALT_MASK));
                    		   		        menuItem.getAccessibleContext().setAccessibleDescription(
                    		   		        		"This doesn't really do anything");
                    		   		        
                    		   		        toolBar.add(changeTimer);
                    		   		        JMenuItem menuItem1 = new JMenuItem("Options");
                    		   		        menuItem1.addActionListener(this);
                    		   		        menu.add(menuItem1);
                    		   		        menu.add(new JMenuItem(new AbstractAction("Option 1") {
                    		   		            public void actionPerformed(ActionEvent e) {
                    		   		                JOptionPane.showMessageDialog(frame, "Option 1 selected");
                    		   		            }
                    		   		        }));
                    		   		        menu.add(new JMenuItem(new AbstractAction("Option 2") {
                    		   		            public void actionPerformed(ActionEvent e) {
                    		   		                JOptionPane.showMessageDialog(frame, "Option 2 selected");
                    		   		            }
                    		   		        }));
                    		   		        
                    		   		        menu.add(menuItem);
                    		   		        menu.add(menuItem1);

                    		   		        final JButton btnOptions = new JButton("Options");
                    		   		        btnOptions.addMouseListener(new MouseAdapter() {
                    		   		            public void mousePressed(MouseEvent e) {
                    		   		                menuBar.show();
                    		   		            }
                    		   		        });
                    		   		        //toolBar.add(btnChangeTimer);

                    		   		        //frame.getContentPane().add(toolBar, BorderLayout.NORTH);
                    		   		        //frame.getContentPane().add(menuBar, BorderLayout.CENTER);
                    		   		        //frame.getContentPane().add(menuItem, BorderLayout.NORTH);
                    		   		        //frame.getContentPane().add(lblText, BorderLayout.CENTER);
                    		   		        frame.getContentPane().add(cmbMessageList, BorderLayout.NORTH);
                    		   		        //frame.getContentPane().add(menu, BorderLayout.NORTH);
                    		   		        frame.setJMenuBar(menuBar);
                    		   		        frame.pack();
                    		   		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    		   		        frame.setLocationRelativeTo(null);
                    		   		        frame.setVisible(true);
                    		   				//changeTimer.add(popup);
                    		   			}
                    			   });
                           
                    	   SwingUtilities.invokeLater(() -> btnRemovePlayer.setText("Remove User"));
                           }).start();
                           
                       }
                   }
               });

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setTitle("Login Monster Game");
       frame.setSize(300, 300);
       frame.setLayout(new FlowLayout());
       frame.setLocationRelativeTo(null); 
       frame.getContentPane().add(btnLogin);
       frame.getContentPane().add(btnRegister);
       frame.getContentPane().add(btnSysAdminLogin);
       //frame.getContentPane().add(btnPlay);
       frame.setVisible(true);
       
       /* Game game = new Game();
       game.setTitle("Monster Game");
       game.setSize(700,700);
       game.setLocationRelativeTo(null);  // center the frame
       game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       game.setVisible(true);
       game.play();*/
       
       
   }
   
   public static void run() throws Exception
   {
	   try {
			 Game game;
			 game = new Game();
			 game.writeToFile();
			 game.setTitle("Monster Game");
             game.setSize(700,700);
             game.setLocationRelativeTo(null);  // center the frame
             game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             game.setVisible(true);
             game.play();
             
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
   }
   
   /*Write to file function
	 * Will write existing user array to "Users.txt" external file which is loaded 
	 * back into the system upon start.
	 * Will write items to array when called upon exit also
	 */

	private void writeToFile() throws Exception {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.txt")))) {
			for (User user : User.users) {
				if(user != null)
				{
				writer.write(String.valueOf(user.getUsername()) + ":");
				writer.write(String.valueOf(user.getPassword()) + ":");
				writer.write(String.valueOf(user.getName()) + ":");
				writer.write(String.valueOf(user.getEmail()) + ":");
				writer.write(String.valueOf(user.getDateOfBirth()+ ":"));

				((BufferedWriter) writer).newLine();
				}
			}
			writer.close();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
