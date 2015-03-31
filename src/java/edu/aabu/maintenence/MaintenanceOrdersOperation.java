/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aabu.maintenence;

import edu.aabu.maintenence.dao.MaintenanceOrdersActionsDao;
import edu.aabu.maintenence.dao.MaintenanceOrdersDao;
import edu.aabu.maintenence.dto.MaintenanceOrders;
import edu.aabu.maintenence.dto.MaintenanceOrdersActions;
import edu.aabu.maintenence.dto.MaintenanceOrdersDesc;
import edu.aabu.maintenence.exceptions.MaintenanceOrdersActionsDaoException;
import edu.aabu.maintenence.exceptions.MaintenanceOrdersDaoException;
import edu.aabu.maintenence.factory.MaintenanceOrdersActionsDaoFactory;
import edu.aabu.maintenence.factory.MaintenanceOrdersDaoFactory;
import edu.aabu.maintenence.jdbc.ResourceManager;
import edu.aabu.peaff.Peparty;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**edu.aabu.maintenence.MaintenanceOrdersOperation
 *
 * @author WEBDEV
 */
public class MaintenanceOrdersOperation {

    private String notes;
    private Connection conn = null;

    public void insert(MaintenanceOrders order) throws Exception {
        try {
            setConn(ResourceManager.getConnection());
            getConn().setAutoCommit(false);
            MaintenanceOrdersDao _dao = MaintenanceOrdersDaoFactory.create(getConn());
            MaintenanceOrdersActionsDao _mDao = MaintenanceOrdersActionsDaoFactory.create(getConn());
            _dao.insert(order);
            MaintenanceOrdersActions orderAction = new MaintenanceOrdersActions();
            orderAction.setOrderNo(order.getOrderNo());
            orderAction.setOrderYear(order.getOrderYear());
            _mDao.insertAction(orderAction, getNotes(), 1);
            getConn().commit();
        } catch (Exception ex) {
            try {
                getConn().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage(),ex);
        }finally {
            getConn().close();
        }
    }

    public void cancel(MaintenanceOrders order) throws Exception {
        try {
            setConn(ResourceManager.getConnection());
            getConn().setAutoCommit(false);
            MaintenanceOrdersActionsDao _mDao = MaintenanceOrdersActionsDaoFactory.create(getConn());
            MaintenanceOrdersActions orderAction = new MaintenanceOrdersActions();
            orderAction.setOrderNo(order.getOrderNo());
            orderAction.setOrderYear(order.getOrderYear());
            MaintenanceOrdersDao _dao = MaintenanceOrdersDaoFactory.create(getConn());
            _dao.cancel(order);
            _mDao.insertAction(orderAction, getNotes(), 3);
            getConn().commit();
        } catch (Exception ex) {
            try {
                getConn().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage(),ex);
        }finally {
            getConn().close();
        }
    }

    public void update(MaintenanceOrders order) throws Exception {
        
        try {
            setConn(ResourceManager.getConnection());
            getConn().setAutoCommit(false);
            MaintenanceOrdersDao _dao = MaintenanceOrdersDaoFactory.create(getConn());
            _dao.update(order);
            MaintenanceOrdersActionsDao _mDao = MaintenanceOrdersActionsDaoFactory.create(getConn());
            MaintenanceOrdersActions orderAction = new MaintenanceOrdersActions();
            orderAction.setOrderNo(order.getOrderNo());
            orderAction.setOrderYear(order.getOrderYear());
            _mDao.insertAction(orderAction, getNotes(), 2);
            getConn().commit();
        } catch (Exception ex) {
            try {
                getConn().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage(),ex);
        }finally {
            getConn().close();
        }
    }

    public void updateOpinion(MaintenanceOrders order) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        _dao.updateOpinion(order);
    }

    public void updateStatus(MaintenanceOrders order) throws Exception {
        
        try {
            setConn(ResourceManager.getConnection());
            getConn().setAutoCommit(false);
            MaintenanceOrdersDao _dao = MaintenanceOrdersDaoFactory.create(getConn());
            _dao.updateStatus(order);
            MaintenanceOrdersActionsDao _mDao = MaintenanceOrdersActionsDaoFactory.create(getConn());
            MaintenanceOrdersActions orderAction = new MaintenanceOrdersActions();
            orderAction.setOrderNo(order.getOrderNo());
            orderAction.setOrderYear(order.getOrderYear());
            int actionType = 1 ;
            switch(order.getOrderStatus()) {
                case 3://manager confirm
                    actionType = 4;
                    break;
                case 4://reciving order
                    actionType=5 ;
                    break ;
                case 5://close order
                    actionType = 6 ;
                    break;
            }
            _mDao.insertAction(orderAction, getNotes(), actionType);
            getConn().commit();
        } catch (Exception ex) {
            try {
                getConn().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(MaintenanceOrdersOperation.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage(),ex);
        }finally {
            getConn().close();
        }
    }

    public List<Peparty> getEmpWorkPlaces(int peEmpNo) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return _dao.getEmpWorkPlaces(peEmpNo);
    }

    public List<MaintenanceOrdersDesc> findByEmpNo(int peEmpNo,Peparty sec) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return _dao.findByEmpNo(peEmpNo,sec);
    }

    /**
     * Method 'findAll'
     *
     */
    public MaintenanceOrders[] findAll() throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return _dao.findAll();
    }

    /**
     * Method 'findWhereOrderStatusEquals'
     *
     * @param orderStatus
     */
    public List<MaintenanceOrdersDesc> findWhereOrderStatusEquals(short orderStatus) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return Arrays.asList(_dao.findWhereOrderStatusEquals(orderStatus));
    } /**
     * Method 'findWhereOrderStatusEquals'
     *
     * @param orderStatus
     */
    public List<MaintenanceOrdersDesc> findWhereOrderStatusEqualsNew(short orderStatus,int empNo) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return Arrays.asList(_dao.findWhereOrderStatusEqualsNew(orderStatus,empNo));
    }

    public MaintenanceOrdersDesc findByPk(MaintenanceOrders order) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return _dao.findByPk(order.getOrderNo(), order.getOrderYear());
    }

    public Peparty getPeparty(MaintenanceOrders order) {
        Peparty peparty = new Peparty();
        peparty.setPeTypeCode(order.getPeTypeCode());
        peparty.setPeMainCode(order.getPeMainCode());
        peparty.setPeSectionCode(order.getPeSectionCode());
        peparty.setPeClassCode(order.getPeClassCode());
        return peparty;
    }

    public List<MaintenanceOrdersDesc> findByManager(int peEmpNo, short orderStatus) throws MaintenanceOrdersDaoException {
        MaintenanceOrdersDao _dao = getMaintenanceOrdersDao();
        return _dao.findByManager(peEmpNo, orderStatus);
    }
    public List<MaintenanceOrdersActions> getOrdersActions(int orderNo,short orderYear ) throws MaintenanceOrdersActionsDaoException {
        MaintenanceOrdersActionsDao _dao =  MaintenanceOrdersActionsDaoFactory.create();
        MaintenanceOrdersActions ordActions = new MaintenanceOrdersActions();
        ordActions.setOrderNo(orderNo);
        ordActions.setOrderYear(orderYear);
        return _dao.findByPk(ordActions);
    }

    /**
     * Method 'getMaintenanceOrdersDao'
     *
     * @return MaintenanceOrdersDao
     */
    public MaintenanceOrdersDao getMaintenanceOrdersDao() {
        return MaintenanceOrdersDaoFactory.create();
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
