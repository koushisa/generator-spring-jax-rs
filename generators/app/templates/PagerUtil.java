package <%= basePackage %>.util;

import org.seasar.doma.jdbc.SelectOptions;

import <%= basePackage %>.model.form.AbstractSearchForm;

/**
 * APIのページング処理に関する共通処理です。
 *
 * @author yeoman
 *
 */
public class PagerUtil {
	private PagerUtil() {

	}

	/**
	 * 検索API用のSelectOptionsを生成します。
	 *
	 * @param form
	 * @return
	 */
	public static SelectOptions newSelectOptions(AbstractSearchForm form) {

		return SelectOptions.get().offset(form.getOffset().intValue()).limit(form.getLimit().intValue()).count();
	}
}
