<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
$(document).ready(function(){
    $("#loginForm").validate({
    	submitHandler: function(form) {
    		$('#content').load('LoginController',$("#loginForm").serialize());
    }
    });
});
</script>

<form id="loginForm" action="" method="POST">
<table>
<tr> 
<td> Username:  </td> 
<td> <input type="text" name="user" value="${login.user}" id="user" class="required" placeholder="Username" minlength="5"/> </td> 

<c:if test="${login.error[0] == 1}">
   <td class="error"> Nonexistent username in our DB! </td> 
</c:if>
</tr>
<tr>
<td> Password:  </td>
<td> 
<input type="password" name="password" id="password_input" class="input" placeholder="Password" value="${login.password}" ></input>
</td>
</tr>
<tr> 
<td> <input name="submit" type="submit" value="Enviar"> </td>
</tr>
</table>
</form>