/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class CategoryDAO {

    public ResultSet getAllCategories() throws SQLException {
        String query = """
                       SELECT [category_uid]
                             ,[title]
                             ,[description]
                             ,[number_of_seats]
                             ,[unit_price]
                             ,[image]
                         FROM [Rent_A_Car].[dbo].[categories]""";
        
        PreparedStatement pstmt = createPreparedStatement(query);
        
        return executeQuery(pstmt);
    }

    public ResultSet getCategoryByCategoryUID(String categoryUID) throws SQLException {
        String query = """
                       SELECT [category_uid]
                             ,[title]
                             ,[description]
                             ,[number_of_seats]
                             ,[unit_price]
                             ,[image]
                         FROM [Rent_A_Car].[dbo].[categories]
                       WHERE [category_uid] = ?""";
        PreparedStatement pstmt = createPreparedStatement(query);
        
        pstmt.setString(1, categoryUID);
        
        return executeQuery(pstmt);
    }

    private static CategoryDAO instance;

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
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

    public CategoryDAO() {
    }

}
