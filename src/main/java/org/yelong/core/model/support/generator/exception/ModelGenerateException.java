/**
 * 
 */
package org.yelong.core.model.support.generator.exception;

/**
 * @author PengFei
 * @date 2020年3月13日下午11:37:08
 * @since 1.0
 */
public class ModelGenerateException extends Exception{

	private static final long serialVersionUID = 7869214973920455368L;

	public ModelGenerateException() {
		
	}
	
	public ModelGenerateException(Throwable t) {
		super(t);
	}

	public ModelGenerateException(String message) {
		super(message);
	}

}
