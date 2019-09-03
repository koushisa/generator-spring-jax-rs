package <%= basePackage %>.dao;

import java.math.BigInteger;
import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.SelectOptions;

import <%= basePackage %>.model.entity.<%= resourceName %>;
import <%= basePackage %>.model.form.<%= resourceName %>SearchForm;

/**
 * <%= resourceNameKana %>テーブルのDAOです。
 *
 * @author yeoman
 *
 */
@Dao
@DaoConfig
public interface <%= resourceName %>Dao {

	/**
	 * <%= resourceNameKana %>の一覧を取得します。
	 *
	 * @param form
	 * @param options
	 * @return
	 */
	@Select
	public List<<%= resourceName %>> select<%= resourceName %>s(<%= resourceName %>SearchForm form, SelectOptions options);

	/**
	 * 主キーに紐づいた<%= resourceNameKana %>を取得します。
	 *
	 * @param <%= camelPrimaryKey %>
	 * @return
	 */
	@Select
	public <%= resourceName %> selectByPk(BigInteger <%= camelPrimaryKey %>);

	/**
	 * <%= resourceNameKana %>を登録します。
	 *
	 * @param entity
	 * @return
	 */
	@Insert(sqlFile = true)
	public int insert(<%= resourceName %> entity);

	/**
	 * <%= resourceNameKana %>を更新します。
	 *
	 * @param entity
	 * @return
	 */
	@Update(sqlFile = true)
	public int update(<%= resourceName %> entity);

	/**
	 * 主キーに紐づいた<%= resourceNameKana %>を削除します。
	 *
	 * @param <%= camelPrimaryKey %>
	 * @return
	 */
	@Delete(sqlFile = true)
	public int delete(BigInteger <%= camelPrimaryKey %>);

}
