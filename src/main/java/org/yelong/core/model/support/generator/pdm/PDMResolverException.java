/**
 * 
 */
package org.yelong.core.model.support.generator.pdm;

/**
 * @author PengFei
 * @date 2020年1月10日下午4:25:44
 */
public class PDMResolverException extends Exception{

	private static final long serialVersionUID = -5052453534630916611L;
	
	public PDMResolverException() {
		
	}
	
	public PDMResolverException(String message) {
		super(message);
	}
	
	public PDMResolverException(Throwable t) {
		super(t);
	}

}
