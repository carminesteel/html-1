<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ex01.*, org.json.simple.*" %>

<%
   SDAO cdao=new SDAO();
   JSONObject jObject=cdao.clist();
   out.print(jObject);
%>