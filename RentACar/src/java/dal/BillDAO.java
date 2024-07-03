/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Bill;

/**
 *
 * @author admin
 */
public class BillDAO {

    public void insertBillToDatabases(Bill bill) throws SQLException {
        String query = """
                       INSERT INTO [Rent_A_Car].[dbo].[bills]
                       ([order_uid],[total_amount],[payment_method],[is_paid],[created_at])
                       VALUES (?,?,?,?,?)
                       """;
        PreparedStatement pstmt = createPreparedStatement(query);
        
        pstmt.setString(1, bill.getOrderUID());
        pstmt.setInt(2, bill.getTotalAmount());
        pstmt.setInt(3, bill.getPaymentMethod());
        pstmt.setBoolean(4, bill.isPaid());
        pstmt.setDate(5, bill.getCreatedDate());
        
        pstmt.executeUpdate();

    }

    private static BillDAO instance;

    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
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

    public BillDAO() {
    }
}
