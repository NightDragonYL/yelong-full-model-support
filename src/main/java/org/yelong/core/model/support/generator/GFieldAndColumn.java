/**
 * 
 */
package org.yelong.core.model.support.generator;

import java.lang.reflect.Field;

import org.yelong.core.model.resolve.FieldAndColumn;

/**
 * @author PengFei
 * @date 2020年3月12日下午7:19:25
 * @since 1.0
 */
public interface GFieldAndColumn extends FieldAndColumn{

	@Override
	default Field getField() {
		throw new UnsupportedOperationException("生成器Model不允许获取字段");
	}
	
}
