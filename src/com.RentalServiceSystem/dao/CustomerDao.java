package com.RSMS.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RSMS.model.Customer;
import com.RSMS.util.StringUtil;

public class CustomerDao extends BaseDao {
	public List<Customer> getCustonerList(Customer customer) {
		// TODO Auto-generated method stub
		List<Customer> retList = new ArrayList<Customer>();
		StringBuffer sqlString = new StringBuffer("select * from customer");
		if(!StringUtil.isEmpty(customer.getCname())){
			sqlString.append(" where cname like '%"+customer.getCname()+"%'");
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString());
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Customer t = new Customer();
				t.setCid(executeQuery.getInt("cid"));
				t.setCname(executeQuery.getString("cname"));
				t.setSex(executeQuery.getString("sex"));
				t.setPhone(executeQuery.getString("phone"));
				t.setAddress(executeQuery.getString("address"));
				t.setPassword(executeQuery.getString("password"));
				retList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean update(Customer customer){
		String sql = "update customer set cname=?,sex=?,phone=?,address=?,password=? where cid=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCname());
			preparedStatement.setString(2, customer.getSex());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setString(5, customer.getPassword());
			preparedStatement.setInt(6, customer.getCid());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Customer login(Customer customer){
		String sql = "select * from customer where cname=? and password=?";
		Customer customerRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, customer.getCname());
			prst.setString(2, customer.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				customerRst = new Customer();				
				customerRst.setCid(executeQuery.getInt("cid"));
				customerRst.setCname(executeQuery.getString("cname"));
				customerRst.setSex(executeQuery.getString("sex"));
				customerRst.setPhone(executeQuery.getString("phone"));
				customerRst.setAddress(executeQuery.getString("address"));
				customerRst.setType(executeQuery.getString("type"));
				customerRst.setPassword(executeQuery.getString("password"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerRst;
	}
	public String editPassword(Customer customer,String newPassword){
		String sql = "select * from customer where cid=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, customer.getCid());
			prst.setString(2, customer.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "¾ÉÃÜÂëÊäÈë´íÎó£¡";
				return retString;
			}
			id = executeQuery.getInt("cid");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String retString = "ÐÞ¸ÄÃÜÂëÊ§°Ü";
		String sqlString = "update customer set password = ? where cid = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if(rst > 0){
				retString = "ÐÞ¸ÄÃÜÂë³É¹¦£¡";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retString;
	}
}
