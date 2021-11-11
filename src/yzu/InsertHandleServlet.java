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

@WebServlet(name = "InsertHandleServlet", urlPatterns = "/InsertHandleServlet")
public class InsertHandleServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement st1=null;
        PreparedStatement st2=null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            request.setCharacterEncoding("UTF-8");
            //String scity=request.getParameter("city").toString().trim();
            String sql1 = "select * from person where id=?";
            st1=conn.prepareStatement(sql1) ;
            String sid=request.getParameter("id").toString();
            int iid=Integer.parseInt( sid);
            st1.setInt(1,iid);
            rs = st1.executeQuery();
            if(rs.next())
            {
                out.print("已存在");

            }
            else
            {
                String sql2 = "insert into person values(?,?,?,?)";
                st2=conn.prepareStatement(sql2) ;
                String sname=request.getParameter("name").toString();
                String spwd=request.getParameter("pwd").toString();
                String sgender=request.getParameter("gender").toString();
                st2.setInt(1,iid);
                st2.setString(2,sname);
                st2.setString(3,spwd);
                st2.setString(4,sgender);
                int iret = st2.executeUpdate();
                if(iret>0)
                {
                    out.print("成功");
                }
                else
                {
                    out.print("不成功");
                }
            }

            out.print("<br/><a href=\"ShowAllPersonServlet\">查看数据库中现有数据</a>");

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
            if (st1 != null) {
                try {
                    st1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ShowAllPersonServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (st2 != null) {
                try {
                    st2.close();
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
