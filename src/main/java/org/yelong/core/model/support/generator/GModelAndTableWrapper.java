/**
 * 
 */
package org.yelong.core.model.support.generator;

import java.util.List;

/**
 * @author PengFei
 * @date 2020年3月15日下午12:49:18
 * @since 1.0
 */
public class GModelAndTableWrapper implements GModelAndTable{

	private final GModelAndTable gModelAndTable;

	public GModelAndTableWrapper(GModelAndTable gModelAndTable) {
		this.gModelAndTable = gModelAndTable;
	}

	@Override
	public String getTableName() {
		return gModelAndTable.getTableName();
	}

	@Override
	public String getTableAlias() {
		return gModelAndTable.getTableAlias();
	}

	@Override
	public String getTableDesc() {
		return gModelAndTable.getTableDesc();
	}

	@Override
	public List<String> getFieldNames() {
		return gModelAndTable.getFieldNames();
	}

	@Override
	public String getModelClassName() {
		return gModelAndTable.getModelClassName();
	}

	@Override
	public String getModelClassSimpleName() {
		return gModelAndTable.getModelClassSimpleName();
	}

	@Override
	public String getModelClassPackageName() {
		return gModelAndTable.getModelClassPackageName();
	}

	@Override
	public List<GFieldAndColumn> getGPrimaryKey() {
		return gModelAndTable.getGPrimaryKey();
	}

	@Override
	public List<GFieldAndColumn> getGFieldAndColumns() {
		return gModelAndTable.getGFieldAndColumns();
	}

	@Override
	public GFieldAndColumn getGFieldAndColumn(String fieldName) {
		return gModelAndTable.getGFieldAndColumn(fieldName);
	}

}
