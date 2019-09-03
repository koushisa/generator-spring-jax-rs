package <%= basePackage %>.model.dto.<%= snakeResourceName %>;

import java.io.Serializable;
import java.util.List;

import org.seasar.doma.Entity;
import org.seasar.doma.Transient;
import org.seasar.doma.jdbc.entity.NamingType;

import <%= basePackage %>.model.entity.<%= resourceName %>;
import lombok.Data;

/**
 * <%= resourceNameKana %>検索APIのレスポンスDTO
 *
 * @author yeoman
 *
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
public class <%= resourceName %>SearchResponse implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 総件数
	 */
	private long count;

	/**
	 * 検索結果
	 */
	@Transient
	private List<<%= resourceName %>> <%=camelResourceName %>s;

}
