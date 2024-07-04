/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Car;
import model.CarStatus;

/**
 *
 * @author admin
 */
public class CarDAO {
    
    public void setUnavailableCars(ArrayList<Car> cars) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[cars]
                       SET [status] = ?
                       WHERE [car_number_plate] = ?
                       """;
        for (Car car : cars) {
            PreparedStatement pstmt = createPreparedStatement(query);

            pstmt.setInt(1, CarStatus.UNAVAILABLE.getKey());
            pstmt.setString(2, car.getCarNumberPlate());

            pstmt.executeUpdate();
        }
    }
    
    public void setBookedCars(ArrayList<Car> cars) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[cars]
                       SET [status] = ?
                       WHERE [car_number_plate] = ?
                       """;
        for (Car car : cars) {
            PreparedStatement pstmt = createPreparedStatement(query);

            pstmt.setInt(1, CarStatus.BOOKED.getKey());
            pstmt.setString(2, car.getCarNumberPlate());

            pstmt.executeUpdate();
        }
    }

    public void insertCarsByOrderUID(String orderUID, ArrayList<Car> cars) throws SQLException {
        String query = """
                       INSERT INTO [Rent_A_Car].[dbo].[orders_detailed_cars]
                       ([order_uid],[car_number_plate])
                       VALUES (?,?)
                       """;
        for (Car car : cars) {
            PreparedStatement pstmt = createPreparedStatement(query);

            pstmt.setString(1, orderUID);
            pstmt.setString(2, car.getCarNumberPlate());

            pstmt.executeUpdate();
        }

    }

    public ResultSet getCarsByOrderUID(String orderUID) throws SQLException {
        String query = """
                           SELECT b.[car_number_plate]
                           	  ,[status]
                                 ,[category_uid]
                             FROM [Rent_A_Car].[dbo].[orders_detailed_cars] a,
                             [Rent_A_Car].[dbo].[cars] b
                             WHERE a.car_number_plate = b.car_number_plate AND [order_uid] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, orderUID);

        return executeQuery(pstmt);
    }

    public ResultSet getAvailableCarsEachCategory(String categoryUID, int carCount) throws SQLException {
        String query = """
                           SELECT TOP(?) [car_number_plate]
                                    ,[status]
                                    ,b.[category_uid]
                           FROM categories a, cars b
                           WHERE a.category_uid = b.category_uid AND b.status = 1
                           AND a.category_uid = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, carCount);
        pstmt.setString(2, categoryUID);

        return executeQuery(pstmt);
    }

    public ResultSet getAvailableCarCountEachCategory(String categoryUID) throws SQLException {
        String query = """
                           SELECT COUNT([car_number_plate]) as car_count
                           FROM categories a, cars b
                           WHERE a.category_uid = b.category_uid AND b.status = 1
                           AND a.category_uid = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, categoryUID);

        return executeQuery(pstmt);
    }

    private static CarDAO instance;

    public static CarDAO getInstance() {
        if (instance == null) {
            instance = new CarDAO();
        }
        return instance;
    }

    /**
     * Create instance for PreparedStatement class
     *
     * @param query the SQL query to be prepared.
     *
     * @return a PreparedStatement object that can be used to execute the query
     * with specified parameters.
     *
     * @throws SQLException if a database access error occurs or the SQL
     * statement cannot be pre-compiled.
     */
    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        Connection conn = DBContext.getInstance().getConnection();
        return conn.prepareStatement(query);
    }

    /**
     * Executes a SQL query using the provided PreparedStatement.
     *
     * @param pstmt the PreparedStatement to be executed.
     * @return the ResultSet obtained from executing the query.
     * @throws java.sql.SQLException
     */
    public ResultSet executeQuery(PreparedStatement pstmt) throws SQLException {
        return pstmt.executeQuery();
    }

    public CarDAO() {
    }
}
