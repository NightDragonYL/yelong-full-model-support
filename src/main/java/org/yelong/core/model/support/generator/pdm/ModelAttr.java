/**
 * 
 */
package org.yelong.core.model.support.generator.pdm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PengFei
 * @date 2020年1月10日下午3:45:16
 */
public class ModelAttr {


	private String modelPackage = "";
	private String tableName = "";
	private String author = "";
	private String tableCode = "";
	private String modelName = "";
	private String nameLowerCase = "";
	private List<ModelField> modelFields = new ArrayList<>();

	public String getModelPackage()
	{
		return this.modelPackage;
	}

	public void setModelPackage(String modelPackage)
	{
		this.modelPackage = modelPackage;
	}

	public String getTableName()
	{
		return this.tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getAuthor()
	{
		return this.author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getTableCode()
	{
		return this.tableCode;
	}

	public void setTableCode(String tableCode)
	{
		this.tableCode = tableCode;
	}

	public String getNameLowerCase()
	{
		return this.nameLowerCase;
	}

	public void setNameLowerCase(String nameLowerCase)
	{
		this.nameLowerCase = nameLowerCase;
	}

	public List<ModelField> getModelFields()
	{
		return this.modelFields;
	}

	public void addModelField(ModelField field)
	{
		this.modelFields.add(field);
	}

	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}

	public String getModelName()
	{
		return this.modelName;
	}


}
