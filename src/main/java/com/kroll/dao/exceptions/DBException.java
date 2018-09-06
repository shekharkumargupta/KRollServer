/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kroll.dao.exceptions;

/**
 *
 * @author Ramesh
 */
public class DBException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 572951379293986429L;
	private String message;
    public DBException(String message){
        this.message = message;
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }
}
