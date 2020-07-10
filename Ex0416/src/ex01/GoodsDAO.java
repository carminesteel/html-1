package ex01;

import java.sql.*;
import java.util.*;

public class GoodsDAO {
	
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "1111";
			      
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
			      
		return con;
	}
	
	//������ method
	public ArrayList<GoodsVO> list() throws Exception{
		ArrayList<GoodsVO> array=new ArrayList<GoodsVO>();
		String sql="select * from tab_goods";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			GoodsVO vo=new GoodsVO();
			vo.setGnum(rs.getString("gnum"));
			vo.setGname(rs.getString("gname"));
			vo.setPrice(rs.getInt("price"));
			array.add(vo);
		}
		return array;
	}
	
	//����Է� method
	public void insert(GoodsVO vo) throws Exception{
		String sql="insert into tab_goods(gnum, gname, price) values(seq_gnum.nextval, ?, ?)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getGname());
		ps.setInt(2, vo.getPrice());
		ps.execute();
		con().close();
	}
	
	//�����ͻ��� method
	public void delete(String gnum) throws Exception{
		String sql="delete from tab_goods where gnum=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, gnum);
		ps.execute();
		con().close();
	}
	
	//�����ͼ��� method
	public void update(GoodsVO vo) throws Exception{
		String sql="update tab_goods set gname=?, price=? where gnum=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getGname());
		ps.setInt(2, vo.getPrice());
		ps.setString(3, vo.getGnum());
		ps.execute();
		con().close();
	}
}
