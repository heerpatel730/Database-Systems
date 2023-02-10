import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import java.time.*;
import java.text.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class Waste_Management {
    
    public static void main(String[] args) {
        try {  
            Connection cn = DB_Connect();
            int i=1;      
            while(true){          
                if(i==9){
                    break;
                }else{
                    System.out.println("1. Print Tables.");
                    System.out.println("2. Generate Report.");
                    System.out.println("3. From the pincode we can figure out which waste type was produced the most in that area..");
                    System.out.println("4. By the collected waste we can also know which type of waste is generated the most.");
                    System.out.println("5. We can get the amount of total quantity of a particular waste type in a particular pincode.");
                    System.out.println("6. We can get the names of employees who picks Biodegradable waste.");
                    System.out.println("7. We can get the list of employee names who work in HR department.");
                    System.out.println("8. We can get the Average quantity of waste greater than 5 generated in a particular pincode.");
                    System.out.println("9. If you want to add a new Client Email.");
                    System.out.println("10. We can figure out which day the waste was produced the most.");
                    Scanner scan = new Scanner(System.in);
                    i = scan.nextInt();
                    System.out.println(i);
                    
                    switch(i) {                    
                        
                        case 1:
                            Display_data(cn);
                            break;
                        
                        case 2:
                            Weekly_report(cn);
                            break;

                        case 3:
                            Pincode_Report(cn);
                            break;
                         
                        case 4:
                            Waste_report(cn);
                            break;
                            
                        case 5:
                            waste_amount(cn);
                            break;
                            
                        case 6:
                            employee_name(cn);
                            break;
       
                        case 7:
                            employee_hr(cn);
                            break;
                            
                        case 8:
                            waste_avg(cn);
                            break;
                            
                        case 9:
                            New_client(cn);
                            break;
                            
                        case 10:
                            waste_date(cn);
                            
                            break;

                        default:
                            System.out.println("Invalid Choice.");
                            break;
                    }
                }
            }         
        }catch(Exception e) {            
            System.out.println("Main Exception: "+e);            
        }             
    }
    
    static Connection DB_Connect() {
        try {           
            String JDBC_Driver = "oracle.jdbc.driver.OracleDriver";
            String DB_URL = "jdbc:oracle:thin:@acaddbprod-2.uta.edu:1523/pcse1p.data.uta.edu";
            String Username = "hxp5102";
            String Password = "Heerpatel147";        
            Class.forName(JDBC_Driver);  
            Connection cn = DriverManager.getConnection(DB_URL,Username,Password);   
            return cn;                 
        }catch(Exception e) {
            System.out.println("DB_Connect Function Exception:"+e);
        }
        return null;        
    }
    
    public static void gap(int a,int b) {
        
        for (int i=b;i<=a;i++){
            System.out.print(" ");
        }
    }
    
   public static void Display_data(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            System.out.println("Which table do you want to print?");
            System.out.println("1. F21_S003_19_client");
            System.out.println("2. F21_S003_19_clientE");
            System.out.println("3. F21_S003_19_clientP");
            System.out.println("4. F21_S003_19_clientA");
            System.out.println("5. F21_S003_19_consumer");
            System.out.println("6. F21_S003_19_location");
            System.out.println("7. F21_S003_19_department");
            System.out.println("8. FF21_S003_19_payment");
            System.out.println("9. F21_S003_19_employee");
            System.out.println("10. F21_S003_19_eEmail");
            System.out.println("11. F21_S003_19_ePHNO");
            System.out.println("12. F21_S003_19_eAddress");
            System.out.println("13. F21_S003_19_transportVeh");
            System.out.println("14. F21_S003_19_typeOW");
            System.out.println("15. F21_S003_19_consumerEmail");
            System.out.println("16. F21_S003_19_consumerPHNO");
            System.out.println("17. F21_S003_19_consumerAddress");
            System.out.print("Enter Your Choice: ");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            ResultSet rs = st.executeQuery("SELECT * from F21_S003_19_client");
            
            switch(i) {
            
                case 1:
                    rs = st.executeQuery("SELECT * from F21_S003_19_client");
                    System.out.print("\nTable: F21_S003_19_client\n\n");  
                    System.out.print("CID ");   
                    System.out.print("Client_FirstName ");   
                    System.out.print("Client_LastName ");   
                    System.out.print("Client_BD ");   
                    System.out.print("Client_Sex \n ");   
                     
                    while(rs.next()) {
                        System.out.print(rs.getString("CID")+" "); 
                        System.out.print(rs.getString("Client_FirstName")+" ");  
                        System.out.print(rs.getString("Client_LastName")+" ");
                        System.out.print(rs.getString("Client_BD")+" ");  
                        System.out.print(rs.getString("Client_Sex")+" ");  
                        System.out.print("\n ");
                    }
                break;
                
                case 2:
                    rs = st.executeQuery("SELECT * from F21_S003_19_clientE");
                    System.out.print("\n\nTable: F21_S003_19_clientE\n\n");
                    System.out.print("CID "); gap(12,8);
                    System.out.print("Client_Email \n"); gap(12,3);
                    
                    while(rs.next()){
                        System.out.print(rs.getString("CID")+" ");  gap(12, rs.getString(1).length());
                        System.out.print(rs.getString("Client_Email")+" \n");  gap(12, rs.getString(2).length());
                    }
                break;

                case 3:
                    rs = st.executeQuery("SELECT * from F21_S003_19_clientP");
                    System.out.print("\n\nTable: F21_S003_19_clientP\n\n");
                    System.out.print("CID "); gap(10,9);
                    System.out.print("Client_PHNO \n");
                    
                    while(rs.next()){
                        System.out.print(rs.getString("CID")+" ");  gap(10, rs.getString(1).length());
                        System.out.print(rs.getString("Client_PHNO")+" \n");  
                    }
                break;
                
                
                case 4:
                    rs = st.executeQuery("SELECT * from F21_S003_19_clientA");
                    System.out.print("\n\nTable: F21_S003_19_clientA\n\n");
                    System.out.print("CID ");
                    System.out.print("Client_Address \n");
                    
                    while(rs.next()){
                        System.out.print(rs.getString("CID")+" ");
                        System.out.print(rs.getString("Client_Address")+" \n");  
                    }
                break;
                
                case 5:
                    rs = st.executeQuery("SELECT * from F21_S003_19_consumer");
                    System.out.print("\n\nTable: F21_S003_19_consumer\n\n");
                    System.out.print("COID ");
                    System.out.print("Consumer_Name\n");
             
                    while(rs.next()){
                        System.out.print(rs.getString("COID")+" ");
                        System.out.print(rs.getString("Consumer_Name")+" \n");           
                    }
                break;

                case 6:
                    rs = st.executeQuery("SELECT * from F21_S003_19_location");
                    System.out.print("\n\nTable: F21_S003_19_location\n\n");
                    System.out.print("LID ");
                    System.out.print("Latitude ");
                    System.out.print("Longitude ");
                    System.out.print("F21_S003_19_location \n"); 
                    
                    while(rs.next()){
                        System.out.print(rs.getString("LID")+" ");
                        System.out.print(rs.getString("Latitude")+" ");
                        System.out.print(rs.getString("Longitude")+" \n");  
                    }
                break;

                case 7:
                    rs = st.executeQuery("SELECT * from F21_S003_19_department");
                    System.out.print("\n\nTable: F21_S003_19_department\n\n");
                    System.out.print("DID ");
                    System.out.print("D_Name \n");
                    
                    while(rs.next()){
                        System.out.print(rs.getString("DID")+" ");
                        System.out.print(rs.getString("D_Name")+"\n");                        
                    }
                break;
                
                case 8:
                    rs = st.executeQuery("SELECT * from F21_S003_19_payment");
                    System.out.print("\nTable: F21_S003_19_payment\n\n");  
                    System.out.print("PID ");   gap(20,4);
                    System.out.print("P_Date \n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("PID")+" "); 
                        System.out.print(rs.getString("P_Date")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 9:
                    rs = st.executeQuery("SELECT * from F21_S003_19_employee");
                    System.out.print("\nTable: F21_S003_19_employee\n\n");  
                    System.out.print("EID ");   gap(20,4);
                    System.out.print("E_Type ");
                    System.out.print("E_Name "); 
                    System.out.print("E_BD ");
                    System.out.print("E_Sex \n");
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("EID")+" "); 
                        System.out.print(rs.getString("E_Type")+" ");
                        System.out.print(rs.getString("E_Name")+" ");
                        System.out.print(rs.getString("E_BD")+" ");
                        System.out.print(rs.getString("E_Sex")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 10:
                    rs = st.executeQuery("SELECT * from F21_S003_19_eEmail");
                    System.out.print("\nTable: F21_S003_19_eEmail\n\n");  
                    System.out.print("EID");
                    System.out.print("E_Email\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("EID")+" "); 
                        System.out.print(rs.getString("E_Email")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 11:
                    rs = st.executeQuery("SELECT * from F21_S003_19_ePHNO");
                    System.out.print("\nTable: F21_S003_19_ePHNO\n\n");  
                    System.out.print("EID");
                    System.out.print("E_PHNO\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("EID")+" "); 
                        System.out.print(rs.getString("E_PHNO")+" ");
                        System.out.print("\n ");
                    }
                break;
                
                case 12:
                    rs = st.executeQuery("SELECT * from F21_S003_19_eAddress");
                    System.out.print("\nTable: F21_S003_19_ePHNO\n\n");  
                    System.out.print("EID");
                    System.out.print("E_Address\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("EID")+" "); 
                        System.out.print(rs.getString("E_Address")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 13:
                    rs = st.executeQuery("SELECT * from F21_S003_19_transportVeh");
                    System.out.print("\nTable: F21_S003_19_transportVeh\n\n");  
                    System.out.print("TID");
                    System.out.print("V_Make");
                    System.out.print("VIN\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("TID")+" "); 
                        System.out.print(rs.getString("V_Make")+" ");
                        System.out.print(rs.getString("VIN")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 14:
                    rs = st.executeQuery("SELECT * from F21_S003_19_typeOW");
                    System.out.print("\nTable: F21_S003_19_typeOW\n\n");  
                    System.out.print("TOID");
                    System.out.print("Pincode");
                    System.out.print("Quantity"); 
                    System.out.print("Waste_generation_date");
                    System.out.print("Waste_Type\n");
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("TOID")+" "); 
                        System.out.print(rs.getString("Pincode")+" ");
                        System.out.print(rs.getString("Quantity")+" ");
                        System.out.print(rs.getString("Waste_generation_date")+" ");
                        System.out.print(rs.getString("Waste_Type")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 15:
                    rs = st.executeQuery("SELECT * from F21_S003_19_consumerEmail");
                    System.out.print("\nTable: F21_S003_19_consumerEmail\n\n");  
                    System.out.print("COID");
                    System.out.print("Consumer_Email\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("COID")+" "); 
                        System.out.print(rs.getString("Consumer_Email")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 16:
                    rs = st.executeQuery("SELECT * from F21_S003_19_consumerPHNO");
                    System.out.print("\nTable: F21_S003_19_consumerPHNO\n\n");  
                    System.out.print("COID");
                    System.out.print("Consumer_PHNO\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("COID")+" "); 
                        System.out.print(rs.getString("Consumer_PHNO")+" ");
                        System.out.print("\n ");
                    }
                break;

                case 17:
                    rs = st.executeQuery("SELECT * from F21_S003_19_consumerAddress");
                    System.out.print("\nTable: F21_S003_19_consumerAddress\n\n");  
                    System.out.print("COID");
                    System.out.print("Consumer_Address\n"); 
                    System.out.print(rs.next());
                       
                    while(rs.next()) {
                        System.out.print(rs.getString("COID")+" "); 
                        System.out.print(rs.getString("Consumer_Address")+" ");
                        System.out.print("\n ");
                    }
                break;
               
                default:
                    System.out.println("Invalid Choice!");
                break;
            }
            st.close();
            System.out.println("\n");
      }
            catch(SQLException e) {
            System.out.println("Display_data function Exception: "+e);
        }
    }
  
    
    public static void Weekly_report(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            System.out.println("We can also figure out which company requires what kind of waste the most");
            ResultSet rs = st.executeQuery("select Consumer_Name,Waste_Type,QUANTITY from  (select  Consumer_Name,total, Waste_Type, MAX(total) OVER (PARTITION BY Consumer_Name) AS QUANTITY from(select    Consumer_Name,  Waste_Type, SUM(Quantity) as total From( select t.TOID, c.COID, t.Waste_Type, t.Quantity, cc.Consumer_Name from F21_S003_19_typeOW t inner join F21_S003_19_collects c ON t.TOID = c.TOID inner join F21_S003_19_consumer cc ON cc.COID = c.COID) dc group by Waste_Type, Consumer_Name ) ab) xy where total = QUANTITY");
            System.out.print("Consumer_Name "); gap(15,4);
            System.out.print("Waste_Type "); gap(40,10);
            System.out.print("QUANTITY \n"); gap(14,13);
                        
            while(rs.next()){
                System.out.print(rs.getString("Consumer_Name")+" ");  gap(15, rs.getString(1).length());
                System.out.print(rs.getString("Waste_Type")+" ");  gap(40, rs.getString(2).length());
                System.out.print(rs.getString("QUANTITY")+" ");  gap(14, rs.getString(3).length());
                System.out.println("\n");              
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
    public static void Pincode_Report(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select Pincode,Waste_Type,Total_Waste_KGs from (select Pincode,total, Waste_Type, MAX(total) OVER(PARTITION BY Pincode) AS Total_Waste_KGs from(select Pincode, Waste_Type, SUM(Quantity) as total From( select t.Pincode, t.Waste_Type, t.Quantity from F21_S003_19_typeOW t ) dc group by Waste_Type, Pincode) ab) xy where total = Total_Waste_KGs");
            System.out.print("Pincode "); gap(15,4);
            System.out.print("Waste_Type "); gap(40,10);
            System.out.print("Total_Waste_KGs "); gap(14,13);
            
            while(rs.next()){
                System.out.print(rs.getString("Pincode")+" ");  gap(15, rs.getString(1).length());
                System.out.print(rs.getString("Waste_Type")+" ");  gap(40, rs.getString(2).length());
                System.out.print(rs.getString("Total_Waste_KGs")+" ");  gap(14, rs.getString(3).length());
                System.out.println("\n");  
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
    
    public static void Waste_report(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select Waste_Type, QUANTITY from (SELECT Waste_Type, SUM(Quantity) QUANTITY FROM F21_S003_19_typeOW t1 GROUP BY Waste_Type) dc where dc.QUANTITY>= ALL (SELECT SUM(Quantity) FROM F21_S003_19_typeOW t1 GROUP BY Waste_Type)");
            System.out.print("Waste_Type ");
            System.out.print("QUANTITY \n"); 
            
            while(rs.next()){
                System.out.print(rs.getString("Waste_Type")+" ");
                System.out.print(rs.getString("QUANTITY")+" ");
                System.out.println("\n");
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    public static void waste_amount(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select Pincode, Waste_Type, QUANTITY AS WMS from (SELECT Pincode, Waste_Type, SUM(Quantity) as QUANTITY FROM F21_S003_19_typeOW GROUP BY ROLLUP (Pincode, Waste_Type)) Where Waste_Type IS  NOT NULL order by (QUANTITY) desc");
            System.out.print("Pincode ");
            System.out.print("Waste_Type "); 
            System.out.print("WMS \n"); 
            
            while(rs.next()){
                System.out.print(rs.getString("Pincode")+" ");
                System.out.print(rs.getString("Waste_Type")+" ");
                System.out.print(rs.getString("WMS")+"\n");
                System.out.println("\n");
                
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
    public static void employee_name(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT E_Name , E_Type, Waste_Type FROM F21_S003_19_employee INNER JOIN F21_S003_19_collection ON F21_S003_19_employee.EID = F21_S003_19_collection.EID inner join F21_S003_19_typeOW on F21_S003_19_typeOW.TOID = F21_S003_19_collection.TOID group by E_Type, Waste_Type,E_Name having Waste_Type='Biodegradable' AND E_Type='Staff'");
            System.out.print("E_Name ");
            System.out.print("E_Type "); 
            System.out.print("Waste_Type \n"); 
            
            while(rs.next()){
                System.out.print(rs.getString("E_Name")+" ");
                System.out.print(rs.getString("E_Type")+" ");
                System.out.print(rs.getString("Waste_Type")+"\n");
                System.out.println("\n");  
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
    public static void employee_hr(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select d.D_Name, e.E_Type, e.E_Name from F21_S003_19_department d inner join F21_S003_19_employee e ON d.DID = e.DID group by D_Name, E_Type, E_Name Having E_Type = 'HR'");
            System.out.print("D_Name ");
            System.out.print("E_Type "); 
            System.out.print("E_Name \n"); 
            
            while(rs.next()){
                System.out.print(rs.getString("D_Name")+" ");
                System.out.print(rs.getString("E_Type")+" ");
                System.out.print(rs.getString("E_Name")+"\n");
                System.out.println("\n"); 
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
    public static void waste_avg(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT  distinct Pincode, AVG(Quantity) as Amount FROM F21_S003_19_typeOW group by Pincode having avg (Quantity) > '5'");
            System.out.print("Pincode ");
            System.out.print("Amount \n"); 
            
            while(rs.next()){
                System.out.print(rs.getString("Pincode")+" ");
                System.out.print(rs.getString("Amount")+"");
                System.out.println("\n");   
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
    public static void waste_date(Connection cn) {
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Waste_generation_date, Quantity FROM F21_S003_19_typeOW where Quantity= (SELECT MAX( Quantity ) FROM F21_S003_19_typeOW )");
            System.out.print("Waste_generation_date ");
            System.out.print("Quantity \n"); 
            
            while(rs.next()){
                System.out.print(rs.getString("Waste_generation_date")+" ");
                System.out.print(rs.getString("Quantity")+"");
                System.out.println("\n");  
            }
            st.close();
            System.out.println("\n");
        }catch(SQLException e) {
            System.out.println("Weekly_report function Exception: "+e);
        }
    }
    
           
    public static void New_client (Connection cn) {
        
        try {
            System.out.println("\nEnter Details of New Client Email: ");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Client ID from 1 to 30: ");
            String cid = sc.nextLine();
            System.out.print("Enter Client Email that you want to add: ");
            String client_Email = sc.nextLine();
            Statement st = cn.createStatement();           
            String sql = "INSERT INTO F21_S003_19_clientE (CID, Client_Email) values (?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,cid);
            ps.setString(2,client_Email);      
            ps.execute();
            st.close();
            System.out.println("\nNew Client Email Added Successfully.\n");
        }catch(SQLException e) {
            System.out.println("New_Client function Exception: "+e);
        }
    }
}

