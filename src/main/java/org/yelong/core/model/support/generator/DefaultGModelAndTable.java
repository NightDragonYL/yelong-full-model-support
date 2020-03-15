/**
 * 
 */
package org.yelong.core.model.support.generator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import org.yelong.core.model.Model;
import org.yelong.core.model.resolve.DefaultModelAndTable;

/**
 * @author PengFei
 * @date 2020年3月13日下午11:40:10
 * @since 1.0
 */
public class DefaultGModelAndTable extends DefaultModelAndTable implements GModelAndTable{

	private String modelClassName;
	
	/**
	 * @param modelName model 类全名称 如 org.yelong.core.model.Model
	 * @param tableName 
	 * @param fieldAndColumns
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DefaultGModelAndTable(String modelClassName, String tableName, List<GFieldAndColumn> fieldAndColumns) {
		super(null, tableName, (List)fieldAndColumns);
		Objects.requireNonNull(modelClassName);
		this.modelClassName = modelClassName;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GFieldAndColumn> getGPrimaryKey() {
		return (List)super.getPrimaryKey();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GFieldAndColumn> getGFieldAndColumns() {
		return (List)super.getFieldAndColumns();
	}

	@Override
	public GFieldAndColumn getGFieldAndColumn(String fieldName) {
		return (GFieldAndColumn) super.getFieldAndColumn(fieldName);
	}

	@Override
	public String getModelClassName() {
		return this.modelClassName;
	}

	@Override
	public String getModelClassSimpleName() {
		return this.modelClassName.substring(this.modelClassName.lastIndexOf(".")+1);
	}

	@Override
	public String getModelClassPackageName() {
		return this.modelClassName.substring(0,this.modelClassName.lastIndexOf("."));
	}
	
	@Override
	public <M extends Model> Class<M> getModelClass() {
		throw new UnsupportedOperationException("生成器Model不允许获取class");
	}
	
	@Override
	public List<Field> getFields() {
		throw new UnsupportedOperationException("生成器Model不允许获取字段");
	}

}
