UPDATE
    <%= snakeResourceName %>
SET
    <%= snakePrimaryKey %> = /*entity.<%= camelPrimaryKey %>*/'1'
WHERE enabled = 1
AND  <%= snakePrimaryKey %> = /*entity.<%= camelPrimaryKey %>*/'1'
