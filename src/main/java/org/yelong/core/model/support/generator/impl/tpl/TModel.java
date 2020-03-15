/**
 * 
 */
package org.yelong.core.model.support.generator.impl.tpl;

import java.util.List;

/**
 * 
 * @author PengFei
 * @date 2020年1月10日下午7:21:23
 */
public class TModel {

	private String modelPackage;
	
	private String tableName;
	
	private String tableDesc;
	
	private String modelName;
	
	private String modelNamePrefixLowerCase;
	
	private List<TModelField> modelFields;
	
	private String author = System.getProperty("user.name");

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<TModelField> getModelFields() {
		return modelFields;
	}

	public void setModelFields(List<TModelField> modelFields) {
		this.modelFields = modelFields;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getModelNamePrefixLowerCase() {
		return modelNamePrefixLowerCase;
	}

	public void setModelNamePrefixLowerCase(String modelNamePrefixLowerCase) {
		this.modelNamePrefixLowerCase = modelNamePrefixLowerCase;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	
}
