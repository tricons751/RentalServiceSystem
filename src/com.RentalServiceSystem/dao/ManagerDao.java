package com.RSMS.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RSMS.model.Manager;

public class ManagerDao extends BaseDao {

	public Manager login(Manager manager){
		String sql = "select * from manager where mname=? and password=?";
		Manager ManagerRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, manager.getMname());
			prst.setString(2, manager.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				ManagerRst = new Manager();
				ManagerRst.setMid(executeQuery.getInt("mid"));
				ManagerRst.setMname(executeQuery.getString("mname"));
				ManagerRst.setPassword(executeQuery.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ManagerRst;
	}
	public String editPassword(Manager manager,String newPassword){
		String sql = "select * from manager where mid=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, manager.getMid());
			prst.setString(2, manager.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "¾ÉÃÜÂëÊäÈë´íÎó£¡";
				return retString;
			}
			id = executeQuery.getInt("mid");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String retString = "ĞŞ¸ÄÃÜÂëÊ§°Ü";
		String sqlString = "update manager set password = ? where mid = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if(rst > 0){
				retString = "ĞŞ¸ÄÃÜÂë³É¹¦£¡";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retString;
	}
}
