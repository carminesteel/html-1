package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EDAO1 {
	
	//�����ͺ��̽� ���� method
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "haksa";
		String password = "pass";
		  
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		  
		return con;
	}
	
	//���������� �л� ��� ��� method
	public ArrayList<EVO1> list(String pcode) throws Exception{
		ArrayList<EVO1> array=new ArrayList<EVO1>();
		String sql="select sname, scode, grade from view_enrollment where lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, pcode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			EVO1 vo=new EVO1();
			vo.setSname(rs.getString("sname"));
			vo.setScode(rs.getString("scode"));
			vo.setGrade(rs.getInt("grade"));
			array.add(vo);
		}
		con().close();
		return array;
	}
	
	//�����Է��� ���� method
	public void update(int grade, String scode, String lcode) throws Exception{
		String sql="update view_enrollment set grade=? where scode=? and lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, grade);
		ps.setString(2, scode);
		ps.setString(3, lcode);
		ps.execute();
		con().close();
	}
}
