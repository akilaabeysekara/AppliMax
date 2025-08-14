package com.applimax.project.model;

import com.applimax.project.db.DBConnection;
import com.applimax.project.dto.OrderTableDTO;
import com.applimax.project.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {

    private final OrderDetailModel orderDetailModel = new OrderDetailModel();


    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT order_id FROM order_table ORDER BY order_id DESC limit 1");
        char tableChar = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }
    public boolean placeOrder(OrderTableDTO orderTableDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isSaved = CrudUtil.execute("INSERT INTO order_table VALUES (?,?,?,?,?,?)",orderTableDTO.getOrderId(),orderTableDTO.getCustomerId(),
                    orderTableDTO.getOrderDate(),orderTableDTO.getQuantity(),orderTableDTO.getUnitPrice(),orderTableDTO.getUnitPrice());
            if(isSaved){
                boolean isOderSaved =  orderDetailModel.saveOrderDetailList(orderTableDTO.getCartList());
                if(isOderSaved){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            e.printStackTrace();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

}
