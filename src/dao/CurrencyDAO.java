package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CurrencyDAO {

    public double getRate(String code) {

        String query = "SELECT rate_to_usd FROM currency WHERE code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("rate_to_usd");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}

