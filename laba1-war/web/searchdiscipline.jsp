<%@page  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Результати пошуку</title>
    </head>
    <body> 
    <center>
    <h1>Результати пошуку: </h1>  
    <table border="1">
<tr><th>Назва дисципліни</th><th>Ім`я та прізвище викладача</th></tr>   
           <c:forEach var="disciplined" items="${disciplines}">
                 <tr><td>${disciplined.discipline}</td>
                   <td>${lecturers}</td>
                    </c:forEach>      
</form> </td> </tr> </table>        
<p><a href=menupage.jsp> Повернутися на головну</a></p></center>
</body>
</html>
