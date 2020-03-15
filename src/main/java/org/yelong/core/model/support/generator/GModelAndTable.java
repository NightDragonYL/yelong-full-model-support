/**
 * 
 */
package org.yelong.core.model.support.generator;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.yelong.core.model.Model;
import org.yelong.core.model.resolve.FieldAndColumn;
import org.yelong.core.model.resolve.ModelAndTable;

/**
 * @author PengFei
 * @date 2020年3月12日下午7:19:14
 * @since 1.0
 */
public interface GModelAndTable extends ModelAndTable{
	
	/**
	 * @return class name
	 */
	String getModelClassName();
	
	/**
	 * @return class simple name
	 */
	String getModelClassSimpleName();
	
	/**
	 * @return package name
	 */
	String getModelClassPackageName();
	
	/**
	 * 获取所有的主键。
	 * @return 所有主键字段列。
	 */
	List<GFieldAndColumn> getGPrimaryKey();
	
	/**
	 * 获取所有的字段列
	 * @return
	 */
	List<GFieldAndColumn> getGFieldAndColumns();
	
	/**
	 * 根据字段名称获取字段列
	 * 如果这个模型表中不存在这个字段的映射则返回 null
	 * @param fieldName 字段名名称
	 * @return 字段映射的列信息
	 */
	GFieldAndColumn getGFieldAndColumn(String fieldName);
	
	@Override
	default <M extends Model> Class<M> getModelClass() {
		throw new UnsupportedOperationException("生成器Model不允许获取class");
	}
	
	@Override
	default List<Field> getFields() {
		throw new UnsupportedOperationException("生成器Model不允许获取字段");
	}
	
	@Override
	default FieldAndColumn getFieldAndColumn(String fieldName) {
		return getGFieldAndColumn(fieldName);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default List<FieldAndColumn> getFieldAndColumns() {
		return (List)getGFieldAndColumns();
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default List<FieldAndColumn> getPrimaryKey() {
		return (List)getGPrimaryKey();
	}
	
	@Override
	default boolean existPrimaryKey() {
		return CollectionUtils.isNotEmpty(getPrimaryKey());
	}
	
}
