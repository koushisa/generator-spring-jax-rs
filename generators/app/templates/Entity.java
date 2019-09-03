package <%= basePackage %>.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;

/**
 * <%= resourceNameKana %>テーブルのエンティティーです。
 *
 * @author yeoman
 *
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
public class <%= resourceName %> implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger <%= camelPrimaryKey %>;
}
