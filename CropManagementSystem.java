package JDBCProject;
import java.sql.*;
import java.util.Scanner;

public class CropManagementSystem {

    Scanner sc = new Scanner(System.in);

    public void createDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
            Statement st = con.createStatement();
            st.executeUpdate("Create Database CropDB");
            System.out.println("Database Created Successfully");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:w://localhost:3306/CropDB", "root", "1234");
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE Crops " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " CropName VARCHAR(255), " +
                    " Type VARCHAR(255), " +
                    " Quantity INTEGER not NULL)");
            System.out.println("Table Created Successfully");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputData() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CropDB", "root", "1234");
        PreparedStatement ps = con.prepareStatement("insert into Crops (CropName, Type, Quantity) values(?,?,?)");
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter Crop Name:");
            String cropName = sc.nextLine();
            System.out.println("Enter Crop Type:");
            String type = sc.nextLine();
            System.out.println("Enter Quantity:");
            int quantity = sc.nextInt();

            ps.setString(1, cropName);
            ps.setString(2, type);
            ps.setInt(3, quantity);
            int i = ps.executeUpdate();
            System.out.println(i + " records affected");

            System.out.println("Do you want to continue: y/n");
            String s = sc.next();
            if (s.startsWith("n")) {
                break;
            }
        } while (true);

        con.close();
    }

    public void readData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CropDB", "root", "1234");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Crops");
            while (rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
            System.out.println("Table Read Successfully");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CropManagementSystem cms = new CropManagementSystem();
        cms.createDatabase();
        cms.createTable();
        try {
            cms.inputData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cms.readData();
    }
}
