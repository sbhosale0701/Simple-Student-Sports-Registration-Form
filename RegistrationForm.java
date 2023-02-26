import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
 
public class RegistrationForm implements ActionListener {
	//creating object of JFrame class
	JFrame frame= new JFrame();
	
	//creating objects
	String[] branch={"CO","IF","EJ","CE","ME","DD"};
	String[] year={"First","Second","Third"};
	String[] sports={"Vollyball","Kabbadi","Kho-kho","Cricket","HandBall","Basketball"};
	
	JLabel titlelabel;
    JLabel nameLabel=new JLabel("NAME");
    JLabel genderLabel=new JLabel("FATHER'S NAME");
	JLabel branchlable=new JLabel("BRANCH");
	JLabel yearlable=new JLabel("YEAR");
	JLabel enrollmentnolable=new JLabel("ENROLLMENT NO");
	JLabel doblable=new JLabel("DOB");
    JLabel passwordLabel=new JLabel("WEIGHT");
    JLabel confirmPasswordLabel=new JLabel("HEIGHT");
	JLabel sportslabel= new JLabel("SPORT");
    JLabel cityLabel=new JLabel("ADDRESS");
	JLabel contactlabel=new JLabel("CONTACT NO");
    JLabel emailLabel=new JLabel("EMAIL");
    
    JTextField nameTextField=new JTextField();
    JTextField fnameTextField=new JTextField();
	JComboBox branchcombobox=new JComboBox(branch);
	JComboBox yearcombobox=new JComboBox(year);
	JTextField enrollmentTextField=new JTextField();
	JTextField dobTextField=new JTextField();
    JTextField heighttextfield=new JTextField();
    JTextField weightfield=new JTextField();
	JComboBox sportscombobox=new JComboBox(sports);
    JTextField cityTextField=new JTextField();
	JTextField contactTextField=new JTextField();
    JTextField emailTextField=new JTextField();
    
    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
	JButton editButton=new JButton("EDIT");
	JButton viewButton=new JButton("VIEW");
	JButton deleteButton=new JButton("DELETE");
	DefaultTableModel model;
	JTable table=new JTable();
	JScrollPane sp=new JScrollPane(table);
	
	//creating constructor
    RegistrationForm()
    {
		titlelabel= new JLabel("COLLEGE SPORTS DATABASE MANAGEMENT SYSTEM");
		titlelabel.setForeground(Color.black);
		titlelabel.setFont(new Font("Serif", Font.BOLD, 20));
		
        createWindow(); //calling method from constructor
        setLocationAndSize(); //calling method from constructor
        addComponentsToFrame(); //calling method from constructor
        actionEvent();
        showTableData(); //calling method from constructor
        
    }
    
    //creating user-defined method
    public void createWindow()
    {
    	//setting properties of JFrame
        frame=new JFrame();
        frame.setTitle("Sports Registration Form");
        frame.setBounds(40,40,1300,710);
        frame.getContentPane().setBackground(Color.yellow);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
    public void setLocationAndSize()
    {
    	//setting location and size of each component
		titlelabel.setBounds(400, 2, 600,30); 
        nameLabel.setBounds(50,20,40,70);
        genderLabel.setBounds(50,60,100,70);
		branchlable.setBounds(50,100,100,70);
		yearlable.setBounds(50,140,100,70);
		enrollmentnolable.setBounds(50,180,100,70);
		doblable.setBounds(50,220,100,70);
        passwordLabel.setBounds(50,260,100,70);
        confirmPasswordLabel.setBounds(50,300,140,70);
		sportslabel.setBounds(50,340,140,70);
        cityLabel.setBounds(50,380,100,70);
		contactlabel.setBounds(50,420,100,70);
        emailLabel.setBounds(50,460,100,70);
        nameTextField.setBounds(180,43,165,23);
        fnameTextField.setBounds(180,83,165,23);
		branchcombobox.setBounds(180,123,165,23);
		yearcombobox.setBounds(180,163,165,23);
		enrollmentTextField.setBounds(180,203,165,23);
		dobTextField.setBounds(180,243,165,23);
        heighttextfield.setBounds(180,283,165,23);
		weightfield.setBounds(180,323,165,23);
		sportscombobox.setBounds(180, 363, 165,23);
        cityTextField.setBounds(180,403,165,23);
		contactTextField.setBounds(180,443,165,23);
        emailTextField.setBounds(180,483,165,23);
        registerButton.setBounds(80,530,100,35);
        resetButton.setBounds(220,530,100,35);
		editButton.setBounds(80,580,100,35);
		viewButton.setBounds(220,580,100,35);
		deleteButton.setBounds(150,630,100,35);
		table.setBounds(450, 40, 800, 600);
    }
    
    public void addComponentsToFrame()
    {	
    	//adding components to frame
		frame.add(titlelabel);
        frame.add(nameLabel);
        frame.add(genderLabel);
		frame.add(branchlable);
		frame.add(yearlable);
		frame.add(enrollmentnolable);
		frame.add(doblable);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(cityLabel);
		frame.add(sportslabel);
		frame.add(contactlabel);
        frame.add(emailLabel);
        frame.add(nameTextField);
        frame.add(fnameTextField);
		frame.add(branchcombobox);
		frame.add(yearcombobox);
		frame.add(enrollmentTextField);
		frame.add(dobTextField);
        frame.add(heighttextfield);
        frame.add(weightfield);
		frame.add(sportscombobox);
        frame.add(cityTextField);
		frame.add(contactTextField);
        frame.add(emailTextField);
        frame.add(registerButton);
        frame.add(resetButton);
		frame.add(editButton);
		frame.add(viewButton);
		frame.add(deleteButton);
		frame.add(table);
		frame.add(sp);
    }
    
    public void actionEvent()
    {
       //Adding Action Listener to buttons
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
		editButton.addActionListener(this);
		viewButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
    }
   
   
    @Override
    public void actionPerformed(ActionEvent e) {	
    	Connection Con=null;
    	
	if(e.getSource()==registerButton)
	{
		try{
		
		    Con= DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			String sql= "INSERT INTO form"
					+"(`name`, `fname`, `branch`, `year`, `e_no`, `dob`, `weight`, `height`, `sport`, `address`, `contact`, `email`)"					
					+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = Con.prepareStatement(sql);
			ps.setString(1,nameTextField.getText());
			ps.setString(2,fnameTextField.getText());
			ps.setString(3,branchcombobox.getSelectedItem().toString());
			ps.setString(4,yearcombobox.getSelectedItem().toString());
			ps.setString(5,enrollmentTextField.getText());
			ps.setString(6,dobTextField.getText());
			ps.setString(7,heighttextfield.getText());
			ps.setString(8,weightfield.getText());
			ps.setString(9,sportscombobox.getSelectedItem().toString());
			ps.setString(10,cityTextField.getText());
			ps.setString(11,contactTextField.getText());
			ps.setString(12,emailTextField.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Inserted Successfully!!");
			Con.close();
		}
	catch(SQLException es)
	{
		es.printStackTrace();
	}
		showTableData();
		
		}
	if(e.getSource()==resetButton)
	{
		nameTextField.setText("");
		fnameTextField.setText("");
		branchcombobox.setSelectedItem("CO");
		yearcombobox.setSelectedItem("First");
		enrollmentTextField.setText("");
		dobTextField.setText("");
		heighttextfield.setText("");
		weightfield.setText("");
		sportscombobox.setSelectedItem("Vollyball");
		cityTextField.setText("");
		contactTextField.setText("");
		emailTextField.setText("");
	}
	
	if(e.getSource()==editButton)
	{
		try{
			Con= DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			String sql= "UPDATE form SET fname =?, branch=?, year=?, e_no=?, dob=?, weight=?, height=?, sport =?, address =?, contact=?, email=? WHERE name=?";
			PreparedStatement ps = Con.prepareStatement(sql);
			ps.setString(12,nameTextField.getText());
			ps.setString(1,fnameTextField.getText());
			ps.setString(2,branchcombobox.getSelectedItem().toString());
			ps.setString(3,yearcombobox.getSelectedItem().toString());
			ps.setString(4,enrollmentTextField.getText());
			ps.setString(5,dobTextField.getText());
			ps.setString(6,heighttextfield.getText());
			ps.setString(7,weightfield.getText());
			ps.setString(8,sportscombobox.getSelectedItem().toString());
			ps.setString(9,cityTextField.getText());
			ps.setString(10,contactTextField.getText());
			ps.setString(11,emailTextField.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Edited successfully!!");
			Con.close();
		}
	catch(SQLException es)
	{
		es.printStackTrace();
	}
		showTableData();
	}
	if(e.getSource()==deleteButton)
	{
		try{
			Con= DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			String sql= "DELETE FROM `form` WHERE Name=?";

			PreparedStatement ps = Con.prepareStatement(sql);
			ps.setString(1,nameTextField.getText());
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Deleted Successfully!!");
			Con.close();
		}
	catch(SQLException es)
	{
		es.printStackTrace();
	}
		showTableData();
	}
	if(e.getSource()==viewButton)
	{
		try{
			Con= DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			String sql= "SELECT 'name', `fname`, `branch`, `year`, `e_no`, `dob`, `weight`, `height`, `sport`, `address`, `contact`, `email` FROM `form` WHERE name=?";
		    PreparedStatement ps = Con.prepareStatement(sql);
		    ps.setString(1,nameTextField.getText());
		    ResultSet rs= ps.executeQuery();
		    if(rs.next()==false)
		    {
		    	JOptionPane.showMessageDialog(null, "Sorry Record Not Fount!!");
		    	nameTextField.setText("");
				fnameTextField.setText("");
				branchcombobox.setSelectedItem("");
				yearcombobox.setSelectedItem("");
				enrollmentTextField.setText("");
				dobTextField.setText("");
				heighttextfield.setText("");
				weightfield.setText("");
				sportscombobox.setSelectedItem("");
				cityTextField.setText("");
				contactTextField.setText("");
				emailTextField.setText("");
		    }
		    else
		    {
		    	nameTextField.setText(rs.getString("name"));
				fnameTextField.setText(rs.getString("fname"));
				branchcombobox.setSelectedItem(rs.getString("branch"));
				yearcombobox.setSelectedItem(rs.getString("year"));
				enrollmentTextField.setText(rs.getString("e_no"));
				dobTextField.setText(rs.getString("dob"));
				heighttextfield.setText(rs.getString("weight"));
				weightfield.setText(rs.getString("height"));
				sportscombobox.setSelectedItem(rs.getString("sport"));
				cityTextField.setText(rs.getString("address"));
				contactTextField.setText(rs.getString("contact"));
				emailTextField.setText(rs.getString("email"));
		    }
		
			Con.close();
		}
	catch(SQLException es)
	{
		es.printStackTrace();
	}
		showTableData();
		}
	}
    
	public void showTableData()
	{
		Connection Con= null;
		try
		{
			Con= DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
			String sql= "SELECT * FROM `form`";
			PreparedStatement ps = Con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			Con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex);
		}
	}
    
    //creating object of RegistrationForm class
	public static void main(String args[]){
		new RegistrationForm();
	}
}