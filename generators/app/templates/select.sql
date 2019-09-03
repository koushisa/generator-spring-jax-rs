SELECT
    * -- TODO カラムを個別に指定する
FROM
    <%= snakeResourceName %>
WHERE enabled = 1
AND <%= snakePrimaryKey %> <> /*form.serialVersionUID*/'pk'
;
