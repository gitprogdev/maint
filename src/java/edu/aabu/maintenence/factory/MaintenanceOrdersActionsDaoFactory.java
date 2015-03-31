/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package edu.aabu.maintenence.factory;

import java.sql.Connection;
import edu.aabu.maintenence.dao.*;
import edu.aabu.maintenence.jdbc.*;

public class MaintenanceOrdersActionsDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return MaintenanceOrdersActionsDao
	 */
	public static MaintenanceOrdersActionsDao create()
	{
		return new MaintenanceOrdersActionsDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return MaintenanceOrdersActionsDao
	 */
	public static MaintenanceOrdersActionsDao create(Connection conn)
	{
		return new MaintenanceOrdersActionsDaoImpl( conn );
	}

}
