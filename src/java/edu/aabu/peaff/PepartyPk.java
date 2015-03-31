/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package edu.aabu.peaff;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the peparty table.
 */
public class PepartyPk implements Serializable
{
	protected short peTypeCode;

	protected short peMainCode;

	protected short peSectionCode;

	protected short peClassCode;

	/** 
	 * This attribute represents whether the primitive attribute peTypeCode is null.
	 */
	protected boolean peTypeCodeNull;

	/** 
	 * This attribute represents whether the primitive attribute peMainCode is null.
	 */
	protected boolean peMainCodeNull;

	/** 
	 * This attribute represents whether the primitive attribute peSectionCode is null.
	 */
	protected boolean peSectionCodeNull;

	/** 
	 * This attribute represents whether the primitive attribute peClassCode is null.
	 */
	protected boolean peClassCodeNull;

	/** 
	 * Sets the value of peTypeCode
	 */
	public void setPeTypeCode(short peTypeCode)
	{
		this.peTypeCode = peTypeCode;
	}

	/** 
	 * Gets the value of peTypeCode
	 */
	public short getPeTypeCode()
	{
		return peTypeCode;
	}

	/** 
	 * Sets the value of peMainCode
	 */
	public void setPeMainCode(short peMainCode)
	{
		this.peMainCode = peMainCode;
	}

	/** 
	 * Gets the value of peMainCode
	 */
	public short getPeMainCode()
	{
		return peMainCode;
	}

	/** 
	 * Sets the value of peSectionCode
	 */
	public void setPeSectionCode(short peSectionCode)
	{
		this.peSectionCode = peSectionCode;
	}

	/** 
	 * Gets the value of peSectionCode
	 */
	public short getPeSectionCode()
	{
		return peSectionCode;
	}

	/** 
	 * Sets the value of peClassCode
	 */
	public void setPeClassCode(short peClassCode)
	{
		this.peClassCode = peClassCode;
	}

	/** 
	 * Gets the value of peClassCode
	 */
	public short getPeClassCode()
	{
		return peClassCode;
	}

	/**
	 * Method 'PepartyPk'
	 * 
	 */
	public PepartyPk()
	{
	}

	/**
	 * Method 'PepartyPk'
	 * 
	 * @param peTypeCode
	 * @param peMainCode
	 * @param peSectionCode
	 * @param peClassCode
	 */
	public PepartyPk(final short peTypeCode, final short peMainCode, final short peSectionCode, final short peClassCode)
	{
		this.peTypeCode = peTypeCode;
		this.peMainCode = peMainCode;
		this.peSectionCode = peSectionCode;
		this.peClassCode = peClassCode;
	}

	/** 
	 * Sets the value of peTypeCodeNull
	 */
	public void setPeTypeCodeNull(boolean peTypeCodeNull)
	{
		this.peTypeCodeNull = peTypeCodeNull;
	}

	/** 
	 * Gets the value of peTypeCodeNull
	 */
	public boolean isPeTypeCodeNull()
	{
		return peTypeCodeNull;
	}

	/** 
	 * Sets the value of peMainCodeNull
	 */
	public void setPeMainCodeNull(boolean peMainCodeNull)
	{
		this.peMainCodeNull = peMainCodeNull;
	}

	/** 
	 * Gets the value of peMainCodeNull
	 */
	public boolean isPeMainCodeNull()
	{
		return peMainCodeNull;
	}

	/** 
	 * Sets the value of peSectionCodeNull
	 */
	public void setPeSectionCodeNull(boolean peSectionCodeNull)
	{
		this.peSectionCodeNull = peSectionCodeNull;
	}

	/** 
	 * Gets the value of peSectionCodeNull
	 */
	public boolean isPeSectionCodeNull()
	{
		return peSectionCodeNull;
	}

	/** 
	 * Sets the value of peClassCodeNull
	 */
	public void setPeClassCodeNull(boolean peClassCodeNull)
	{
		this.peClassCodeNull = peClassCodeNull;
	}

	/** 
	 * Gets the value of peClassCodeNull
	 */
	public boolean isPeClassCodeNull()
	{
		return peClassCodeNull;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof PepartyPk)) {
			return false;
		}
		
		final PepartyPk _cast = (PepartyPk) _other;
		if (peTypeCode != _cast.peTypeCode) {
			return false;
		}
		
		if (peMainCode != _cast.peMainCode) {
			return false;
		}
		
		if (peSectionCode != _cast.peSectionCode) {
			return false;
		}
		
		if (peClassCode != _cast.peClassCode) {
			return false;
		}
		
		if (peTypeCodeNull != _cast.peTypeCodeNull) {
			return false;
		}
		
		if (peMainCodeNull != _cast.peMainCodeNull) {
			return false;
		}
		
		if (peSectionCodeNull != _cast.peSectionCodeNull) {
			return false;
		}
		
		if (peClassCodeNull != _cast.peClassCodeNull) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int) peTypeCode;
		_hashCode = 29 * _hashCode + (int) peMainCode;
		_hashCode = 29 * _hashCode + (int) peSectionCode;
		_hashCode = 29 * _hashCode + (int) peClassCode;
		_hashCode = 29 * _hashCode + (peTypeCodeNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (peMainCodeNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (peSectionCodeNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (peClassCodeNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "edu.aabu.peaff.dto.PepartyPk: " );
		ret.append( "peTypeCode=" + peTypeCode );
		ret.append( ", peMainCode=" + peMainCode );
		ret.append( ", peSectionCode=" + peSectionCode );
		ret.append( ", peClassCode=" + peClassCode );
		return ret.toString();
	}

}
