package <%= basePackage %>.service;

import java.math.BigInteger;
import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import <%= basePackage %>.dao.<%= resourceName %>Dao;
import <%= basePackage %>.exception.ApplicationException;
import <%= basePackage %>.model.dto.<%= snakeResourceName %>.<%= resourceName %>SearchResponse;
import <%= basePackage %>.model.entity.<%= resourceName %>;
import <%= basePackage %>.model.form.PostPut<%= resourceName %>Form;
import <%= basePackage %>.model.form.<%= resourceName %>SearchForm;
import <%= basePackage %>.util.PagerUtil;

/**
 * <%= resourceNameKana %>に関するサービスです。
 *
 * @author yeoman
 *
 */
@Service
@Transactional
public class <%= resourceName %>Service {
	/**
	 * ロガー
	 */
	private static final Logger logger = LoggerFactory.getLogger(<%= resourceName %>Service.class);

	@Autowired
	private <%= resourceName %>Dao <%= camelResourceName %>Dao;

	/**
	 * <%= resourceNameKana %>の一覧を取得します。
	 *
	 */
	public <%= resourceName %>SearchResponse find<%= resourceName %>s(<%= resourceName %>SearchForm form) {
		// 検索
		SelectOptions options = PagerUtil.newSelectOptions(form);
		List<<%= resourceName %>> <%= camelResourceName %>s = <%= camelResourceName %>Dao.select<%= resourceName %>s(form, options);

		// レスポンスの生成
		<%= resourceName %>SearchResponse response = new <%= resourceName %>SearchResponse();
		response.setCount(options.getCount());
		response.set<%= resourceName %>s(<%= camelResourceName %>s);

		return response;
	}

	/**
	 * <%= resourceNameKana %>詳細を取得します。
	 *
	 * @param <%= camelPrimaryKey %>
	 * @return
	 */
	public <%= resourceName %> find<%= resourceName %>ByPk(BigInteger <%= camelPrimaryKey %>) {
		<%= resourceName %> response = <%= camelResourceName %>Dao.selectByPk(<%= camelPrimaryKey %>);
		if (response == null) {
			String message = String.format("<%= camelPrimaryKey %>[%s]に該当する<%= resourceNameKana %>が存在しません", <%= camelPrimaryKey %>);
			throw ApplicationException.create("error.A-XX-XXX-XXX").serverMessage(message).build();
		}

		return response;
	}

	/**
	 * <%= resourceNameKana %>を登録します。
	 *
	 * @param form
	 */
	public void add<%= resourceName %>(PostPut<%= resourceName %>Form form) {
		// TODO formからエンティティを設定
		<%= resourceName %> <%= camelResourceName %> = new <%= resourceName %>();

		// 登録
		<%= camelResourceName %>Dao.insert(<%= camelResourceName %>);
	}

	/**
	 * <%= resourceNameKana %>を更新します。
	 *
	 * @param <%= camelPrimaryKey %>
	 * @param form
	 */
	public void update<%= resourceName %>(BigInteger <%= camelPrimaryKey %>, PostPut<%= resourceName %>Form form) {
		// 存在チェック
		<%= resourceName %> <%= camelResourceName %> = <%= camelResourceName %>Dao.selectByPk(<%= camelPrimaryKey %>);
		if (<%= camelResourceName %> == null) {
			String message = String.format("<%= camelPrimaryKey %>[%s]に該当する<%= resourceNameKana %>が存在しません", <%= camelPrimaryKey %>);
			throw ApplicationException.create("error.A-XX-XXX-XXX").serverMessage(message).build();
		}

		// TODO formからエンティティを設定

		// 更新
		<%= camelResourceName %>Dao.update(<%= camelResourceName %>);
	}

	/**
	 * <%= resourceNameKana %>を削除します。
	 *
	 * @param <%= camelPrimaryKey %>
	 */
	public void delete<%= resourceName %>(BigInteger <%= camelPrimaryKey %>) {
		int count = <%= camelResourceName %>Dao.delete(<%= camelPrimaryKey %>);
		if (count == 0) {
			String message = String.format("<%= camelPrimaryKey %>[%s]に該当する<%= resourceNameKana %>の削除に失敗しました。", <%= camelPrimaryKey %>);
			throw ApplicationException.create("error.A-XX-XXX-XXX").serverMessage(message).build();
		}

		// 削除
		<%= camelResourceName %>Dao.delete(<%= camelPrimaryKey %>);
	}

}
