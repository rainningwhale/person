package yzu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

@WebServlet(name = "UpdateHandleServlet", urlPatterns = "/UpdateHandleServlet")
public class UpdateHandleServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from person where id=?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(id));
            rs = prst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String pwd = rs.getString("pwd");
                String gender = rs.getString("gender");
                out.print("<html>");
                out.print("<head><title>更新用户信息</title></head>");
                out.print("<body>");
                out.print("<form action=\"UpdatePersonServlet\" method=\"post\">");
                out.print("ID:" + id + "<input type=\"hidden\" name=\"id\" value=\"" + id + "\"/><br/>");
                out.print("NAME:<input type=\"text\" name=\"name\" value=\"" + name + "\"/><br/>");
                out.print("PWD:<input type=\"password\" name=\"pwd\" value=\"" + pwd + "\"/><br/>");
                /*
                if ("男".equals(gender)) {
                    out.print("GENDER:<input type=\"radio\" name=\"gender\" value=\"男\" checked />男");
                    out.print("<input type=\"radio\" name=\"gender\" value=\"女\"/>女<br/>");
                } else {
                    out.print("GENDER:<input type=\"radio\" name=\"gender\" value=\"男\"/>男");
                    out.print("<input type=\"radio\" name=\"gender\" value=\"女\" checked />女<br/>");
                }
                \*/
                out.print("GENDER:<input type=\"radio\" name=\"gender\" value=\"男\" "
                        +("男".equals(gender)?"checked":"")+" />男");
                out.print("<input type=\"radio\" name=\"gender\" value=\"女\" "
                        +(!"男".equals(gender)?"checked":"")+" />女<br/>");

                out.print("<input type=\"submit\" value=\"更新\"/>");
                out.print("</form>");
                out.print("</body>");
                out.print("</html>");
            } else {
                out.print("查无此人！");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(UpdateHandleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(UpdateHandleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(UpdateHandleServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (prst != null) {
                try {
                    prst.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(UpdateHandleServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(UpdateHandleServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
