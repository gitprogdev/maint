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
public class ApplicationsPK implements Serializable,Comparable<ApplicationsPK> {
    private short level1;
    private short level2;
    private short level3;
    private short level4;
    private short level5;

    public ApplicationsPK() {
    }

    public ApplicationsPK(short level1, short level2, short level3, short level4, short level5) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.level5 = level5;
    }

    public short getLevel1() {
        return level1;
    }

    public void setLevel1(short level1) {
        this.level1 = level1;
    }

    public short getLevel2() {
        return level2;
    }

    public void setLevel2(short level2) {
        this.level2 = level2;
    }

    public short getLevel3() {
        return level3;
    }

    public void setLevel3(short level3) {
        this.level3 = level3;
    }

    public short getLevel4() {
        return level4;
    }

    public void setLevel4(short level4) {
        this.level4 = level4;
    }

    public short getLevel5() {
        return level5;
    }

    public void setLevel5(short level5) {
        this.level5 = level5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) level1;
        hash += (int) level2;
        hash += (int) level3;
        hash += (int) level4;
        hash += (int) level5;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationsPK)) {
            return false;
        }
        ApplicationsPK other = (ApplicationsPK) object;
        if (this.level1 != other.level1) {
            return false;
        }
        if (this.level2 != other.level2) {
            return false;
        }
        if (this.level3 != other.level3) {
            return false;
        }
        if (this.level4 != other.level4) {
            return false;
        }
        if (this.level5 != other.level5) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + level1 + ", level2=" + level2 + ", level3=" + level3 + ", level4=" + level4 + ", level5=" + level5 + "]";
    }

    public int compareTo(ApplicationsPK o) {
        if (this.level1 < o.level1) {
            return -1;
        }
        if (this.level1 > o.level1) {
            return 1;
        }
        if (this.level2 < o.level2) {
            return -1;
        }
        if (this.level2 > o.level2) {
            return 1;
        }
        if (this.level3 < o.level3) {
            return -1;
        }
        if (this.level3 > o.level3) {
            return 1;
        }
        if (this.level4 < o.level4) {
            return -1;
        }
        if (this.level4 > o.level4) {
            return 1;
        }
        if (this.level5 < o.level5) {
            return -1;
        }
        if (this.level5 > o.level5) {
            return 1;
        }
        return 0;
    }

}
