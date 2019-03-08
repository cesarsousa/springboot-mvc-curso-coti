<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
<h2>Busca pelo Codigo</h2>

<form method="post" action="buscaCodigoMvc" id="busca" name="busca">
Codigo<br/>
 <input type="text" value="" name="idProduto" id="id" size="10" />
 
  <button type="submit">Enviar os Dados</button>
 </form>
  
 <form method="post" id="altera" name="altera" action="alterarMvc">
 <c:if test="${flag=='1'}">
   <table>
   <tr>
   <th><input type="number" name="idProduto" id="idProduto" value="${produto.idProduto}" readonly="readonly"/></th>
   <th><input type="text" name="tipo" id="tipo" value="${produto.tipo}"/></th>
   <th><input type="text" name="nomeProduto" id="nomeProduto" value="${produto.nomeProduto}"/></th>
  <th><input type="number" name="preco" id="preco" value="${produto.preco}"/></th>
        
        <th><button type="submit">Alterar os Dados</button></th>
  </tr>      
  </table>
 </c:if>
</form> 
  
 ${msg} 



