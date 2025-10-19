package com.example.api.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBUtils {

    private static Connection getConnection() throws SQLException {
        String url = ConfigReader.get("db.url");
        String username = ConfigReader.get("db.username");
        String password = ConfigReader.get("db.password");
        return DriverManager.getConnection(url, username, password);
    }

    public static Map<String, Object> getBookingDetailsById(int bookingId) {
        Map<String, Object> bookingData = new HashMap<>();
        String query = "SELECT * FROM bookings WHERE booking_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bookingData.put("firstname", rs.getString("firstname"));
                bookingData.put("lastname", rs.getString("lastname"));
                bookingData.put("totalprice", rs.getInt("totalprice"));
                bookingData.put("depositpaid", rs.getBoolean("depositpaid"));
                bookingData.put("additionalneeds", rs.getString("additionalneeds"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingData;
    }
}
