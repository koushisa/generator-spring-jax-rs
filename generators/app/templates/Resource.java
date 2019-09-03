package <%= basePackage %>.resource;

import java.math.BigInteger;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import <%= basePackage %>.model.dto.<%= snakeResourceName %>.<%= resourceName %>SearchResponse;
import <%= basePackage %>.model.entity.<%= resourceName %>;
import <%= basePackage %>.model.form.PostPut<%= resourceName %>Form;
import <%= basePackage %>.model.form.<%= resourceName %>SearchForm;
import <%= basePackage %>.service.<%= resourceName %>Service;

/**
 * <%= resourceNameKana %>のリソースです。
 *
 * @author yeoman
 */
@Path("v1/<%= endPoint %>")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
public class <%= resourceName %>Resource {
	/**
	 * ロガー
	 */
	private static final Logger logger = LoggerFactory.getLogger(<%= resourceName %>Resource.class);

	@Autowired
	private <%= resourceName %>Service <%= camelResourceName %>Service;

	/**
	 * 一覧取得API
	 *
	 * @return
	 */
	@GET
	public Response find<%= resourceName %>s(<%= resourceName %>SearchForm form) {
		logger.info("A-XX-XXX APIの処理を開始します。");
		<%= resourceName %>SearchResponse response = <%= camelResourceName %>Service.find<%= resourceName %>s(form);
		logger.info("A-XX-XXX APIの処理を終了します。");
		return Response.ok(response).build();
	}

	/**
	 * 詳細取得API
	 *
	 * @param <%= camelPrimaryKey %>
	 * @return
	 */
	@GET
	@Path("{<%= camelPrimaryKey %>}")
	public Response find<%= resourceName %>ByPk(@PathParam("<%= camelPrimaryKey %>") BigInteger <%= camelPrimaryKey %>) {
		logger.info("A-XX-XXX APIの処理を開始します。");
		<%= resourceName %> response = <%= camelResourceName %>Service.find<%= resourceName %>ByPk(<%= camelPrimaryKey %>);
		logger.info("A-XX-XXX APIの処理を終了します。");
		return Response.ok(response).build();
	}

	/**
	 * 追加API
	 *
	 * @param form
	 * @return
	 */
	@POST
	public Response add(@Valid PostPut<%= resourceName %>Form form) {
		logger.info("A-XX-XXX APIの処理を開始します。");
		<%= camelResourceName %>Service.add<%= resourceName %>(form);
		logger.info("A-XX-XXX APIの処理を終了します。");
		return Response.ok().build();
	}

	/**
	 * 更新API
	 *
	 * @param <%= camelPrimaryKey %>
	 * @param form
	 * @return
	 */
	@Path("{<%= camelPrimaryKey %>}")
	@PUT
	public Response update(@PathParam("<%= camelPrimaryKey %>") BigInteger <%= camelPrimaryKey %>, @Valid PostPut<%= resourceName %>Form form) {
		logger.info("A-XX-XXX APIの処理を開始します。");
		<%= camelResourceName %>Service.update<%= resourceName %>(<%= camelPrimaryKey %>, form);
		logger.info("A-XX-XXX APIの処理を終了します。");
		return Response.ok().build();
	}

	/**
	 * 削除API
	 *
	 * @param <%= camelPrimaryKey %>
	 * @return
	 */
	@Path("{<%= camelPrimaryKey %>}")
	@DELETE
	public Response delete(@PathParam("<%= camelPrimaryKey %>") BigInteger <%= camelPrimaryKey %>) {
		logger.info("A-XX-XXX APIの処理を開始します。");
		<%= camelResourceName %>Service.delete<%= resourceName %>(<%= camelPrimaryKey %>);
		logger.info("A-XX-XXX APIの処理を終了します。");
		return Response.ok().build();
	}

}
