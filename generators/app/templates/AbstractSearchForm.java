package <%= basePackage %>.model.form;

import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import lombok.Data;

/**
 * ページングのリクエストパラメータを共通化するための抽象フォームクラスです。
 *
 * @author yeoman
 *
 */
@Data
public abstract class AbstractSearchForm {

	/**
	 * 取得位置
	 */
	@QueryParam(value = "offset")
	@DefaultValue("0")
	private BigInteger offset;

	/**
	 * 取得数
	 */
	@QueryParam(value = "limit")
	@DefaultValue("999999999")
	private BigInteger limit;
}
