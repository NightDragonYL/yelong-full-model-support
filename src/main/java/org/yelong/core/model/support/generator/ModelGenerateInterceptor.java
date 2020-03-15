/**
 * 
 */
package org.yelong.core.model.support.generator;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:43:58
 * @since 1.0
 */
public interface ModelGenerateInterceptor {

	default GModelAndTable process(GModelAndTable gModelAndTable) {
		return gModelAndTable;
	}
	
	default GFieldAndColumn process(GFieldAndColumn gFieldAndColumn) {
		return gFieldAndColumn;
	}
	
}
