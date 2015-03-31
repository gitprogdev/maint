/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aabu.maintenence.dto;

/**
 *
 * @author WEBDEV
 */
public class MaintenanceOrdersDesc extends MaintenanceOrders {
    private String orderStatusDesc ="";
    private String empName = "" ;
    private String pepartydesc = "";
    private String toPepartydesc = "";
    
    /**
     * @return the orderStatusDesc
     */
    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    /**
     * @param orderStatusDesc the orderStatusDesc to set
     */
    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the pepartydesc
     */
    public String getPepartydesc() {
        return pepartydesc;
    }

    /**
     * @param pepartydesc the pepartydesc to set
     */
    public void setPepartydesc(String pepartydesc) {
        this.pepartydesc = pepartydesc;
    }

    /**
     * @return the toPepartydesc
     */
    public String getToPepartydesc() {
        return toPepartydesc;
    }

    /**
     * @param toPepartydesc the toPepartydesc to set
     */
    public void setToPepartydesc(String toPepartydesc) {
        this.toPepartydesc = toPepartydesc;
    }

}
