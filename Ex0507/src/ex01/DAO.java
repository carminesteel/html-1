package ex01;

import java.sql.*;
import java.util.*;
import org.json.simple.*;

public class DAO {
	//데이터베이스 연결 method
	public Connection con() throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "haksa";
		String password = "pass";
		  
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		  
		return con;
	}
	
	//교수입력 method
	public ArrayList<VO> pinsert(String pcode, String pname, String dept, String hiredate, String title, int salary) throws Exception{
		String sql="{call add_professor(?,?,?,?,?,?,?)}";
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1, pcode);
		cs.setString(2, pname);
		cs.setString(3, dept);
		cs.setString(4, hiredate);
		cs.setString(5, title);
		cs.setInt(6, salary);
		cs.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
		cs.execute();
		
		ArrayList<VO> array=new ArrayList<VO>();
		ResultSet rs=(ResultSet)cs.getObject(7);
		while(rs.next()) {
			VO vo=new VO();
			vo.setPcode(rs.getString("pcode"));
			vo.setPname(rs.getString("pname"));
			vo.setDept(rs.getString("dept"));
			vo.setHiredate(rs.getString("hiredate"));
			vo.setTitle(rs.getString("title"));
			vo.setSalary(rs.getInt("salary"));
			array.add(vo);
		}
		con().close();
		return array;
	}
	
	
	//특정학과에 속하는 교수목록, 학생목록 출력 method
	public JSONObject list(String dept) throws Exception{
		JSONObject jObject=new JSONObject();
		String sql="{call out_dept(?,?,?,?,?)}";
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1,  dept);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
		cs.execute();
		
		
		//특정학과에 교수목록과 교수인원수
		JSONArray pArray=new JSONArray();
		ResultSet rs=(ResultSet)cs.getObject(2);
		while(rs.next()) {
			JSONObject obj=new JSONObject();
			obj.put("pcode", rs.getString("pcode"));
			obj.put("pname", rs.getString("pname"));
			obj.put("dept", rs.getString("dept"));
			obj.put("hiredate", rs.getString("hiredate"));
			obj.put("title", rs.getString("title"));
			obj.put("salary", rs.getInt("salary"));
			pArray.add(obj);
		}
		jObject.put("pArray", pArray);
		
		int pcnt=cs.getInt(3);
		jObject.put("pcnt", pcnt);
		
		//특정학과에 학생목록과 학생인원수
		rs=(ResultSet)cs.getObject(4);
		JSONArray sArray=new JSONArray();
		while(rs.next()) {
			JSONObject obj=new JSONObject();
			obj.put("scode", rs.getString("scode"));
			obj.put("sname", rs.getString("sname"));
			obj.put("dept", rs.getString("dept"));
			sArray.add(obj);
		}
		jObject.put("sArray", sArray);
		
		int scnt=cs.getInt(5);
		jObject.put("scnt", scnt);
		
		con().close();
		return jObject;
	}
	
	//특정학생의 수강신청 목록 method
	public JSONObject elist(String scode) throws Exception{
		JSONObject jObject=new JSONObject();
		String sql="{call out_enrollment(?,?,?,?)}";
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1, scode);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
		cs.execute();
		ResultSet rs=(ResultSet)cs.getObject(2);
		JSONArray eArray=new JSONArray();
		while(rs.next()) {
			JSONObject obj=new JSONObject();
			obj.put("lcode", rs.getString("lcode"));
			obj.put("lname", rs.getString("lname"));
			obj.put("grade", rs.getString("grade"));
			eArray.add(obj);
		}
		jObject.put("eArray", eArray);
		
		double avg=cs.getDouble(3);
		jObject.put("avg", avg);
		
		int cnt=cs.getInt(4);
		jObject.put("cnt", cnt);
		
		con().close();
		return jObject;
	}
}
