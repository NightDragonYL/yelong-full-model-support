/**
 * 
 */
package org.yelong.core.model.support.generator;

import java.lang.reflect.Field;
import java.util.Objects;

import org.yelong.core.model.resolve.DefaultFieldAndColumn;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:25:32
 * @since 1.0
 */
public class DefaultGFieldAndColumn extends DefaultFieldAndColumn implements GFieldAndColumn{

	private String fieldName;
	
	private Class<?> fieldType;
	
	public DefaultGFieldAndColumn(String column , String fieldName , Class<?> fieldType) {
		super(null, column);
		Objects.requireNonNull(fieldName);
		Objects.requireNonNull(fieldType);
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}
	
	@Override
	public Field getField() {
		throw new UnsupportedOperationException("生成器Model不允许获取字段");
	}
	
	@Override
	public Class<?> getFieldType() {
		return this.fieldType;
	}

	@Override
	public String getFieldName() {
		return this.fieldName;
	}

	@Override
	public String toString() {
		return "DefaultGFieldAndColumn [column=" + getColumn() + ",fieldName=" + fieldName + ", fieldType=" + fieldType + "]";
	}
	
	
	
}
