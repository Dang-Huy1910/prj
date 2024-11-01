/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import model.PlanDetails;
import model.PlanHeader;
import model.Shift;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDetailsDAO {
    private Connection connection;

    public PlanDetailsDAO(Connection connection) {
        this.connection = connection;
    }
    public PlanDetailsDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addPlanDetails(PlanDetails planDetails) throws SQLException {
        String sql = "INSERT INTO PlanDetails (phid, sid, date, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planDetails.getPlanHeader() != null ? planDetails.getPlanHeader().getPhid() : 0); // Get PlanHeader ID
            statement.setInt(2, planDetails.getShift() != null ? planDetails.getShift().getSid() : 0); // Get Shift ID
            statement.setDate(3, planDetails.getDate());
            statement.setInt(4, planDetails.getQuantity());
            statement.executeUpdate();
        }
    }

    // Read
    public PlanDetails getPlanDetails(int pdid) throws SQLException {
        String sql = "SELECT * FROM PlanDetails WHERE pdid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pdid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PlanHeaderDAO planHeaderDAO = new PlanHeaderDAO(connection);
                ShiftDAO shiftDAO = new ShiftDAO(connection);
                PlanHeader planHeader = planHeaderDAO.getPlanHeader(resultSet.getInt("phid")); // Get PlanHeader by ID
                Shift shift = shiftDAO.getShiftById(resultSet.getInt("sid")); // Get Shift by ID
                return new PlanDetails(
                    resultSet.getInt("pdid"),
                    planHeader,
                    shift,
                    resultSet.getDate("date"),
                    resultSet.getInt("quantity")
                );
            }
        }
        return null; // Return null if no PlanDetails found
    }

    // Read all
    public List<PlanDetails> getAllPlanDetails() throws SQLException {
        List<PlanDetails> planDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM PlanDetails";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                PlanHeaderDAO planHeaderDAO = new PlanHeaderDAO(connection);
                ShiftDAO shiftDAO = new ShiftDAO(connection);
                PlanHeader planHeader = planHeaderDAO.getPlanHeader(resultSet.getInt("phid")); // Get PlanHeader by ID
                Shift shift = shiftDAO.getShiftById(resultSet.getInt("sid")); // Get Shift by ID
                PlanDetails planDetails = new PlanDetails(
                    resultSet.getInt("pdid"),
                    planHeader,
                    shift,
                    resultSet.getDate("date"),
                    resultSet.getInt("quantity")
                );
                planDetailsList.add(planDetails);
            }
        }
        return planDetailsList;
    }

    // Update
    public void updatePlanDetails(PlanDetails planDetails) throws SQLException {
        String sql = "UPDATE PlanDetails SET phid = ?, sid = ?, date = ?, quantity = ? WHERE pdid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planDetails.getPlanHeader() != null ? planDetails.getPlanHeader().getPhid() : 0);
            statement.setInt(2, planDetails.getShift() != null ? planDetails.getShift().getSid() : 0);
            statement.setDate(3, planDetails.getDate());
            statement.setInt(4, planDetails.getQuantity());
            statement.setInt(5, planDetails.getPdid());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deletePlanDetails(int pdid) throws SQLException {
        String sql = "DELETE FROM PlanDetails WHERE pdid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pdid);
            statement.executeUpdate();
        }
    }
}
