/**
 * 
 */
package org.yelong.core.model.support.generator.test;

import java.io.File;
import java.util.List;

import org.yelong.core.model.support.generator.GFieldAndColumn;
import org.yelong.core.model.support.generator.GFieldAndColumnWrapper;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.ModelGenerateInterceptor;
import org.yelong.core.model.support.generator.ModelGenerator;
import org.yelong.core.model.support.generator.exception.ModelGenerateException;
import org.yelong.core.model.support.generator.impl.DefaultModelGenerator;
import org.yelong.core.model.support.generator.pdm.DefaultPDMResolver;
import org.yelong.core.model.support.generator.pdm.PDMResolver;
import org.yelong.core.model.support.generator.pdm.PDMResolverException;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:53:25
 * @since 1.0
 */
public class ModelGeneratorTest {

	/**
	 * @param args
	 * @throws PDMResolverException 
	 * @throws ModelGenerateException 
	 */
	public static void main(String[] args) throws PDMResolverException, ModelGenerateException {
		ModelGenerator modelGenerator = new DefaultModelGenerator();
		modelGenerator.addInterceptor(new ModelGenerateInterceptor() {
			@Override
			public GFieldAndColumn process(GFieldAndColumn gFieldAndColumn) {
				if(gFieldAndColumn.getColumn().contains("id")) {
					return null;
				}
				return gFieldAndColumn;
			}
		});
		
		PDMResolver pdmResolver = new DefaultPDMResolver();
		List<GModelAndTable> gModelAndTables = pdmResolver.resolve(new File("D:\\PowerDesigner\\数模\\generator.pdm"));
		for (GModelAndTable gModelAndTable : gModelAndTables) {
			modelGenerator.generate(gModelAndTable, new File("D:\\PowerDesigner\\数模\\model",gModelAndTable.getModelClassSimpleName()+".java"));
		}
	}
	
	public class GFieldAndColumnWrapperTest extends GFieldAndColumnWrapper{

		public GFieldAndColumnWrapperTest(GFieldAndColumn gFieldAndColumn) {
			super(gFieldAndColumn);
		}

	}
	

}
