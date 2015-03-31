/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aabu.security;


import java.sql.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fatima
 */
public class LoginFilter implements Filter {

    private static final boolean debug = true;
    private ArrayList<String> urlList;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public LoginFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession(false);
       httpRes.setHeader("Cache-Control","no-cache, no-store");
       httpRes.setHeader("Cache-Control","max-age=0");
       httpRes.setHeader("Pragma","public");
       httpRes.setDateHeader ("Expires", -1);
       httpReq.setCharacterEncoding("utf-8");
       httpRes.setCharacterEncoding("utf-8");
        Object UsrId = null;
        Object logIn = null;
        boolean allowedRequest = false;
        boolean allDo = false;
        boolean allDo2 = false;
        boolean allDo3 = false;
        String uri = httpReq.getRequestURI();
        int index = uri.lastIndexOf("/");
        String path = uri.substring(index);
        String url = httpReq.getServletPath();
        System.out.println("FbURL=" + url);
        if (urlList.contains(url)) {
            System.out.println("FB index pass here" + url);
            allowedRequest = true;
           //  httpRes.sendRedirect(httpReq.getContextPath() + "/handlePage.jsp");
        }
        if (!allowedRequest) {
              if (session == null) {
             
                      httpRes.sendRedirect(httpReq.getContextPath() + "/login.jsp");
                         allDo = true;
                 } 
            else {
                if (session != null) {
               //     System.out.println("session not null cont");
                    UsrId = session.getAttribute("UsrId");
                    logIn = session.getAttribute("logIn");
                    if (logIn == null) {
                        // request.getRequestDispatcher("/index.jsp").forward(httpReq, httpRes);
                        allDo2 = true;
                        httpRes.sendRedirect(httpReq.getContextPath() + "login.jsp");
                        System.out.print("logIn ==null" );
                    }
           
               /* if (session != null) {
               //     System.out.println("session not null cont");
                    UsrId = session.getAttribute("UsrId");
                    logIn = session.getAttribute("logIn");
                    if ( logIn  == null) {
                        // request.getRequestDispatcher("/index.jsp").forward(httpReq, httpRes);
                        allDo2 = true;
                        httpRes.sendRedirect(httpReq.getContextPath() + "/index2.jsp");
                    } /*else {
                   //     System.out.println("new filter exe");
                     //   System.out.println("path = " + path + " logIn=" + logIn);
                        String prog_url = httpReq.getServletPath();
                        String subprog = "";
                        String prog_detail[] = null;
                        boolean found = false;
                        trb:
                        try {
                            if (prog_url.equals("/news.jsp") || prog_url.equals("/welcome.jsp") || prog_url.equals("/test-news.jsp")
                                    || prog_url.equals("/msg_text.jsp") || prog_url.equals("/msg2_text.jsp")
                                    || prog_url.equals("/del_msg.jsp") || prog_url.equals("/del_msg2.jsp")) {
                                found = true;
                                break trb;
                            }
                            Applications app = null;
                            TreeSet<Applications> hap = (TreeSet<Applications>) session.getAttribute("userApplications");
                              System.out.println("-------------------------------------------------=="+hap);
                            Iterator<Applications> it = hap.iterator();
                            while (it.hasNext()) {
                                app = it.next();
                                int findex = app.getProgUrl().indexOf("?");
                                if (findex != -1) {
                                    subprog = app.getProgUrl().substring(0, findex);
                                } else {
                                    subprog = app.getProgUrl();
                                }
                                if (subprog.equals(prog_url)) {
                                    found = true;
                                    break;
                                } else if (app.getProgDetail().trim().length() > 0) {
                                    prog_detail = app.getProgDetail().split(",");
                                    for (int i = 0; i < prog_detail.length; i++) {
                                        if (prog_detail[i].trim().equals(prog_url)) {
                                            found = true;
                                            prog_detail = null;
                                            break trb;
                                        }
                                    }
                                }
                                prog_detail = null;
                            }
                            if (!found) {
                                Connection scon = null;
                                Statement sstatement = null;
                                try {
                                    String semp_code = (String) session.getAttribute("emp_code");
                                    String suser_type = (String) session.getAttribute("dbusertype");
                                    String sql = " insert into security_tbl(emp_id,ip_add,trans_time,prog_url) "
                                            + " values(" + semp_code + ",'" + request.getRemoteAddr() + "',date('now'),'" + httpReq.getServletPath() + "')";
                                    ServiceLocator sv = ServiceLocator.getInstance();
                                    scon = sv.getDBConn(0);
                                    sstatement = scon.createStatement();
                                    sstatement.executeUpdate(sql);
                                    sstatement.close();
                                    scon.close();
                                    //  RequestDispatcher rd = request.getRequestDispatcher("/nopermission.jsp");
                                    // rd.forward(request, response);
                                    allDo3 = true;
                                    httpRes.sendRedirect(httpReq.getContextPath() + "/nopermission.jsp");
                                } catch (Exception e) {
                                } finally {
                                    if (sstatement != null) {
                                        sstatement.close();
                                    }
                                    if (scon != null) {
                                        scon.close();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }//if login!ping=null*/

                    }//if session!=null
            }//if not avoid urls
        }
       // System.out.println(allDo + "---------------------------------------" + allDo2);
        if (!allDo ) {
            chain.doFilter(request, response);
          //  System.out.println("index pghgfhgfhgf gfhgf re");
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Destroy method for this filter 
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter 
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        String urls = filterConfig.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }

    } // init()
}
