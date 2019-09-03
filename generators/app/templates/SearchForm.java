package <%= basePackage %>.model.form;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <%= resourceNameKana %>検索APIのフォームです。
 *
 * @author yeoman
 *
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
@EqualsAndHashCode(callSuper = false)
public class <%= resourceName %>SearchForm extends AbstractSearchForm implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	//TODO
}
