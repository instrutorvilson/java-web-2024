<%@ page import="classes.Carro" %>
<%
   String placa = request.getParameter("placa");
   String modelo = request.getParameter("modelo");
 
  /* Carro c1 = new Carro();
   c1.placa = placa;
   c1.modelo = modelo;
   
   out.write(c1.placa + " " + c1.modelo);*/
   Carro c1 = new Carro(placa, modelo);
   out.write(c1.toString());
%>