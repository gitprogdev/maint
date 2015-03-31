/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package edu.aabu.maintenence.jdbc;

import edu.aabu.maintenence.dao.*;
import edu.aabu.maintenence.factory.*;
import java.util.Date;
import edu.aabu.maintenence.dto.*;
import edu.aabu.maintenence.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

public class MaintenanceOrdersActionsDaoImpl extends AbstractDAO implements MaintenanceOrdersActionsDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT ACTION_DESC, ACTION_TIME, ORDER_NO, ORDER_YEAR FROM " + getTableName() + "";
        protected final String ACTION_INSERT = "INSERT INTO maintenance_orders_actions ( ORDER_NO, ORDER_YEAR , ACTION_TIME , ACTION_DESC ) VALUES ( ?, ?, date('now'), ? )";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ACTION_DESC, ACTION_TIME, ORDER_NO, ORDER_YEAR ) VALUES ( ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ACTION_DESC = ?, ACTION_TIME = ?, ORDER_NO = ?, ORDER_YEAR = ? WHERE ORDER_NO = ? AND ORDER_YEAR = ? AND ACTION_TIME = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ORDER_NO = ? AND ORDER_YEAR = ? AND ACTION_TIME = ?";

	/** 
	 * Index of column ACTION_DESC
	 */
	protected static final int COLUMN_ACTION_DESC = 1;

	/** 
	 * Index of column ACTION_TIME
	 */
	protected static final int COLUMN_ACTION_TIME = 2;

	/** 
	 * Index of column ORDER_NO
	 */
	protected static final int COLUMN_ORDER_NO = 3;

	/** 
	 * Index of column ORDER_YEAR
	 */
	protected static final int COLUMN_ORDER_YEAR = 4;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/** 
	 * Index of primary-key column ORDER_NO
	 */
	protected static final int PK_COLUMN_ORDER_NO = 1;

	/** 
	 * Index of primary-key column ORDER_YEAR
	 */
	protected static final int PK_COLUMN_ORDER_YEAR = 2;

	/** 
	 * Index of primary-key column ACTION_TIME
	 */
	protected static final int PK_COLUMN_ACTION_TIME = 3;

	/** 
	 * Inserts a new row in the MAINTENANCE_ORDERS_ACTIONS table.
	 */
	public MaintenanceOrdersActionsPk insert(MaintenanceOrdersActions dto) throws MaintenanceOrdersActionsDaoException
	{
		long t1 = System.currentTimeMillis();
                String ordersActionsDesc[] = {"ادخال الطلب","تعديل الطلب","الغاء الطلب","اعتماد الطلب","استلام الطلب دائرة الصيانة","اغلاق الطلب دائرة الصيانة"};
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			stmt.setString( index++, dto.getActionDesc() );
			stmt.setTimestamp(index++, dto.getActionTime()==null ? null : new java.sql.Timestamp( dto.getActionTime().getTime() ) );
			stmt.setInt( index++, dto.getOrderNo() );
			stmt.setShort( index++, dto.getOrderYear() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MaintenanceOrdersActionsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}
	public void insertAction(MaintenanceOrdersActions dto,String notes,int actionType) throws MaintenanceOrdersActionsDaoException
	{
		long t1 = System.currentTimeMillis();
                String ordersActionsDesc[] = {"ادخال الطلب","تعديل الطلب","الغاء الطلب","اعتماد الطلب","استلام الطلب دائرة الصيانة","اغلاق الطلب دائرة الصيانة"};
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		actionType--;
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();

			stmt = conn.prepareStatement( ACTION_INSERT );
			int index = 1;
			stmt.setInt( index++, dto.getOrderNo() );
			stmt.setShort( index++, dto.getOrderYear() );
			stmt.setString( index++, ordersActionsDesc[actionType] + "--"+notes );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			//return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MaintenanceOrdersActionsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}

	}

	/** 
	 * Updates a single row in the MAINTENANCE_ORDERS_ACTIONS table.
	 */
	public void update(MaintenanceOrdersActionsPk pk, MaintenanceOrdersActions dto) throws MaintenanceOrdersActionsDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + dto );
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setString( index++, dto.getActionDesc() );
			stmt.setTimestamp(index++, dto.getActionTime()==null ? null : new java.sql.Timestamp( dto.getActionTime().getTime() ) );
			stmt.setInt( index++, dto.getOrderNo() );
			stmt.setShort( index++, dto.getOrderYear() );
			stmt.setInt( 5, pk.getOrderNo() );
			stmt.setShort( 6, pk.getOrderYear() );
			stmt.setTimestamp(7, pk.getActionTime()==null ? null : new java.sql.Timestamp( pk.getActionTime().getTime() ) );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MaintenanceOrdersActionsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the MAINTENANCE_ORDERS_ACTIONS table.
	 */
	public void delete(MaintenanceOrdersActionsPk pk) throws MaintenanceOrdersActionsDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_DELETE + " with PK: " + pk );
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setInt( 1, pk.getOrderNo() );
			stmt.setShort( 2, pk.getOrderYear() );
			stmt.setTimestamp(3, pk.getActionTime()==null ? null : new java.sql.Timestamp( pk.getActionTime().getTime() ) );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MaintenanceOrdersActionsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the MAINTENANCE_ORDERS_ACTIONS table that matches the specified primary-key value.
	 */
	public MaintenanceOrdersActions findByPrimaryKey(MaintenanceOrdersActionsPk pk) throws MaintenanceOrdersActionsDaoException
	{
		return findByPrimaryKey( pk.getOrderNo(), pk.getOrderYear(), pk.getActionTime() );
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the criteria 'ORDER_NO = :orderNo AND ORDER_YEAR = :orderYear AND ACTION_TIME = :actionTime'.
	 */
	public MaintenanceOrdersActions findByPrimaryKey(int orderNo, short orderYear, Date actionTime) throws MaintenanceOrdersActionsDaoException
	{
		MaintenanceOrdersActions ret[] = findByDynamicSelect( SQL_SELECT + " WHERE ORDER_NO = ? AND ORDER_YEAR = ? AND ACTION_TIME = ?", new Object[] {  new Integer(orderNo),  new Short(orderYear), actionTime==null ? null : new java.sql.Timestamp( actionTime.getTime() ) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the criteria ''.
	 */
	public MaintenanceOrdersActions[] findAll() throws MaintenanceOrdersActionsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ORDER_NO, ORDER_YEAR, ACTION_TIME", null );
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the criteria 'ACTION_DESC = :actionDesc'.
	 */
	public MaintenanceOrdersActions[] findWhereActionDescEquals(String actionDesc) throws MaintenanceOrdersActionsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ACTION_DESC = ? ORDER BY ACTION_DESC", new Object[] { actionDesc } );
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the criteria 'ACTION_TIME = :actionTime'.
	 */
	public MaintenanceOrdersActions[] findWhereActionTimeEquals(Date actionTime) throws MaintenanceOrdersActionsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ACTION_TIME = ? ORDER BY ACTION_TIME", new Object[] { actionTime==null ? null : new java.sql.Timestamp( actionTime.getTime() ) } );
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the criteria 'ORDER_NO = :orderNo'.
	 */
	public MaintenanceOrdersActions[] findWhereOrderNoEquals(int orderNo) throws MaintenanceOrdersActionsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ORDER_NO = ? ORDER BY ORDER_NO", new Object[] {  new Integer(orderNo) } );
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the criteria 'ORDER_YEAR = :orderYear'.
	 */
	public MaintenanceOrdersActions[] findWhereOrderYearEquals(short orderYear) throws MaintenanceOrdersActionsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ORDER_YEAR = ? ORDER BY ORDER_YEAR", new Object[] {  new Short(orderYear) } );
	}

	/**
	 * Method 'MaintenanceOrdersActionsDaoImpl'
	 * 
	 */
	public MaintenanceOrdersActionsDaoImpl()
	{
	}

	/**
	 * Method 'MaintenanceOrdersActionsDaoImpl'
	 * 
	 * @param userConn
	 */
	public MaintenanceOrdersActionsDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "MAINTENANCE_ORDERS_ACTIONS";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected MaintenanceOrdersActions fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			MaintenanceOrdersActions dto = new MaintenanceOrdersActions();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected MaintenanceOrdersActions[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			MaintenanceOrdersActions dto = new MaintenanceOrdersActions();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		MaintenanceOrdersActions ret[] = new MaintenanceOrdersActions[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(MaintenanceOrdersActions dto, ResultSet rs) throws SQLException
	{
		dto.setActionDesc( rs.getString( COLUMN_ACTION_DESC ) );
		dto.setActionTime( rs.getTimestamp(COLUMN_ACTION_TIME ) );
		dto.setOrderNo( rs.getInt( COLUMN_ORDER_NO ) );
		dto.setOrderYear( rs.getShort( COLUMN_ORDER_YEAR ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(MaintenanceOrdersActions dto)
	{
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the specified arbitrary SQL statement
	 */
	public MaintenanceOrdersActions[] findByDynamicSelect(String sql, Object[] sqlParams) throws MaintenanceOrdersActionsDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MaintenanceOrdersActionsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the MAINTENANCE_ORDERS_ACTIONS table that match the specified arbitrary SQL statement
	 */
	public MaintenanceOrdersActions[] findByDynamicWhere(String sql, Object[] sqlParams) throws MaintenanceOrdersActionsDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new MaintenanceOrdersActionsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

    public List<MaintenanceOrdersActions> findByPk(MaintenanceOrdersActions dto) throws MaintenanceOrdersActionsDaoException {
	return Arrays.asList(findByDynamicSelect( SQL_SELECT + " WHERE ORDER_NO = ? AND ORDER_YEAR = ? ", new Object[] {  new Integer(dto.getOrderNo()),new Short(dto.getOrderYear()) } ));
    }

}