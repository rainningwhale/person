<%--
  Created by IntelliJ IDEA.
  User: lizhiqiang
  Date: 2021-11-5
  Time: 上午8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <a href="ShowAllPersonServlet">查看数据库中现有数据</a>
  <hr/>
  在PERSON表中添加一条记录，请输入该person的全部信息

  <form method="POST" action="InsertHandleServlet">
    ID:<input type="text" name="id" value="" /><br/>
    NAME:<input type="text" name="name" value="" /><br/>
    PWD:<input type="password" name="pwd" value="" /><br/>
    GENDER:<input type="radio" name="gender" value="男" checked/>男
    <input type="radio" name="gender" value="女" />女<br/>
    <input type="submit" value="插入记录" />
  </form>

  </body>
</html>
