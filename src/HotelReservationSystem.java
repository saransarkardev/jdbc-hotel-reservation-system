import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "mSm@6951";


    public static void main(String[] args) throws ClassNotFoundException, SQLException{

        // Driver load
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            while (true) {
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(statement, scanner);
                        break;
                    case 2:
                        viewReservations(statement);
                        break;
                    case 3:
                        getRoomNumber(statement, scanner);
                        break;
                    case 4:
                        updateReservation(statement, scanner);
                        break;
                    case 5:
                        deleteReservation(statement, scanner);
                        break;
                    case 0:
                        exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid Choice. Try Again.");
                }

            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void reserveRoom(Statement statement, Scanner scanner) {

        try{
            System.out.print("Enter Guest Name: ");
            scanner.nextLine();
            String guestName = scanner.nextLine();

            System.out.print("Enter Room Number: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Contact Number: ");
            String contactNumber = scanner.nextLine();

            String sqlQuery = "INSERT INTO reservations (guest_name, room_number, contact_number) "+
                    "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";


            int affectedRows = statement.executeUpdate(sqlQuery);

            if (affectedRows > 0) {
                System.out.println("Reservation Successful!");
            }
            else {
                System.out.println("Reservation failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservations(Statement statement) throws SQLException{

        String sqlQuery = "SELECT * FROM reservations;";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        System.out.println();
        System.out.println("========================================================================================================");
        System.out.printf("| %-13s | %-20s | %-13s | %-18s | %-22s |%n",
                "Reservation ID", "Guest Name", "Room Number", "Contact Number", "Reservation Date");
        System.out.println("========================================================================================================");

        while (resultSet.next()) {
            int reservationId = resultSet.getInt("reservation_id");
            String guestName = resultSet.getString("guest_name");
            int roomNumber = resultSet.getInt("room_number");
            String contactNumber = resultSet.getString("contact_number");
            String reservationDate = resultSet.getTimestamp("reservation_date").toString();

            System.out.printf("| %-13d | %-20s | %-13d | %-18s | %-22s |%n",
                    reservationId, guestName, roomNumber, contactNumber, reservationDate);
        }

        System.out.println("========================================================================================================");

    }

    private static void getRoomNumber(Statement statement, Scanner scanner) throws SQLException{

        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Guest Name: ");
        String guestName = scanner.nextLine();

        String sqlQuery = "SELECT room_number FROM reservations WHERE reservation_id = " + reservationId +
                " AND guest_name = '"+ guestName + "'";

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                int roomNumber = resultSet.getInt("room_number");
                System.out.println("Room number for Reservation ID "+ reservationId + " and Guest " +
                        guestName + " is " + roomNumber);
            }
            else {
                System.out.println("Reservation not found for the given ID and guest name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void updateReservation(Statement statement, Scanner scanner) {
        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consumes the newLine character

        if (!reservationExists(statement, reservationId)) {
            System.out.println("Reservation not found for the given ID.");
            return;
        }

        System.out.print("Enter New Guest Name: ");
        String newGuestName = scanner.nextLine();

        System.out.print("Enter New Room Number: ");
        int newRoomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Contact Number: ");
        String newContactNumber = scanner.nextLine();

        String sqlQuery = "UPDATE reservations SET guest_name = '" + newGuestName + "', " + "room_number = " +
                newRoomNumber + ", " + "contact_number = '" + newContactNumber + "' " + "WHERE reservation_id = "
                + reservationId;

        try {
            int affectedRows = statement.executeUpdate(sqlQuery);

            if (affectedRows > 0) {
                System.out.println("Reservation Updated Successfully!");
            }
            else {
                System.out.println("Reservation Update failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void deleteReservation(Statement statement, Scanner scanner) {
        System.out.println("Enter Reservation Id: ");
        int reservationId = scanner.nextInt();

        if (!reservationExists(statement, reservationId)) {
            System.out.println("Reservation not found for the given Id");
            return;
        }

        String sqlQuery = "DELETE FROM reservations WHERE reservation_id = " + reservationId;

        try {
            int afftectedRows = statement.executeUpdate(sqlQuery);

            if (afftectedRows > 0) {
                System.out.println("Reservation deleted Successfully.");
            }
            else {
                System.out.println("Reservation deletion Failed.d");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
    private static boolean reservationExists(Statement statement, int reservationId) {

        String sqlQuery = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;

        try {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // If reservation id found found then it returns true, else false.
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void exit() throws InterruptedException{
        System.out.println();
        System.out.print("Exiting System");
        int i = 5;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
    }
}