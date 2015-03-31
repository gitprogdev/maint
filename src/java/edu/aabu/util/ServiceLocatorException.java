/* Application : C.V
 * File   : Service Locator
 * Autohr : Eng.Zaid
 * Date   : 12/7/2009.
 */

package edu.aabu.util;

/*
 * The ServiceLocatorException class is an exception that is
 * thrown whenever a user requests an EJB or database connection from the
 * ServiceLocator and the ServiceLocator can not find the item the user is
 * loogkinh for.
 *
 */
public class ServiceLocatorException extends Exception{
  public ServiceLocatorException(String pExceptionMsg){
    super(pExceptionMsg);
  } 

  public ServiceLocatorException(String pExceptionMsg, Throwable pException){
    super(pExceptionMsg, pException);
  } 
}
