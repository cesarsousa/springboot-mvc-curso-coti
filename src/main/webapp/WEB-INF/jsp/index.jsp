<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

<h2>${message}</h2>

<h2>Gravar produto</h2>
<form method="post" action="gravamvc">

<input type="text" name="nomeProduto" id="nomeProduto" required="required" placeholder="Nome Produto"><br/>
<input type="text" name="tipo" id="tipo" required="required" placeholder="Tipo Produto"><br/>
<input type="text" name="preco" id="preco" required="required" placeholder="Preco Produto"><br/>
<button type="submit"> Enviar Dados </button>

</form>

<h2>Listar produto</h2>
<table>
<tr>
	<th>id</th>
	<th>tipo</th>
	<th>nome</th>
	<th>preco</th>
	<th>#</th>
	<th>#</th>
</tr>
<c:forEach items="${lista}" var="linha" >
<tr>
	<th>${linha.idProduto}</th>
	<th>${linha.tipo}</th>
	<th>${linha.nomeProduto}</th>
	<th>${linha.preco}</th>
</tr>
</c:forEach>
</table>




</body>
</html>