package com.example.hotel_8;

import com.sun.mail.imap.protocol.INTERNALDATE;
import javafx.scene.chart.PieChart;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.sql.*;
import java.sql.Date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Data_work extends SQLException {
    private static String myDriver;
    public static Connection conn;
    public static String login;
    public static String password;
    public static String name;
    public static String Dpassport;
    public static int id;
    public static int hotelID;
    public static int finances;
    public static int workers_count;
    public static String post;
    public static int salary;
    public static String email;
    public static String email_pas;
    public static String host = "imap.gmail.com";


    static {
        try {
            myDriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(myDriver);
                conn = DriverManager.getConnection("jdbc:mysql://bcalydbu3z2fxht2xnnd-mysql.services.clever-cloud.com:3306/bcalydbu3z2fxht2xnnd", "uy0pllhi16asmquc", "aiMfsx2EM5VcOUR1TFS3");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String
    removeFirstandLast(String str)
    {


        StringBuilder sb = new StringBuilder(str);



        sb.deleteCharAt(str.length() - 1);



        sb.deleteCharAt(0);



        return sb.toString();
    }





    public static ArrayList<String> get_hotels() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {







            String query = "SELECT Name FROM Hotels";


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String Name = rs.getString("Name");
                name_hotels.add(Name);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static Map<String, Integer> getCountry() {
        HashMap<String, Integer> con = new HashMap<String, Integer>();
        try
        {







            String query = "select *\n" +
                    "from Countries where clients != 0\n" +
                    "order by clients desc\n" +
                    "limit 5";


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String Name = rs.getString("Name");
                int clients = rs.getInt("clients");
                System.out.println("aaaaa" + Name + clients);
                con.put(Name, clients);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }

        return con;
    }

    public static Map<String, int[]> getHotels_chart() {
        HashMap<String, int[]> con = new HashMap<String, int[]>();
        try
        {







            String query = "select *\n" +
                    "from Hotels where HotelID != 1\n" +
                    "order by Clients desc\n" +
                    "limit 5";


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String Name = rs.getString("Name");
                int clients = rs.getInt("Clients");
                int worker = rs.getInt("Workers");
                int room = rs.getInt("Rooms");
                System.out.println("aaaaa" + Name + clients);
                con.put(Name, new int[]{clients, worker, room});

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }

        return con;
    }



    public static ArrayList<String> get_rooms(boolean EXB, boolean CHD) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;







            if(EXB && CHD) {
                query = "SELECT Name, Size, Type, View FROM Rooms WHERE Rooms.Extra_bed = 1 and Rooms.Child = 1 and Rooms.isAvailable = 1 and HotelID = " + hotelID;
            } else if(EXB){
                query = "SELECT Name, Size, Type, View FROM Rooms WHERE Rooms.Extra_bed = 1 and Rooms.isAvailable = 1 and HotelID = " + hotelID;
            } else if(CHD){
                query = "SELECT Name, Size, Type, View FROM Rooms WHERE Rooms.Child = 1 and Rooms.isAvailable = 1 and HotelID = " + hotelID;
            } else{
                query = "SELECT Name, Size, Type, View FROM Rooms Where Rooms.isAvailable = 1 and HotelID = " + hotelID;
            }

            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String Name = rs.getString("Name");
                String View = rs.getString("View");
                String Size = rs.getString("Size");
                String Type = rs.getString("Type");
                name_hotels.add(Name + " " + Size + " " + View + " " + Type);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> get_Counry() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "select id ,name, capital, continent from Countries";

            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String Name = rs.getString("name");
                String View = rs.getString("capital");
                String Size = rs.getString("continent");
                int id = rs.getInt("id");
                name_hotels.add(id +" " + Name + " " + Size + " " + View);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> getWorkers() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT WorkerID, Fullname, Post FROM Workers Where Workers.HotelID = " + hotelID;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("WorkerID");
                String Name = rs.getString("Fullname");
                String POST = rs.getString("Post");
                name_hotels.add(idd + " " +  Name + " " + POST);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> getMaids() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT WorkerID, Fullname, Post FROM Workers Where Post = 'Maid' and Workers.HotelID = " + hotelID;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("WorkerID");
                String Name = rs.getString("Fullname");
                name_hotels.add(idd + " " +  Name);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> getCooks() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;
   
   
   





            query = "SELECT WorkerID, Fullname, Post FROM Workers Where Post = 'Cook' and Workers.HotelID = " + hotelID;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("WorkerID");
                String Name = rs.getString("Fullname");
                name_hotels.add(idd + " " +  Name);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }




    public static ArrayList<String> getWorkers(int iddd) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT WorkerID, Fullname, Post FROM Workers Where Workers.HotelID = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("WorkerID");
                String Name = rs.getString("Fullname");
                String POST = rs.getString("Post");
                name_hotels.add(idd + " | " +  Name + " | " + POST);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> getHotels() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT HotelID, Name, Address, Stars FROM Hotels Where HotelID != 1";


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("HotelID");
                String Name = rs.getString("Name");
                String POST = rs.getString("Address");
                String stas = rs.getString("Stars");
                name_hotels.add(idd + " | " +  Name + " | " + POST + " | " + stas);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> getRooms() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT * FROM Rooms Where HotelID = " + hotelID;
            System.out.println(query);


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                int idd = rs.getInt("Name");
                String Name = rs.getString("Size");
                String POST = rs.getString("Type");
                name_hotels.add(idd + " | " +  Name + " | " + POST);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<Integer> getRoomsNumber(int num) {
        ArrayList<Integer> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT Name FROM Rooms Where HotelID = " + hotelID + " and Name != " + num;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                int idd = rs.getInt("Name");
                name_hotels.add(idd);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }



    public static ArrayList<String> getClients() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT ClientID, Fullname, Name FROM Clients Where HotelID = " + hotelID  + " and '" + LocalDate.now() + "' between Clients.CheckInTime and Clients.CheckOutTime";


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("ClientID");
                String Name = rs.getString("Fullname");
                String POST = rs.getString("Name");
                name_hotels.add(idd + " " +  Name + " " + POST);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }


    public static ArrayList<String> getWorkerswithoutDirector() {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT WorkerID, Fullname, Post FROM Workers Where Workers.HotelID = " + hotelID + " and Post != \"Director\" and Post != \"Admin\"";


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("WorkerID");
                String Name = rs.getString("Fullname");
                String POST = rs.getString("Post");
                name_hotels.add(idd + " " +  Name + " " + POST);

            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return name_hotels;
    }

    public static ArrayList<String> getView() {
        ArrayList<String> view = new ArrayList<>();
        try
        {
            String query;








            query = "select Name from Сlassification_of_rooms Where Type = 'View'";
            System.out.println(query);


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("Name");
                view.add(idd);

            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return view;
    }

    public static ArrayList<String> getSize() {
        ArrayList<String> view = new ArrayList<>();
        try
        {
            String query;








            query = "select Name from Сlassification_of_rooms Where Type = 'Basic'";
            System.out.println(query);


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("Name");
                view.add(idd);

            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return view;
    }

    public static ArrayList<String> getType() {
        ArrayList<String> view = new ArrayList<>();
        try
        {
            String query;








            query = "select Name from Сlassification_of_rooms Where Type = 'Comfort'";
            System.out.println(query);


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String idd = rs.getString("Name");
                view.add(idd);

            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return view;
    }





    public static ResultSet getClientInfo(String iddd) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT Fullname, Passport, Name, DateOfBirth, CheckInTime, CheckOutTime FROM Clients Where HotelID = " + hotelID + " and ClientID = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ResultSet getWorkerInfo(String iddd) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT Fullname, Email, Passport, Password, Login, Salary FROM Workers Where HotelID = " + hotelID + " and WorkerID = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);
            System.out.println(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ResultSet getWorkerInfoAdmin(String iddd, String hid) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT Fullname, Email, Passport, Password, Login, Salary FROM Workers Where HotelID = " + hid + " and WorkerID = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);
            System.out.println(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public static ResultSet getHotelInfo(String iddd) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT Name, Address, Clients, Finances, Workers, Rooms, Stars FROM Hotels Where HotelID = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ResultSet getRoomInfo(String iddd) {
        ArrayList<String> name_hotels = new ArrayList<>();
        try
        {
            String query;








            query = "SELECT * FROM Rooms Where HotelID = " + hotelID + " and Name = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }



    public static ResultSet getWorkerSalary(String iddd) {
        try
        {
            String query;








            query = "SELECT Salary FROM Workers Where Workers.HotelID = " + hotelID + " and Workers.WorkerID = " + iddd;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ResultSet getHotelinfo() {
        try
        {
            String query;








            query = "SELECT * FROM Hotels Where HotelID = " + hotelID;


            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery(query);













            return rs;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }



    public static boolean sign_in(String log, String pass, String hotel){
        try
        {
            int idHotel = getIdHotel(hotel);
            String query = "SELECT * FROM Workers WHERE HotelID = " + idHotel + " and Login = '" + log + "' and Password = '" + pass + "'";


            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                login = rs.getString("Login");
                password = rs.getString("Password");
                id = rs.getInt("WorkerID");
                hotelID = rs.getInt("HotelID");
                post = rs.getString("Post");
                salary = rs.getInt("Salary");
                email = rs.getString("Email");
                name = rs.getString("Fullname");
                email_pas = rs.getString("Email_password");
                if(post.equals("Director") || post.equals("Admin"))
                    finances = getFinances();
                    Dpassport = rs.getString("Passport");
                workers_count = getWorkers_count();


                if(login.equals(log) & password.equals(pass)){
                    hotelID = idHotel;
                    return true;
                }

            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception!!! ");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static int getCost(String room){
        try
        {




            List<String> list = new ArrayList<String>(Arrays.asList(room.split(" ")));
            System.out.println(list);


            String query = "SELECT Price FROM Rooms WHERE Rooms.Name = \"";


            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query + list.get(0) + "Rooms.Size = " + list.get(1) + "\"");


            while (rs.next())
            {
                int price = rs.getInt("Price");
                return price;
                }


        }
        catch (Exception e) {
            System.err.println("Got an exception!!! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public static String getJob(String login, String hotel){
        try
        {







            String query = "SELECT Post FROM Workers WHERE Workers.HotelID = \"" ;
            int idHotel = getIdHotel(hotel);

            Statement st = conn.createStatement();

            System.out.println(login);
            ResultSet rs = st.executeQuery(query + idHotel +"and Workers.Login = " + login + "\"");



            while(rs.next()){
                String post = rs.getString("Post");
                System.out.println(post);}
            return post;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static int getFinances(){
        try
        {
            String query = "SELECT Finances FROM Hotels WHERE Hotels.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                System.out.println("profit " + getProfit(String.valueOf(LocalDate.now().getMonth())));
                finances = rs.getInt("Finances") - getLoss(String.valueOf(LocalDate.now().getMonth())) + getProfit(String.valueOf(LocalDate.now().getMonth()));
                System.out.println(finances);
            }
            return finances;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return finances;
    }

    public static int getWorkers_count(){
        int count = 0;
        try
        {
            String query = "SELECT * FROM Workers WHERE Workers.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                count++;
            }
            return count;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return count;
    }

    public static int getCountRooms(){
        int count = 0;
        try
        {
            String query = "SELECT * FROM Rooms WHERE Rooms.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                count++;
            }
            return count;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return count;
    }

    public static int getCountRoomsOwner(){
        int count = 0;
        try
        {
            String query = "SELECT * FROM Rooms";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                count++;
            }
            return count;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return count;
    }


    public static int getCountWorkers(){
        int count = 0;
        try
        {
            String query = "SELECT * FROM Rooms";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                count++;
            }
            return count;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return count;
    }


    public static int getCountClients(){
        int count = 0;
        try
        {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println(date);

            String query = "SELECT * FROM Clients WHERE Clients.HotelID = " + hotelID + " and '" + date + "' between Clients.CheckInTime and Clients.CheckOutTime";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                count++;
            }
            return count;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return count;
    }

    public static int getCountClientsOwner(){
        int count = 0;
        try
        {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println(date);

            String query = "SELECT * FROM Clients WHERE '" + date + "' between Clients.CheckInTime and Clients.CheckOutTime";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                count++;
            }
            return count;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return count;
    }


    public static int getLoss(String month){
        try
        {

            String query = "SELECT " + month +" FROM Loss WHERE Loss.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                return rs.getInt(month);
            }


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public static void setLoss(String month){
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Loss Set "+ month +" = '" + getLossNow() + "' WHERE Loss.HotelID = " + hotelID;
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int getLossNow(){
        int loss = 0;
        try
        {
            String query = "SELECT Salary FROM Workers WHERE Workers.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                loss += rs.getInt("Salary");
            }

            query = "Select Sum from withdraw_money where HotelID = " + hotelID + " and withdraw_money.date between '" + LocalDate.now().withDayOfMonth(1) + "' and '" + LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()) + "'";

            rs = st.executeQuery(query);

            System.out.println(query);
            while(rs.next()) {
                loss += rs.getInt("Sum");
            }


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return loss;
    }


    public static int getProfit(String month){
        try
        {

            String query = "SELECT " + month +" FROM Profit WHERE Profit.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                return rs.getInt(month);
            }


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }



    public static HashMap getProfit2(){
        try
        {
            HashMap<String, Integer> profit = new HashMap<>();


            String query = "SELECT * FROM Profit WHERE HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            String[] monthss = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            while (rs.next()){
                for(int i = 0; i < 12; i++){
                profit.put(monthss[i], rs.getInt(monthss[i]));
                }

            }


            return profit;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public static HashMap<String, Integer> getLoss2(){
        try
        {
            HashMap<String, Integer> loss = new HashMap<>();


            String query = "SELECT * FROM Loss WHERE Loss.HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            String[] monthss = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            while (rs.next()){
                for(int i = 0; i < 12; i++){
                    loss.put(monthss[i], rs.getInt(monthss[i]));
                }

            }

            return loss;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public static HashMap<String, Integer> getProfitOwner(){
        try
        {
            HashMap<String, Integer> profit = new HashMap<>();


            String query = "select sum(January) as January, sum(February) as February, sum(March) as March, sum(April) as April, sum(May) as May, sum(June) as June, sum(July) as July, sum(August) as August, sum(September) as September, sum(October) as October, sum(November) as November, sum(December) as December from Profit;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            String[] monthss = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            while (rs.next()){
                for(int i = 0; i < 12; i++){
                    profit.put(monthss[i], rs.getInt(monthss[i]));
                }

            }


            return profit;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public static HashMap<String, Integer> getLossOwner(){
        try
        {
            HashMap<String, Integer> loss = new HashMap<>();


            String query = "select sum(January) as January, sum(February) as February, sum(March) as March, sum(April) as April, sum(May) as May, sum(June) as June, sum(July) as July, sum(August) as August, sum(September) as September, sum(October) as October, sum(November) as November, sum(December) as December from Loss;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            String[] monthss = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            while (rs.next()){
                for(int i = 0; i < 12; i++){
                    loss.put(monthss[i], rs.getInt(monthss[i]));
                }

            }

            return loss;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public static void setProfit(String month){
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Profit Set "+ month +" = '" + getProfitNow() + "' WHERE Profit.HotelID = " + hotelID;
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int getProfitNow(){
        int profit = 0;
        try
        {
            String query = "select Amount from Clients where CheckInTime between '" + LocalDate.now().withDayOfMonth(1) + "' and '" + LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()) + "' and HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            System.out.println(query);
            while(rs.next()) {
                profit += rs.getInt("Amount");
                System.out.println("cc " + profit);
            }

            query = "Select Price from monetary_fine where HotelID = " + hotelID + " and monetary_fine.Date between '" + LocalDate.now().withDayOfMonth(1) + "' and '" + LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()) + "'";

            rs = st.executeQuery(query);

            System.out.println(query);
            while(rs.next()) {
                profit += rs.getInt("Price");
                System.out.println("cc " + profit);

            }


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return profit;
    }


    public static String getDescriptionS(String room){
        try
        {
            List<String> list = new ArrayList<String>(Arrays.asList(room.split(" ")));
            System.out.println(list);







            String query = "SELECT Description FROM Сlassification_of_rooms WHERE Сlassification_of_rooms.Name = \"";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query + list.get(1) + "\"");


            rs.next();
            String post = rs.getString("Description");
            return post;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public static String getHotelName(int iddd){
        try
        {

            String query = "Select Name From Hotels Where HotelID = " + iddd;
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            rs.next();
            String post = rs.getString("Name");
            System.out.println(post);
            return post;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return " ";
    }

    public static String getDescriptionT(String room){
        try
        {
            List<String> list = new ArrayList<String>(Arrays.asList(room.split(" ")));
            System.out.println(list);
            String n;





            if(list.size() == 7){
                n = list.get(5) + " " + list.get(6);

            }else if(list.size() == 6){
                n = list.get(5);
            } else{
                n = list.get(4);
            }


            String query = "SELECT Description FROM Сlassification_of_rooms WHERE Сlassification_of_rooms.Name = \"";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query + n + "\"");


            rs.next();
            String post = rs.getString("Description");
            return post;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }






























































    public static void changeEmail(String newEmail) throws SQLException, ClassNotFoundException{
        try{
            Statement st = conn.createStatement();
            String query = "UPDATE Workers Set Email = '" +newEmail + "' WHERE Workers.Login = '" + login + "' and Workers.Password = '" + password + "'";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();
            email = newEmail;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update_rooms() throws SQLException, ClassNotFoundException{
        try{
            Statement st = conn.createStatement();
            Date date = Date.valueOf(LocalDate.now());


            String query2 = "select Name from Clients where '" + LocalDate.now() + "' > CheckOutTime";
            System.out.println(query2);

            ResultSet rs2 = st.executeQuery(query2);

            while (rs2.next()){
                String query3 = "update Rooms set isAvailable = 1 where Name = " + rs2.getInt("Name");
                System.out.println("check " +query3);
                PreparedStatement preparedStmt = conn.prepareStatement(query3);
                preparedStmt.executeUpdate();
            }

            query2 = "select Name from Clients where '" + LocalDate.now() + "' between CheckInTime and CheckOutTime";

            rs2 = st.executeQuery(query2);

            while (rs2.next()){
                String query3 = "update Rooms set isAvailable = 0 where Name = " + rs2.getInt("Name");
                System.out.println(query3);
                PreparedStatement preparedStmt = conn.prepareStatement(query3);
                preparedStmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean changeEmailHR(String emailtest, String pass) throws SQLException, ClassNotFoundException{

        try{
            Properties properties = new Properties();

            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", host);

            Session emailSession = Session.getDefaultInstance(properties);


            Store store = emailSession.getStore("imaps");

            store.connect(host, emailtest, pass);


            email = emailtest;
            email_pas = pass;
            return true;

        } catch (NoSuchProviderException e) {
            return false;
        } catch (MessagingException e) {
            return false;
        }

    }

    public static boolean addNewWorker(String name, String newPassport, String newEmail, String newLogin, String newPassword, String newPost, int newSalary) throws SQLException, ClassNotFoundException{

        try {



            String query = " insert into Workers (HotelID, Fullname, Passport, Salary, Post, Login, Password, Email)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, hotelID);
            preparedStmt.setString(2, name);
            preparedStmt.setString(3, newPassport);
            preparedStmt.setInt(4, newSalary);
            preparedStmt.setString(5, newPost);
            preparedStmt.setString(6, newLogin);
            preparedStmt.setString(7, newPassword);
            preparedStmt.setString(8, newEmail);


            preparedStmt.execute();
            if(!newPost.equals("Admin") || !newPost.equals("Marketing") || !newPost.equals("Director")) {
                String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                int iddd = getWorkerId(newPassport);
                for (int i = 0; i < 12; i++) {
                    query = "insert into " + months[i] + " (WorkerID) values (?)";
                    System.out.println(query);
                    preparedStmt = conn.prepareStatement(query);

                    preparedStmt.setInt(1, iddd);

                    preparedStmt.execute();
                }
            }

            query = "UPDATE Hotels Set Workers = Workers + 1 WHERE HotelID = '" + hotelID + "'";
            System.out.println(query);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();











            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



        return false;

    }

    public static boolean addNewHotel(String name, String address, int stars, int finances){

        try {



            String query = " insert into Hotels ( Name, Address, Finances, Stars)"
                    + " values (?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, address);
            preparedStmt.setInt(3, finances);
            preparedStmt.setInt(4, stars);

            preparedStmt.execute();

            int lastHotel = getMaxHotelID();


            String query1 = " insert into Workers (HotelID, Fullname, Passport, Salary, Post, Login, Email)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";


            preparedStmt = conn.prepareStatement(query1);
            preparedStmt.setInt(1, lastHotel);
            preparedStmt.setString(2, "director");
            preparedStmt.setString(3, "director");
            preparedStmt.setInt(4, 0);
            preparedStmt.setString(5, "Director");
            preparedStmt.setString(6, "director");
            preparedStmt.setString(7, "director");


            preparedStmt.execute();

            preparedStmt = conn.prepareStatement(query1);
            preparedStmt.setInt(1, lastHotel);
            preparedStmt.setString(2, "admin");
            preparedStmt.setString(3, "admin");
            preparedStmt.setInt(4, 0);
            preparedStmt.setString(5, "Admin");
            preparedStmt.setString(6, "admin");
            preparedStmt.setString(7, "admin");


            preparedStmt.execute();

            String query2 = " insert into Profit (HotelID)"
                    + " values (?)";


            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setInt(1, lastHotel);

            preparedStmt.execute();

            String query3 = " insert into Loss (HotelID)"
                    + " values (?)";


            preparedStmt = conn.prepareStatement(query3);
            preparedStmt.setInt(1, lastHotel);

            preparedStmt.execute();







            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



        return false;

    }




    public static boolean addNewRoom(int number, int price ,String size, String view, String type, boolean extrabed, boolean child){

        try {



            String query = " insert into Rooms (HotelID, Price, Size, Extra_bed, Child, Type, View, Name)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, hotelID);
            preparedStmt.setInt(2, price);
            preparedStmt.setString(3, size);
            if(extrabed)
                preparedStmt.setInt(4, 1);
            else
                preparedStmt.setInt(4, 0);
            if(child)
                preparedStmt.setInt(5, 1);
            else
                preparedStmt.setInt(5, 0);
            preparedStmt.setString(6, type);
            preparedStmt.setString(7, view);
            preparedStmt.setInt(8, number);


            preparedStmt.execute();

            query = "UPDATE Hotels Set Rooms = Rooms + 1 WHERE HotelID = '" + hotelID + "'";
            System.out.println(query);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();


            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



        return false;

    }


    public static int getWorkerId(String passport){
        try
        {








            String query = "SELECT WorkerID FROM Workers WHERE Passport = \'" + passport + "\'";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            rs.next();
            int post = rs.getInt("WorkerID");
            return post;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public static void update_finances(){
            try {
                Statement st = conn.createStatement();
                String query = "UPDATE Hotels Set Finances = '" + getFinances() + "' WHERE HotelID = " + hotelID;
                System.out.println(query);
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                query = "update Workers Set Loss = 0 where HotelID = " + hotelID;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static boolean changePassword(String oldPas, String newPas, String conNewPas) throws SQLException, ClassNotFoundException{
        if(oldPas.equals(password) && newPas.equals(conNewPas) && !newPas.equals(oldPas)) {
            try {
                Statement st = conn.createStatement();
                String query = "UPDATE Workers Set Password = '" + newPas + "' WHERE Workers.Login = '" + login + "' and Workers.Password = '" + password + "' and WorkerID = " + id;
                System.out.println(query);
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();
                password = newPas;
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static void changeWorker(int idd, String name, String passport, String email, String login, String password, int salary) throws SQLException, ClassNotFoundException{
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Workers Set Fullname = ?, Passport = ?, Salary = ?, Login = ?, Password = ?, Email = ? where WorkerID = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,name);
            preparedStmt.setString(2, passport);
            preparedStmt.setInt(3, salary);
            preparedStmt.setString(4, login);
            preparedStmt.setString(5, password);
            preparedStmt.setString(6, email);
            preparedStmt.setInt(7, idd);

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean changeRoom(int Fid  ,int idd, int price, String size, String view, String type, boolean ext, boolean child){
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Rooms Set Name = ?, Price = ?, Size = ?, View = ?, Type = ?, Extra_bed = ?, Child = ? where Name = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,idd);
            preparedStmt.setInt(2, price);
            preparedStmt.setString(3, size);
            preparedStmt.setString(4, view);
            preparedStmt.setString(5, type);
            preparedStmt.setBoolean(6, ext);
            preparedStmt.setBoolean(7, child);
            preparedStmt.setInt(8, Fid);

            preparedStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    public static void changeClient(int idd, String name, String passport, int Name, LocalDate Checkin, LocalDate Checkout, LocalDate Dob) throws SQLException, ClassNotFoundException{
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Clients Set Fullname = ?, Passport = ?, Name = ?, CheckInTime = ?, CheckOutTime = ?, DateOfBirth = ? where ClientID = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,name);
            preparedStmt.setString(2, passport);
            preparedStmt.setInt(3, Name);
            preparedStmt.setDate(4, Date.valueOf(Checkin));
            preparedStmt.setDate(5, Date.valueOf(Checkout));
            preparedStmt.setDate(6, Date.valueOf(Dob));
            preparedStmt.setInt(7, idd);

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public static void changeHotel(String name, String address, int stars, int finances) throws SQLException, ClassNotFoundException{
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Hotels Set Name = ?, Address = ?, Stars = ?, Finances = ? where HotelID = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,name);
            preparedStmt.setString(2, address);
            preparedStmt.setInt(3, stars);
            preparedStmt.setInt(4, finances);
            preparedStmt.setInt(5, hotelID);


            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void changeHotelAdmin(int idd, String name, String address, int finances, int rooms, int clients, int workers, int stars) throws SQLException, ClassNotFoundException{
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Hotels Set Name = ?, Address = ?, Stars = ?, Finances = ?, Rooms = ?, Workers = ?, Clients = ? where HotelID = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,name);
            preparedStmt.setString(2, address);
            preparedStmt.setInt(3, stars);
            preparedStmt.setInt(4, finances);
            preparedStmt.setInt(5, rooms);
            preparedStmt.setInt(6, workers);
            preparedStmt.setInt(7, clients);
            preparedStmt.setInt(8, idd);


            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public static void changeWorkerSalary(int idd, int salary) throws SQLException, ClassNotFoundException{
        try {
            Statement st = conn.createStatement();
            String query = "UPDATE Workers Set Salary = ? where WorkerID = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,salary);
            preparedStmt.setInt(2, idd);

            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void update_attendance(int idd, Date date, String radio) throws SQLException, ClassNotFoundException{
        try {
            String[] months = {"January", "February", "March", "April", "May", "June", "Juli", "August", "September", "October", "November", "December"};


            Statement st = conn.createStatement();

            String query = "update "+ months[date.getMonth()]+" set `"+ date.getDate() +"` = ? where WorkerID = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = conn.prepareStatement(query);


            preparedStmt.setString(1, radio);
            preparedStmt.setInt(2, idd);


            preparedStmt.executeUpdate();
            System.out.println("okkkkkkkkkkk");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public static boolean addClient(String name, String passport, LocalDate dob, LocalDate intime, LocalDate outtime, String room, String counry) throws SQLException, ClassNotFoundException {


        try {

            List<String> list = new ArrayList<String>(Arrays.asList(room.split(" ")));
            System.out.println(list);

            List<String> con = new ArrayList<String>(Arrays.asList(counry.split(" ")));
            System.out.println(con);

            String query = " insert into Clients (HotelID, Name, Fullname, Passport, DateOfBirth, CheckInTime, CheckOutTime, Amount)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, hotelID);
            preparedStmt.setInt(2, Integer.parseInt(list.get(0)));
            preparedStmt.setString(3, name);
            preparedStmt.setString(4, passport);
            preparedStmt.setDate(5, Date.valueOf(dob));
            preparedStmt.setDate(6, Date.valueOf(intime));
            preparedStmt.setDate(7, Date.valueOf(outtime));
            System.out.println("cost " + list.get(0));
            preparedStmt.setInt(8, ((int) ChronoUnit.DAYS.between(intime, outtime) )* Data_work.getCost(room));


            preparedStmt.execute();


            query = "UPDATE Rooms Set isAvailable = 0 WHERE Rooms.HotelID = " + hotelID + " and Rooms.Name = " + list.get(0);
            System.out.println(query);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();

            query = "UPDATE Countries Set clients = clients + 1 WHERE Countries.id = '" + con.get(0) + "'";
            System.out.println(query);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



        return false;
    }

    public static boolean withdraw(String des, Date date, int sum) {


        try {


            String query = " insert into withdraw_money (HotelID, Workers_passport, Sum, Description, Date)"
                    + " values (?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, hotelID);
            preparedStmt.setString(2, Dpassport);
            preparedStmt.setInt(3, sum);
            preparedStmt.setString(4, des);
            preparedStmt.setDate(5, date);



            preparedStmt.execute();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

            query = "UPDATE Loss Set " +month + " = " +month +" + " + sum + " WHERE HotelID = '" + hotelID + "'";
            System.out.println(query);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();



            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



        return false;
    }

    public static void ForfeitWorker(int idd , String des, Date date, int sum) {


        try {


            String query = " insert into monetary_fine (HotelID, WorkerID, Price, Description, Date)"
                    + " values (?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, hotelID);
            preparedStmt.setInt(2, idd);
            preparedStmt.setInt(3, sum);
            preparedStmt.setString(4, des);
            preparedStmt.setDate(5, date);



            preparedStmt.execute();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void addWorkMaid(int idd , String des, Date stars, Date end) {


        try {


            String query = " insert into Cleaners (HotelID, WorkerID,  Description, Start, End)"
                    + " values (?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, hotelID);
            preparedStmt.setInt(2, idd);
            preparedStmt.setString(3, des);
            preparedStmt.setDate(4, stars);
            preparedStmt.setDate(5, end);



            preparedStmt.execute();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }





    public static int getId() throws ClassNotFoundException, SQLException {






        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(idHotels) AS id FROM Hotels");
        rs.next();
        int lastid = rs.getInt("id");
        return lastid;
    }
    public static int getIdHotel(String hotel) throws ClassNotFoundException, SQLException {






        Statement st = conn.createStatement();
        String query = "SELECT HotelID AS id FROM Hotels WHERE Hotels.Name = \"";
        ResultSet rs = st.executeQuery(query + hotel + "\"");
        rs.next();
        int lastid = rs.getInt("id");
        return lastid;
    }

    public static Map<String, String> getMonths(String month){
        try
        {







            String dmonth = "$." + month.toLowerCase().substring(0, 1).toUpperCase() + month.toLowerCase().substring(1);
            String query = "SELECT JSON_EXTRACT(Month, \"" + dmonth + "\" ) AS Months FROM Workers WHERE Workers.Login = \"";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query + login +"\" and Workers.Password = \"" + password + "\"");


            rs.next();

            String post = removeFirstandLast(rs.getString("Months"));
            System.out.println(post);


            Map<String, String> hashMap
                    = new HashMap<String, String>();


            String parts[] = post.split(",");


            for (String part : parts) {



                String stuData[] = part.split(":");

                String stuRollNo = stuData[0].trim();
                String stuName = stuData[1].trim();


                hashMap.put(stuRollNo, stuName);
            }



            return hashMap;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static Map<String, String> getMonth(String month){
        try
        {
            String[] m31 = {"January", "March", "May", "Juli", "August", "October", "December"};
            String[] m30 = {"April", "June", "September", "November"};
            String query;
            int d;







            String dmonth = month.toLowerCase().substring(0, 1).toUpperCase() + month.toLowerCase().substring(1);
            if(Arrays.stream(m31).anyMatch(dmonth::equals)) {
                query = "SELECT `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31` from " + dmonth + " WHERE " + dmonth + ".WorkerID = " + id;
                d = 31;
            }
            else if(Arrays.stream(m30).anyMatch(dmonth::equals)){
                query = "SELECT `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30` from " + dmonth + " WHERE " + dmonth + ".WorkerID = " + id;
                d = 30;
            }
            else{
                query = "SELECT `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29` from " + dmonth + " WHERE " + dmonth + ".WorkerID = " + id;
                d = 29;
            }


            System.out.println(query);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            Map<String, String> hashMap
                    = new HashMap<String, String>();
            rs.next();
            for (int i = 1; i <= d; i++) {
                hashMap.put(String.valueOf(i), rs.getString(String.valueOf(i)));
            }


            System.out.println(hashMap);





            return hashMap;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static Map<String, String> getMonth(String month, int id){
        try
        {
            String[] m31 = {"January", "March", "May", "Juli", "August", "October", "December"};
            String[] m30 = {"April", "June", "September", "November"};
            String query;
            int d;







            String dmonth = month.toLowerCase().substring(0, 1).toUpperCase() + month.toLowerCase().substring(1);
            if(Arrays.stream(m31).anyMatch(dmonth::equals)) {
                query = "SELECT `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31` from " + dmonth + " WHERE " + dmonth + ".WorkerID = " + id;
                d = 31;
            }
            else if(Arrays.stream(m30).anyMatch(dmonth::equals)){
                query = "SELECT `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30` from " + dmonth + " WHERE " + dmonth + ".WorkerID = " + id;
                d = 30;
            }
            else{
                query = "SELECT `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29` from " + dmonth + " WHERE " + dmonth + ".WorkerID = " + id;
                d = 29;
            }


            System.out.println(query);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);


            Map<String, String> hashMap
                    = new HashMap<String, String>();
            rs.next();
            for (int i = 1; i <= d; i++) {
                hashMap.put(String.valueOf(i), rs.getString(String.valueOf(i)));
            }


            System.out.println(hashMap);





            return hashMap;


        }
        catch (Exception e) {
            System.err.println("Got an exception!!!! ");
            System.err.println(e.getMessage());
        }
        return null;
    }



















        public static void deleteWorker(String id) {
            System.out.println(id);
            try {





                PreparedStatement st = conn.prepareStatement("DELETE FROM Workers WHERE WorkerID = ?");

                st.setInt(1, Integer.parseInt(id));
                st.executeUpdate();




                PreparedStatement preparedStmt;
                String query = "UPDATE Hotels Set Workers = Workers - 1 WHERE HotelID = '" + hotelID + "'";
                System.out.println(query);
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();


            } catch (Exception e) {
                System.out.println(e);
            }
        }

    public static void deleteCleaners(String id) {
        System.out.println(id);
        try {





            PreparedStatement st = conn.prepareStatement("DELETE FROM Cleaners WHERE id = ?");

            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();




        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public static boolean deleteRoom(int id) {
        try {





            PreparedStatement st = conn.prepareStatement("DELETE FROM Rooms WHERE Name = ?");

            st.setInt(1, id);
            st.executeUpdate();


            PreparedStatement preparedStmt;
            String query = "UPDATE Hotels Set Rooms = Rooms - 1 WHERE HotelID = '" + hotelID + "'";
            System.out.println(query);
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();

            return true;


        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean deleteHotel(int id) {
        try {





            PreparedStatement st = conn.prepareStatement("DELETE FROM Hotels WHERE HotelID = ?");

            st.setInt(1, id);
            st.executeUpdate();


            return true;


        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean isAvailable(int id) {
        try {





            String query =  "Select isAvailable FROM Rooms WHERE Name = " + id + " and HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                if(rs.getBoolean("isAvailable")){
                    return true;
                }
            }

            return false;



        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static int getMaxRoomNumber() {
        try {





            String query =  "Select MAX(Name) FROM Rooms WHERE HotelID = " + hotelID;

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                return rs.getInt("MAX(Name)");
            }

            return 0;



        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getMaxHotelID() {
        try {





            String query =  "Select MAX(HotelID) FROM Hotels";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                return rs.getInt("MAX(HotelID)");
            }

            return 0;



        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }







}


