/* Application : C.V
 * File   : Employee Experiences Bean
 * Autohr : Eng.Zaid
 * Date   : 12/7/2009.
 */
package edu.aabu.util;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.*;

/*
 *  The ServiceLocator pattern is abstracts away the JNDI
 *  logic necessary for retrieving a JDBC Connection or EJBHome
 *  interface
 */
public class ServiceLocator {

    private static ServiceLocator serviceLocatorRef = null;
    private static HashMap ejbHomeCache = null;
    private static HashMap<String,DataSource> dataSourceCache = null;

    /*Enumerating the different services available from the Service Locator*/
    public static final int INTDB = 0;
    public static final int ADMDB = 1;
    public static final int FINDB = 2;
    public static final int STORES = 3;
    public static final int INTDB2 = 4;

    /*The JNDI Names used to lookup a service*/
    private static final String intdb = "java:comp/env/jdbc/intdb";
    private static final String admdb = "java:comp/env/jdbc/admdb";
    private static final String findb = "java:comp/env/jdbc/findb";
    private static final String stores = "java:comp/env/jdbc/stores";
    private static final String intdb2 = "java:comp/env/jdbc/intdb";

    static {
        serviceLocatorRef = new ServiceLocator();
    }

    /*Private Constructor for the ServiceLocator*/
    private ServiceLocator() {
        ejbHomeCache = new HashMap();
        dataSourceCache = new HashMap<String,DataSource>();
    }

    /*
     * The ServiceLocator is implemented as a Singleton.  The getInstance()
     * method will return the static reference to the ServiceLocator stored
     * inside of the ServiceLocator Class.
     */
    public static ServiceLocator getInstance() {
        return serviceLocatorRef;
    }

    /*
     * The getServiceName will retrieve the JNDI name for a requested
     * service.  The service is indicated by the ServiceId passed into
     * the method.
     */

    static private String getServiceName(int pServiceId)
            throws ServiceLocatorException {
        String serviceName = null;
        switch (pServiceId) {
            case ADMDB:
                serviceName = admdb;
                break;
            case FINDB:
                serviceName = findb;
                break;
            case INTDB:
                serviceName = intdb;
                break;
            case STORES:
                serviceName = stores;
                break;
            case INTDB2:
                serviceName = intdb2;
                break;
            default:
                throw new ServiceLocatorException(
                        "Unable to locate the service requested in "
                        + "ServiceLocator.getServiceName() method.  ");
        }
        return serviceName;
    }

    public Connection getDBConn(int pServiceId)
            throws ServiceLocatorException {
        /*Getting the JNDI Service Name*/
        String serviceName = getServiceName(pServiceId);
        Connection conn = null;
        try {
            /*Checking to see if the requested DataSource is in the Cache*/
            if (dataSourceCache.containsKey(serviceName)) {
                DataSource ds = (DataSource) dataSourceCache.get(serviceName);
                conn = ((DataSource) ds).getConnection();
                return conn;
            } else {
                /*
                 * The DataSource was not in the cache.  Retrieve it from JNDI
                 * and put it in the cache.
                 */
                Context ctx = new InitialContext();
                DataSource newDataSource = (DataSource) ctx.lookup(serviceName);
                dataSourceCache.put(serviceName, newDataSource);
                conn = newDataSource.getConnection();
                return conn;
            }
        } catch (SQLException e) {
            throw new ServiceLocatorException("A SQL error has occurred in "
                    + "ServiceLocator.getDBConn()" + e.getMessage(), e);
        } catch (NamingException e) {
            throw new ServiceLocatorException("A JNDI Naming exception has occurred "
                    + " in ServiceLocator.getDBConn()", e);
        } catch (Exception e) {
            throw new ServiceLocatorException("An exception has occurred "
                    + " in ServiceLocator.getDBConn()", e);
        }
    }
}
