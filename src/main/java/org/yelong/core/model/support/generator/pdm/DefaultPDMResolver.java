/**
 * 
 */
package org.yelong.core.model.support.generator.pdm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yelong.core.model.support.generator.DefaultGFieldAndColumn;
import org.yelong.core.model.support.generator.DefaultGModelAndTable;
import org.yelong.core.model.support.generator.GFieldAndColumn;
import org.yelong.core.model.support.generator.GModelAndTable;

/**
 * @author PengFei
 * @date 2020年1月10日下午4:23:06
 */
public class DefaultPDMResolver implements PDMResolver{

	private static final DocumentBuilderFactory builderFactory;
	
	private static final DocumentBuilder builder;
	
	static {
		try {
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<GModelAndTable> resolve(File pdm) throws PDMResolverException{
		try {
			return resolve(new FileInputStream(pdm));
		} catch (FileNotFoundException e) {
			throw new PDMResolverException(e);
		}
	}
	
	
	@Override
	public List<GModelAndTable> resolve(InputStream is) throws PDMResolverException{
		try {
			Document xml = builder.parse(is);
			NodeList root = xml.getElementsByTagName("c:Tables");
			if( null == root || root.getLength() <= 0 ) {
				return Collections.emptyList();	
			}
			NodeList tableList = root.item(0).getChildNodes();
			if( tableList == null || tableList.getLength() <= 0 ) {
				return Collections.emptyList();	
			}
			List<GModelAndTable> modelAndTables = new ArrayList<GModelAndTable>();
			for (int i = 0; i < tableList.getLength(); i++) {
				Node table = tableList.item(i);
				if( 1 != table.getNodeType() ) {
					continue;
				}
				GModelAndTable modelAndTable = buildModelAndTable(table);
				if( null != modelAndTable) {
					modelAndTables.add(modelAndTable);
				}
			}
			return modelAndTables;
		} catch (Exception e) {
			throw new PDMResolverException(e);
		}
	}

	/**
	 * 构建模型与表
	 * @param table c:Tables节点
	 * @return 模型与表
	 * @throws PDMResolverException 
	 */
	protected GModelAndTable buildModelAndTable(Node table) throws PDMResolverException {
		if(1 != table.getNodeType()) {
			throw new PDMResolverException("解析表异常：不符合的节点");
		}
		NodeList childNodeList = table.getChildNodes();
		if( null == childNodeList || childNodeList.getLength() <= 0 ) {
			return null;
		}
		DefaultGModelAndTable modelAndTable = null;
		String tableName = "";
		String tableCode = "";
		String modelClassName = "";
		List<GFieldAndColumn> fieldAndColumns = new ArrayList<>();
		for (int i = 0 ; i < childNodeList.getLength() ; i ++ ) {
			Node childNode = childNodeList.item(i);
			if( 1 != childNode.getNodeType()) {
				continue;
			}
			String childNodeName = childNode.getNodeName();
			if("a:Name".equals(childNodeName)) {//表描述
				tableName = childNode.getTextContent();
			} else if("a:Code".equals(childNodeName)) {//表名
				tableCode = childNode.getTextContent();
			} else if("a:Comment".equals(childNodeName)) {//备注->表全名称
				String textContent = childNode.getTextContent();
				modelClassName = textContent;
			} else if("c:Columns".equals(childNodeName)) {
				NodeList columnList = childNode.getChildNodes();
				if( null != columnList && columnList.getLength()>0) {
					for (int j = 0; j < columnList.getLength(); j++) {
						Node column = columnList.item(j);
						if(1 == column.getNodeType()) {
							GFieldAndColumn fieldAndColumn = buildGFieldAndColumn(column);
							if( null != fieldAndColumn ) {
								fieldAndColumns.add(fieldAndColumn);
							}
						}
					}
				}
			}
		}
		modelAndTable = new DefaultGModelAndTable(modelClassName,tableCode, fieldAndColumns);
		modelAndTable.setTableDesc(tableName);
		return modelAndTable;
	}
	
	/**
	 * 构建字段
	 * @param column
	 * @return
	 * @throws PDMResolverException
	 */
	protected GFieldAndColumn buildGFieldAndColumn(Node column) throws PDMResolverException {
		if(1 != column.getNodeType()) {
			throw new PDMResolverException("解析列异常：不符合的节点");
		}
		NodeList columnPropertyList = column.getChildNodes();
		if( null == columnPropertyList || columnPropertyList.getLength() <= 0 ) {
			throw new PDMResolverException("解析列异常：不符合的节点");
		}
		String columnName = "";
		Class<?> fieldType = null;
		String columnDesc = "";
		Long columnLength = Long.MAX_VALUE;
		boolean mandatory = true;
		String columnType = "";
		for (int i = 0; i < columnPropertyList.getLength(); i++) {
			Node columnProperty = columnPropertyList.item(i);
			if(1 != columnProperty.getNodeType()) {
				continue;
			}
			String nodeName = columnProperty.getNodeName();
			if("a:Code".equals(nodeName)) { // 列名
				columnName = columnProperty.getTextContent();
			} else if("a:Name".equals(nodeName)) { // 列名称说明
				columnDesc = columnProperty.getTextContent();
			} else if("a:DataType".equals(nodeName)) { // 数据类型
				String dataType = columnProperty.getTextContent().toUpperCase();
				if (dataType.contains("INTEGER")) {
					fieldType = Integer.class;
					columnType = "INTEGER";
				} else if (dataType.contains("FLOAT")) {
					fieldType = Float.class;
					columnType = "FLOAT";
				} else if ((dataType.contains("NUMBER")) || (dataType.contains("NUMERIC")) || (dataType.contains("DECIMAL"))) {
					fieldType = Double.class;
					columnType = "NUMBER";
				} else if ((dataType.contains("DATE")) || (dataType.contains("TIMESTAMP"))) {
					fieldType = Date.class;
					columnType = "TIMESTAMP";
				} else {
					fieldType = String.class;
					columnType = "VARCHAR";
				}
			} else if("a:Length".equals(nodeName)) {
				String length = columnProperty.getTextContent();
				if(StringUtils.isNotBlank(length)) {
					columnLength = Long.valueOf(length);
				}
			} else if("a:Column.Mandatory".equals(nodeName)) {
				mandatory = false;
			}
		}
		DefaultGFieldAndColumn fieldAndColumn = new DefaultGFieldAndColumn(columnName,columnName,fieldType);
		fieldAndColumn.setDesc(columnDesc);
		fieldAndColumn.setJdbcType(columnType);
		fieldAndColumn.setMaxLength(columnLength);
		fieldAndColumn.setAllowNull(mandatory);
		return fieldAndColumn;
	}

}
