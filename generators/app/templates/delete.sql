DELETE
FROM
    <%= snakeResourceName %>
WHERE enabled = 1
AND  <%= snakePrimaryKey %> = /*<%= camelPrimaryKey %>*/'1'
