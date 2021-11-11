package yzu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ShowAllPersonServlet", urlPatterns = "/ShowAllPersonServlet")
public class ShowAllPersonServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement st=null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "daaiALICE123";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            request.setCharacterEncoding("UTF-8");
            //String scity=request.getParameter("city").toString().trim();
            String sql = "select * from person";

            st=conn.prepareStatement(sql) ;
            rs = st.executeQuery();
            out.print("<script>"
                    +"function mydelete(id){\n" +
                            "var r=confirm(\"是否删除？\")\n" +
                            "  if (r==true)\n" +
                            "    {\n" +
                            "    window.location.href='DeleteHandleServlet?id='+id;\n" +
                            "    }\n" +
                            "}"
                    +"</script>"
                            );
            out.print("<table border=1>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Name</th>");
            out.print("<th>PWD</th>");
            out.print("<th>Gender</th>");
            out.print("<th>操作</th>");
            out.print("</tr>");
            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getInt("ID") + "</td>");
                out.print("<td>" + rs.getString("NAME") + "</td>");
                out.print("<td>" + rs.getString("PWD") + "</td>");
                out.print("<td>" + rs.getString("GENDER") + "</td>");
                //out.print("<td><a href='DeleteHandleServlet?id=" + rs.getString("ID") + "'>删除</a>");
                out.print("<td><a href='#' onclick=\"mydelete("+rs.getString("ID") +")\">删除</a>");

                out.print("<a href='UpdateHandleServlet?id=" + rs.getString("ID") + "'>修改</a></td>");

                out.print("</tr>");
            }
            out.print("</table>");

        } catch (SQLException ex) {
            Logger.getLogger(ShowAllPersonServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowAllPersonServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShowAllPersonServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShowAllPersonServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShowAllPersonServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
