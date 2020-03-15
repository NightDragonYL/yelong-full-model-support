/**
 * 
 */
package org.yelong.core.model.support.generator;

import org.yelong.core.model.resolve.SelectColumnCondition;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:49:41
 * @since 1.0
 */
public class GFieldAndColumnWrapper implements GFieldAndColumn{

	private final GFieldAndColumn gFieldAndColumn;
	
	public GFieldAndColumnWrapper(GFieldAndColumn gFieldAndColumn) {
		this.gFieldAndColumn = gFieldAndColumn;
	}
	
	@Override
	public String getFieldName() {
		return gFieldAndColumn.getFieldName();
	}

	@Override
	public Class<?> getFieldType() {
		return gFieldAndColumn.getFieldType();
	}

	@Override
	public String getColumn() {
		return gFieldAndColumn.getColumn();
	}

	@Override
	public String getSelectColumn() {
		return gFieldAndColumn.getSelectColumn();
	}

	@Override
	public SelectColumnCondition getSelectColumnCondition() {
		return gFieldAndColumn.getSelectColumnCondition();
	}

	@Override
	public boolean isExtend() {
		return gFieldAndColumn.isExtend();
	}

	@Override
	public boolean isPrimaryKey() {
		return gFieldAndColumn.isPrimaryKey();
	}

	@Override
	public Long getMinLength() {
		return gFieldAndColumn.getMinLength();
	}

	@Override
	public Long getMaxLength() {
		return gFieldAndColumn.getMaxLength();
	}

	@Override
	public boolean isAllowBlank() {
		return gFieldAndColumn.isAllowBlank();
	}

	@Override
	public boolean isAllowNull() {
		return gFieldAndColumn.isAllowNull();
	}

	@Override
	public String getJdbcType() {
		return gFieldAndColumn.getJdbcType();
	}

	@Override
	public String getDesc() {
		return gFieldAndColumn.getDesc();
	}

}
