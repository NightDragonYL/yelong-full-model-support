/**
 * 
 */
package org.yelong.core.model.support.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:46:10
 * @since 1.0
 */
public abstract class AbstractModelGenerator implements ModelGenerator{

	protected final List<ModelGenerateInterceptor> modelGenerateInterceptors = new ArrayList<>();
	
	@Override
	public void addInterceptor(ModelGenerateInterceptor modelGenerateInterceptor) {
		modelGenerateInterceptors.add(modelGenerateInterceptor);
	}

}
