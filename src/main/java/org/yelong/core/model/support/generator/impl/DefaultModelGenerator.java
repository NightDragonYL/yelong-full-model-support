/**
 * 
 */
package org.yelong.core.model.support.generator.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.yelong.core.model.resolve.FieldAndColumn;
import org.yelong.core.model.support.generator.AbstractModelGenerator;
import org.yelong.core.model.support.generator.GFieldAndColumn;
import org.yelong.core.model.support.generator.GModelAndTable;
import org.yelong.core.model.support.generator.ModelGenerateInterceptor;
import org.yelong.core.model.support.generator.ModelGenerator;
import org.yelong.core.model.support.generator.exception.ModelGenerateException;
import org.yelong.core.model.support.generator.impl.tpl.TModel;
import org.yelong.core.model.support.generator.impl.tpl.TModelField;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:29:36
 * @since 1.0
 */
public class DefaultModelGenerator extends AbstractModelGenerator implements ModelGenerator{

	private static final FreeMarkerConfigurationFactoryBean factory = new FreeMarkerConfigurationFactoryBean();

	private static Configuration freemarkerConfiguration = null;

	private static final String FTL_NAME = "model.ftl";

	static {
		factory.setTemplateLoaderPath("org/yelong/core/model/support/generator/impl/tpl");
		try {
			freemarkerConfiguration = factory.createConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generate(GModelAndTable modelAndTable, File modelFile) throws ModelGenerateException {
		TModel TModel = convert(modelAndTable);
		try {
			Template template = freemarkerConfiguration.getTemplate(FTL_NAME,"UTF-8");
			Map<String,Object> root = new HashMap<>();
			root.put("model", TModel);
			root.put("existDateField", existDateField(modelAndTable));
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(modelFile), "utf-8"));
			//生成word文件
			template.process(root,writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			throw new ModelGenerateException(e);
		}
	}

	private boolean existDateField(GModelAndTable modelAndTable) {
		for (FieldAndColumn fieldAndColumn : modelAndTable.getFieldAndColumns()) {
			Class<?> fieldType = fieldAndColumn.getFieldType();
			if(fieldType.isAssignableFrom(Date.class) ) {
				return true;
			}
		}
		return false;
	}

	public TModel convert(GModelAndTable modelAndTable) {
		TModel tModel = new TModel();
		for (ModelGenerateInterceptor modelGenerateInterceptor : modelGenerateInterceptors) {
			modelAndTable = modelGenerateInterceptor.process(modelAndTable);
		}
		String modelName = modelAndTable.getModelClassSimpleName();
		tModel.setModelName(modelName);
		tModel.setModelPackage(modelAndTable.getModelClassPackageName());
		tModel.setTableName(modelAndTable.getTableName());
		tModel.setModelNamePrefixLowerCase(modelName.substring(0, 1).toLowerCase()+modelName.substring(1));
		tModel.setTableDesc(modelAndTable.getTableDesc());
		
		
		List<GFieldAndColumn> fieldAndColumns = modelAndTable.getGFieldAndColumns();
		List<TModelField> modelFields = new ArrayList<TModelField>(fieldAndColumns.size());
		for (GFieldAndColumn fieldAndColumn : fieldAndColumns) {
			TModelField tModelField = new TModelField();
			for (ModelGenerateInterceptor modelGenerateInterceptor : modelGenerateInterceptors) {
				fieldAndColumn = modelGenerateInterceptor.process(fieldAndColumn);
			}
			if( null == fieldAndColumn ) {
				continue;
			}
			String fieldName = fieldAndColumn.getFieldName();
			tModelField.setCode(fieldName);
			tModelField.setCodePrefixUpperCase(fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1));
			tModelField.setLength(fieldAndColumn.getMaxLength().toString());
			tModelField.setMandatory(Boolean.toString(fieldAndColumn.isAllowNull()));
			tModelField.setName(fieldAndColumn.getDesc());
			tModelField.setPrimaryKey(Boolean.toString(fieldAndColumn.isPrimaryKey()));
			tModelField.setType(fieldAndColumn.getFieldType().getSimpleName());
			StringBuilder columnAnnotation = new StringBuilder("@Column(");
			columnAnnotation.append("column = \""+fieldName+"\"");
			if(fieldAndColumn.getMaxLength() < Long.MAX_VALUE) {
				columnAnnotation.append(",maxLength = "+fieldAndColumn.getMaxLength());
			}
			if(!fieldAndColumn.isAllowNull()) {
				columnAnnotation.append(",allowNull = "+fieldAndColumn.isAllowNull());
			}
			//columnAnnotation.append(",jdbcType = \""+fieldAndColumn.getJdbcType()+"\"");
			columnAnnotation.append(",desc = \""+fieldAndColumn.getDesc()+"\"");
			columnAnnotation.append(")");
			tModelField.setColumnAnnotation(columnAnnotation.toString());
			
			modelFields.add(tModelField);
		}
		tModel.setModelFields(modelFields);
		return tModel;
	}

}
