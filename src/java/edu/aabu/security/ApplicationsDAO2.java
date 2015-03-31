/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aabu.security;

import edu.aabu.util.ServiceLocator;
import edu.aabu.util.ServiceLocatorException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akaleek
 */
public class ApplicationsDAO2 {
    public HashSet<Applications> getAllApplications() {
        HashSet<Applications> apps = new HashSet<Applications>();
        ServiceLocator sv = ServiceLocator.getInstance() ;
        Connection cn = null ;
        Statement st = null ;
        ResultSet rs = null ;
        String sql = "select * from applications" ;
        try {
            cn = sv.getDBConn(0);
            st = cn.createStatement() ;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ApplicationsPK apPK = new ApplicationsPK(rs.getShort(1),
                        rs.getShort(2),
                        rs.getShort(3),
                        rs.getShort(4),
                        rs.getShort(5)) ;
                Applications ap = new Applications(apPK, rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
						rs.getString(11));
                apps.add(ap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apps ;
    }
    public TreeSet<Applications> getUserApplications(String emp_code,String user_type) {
        TreeSet<Applications> apps = new TreeSet<Applications>();
        ServiceLocator sv = ServiceLocator.getInstance() ;
        Connection cn = null,cnn=null ;
        Statement st = null,st2=null ;
        ResultSet rs = null,rs2=null ;
        ApplicationsPK apPK = null ;
        Applications ap = null ;
        String sql = " SELECT a.* "+
		 " FROM  applications a , usership u"+
		 " WHERE (u.user_type = '"+ user_type + "' ) AND " +
		 " 		a.level_1 = u.level_1 AND "+
		 " 		a.level_2 = u.level_2 AND " +
		 " 		a.level_3 = u.level_3 AND "+
		 " 		a.level_4 = u.level_4 AND " +
		 " 		a.level_5 = u.level_5 AND "+
		 " 	    a.arabic_name Not IN  " +
		 " 		( SELECT a.arabic_name " +
		 " 			FROM applications a, emp_id_per e"+
		 " 			WHERE e.inst_no = " + emp_code +
		 " 				AND e.level_1 = a.level_1 AND e.level_2 = a.level_2 " +
		 " 				AND e.level_3 = a.level_3 AND e.level_4 = a.level_4" +
		 " 				AND e.level_5 = a.level_5 AND e.prog_flag = 2 )"+

		 " UNION "+

		 " SELECT a.* "+
		 " FROM  applications a , emp_id_per e"+
		 " WHERE e.inst_no = " + emp_code+
		 " AND e.level_1 = a.level_1 AND e.level_2 = a.level_2 " +
		 " AND e.level_3 = a.level_3 AND e.level_4 = a.level_4"+
		 " AND e.level_5 = a.level_5 AND e.prog_flag = 1"+
		 " ORDER BY 1,2,3,4,5 " ;
        try {
            cn = sv.getDBConn(0);
            st = cn.createStatement() ;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                apPK = new ApplicationsPK(rs.getShort(1),
                        rs.getShort(2),
                        rs.getShort(3),
                        rs.getShort(4),
                        rs.getShort(5)) ;
                ap = new Applications(apPK, rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
						rs.getString(11));
                apps.add(ap);
            }
			rs.close() ;
			sql = "SELECT distinct a.mat_no, a.mat_aname ,a.mat_year " +
				"FROM mat_tbl a , mapCourseInstructors b " +
				"WHERE a.mat_no = b.mat_no AND " +
				"	a.mat_year = b.mat_year AND " +
				"	b.instructor_id = "+ emp_code ;
            rs = st.executeQuery(sql);
			int i = 0 ;
			String c_id = "" , mat_year = "" ;
            boolean found = false ;
            while (rs.next())
            {
                found = true ;
                apPK = new ApplicationsPK((short)99,
                        (short)++i,
                        (short)0,
                        (short)0,
                        (short)0) ;
                ap = new Applications(apPK, rs.getString(2),
                        "",
                        "",
                        "",
                        "",
						"");
                apps.add(ap) ;
				c_id = rs.getString(1) ;
				mat_year =  rs.getString(3) ;
				sql = "SELECT a.level_2, a.arabic_name, a.prog_url " +
					"FROM applications a " +
					"WHERE " +
					"      a.level_1 = 99 AND " +
					"      a.level_2 > 0 AND " +
					"      a.level_3 = 0 " +
					"ORDER BY a.level_1 " ;
				cnn = sv.getDBConn(0);
				st2 = cnn.createStatement() ;
				rs2 = st2.executeQuery(sql);
				while (rs2.next()) {
					found = true ;
					apPK = new ApplicationsPK((short)99,
							(short)i,
							rs2.getShort(1),
							(short)0,
							(short)0) ;
					ap = new Applications(apPK, rs2.getString(2),
							"",
							rs2.getString( 3 ) + "?branch=" + c_id + "&mat_year=" + mat_year,
							"",
							"",
							"");
                    apps.add(ap) ;
                }
			}
            if(found)
            {
					apPK = new ApplicationsPK((short)99,
							(short)0,
							(short)0,
							(short)0,
							(short)0) ;
					ap = new Applications(apPK, "قائمة المساقات",
							"",
							"",
							"",
							"",
							"");
					apps.add(ap);

            }
		} catch (SQLException ex) {
            Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
             try {
                 if(st!=null) st.close();
                 if(st2!=null) st.close();
                 if(cn != null) cn.close();
                 if(cnn != null) cnn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return apps ;
    }


    public TreeSet<Applications> getUsrApplications() {
        TreeSet<Applications> apps = new TreeSet<Applications>();
        ServiceLocator sv = ServiceLocator.getInstance() ;
        Connection cn = null ;
        Statement st = null ;
        ResultSet rs = null ;
        String sql = "select * from applications" ;
        try {
            cn = sv.getDBConn(0);
            st = cn.createStatement() ;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ApplicationsPK apPK = new ApplicationsPK(rs.getShort(1),
                        rs.getShort(2),
                        rs.getShort(3),
                        rs.getShort(4),
                        rs.getShort(5)) ;
                Applications ap = new Applications(apPK, rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
						rs.getString(11));
                apps.add(ap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(ApplicationsDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apps ;
    }
}
