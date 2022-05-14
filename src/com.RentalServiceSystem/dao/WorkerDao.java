package com.RentalServiceSystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RentalServiceSystem.model.Worker;
import com.RentalServiceSystem.util.StringUtil;

public class WorkerDao extends BaseDao {
	public boolean addStudent(Worker worker){
		String sql = "insert into worker(wname,sex,phone,address,type,password) values(?,?,?,?,?,?)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, worker.getWname());
			preparedStatement.setString(2, worker.getSex());
			preparedStatement.setString(3, worker.getPhone());
			preparedStatement.setString(4, worker.getAddress());
			preparedStatement.setString(5, worker.getType());
			preparedStatement.setString(6, worker.getPassword());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<Worker> getWorkerList(Worker worker){
		List<Worker> retList = new ArrayList<Worker>();
		StringBuffer sqlString = new StringBuffer("select * from worker");
		if(worker!=null&&!StringUtil.isEmpty(worker.getWname())){
			sqlString.append(" and wname like '%"+worker.getWname()+"%'");
		}
		if((worker!=null&&worker.getType()!= null)){
			sqlString.append(" and type like '%"+ worker.getType()+"%'");
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Worker s = new Worker();
				s.setWid(executeQuery.getInt("wid"));
				s.setWname(executeQuery.getString("wname"));
				s.setSex(executeQuery.getString("sex"));
				s.setPassword(executeQuery.getString("password"));
				s.setPhone(executeQuery.getString("phone"));
				s.setAddress(executeQuery.getString("address"));
				s.setType(executeQuery.getString("type"));
				retList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean delete(int id){
		String sql = "delete from worker where wid=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean update(Worker worker){
		String sql = "update worker set wname=?,sex=?,phone=?,address=?,type=?,password=? where wid=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, worker.getWname());
			preparedStatement.setString(2, worker.getSex());
			preparedStatement.setString(3, worker.getPhone());
			preparedStatement.setString(4, worker.getAddress());
			preparedStatement.setString(5, worker.getType());
			preparedStatement.setString(6, worker.getPassword());
			preparedStatement.setInt(7, worker.getWid());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String editPassword(Worker worker,String newPassword){
		String sql = "select * from worker where Wid=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, worker.getWid());
			prst.setString(2, worker.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "¾ÉÃÜÂëÊäÈë´íÎó£¡";
				return retString;
			}
			id = executeQuery.getInt("wid");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String retString = "ÐÞ¸ÄÃÜÂëÊ§°Ü";
		String sqlString = "update worker set password = ? where wid = ?";
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
	public Worker login(Worker worker){
		String sql = "select * from worker where wname=? and password=?";
		Worker workerRst = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, worker.getWname());
			prst.setString(2, worker.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				workerRst = new Worker();
				workerRst.setWid(executeQuery.getInt("wid"));
				workerRst.setWname(executeQuery.getString("wname"));
				workerRst.setSex(executeQuery.getString("sex"));
				workerRst.setPhone(executeQuery.getString("phone"));
				workerRst.setAddress(executeQuery.getString("address"));
				workerRst.setType(executeQuery.getString("type"));
				workerRst.setPassword(executeQuery.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workerRst;
	}
}
