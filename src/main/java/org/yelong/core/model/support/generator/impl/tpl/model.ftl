package ${model.modelPackage};

import org.yelong.core.model.annotation.Column;
import org.yelong.core.model.annotation.Table;
import org.yelong.core.model.Model;

/**
 * ${model.tableName} ${model.tableDesc}
 * @author ${model.author}
 *
 */
@Table(value="${model.tableName}",alias="${model.modelNamePrefixLowerCase}",desc="${model.tableDesc}")
public class ${model.modelName} extends Model {
	<#list model.modelFields as modelField>
	
	<#if modelField.primaryKey == "true">
	@Id
	</#if>
	${modelField.columnAnnotation}
	private ${modelField.type} ${modelField.code};
	</#list>

	<#list model.modelFields as modelField>
	public ${modelField.type} get${modelField.codePrefixUpperCase}() {
		return ${modelField.code};
	}

	public void set${modelField.codePrefixUpperCase}(${modelField.type} ${modelField.code}) {
		this.${modelField.code} = ${modelField.code};
	}
	</#list>
}
