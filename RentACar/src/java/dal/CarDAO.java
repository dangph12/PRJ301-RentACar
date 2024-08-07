/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CarStatus;

/**
 *
 * @author admin
 */
public class CarDAO {
    
    public void deleteCarByOrderUID(String orderUID) throws SQLException {
        String query = """
                       Delete from [Rent_A_Car].[dbo].[orders_detailed_cars]
                        WHERE [order_uid] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, orderUID);

        pstmt.executeUpdate();

    }

    public void setRunningCar(String carNumberPlate) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[cars]
                       SET [status] = ?
                       WHERE [car_number_plate] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, CarStatus.RUNNING.getKey());
        pstmt.setString(2, carNumberPlate);

        pstmt.executeUpdate();

    }

    public void setUnavailableCar(String carNumberPlate) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[cars]
                       SET [status] = ?
                       WHERE [car_number_plate] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, CarStatus.UNAVAILABLE.getKey());
        pstmt.setString(2, carNumberPlate);

        pstmt.executeUpdate();

    }

    public ResultSet getCarsCountWithTitle(String title) throws SQLException {
        String full_title = "%" + title + "%";
        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[cars]
                       INNER JOIN [Rent_A_Car].[dbo].[categories] ON [cars].category_uid = [categories].category_uid
                        WHERE [title] LIKE ?
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, full_title);

        return executeQuery(pstmt);
    }

    public ResultSet getCarsCount() throws SQLException {
        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[cars]
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);

        return executeQuery(pstmt);
    }

    public ResultSet pagingCarsWithTitle(int index, String title) throws SQLException {
        String full_title = "%" + title + "%";
        int carsCountPerPage = 7;

        String query = """
                       SELECT [car_number_plate]
                             ,[cars].category_uid
                             ,[status]
                         FROM [Rent_A_Car].[dbo].[cars]
                         INNER JOIN [Rent_A_Car].[dbo].[categories] ON [cars].category_uid = [categories].category_uid
                       WHERE [title] LIKE ?
                       ORDER BY [car_number_plate]
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, full_title);
        pstmt.setInt(2, (index - 1) * carsCountPerPage);
        pstmt.setInt(3, carsCountPerPage);

        return executeQuery(pstmt);
    }

    public ResultSet pagingCars(int index) throws SQLException {
        int carsCountPerPage = 7;

        String query = """
                       SELECT [car_number_plate]
                             ,[cars].category_uid
                             ,[status]
                         FROM [Rent_A_Car].[dbo].[cars]
                         INNER JOIN [Rent_A_Car].[dbo].[categories] ON [cars].category_uid = [categories].category_uid
                       ORDER BY [car_number_plate]
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, (index - 1) * carsCountPerPage);
        pstmt.setInt(2, carsCountPerPage);

        return executeQuery(pstmt);
    }

    public void setAvailableCar(String carNumberPlate) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[cars]
                       SET [status] = ?
                       WHERE [car_number_plate] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, CarStatus.AVAILABLE.getKey());
        pstmt.setString(2, carNumberPlate);

        pstmt.executeUpdate();

    }

    public void setBookedCar(String carNumberPlate) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[cars]
                       SET [status] = ?
                       WHERE [car_number_plate] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, CarStatus.BOOKED.getKey());
        pstmt.setString(2, carNumberPlate);

        pstmt.executeUpdate();

    }

    public void insertCarByOrderUID(String orderUID, String carNumberPlate) throws SQLException {
        String query = """
                       INSERT INTO [Rent_A_Car].[dbo].[orders_detailed_cars]
                       ([order_uid],[car_number_plate])
                       VALUES (?,?)
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, orderUID);
        pstmt.setString(2, carNumberPlate);

        pstmt.executeUpdate();

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
