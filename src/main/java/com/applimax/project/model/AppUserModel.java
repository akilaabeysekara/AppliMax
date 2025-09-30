package com.applimax.project.model;

import com.applimax.project.dto.AppUserDTO;
import com.applimax.project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppUserModel {
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM app_user WHERE user_name = ? AND password = ?";
        ResultSet rs = CrudUtil.execute(sql, username, password);
        return rs.next();
    }

    public ArrayList<AppUserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM app_user");
        ArrayList<AppUserDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new AppUserDTO(
                    rs.getString(1), // user_id
                    rs.getString(2), // employee_id
                    rs.getString(3), // user_name
                    rs.getString(4), // password
                    rs.getString(5), // email
                    rs.getString(6)  // user_role
            ));
        }
        return list;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT user_id FROM app_user ORDER BY user_id DESC LIMIT 1");
        char p = 'U';
        if (rs.next()) {
            int next = Integer.parseInt(rs.getString(1).substring(1)) + 1;
            return String.format(p + "%03d", next);
        }
        return p + "001";
    }

    public boolean saveUser(AppUserDTO u) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO app_user (user_id, employee_id, user_name, password, email, user_role) VALUES (?,?,?,?,?,?)",
                u.getUserId(), u.getEmployeeId(), u.getUserName(), u.getPassword(), u.getEmail(), u.getRole()
        );
    }

    public boolean updateUser(AppUserDTO u) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE app_user SET employee_id=?, user_name=?, password=?, email=?, user_role=? WHERE user_id=?",
                u.getEmployeeId(), u.getUserName(), u.getPassword(), u.getEmail(), u.getRole(), u.getUserId()
        );
    }

    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM app_user WHERE user_id=?", userId);
    }

    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT user_id FROM app_user");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) list.add(rst.getString(1));
        return list;
    }

    public String findNameById(String userId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT user_name FROM app_user WHERE user_id=?", userId);
        return rst.next() ? rst.getString(1) : "";
    }

    public AppUserDTO findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM app_user WHERE user_id=?", selectedId);
        if (rs.next()) {
            return new AppUserDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return null;
    }
}
