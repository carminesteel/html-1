package ex01;

import java.sql.*;
import java.util.*;

public class AddrDAO {
	
	//�����ͺ��̽� ����
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "1111";
		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	//�����͸����� method
	public ArrayList<AddrVO> list() throws Exception{
		ArrayList<AddrVO> array=new ArrayList<AddrVO>();
		String sql="select * from tab_addr order by anum desc";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			AddrVO vo=new AddrVO();
			vo.setAnum(rs.getInt("anum"));
			vo.setAname(rs.getString("aname"));
			vo.setAtel(rs.getString("atel"));
			vo.setAddress(rs.getString("address"));
			array.add(vo);
		}
		return array;
	}
	
	//�������Է� method
	public void insert(AddrVO vo) throws Exception{
		String sql="insert into tab_addr(anum, aname, atel, address) values(seq_anum.nextval,?,?,?)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getAname());
		ps.setString(2, vo.getAtel());
		ps.setString(3, vo.getAddress());
		ps.execute();
		con().close();
	}
	
	//Ư�������� ��� method
	public AddrVO read(int anum) throws Exception{
		String sql="select * from tab_addr where anum=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, anum);
		ResultSet rs=ps.executeQuery();
		AddrVO vo=new AddrVO();
		
		if(rs.next()) {
			vo.setAnum(rs.getInt("anum"));
			vo.setAname(rs.getString("aname"));
			vo.setAtel(rs.getString("atel"));
			vo.setAddress(rs.getString("address"));
		}
		
		return vo;
	}
	
	//������ ���� method
	public void delete(int anum) throws Exception{
		String sql="delete from tab_addr where anum=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, anum);
		ps.execute();
		con().close();
	}
	
	//������ ���� method
	public void update(AddrVO vo) throws Exception{
		String sql="update tab_addr set aname=?, atel=?, address=? where anum=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getAname());
		ps.setString(2, vo.getAtel());
		ps.setString(3, vo.getAddress());
		ps.setInt(4, vo.getAnum());
		ps.execute();
		con().close();
	}
}
