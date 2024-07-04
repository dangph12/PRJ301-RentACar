/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Order;
import model.OrderStatus;

/**
 *
 * @author admin
 */
public class OrderDAO {

    public void cancelOrderByOrderUID(String orderUID) throws SQLException {
        String query = """
                       UPDATE [Rent_A_Car].[dbo].[orders]
                        SET [status] = ?
                        WHERE [order_uid] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, OrderStatus.CANCELLED.getKey());
        pstmt.setString(2, orderUID);

        pstmt.executeUpdate();
    }

    public ResultSet getOrdersByUserUID(String userUID) throws SQLException {
        String query = """
                       SELECT [order_uid]
                             ,[user_uid]
                             ,[category_uid]
                             ,[car_count]
                             ,[received_at]
                             ,[returned_at]
                             ,[status]
                             ,[created_at]
                         FROM [Rent_A_Car].[dbo].[orders]
                       WHERE [user_uid] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, userUID);

        return executeQuery(pstmt);

    }

    public void insertOrderToDatabases(Order order) throws SQLException {
        String query = """
                       INSERT INTO [Rent_A_Car].[dbo].[orders]
                       ([order_uid],[user_uid],[category_uid],[car_count],[received_at]
                             ,[returned_at],[status],[created_at])
                       VALUES (?,?,?,?,?,?,?,?)
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, order.getOrderUID());
        pstmt.setString(2, order.getUserUID());
        pstmt.setString(3, order.getCategoryUID());
        pstmt.setInt(4, order.getCarCount());
        pstmt.setDate(5, order.getReceivedDate());
        pstmt.setDate(6, order.getReturnedDate());
        pstmt.setInt(7, order.getOrderStatus().getKey());
        pstmt.setDate(8, order.getCreatedDate());

        pstmt.executeUpdate();

    }

    private static OrderDAO instance;

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
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

    public OrderDAO() {
    }
}
