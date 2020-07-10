<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ex01.*, java.util.*, org.json.simple.*, java.text.*" %>

<%
	DecimalFormat df=new DecimalFormat("##0.00");
	EnrollmentDAO cdao=new EnrollmentDAO();
	StudentDAO sdao=new StudentDAO();
	
	ArrayList<EnrollmentVO> carray=cdao.clist();
	
	JSONObject jObject=new JSONObject();
	JSONArray jArray=new JSONArray();
	for(EnrollmentVO vo:carray){
		JSONObject obj=new JSONObject();
		obj.put("lcode", vo.getLcode());
		obj.put("lname", vo.getLname());
		obj.put("pname", vo.getPname());
		
		String avg=df.format(vo.getAvg());
		obj.put("avg", avg);
		jArray.add(obj);
	}
	
	jObject.put("clist", jArray);

	
	
	ArrayList<StudentVO> sarray=sdao.slist();
	JSONArray jArray1=new JSONArray();
	for(StudentVO vo:sarray){
		JSONObject obj=new JSONObject();
		obj.put("scode", vo.getScode());
		obj.put("sname", vo.getSname());
		obj.put("dept", vo.getDept());
		
		String avg1=df.format(vo.getAvg1());
		obj.put("avg1", avg1);
		jArray1.add(obj);
	}
	
	jObject.put("slist", jArray1);
	out.print(jObject);
%>