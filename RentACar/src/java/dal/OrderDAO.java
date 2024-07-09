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

    public ResultSet getOrdersCountWithName(String name) throws SQLException {
        String full_name = "%" + name + "%";
        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[orders]
                       INNER JOIN [Rent_A_Car].[dbo].[users_information] ON [orders].user_uid = [users_information].user_uid
                       WHERE full_name LIKE ?
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, full_name);

        return executeQuery(pstmt);
    }

    public ResultSet pagingOrdersWithName(int index, String name) throws SQLException {
        int ordersCountPerPage = 7;
        String full_name = "%" + name + "%";

        String query = """
                        SELECT orders.[order_uid]
                        ,orders.[user_uid]
                        ,[received_at]
                        ,[returned_at]
                        ,[status]
                        ,[created_at]
                        FROM [Rent_A_Car].[dbo].[orders]
                        INNER JOIN [Rent_A_Car].[dbo].[users_information] ON [orders].user_uid = [users_information].user_uid
                        INNER JOIN [Rent_A_Car].[dbo].[bills] ON [orders].order_uid = [bills].order_uid
                        WHERE full_name LIKE ?
                        ORDER BY [orders].order_uid
                       	OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, full_name);
        pstmt.setInt(2, (index - 1) * ordersCountPerPage);
        pstmt.setInt(3, ordersCountPerPage);

        return executeQuery(pstmt);
    }

    public ResultSet getOrdersCount() throws SQLException {
        String query = """
                       SELECT count(*) FROM [Rent_A_Car].[dbo].[orders]
                       """;

        PreparedStatement pstmt = createPreparedStatement(query);

        return executeQuery(pstmt);
    }

    public ResultSet pagingOrders(int index) throws SQLException {
        int ordersCountPerPage = 7;

        String query = """
                       SELECT orders.[order_uid]
                       ,orders.[user_uid]
                       ,[received_at]
                       ,[returned_at]
                       ,[status]
                       ,[created_at]
                       FROM [Rent_A_Car].[dbo].[orders]
                       INNER JOIN [Rent_A_Car].[dbo].[users_information] ON [orders].user_uid = [users_information].user_uid
                       INNER JOIN [Rent_A_Car].[dbo].[bills] ON [orders].order_uid = [bills].order_uid
                       ORDER BY [orders].order_uid
                       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setInt(1, (index - 1) * ordersCountPerPage);
        pstmt.setInt(2, ordersCountPerPage);

        return executeQuery(pstmt);
    }

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

    public ResultSet getOrderByOrderUID(String orderUID) throws SQLException {
        String query = """
                       SELECT [order_uid]
                             ,[user_uid]
                             ,[received_at]
                             ,[returned_at]
                             ,[status]
                             ,[created_at]
                         FROM [Rent_A_Car].[dbo].[orders]
                       WHERE [order_uid] = ?
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, orderUID);

        return executeQuery(pstmt);

    }

    public ResultSet getOrdersByUserUID(String userUID) throws SQLException {
        String query = """
                       SELECT [order_uid]
                             ,[user_uid]
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
                       ([order_uid],[user_uid],[received_at]
                             ,[returned_at],[status],[created_at])
                       VALUES (?,?,?,?,?,?)
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, order.getOrderUID());
        pstmt.setString(2, order.getUser().getUserUID());
        pstmt.setDate(3, order.getReceivedDate());
        pstmt.setDate(4, order.getReturnedDate());
        pstmt.setInt(5, order.getOrderStatus().getKey());
        pstmt.setDate(6, order.getCreatedDate());

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
