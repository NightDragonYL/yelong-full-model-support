/**
 * 
 */
package org.yelong.core.model.support.generator.pdm;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.yelong.core.model.support.generator.GModelAndTable;

/**
 * @author PengFei
 * @date 2020年1月10日下午4:21:17
 */
public interface PDMResolver {
	
	List<GModelAndTable> resolve(InputStream is) throws PDMResolverException;
	
	List<GModelAndTable> resolve(File pdm) throws PDMResolverException;
	
}
