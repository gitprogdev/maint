/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aabu.security;

import java.io.Serializable;

/**
 *
 * @author akaleek
 */
public class Applications2 implements Serializable {
    private String arabicName;
    private String engName;
    private String progUrl;
    private String tabl;
    private String utf8Title;

    public Applications2() {
    }

    public Applications2(String progUrl) {
        this.progUrl = progUrl;
    }

    public Applications2(String progUrl, String arabicName, String engName, String tabl, String utf8Title) {
        this.arabicName = arabicName;
        this.engName = engName;
        this.progUrl = progUrl;
        this.tabl = tabl;
        this.utf8Title = utf8Title;
    }



    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getProgUrl() {
        return progUrl;
    }

    public void setProgUrl(String progUrl) {
        this.progUrl = progUrl;
    }

    public String getTabl() {
        return tabl;
    }

    public void setTabl(String tabl) {
        this.tabl = tabl;
    }

    public String getUtf8Title() {
        return utf8Title;
    }

    public void setUtf8Title(String utf8Title) {
        this.utf8Title = utf8Title;
    }

    @Override
    public int hashCode() {
        return progUrl.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applications2)) {
            return false;
        }
        Applications2 other = (Applications2) object;
        return this.progUrl.equals(other.getProgUrl());
    }

    @Override
    public String toString() {
        return "edu.aabu.security.Applications[" + progUrl + "]";
    }

}
