const Generator = require("yeoman-generator");
const yosay = require("yosay");
const chalk = require("chalk");

module.exports = class extends Generator {
  constructor(args, opts) {
    super(args, opts);

    this.log(yosay("Welcome to \nSpring With JAX-RS \nResource Generator!"));
  }

  async prompting() {
    this.answers = await this.prompt([
      {
        type: "input",
        name: "basePackage",
        message: "ベースパッケージを入力してください。 (例 jp.co.api)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "文字が入力されていません。";
          }
        }
      },
      {
        type: "input",
        name: "resourceName",
        message:
          "リソース名(物理名)をパスカルケースで入力してください。(例:JobSchedule)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "文字が入力されていません。";
          }
        }
      },
      {
        type: "input",
        name: "resourceNameKana",
        message: "リソース名(論理名)を入力してください。(例:作業予定)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "文字が入力されていません。";
          }
        }
      },
      {
        type: "input",
        name: "primaryKey",
        message:
          "対応するテーブルの主キーをスネークケースで入力してください。(例:job_schedule_id)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "文字が入力されていません。";
          }
        }
      },
      {
        type: "input",
        name: "endPoint",
        message:
          "APIのエンドポイント(URI)を入力してください。(例:job-schedules)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "文字が入力されていません。";
          }
        }
      }
    ]);
  }

  writing() {
    const basePackage = this.answers.basePackage;
    const resourceName = this.answers.resourceName;
    const camelResourceName = `${camelCase(this.answers.resourceName)}`;
    const snakeResourceName = `${snakeCase(this.answers.resourceName)}`;
    const resourceNameKana = this.answers.resourceNameKana;
    const camelPrimaryKey = `${camelCase(this.answers.primaryKey)}`;
    const snakePrimaryKey = `${snakeCase(this.answers.primaryKey)}`;
    const endPoint = this.answers.endPoint;

    const params = {
      basePackage,
      resourceName,
      camelResourceName,
      snakeResourceName,
      resourceNameKana,
      camelPrimaryKey,
      snakePrimaryKey,
      endPoint
    };

    //Dao
    this.fs.copyTpl(
      this.templatePath("Dao.java"),
      this.destinationPath(`dest/${resourceName}/dao/${resourceName}Dao.java`),
      {
        ...params
      }
    );

    //sql
    this.fs.copyTpl(
      this.templatePath("select.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/select${resourceName}s.sql`
      ),
      {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("selectByPk.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/selectByPk.sql`
      ),
      {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("insert.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/insert.sql`
      ),
      {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("update.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/update.sql`
      ),
      {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("delete.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/delete.sql`
      ),
      {
        ...params
      }
    );

    //Resource
    this.fs.copyTpl(
      this.templatePath("Resource.java"),
      this.destinationPath(
        `dest/${resourceName}/resource/${resourceName}Resource.java`
      ),
      {
        ...params
      }
    );

    //Service
    this.fs.copyTpl(
      this.templatePath("Service.java"),
      this.destinationPath(
        `dest/${resourceName}/service/${resourceName}Service.java`
      ),
      {
        ...params
      }
    );

    //Entity
    this.fs.copyTpl(
      this.templatePath("Entity.java"),
      this.destinationPath(
        `dest/${resourceName}/model/entity/${resourceName}.java`
      ),
      {
        ...params
      }
    );

    //PostPutForm
    this.fs.copyTpl(
      this.templatePath("PostPutForm.java"),
      this.destinationPath(
        `dest/${resourceName}/model/form/PostPut${resourceName}Form.java`
      ),
      {
        ...params
      }
    );

    //SearchForm
    this.fs.copyTpl(
      this.templatePath("SearchForm.java"),
      this.destinationPath(
        `dest/${resourceName}/model/form/${resourceName}SearchForm.java`
      ),
      {
        ...params
      }
    );

    //SearchResponse
    this.fs.copyTpl(
      this.templatePath("SearchResponse.java"),
      this.destinationPath(
        `dest/${resourceName}/model/dto/${snakeResourceName}/${resourceName}SearchResponse.java`
      ),
      {
        ...params
      }
    );

    //package-info
    this.fs.copyTpl(
      this.templatePath("package-info.java"),
      this.destinationPath(
        `dest/${resourceName}/model/dto/${snakeResourceName}/package-info.java`
      ),
      {
        ...params
      }
    );

    //AbstractSearchForm
    this.fs.copyTpl(
      this.templatePath("AbstractSearchForm.java"),
      this.destinationPath(
        `dest/${resourceName}/model/form/AbstractSearchForm.java`
      ),
      {
        ...params
      }
    );

    //PagerUtil
    this.fs.copyTpl(
      this.templatePath("PagerUtil.java"),
      this.destinationPath(`dest/${resourceName}/util/PagerUtil.java`),
      {
        ...params
      }
    );
  }

  install() {
    //後書き
    this.log(yosay("使用するにあたり\n以下の説明を\n読んでください。"));

    this.log("↓成果物を以下に生成しました。↓\n");
    const filePath = __dirname + "/dest/" + this.answers.resourceName;
    this.log(filePath + "\n");

    const caution = [
      "************************************************************************************\n",
      "コード中のTODOの箇所は適宜置き換えてください\n",
      "************************************************************************************\n"
    ];
    this.log(chalk.bold(caution.join("")));
  }
};

/**
 * キャメルケースへ変換
 * @param string
 * @return string
 */
function camelCase(str) {
  str = str.charAt(0).toLowerCase() + str.slice(1);
  return str.replace(/[-_](.)/g, function(match, group1) {
    return group1.toUpperCase();
  });
}

/**
 * スネークケースへ変換
 * @param string
 * @return string
 */
function snakeCase(str) {
  var camel = camelCase(str);
  return camel.replace(/[A-Z]/g, function(s) {
    return "_" + s.charAt(0).toLowerCase();
  });
}
