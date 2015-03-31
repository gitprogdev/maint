/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aabu.security;
import java.io.Serializable ;


/**
 *
 * @author akaleek
 */
public class Applications implements Serializable,Comparable<Applications> {
    private static final long serialVersionUID = 1L;
    protected ApplicationsPK applicationsPK;
    private String arabicName;
    private String engName;
    private String progUrl;
    private String tabl;
    private String utf8Title;
	private String progDetail ;

    public Applications() {
    }

    public Applications(ApplicationsPK applicationsPK) {
        this.applicationsPK = applicationsPK;
    }

    public Applications(ApplicationsPK applicationsPK, String arabicName, String engName, String progUrl, String tabl, String utf8Title,String progDetail) {
        this.applicationsPK = applicationsPK;
        this.arabicName = arabicName;
        this.engName = engName;
        this.progUrl = progUrl;
        this.tabl = tabl;
        this.utf8Title = utf8Title;
		this.progDetail = progDetail;
    }

    public Applications(short level1, short level2, short level3, short level4, short level5) {
        this.applicationsPK = new ApplicationsPK(level1, level2, level3, level4, level5);
    }

    public ApplicationsPK getApplicationsPK() {
        return applicationsPK;
    }

    public void setApplicationsPK(ApplicationsPK applicationsPK) {
        this.applicationsPK = applicationsPK;
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

    public String getProgDetail() {
        return progDetail;
    }

    public void setProgDetail(String progDetail) {
        this.progDetail = progDetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationsPK != null ? applicationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applications)) {
            return false;
        }
        Applications other = (Applications) object;
        if ((this.applicationsPK == null && other.applicationsPK != null) || (this.applicationsPK != null && !this.applicationsPK.equals(other.applicationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" +  applicationsPK + "," + arabicName + "," + progUrl + "]\n";
    }

    public int compareTo(Applications o) {
        return (this.applicationsPK.compareTo(o.getApplicationsPK()));
    }

}
