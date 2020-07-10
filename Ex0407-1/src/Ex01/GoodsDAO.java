package Ex01;

import java.sql.*;
import java.util.*;

public class GoodsDAO {
	
	//���������� method
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "1111";
	
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	//�������Է� method
	public void insert(GoodsVO vo) throws Exception{
		String sql="insert into tab_goods(gnum, gname, gprice) values(?,?,?)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getGnum());
		ps.setString(2, vo.getGname());
		ps.setInt(3, vo.getGprice());
		ps.execute();
		con().close();
	}
	
	//������ ������ method
	public ArrayList<GoodsVO> list() throws Exception{
		ArrayList<GoodsVO> array = new ArrayList<GoodsVO>();
		String sql="select * from tab_goods order by gnum";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			GoodsVO vo = new GoodsVO();
			vo.setGnum(rs.getString("gnum"));
			vo.setGname(rs.getString("gname"));
			vo.setGprice(rs.getInt("gprice"));
			array.add(vo);
		}
		
		return array;
	}
}
