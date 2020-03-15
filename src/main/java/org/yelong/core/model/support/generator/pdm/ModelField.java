/**
 * 
 */
package org.yelong.core.model.support.generator.pdm;

/**
 * @author PengFei
 * @date 2020年1月10日下午3:45:47
 */
public class ModelField {
	
	private String isPrimaryKey = "false";
	private String name = "";
	private String code = "";
	private String isMandatory = "false";
	private String type = "";
	private String length = "";
	private String codePrefixUpperCase;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return this.code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getLength()
	{
		return this.length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}

	public String getCodePrefixUpperCase()
	{
		return this.codePrefixUpperCase;
	}

	public void setCodePrefixUpperCase(String codePrefixUpperCase)
	{
		this.codePrefixUpperCase = codePrefixUpperCase;
	}

	public String getIsPrimaryKey()
	{
		return this.isPrimaryKey;
	}

	public void setIsPrimaryKey(String isPrimaryKey)
	{
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getIsMandatory()
	{
		return this.isMandatory;
	}

	public void setIsMandatory(String isMandatory)
	{
		this.isMandatory = isMandatory;
	}
}
