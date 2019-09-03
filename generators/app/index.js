const Generator = require("yeoman-generator");
const yosay = require("yosay");
const chalk = require("chalk");

module.exports = class extends Generator {
  constructor(args, opts) {
    super(args, opts);

    this.log(yosay("Welcome to \nSpring With JAX-RS \nResource Generator!"));
  }

  async prompting() {
    this.answers = await this.prompt([{
        type: "input",
        name: "basePackage",
        message: "Tell me base package (e.g. example.api)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "not allowed empty";
          }
        }
      },
      {
        type: "input",
        name: "resourceName",
        message: "Tell me physical resource name in pascal case. (e.g JobSchedule)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "not allowed empty";
          }
        }
      },
      {
        type: "input",
        name: "resourceNameKana",
        message: "Tell me logical resource name. this is used for comments (e.g 作業予定 sorry for Japansese)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "not allowed empty";
          }
        }
      },
      {
        type: "input",
        name: "primaryKey",
        message: "Tell me primary key that supporting table in snake case (e.g. job_schedule_id)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "not allowed empty";
          }
        }
      },
      {
        type: "input",
        name: "endPoint",
        message: "Tell me API end point (e.g. job-schedules)",
        validate(input) {
          if (input.length > 0) {
            return true;
          } else {
            return "not allowed empty";
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
      this.destinationPath(`dest/${resourceName}/dao/${resourceName}Dao.java`), {
        ...params
      }
    );

    //sql
    this.fs.copyTpl(
      this.templatePath("select.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/select${resourceName}s.sql`
      ), {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("selectByPk.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/selectByPk.sql`
      ), {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("insert.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/insert.sql`
      ), {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("update.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/update.sql`
      ), {
        ...params
      }
    );
    this.fs.copyTpl(
      this.templatePath("delete.sql"),
      this.destinationPath(
        `dest/${resourceName}/sql/${resourceName}Dao/delete.sql`
      ), {
        ...params
      }
    );

    //Resource
    this.fs.copyTpl(
      this.templatePath("Resource.java"),
      this.destinationPath(
        `dest/${resourceName}/resource/${resourceName}Resource.java`
      ), {
        ...params
      }
    );

    //Service
    this.fs.copyTpl(
      this.templatePath("Service.java"),
      this.destinationPath(
        `dest/${resourceName}/service/${resourceName}Service.java`
      ), {
        ...params
      }
    );

    //Entity
    this.fs.copyTpl(
      this.templatePath("Entity.java"),
      this.destinationPath(
        `dest/${resourceName}/model/entity/${resourceName}.java`
      ), {
        ...params
      }
    );

    //PostPutForm
    this.fs.copyTpl(
      this.templatePath("PostPutForm.java"),
      this.destinationPath(
        `dest/${resourceName}/model/form/PostPut${resourceName}Form.java`
      ), {
        ...params
      }
    );

    //SearchForm
    this.fs.copyTpl(
      this.templatePath("SearchForm.java"),
      this.destinationPath(
        `dest/${resourceName}/model/form/${resourceName}SearchForm.java`
      ), {
        ...params
      }
    );

    //SearchResponse
    this.fs.copyTpl(
      this.templatePath("SearchResponse.java"),
      this.destinationPath(
        `dest/${resourceName}/model/dto/${snakeResourceName}/${resourceName}SearchResponse.java`
      ), {
        ...params
      }
    );

    //package-info
    this.fs.copyTpl(
      this.templatePath("package-info.java"),
      this.destinationPath(
        `dest/${resourceName}/model/dto/${snakeResourceName}/package-info.java`
      ), {
        ...params
      }
    );

    //AbstractSearchForm
    this.fs.copyTpl(
      this.templatePath("AbstractSearchForm.java"),
      this.destinationPath(
        `dest/${resourceName}/model/form/AbstractSearchForm.java`
      ), {
        ...params
      }
    );

    //PagerUtil
    this.fs.copyTpl(
      this.templatePath("PagerUtil.java"),
      this.destinationPath(`dest/${resourceName}/util/PagerUtil.java`), {
        ...params
      }
    );
  }

  install() {
    //後書き
    this.log(yosay("Please read\nfollowing\ncomments."));

    this.log("↓created successfully↓\n");
    const filePath = __dirname + "/dest/" + this.answers.resourceName;
    this.log(filePath + "\n");

    const caution = [
      "************************************************************************************\n",
      "Please replace the code on TODO comments appropriately.\n",
      "************************************************************************************\n"
    ];
    this.log(chalk.bold(caution.join("")));
  }
};

/**
 * convert camel case
 * @param string
 * @return string
 */
function camelCase(str) {
  str = str.charAt(0).toLowerCase() + str.slice(1);
  return str.replace(/[-_](.)/g, function (match, group1) {
    return group1.toUpperCase();
  });
}

/**
 * convert snake case
 * @param string
 * @return string
 */
function snakeCase(str) {
  var camel = camelCase(str);
  return camel.replace(/[A-Z]/g, function (s) {
    return "_" + s.charAt(0).toLowerCase();
  });
}
