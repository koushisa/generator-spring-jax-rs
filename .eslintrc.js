module.exports = {
  root: true,
  env: {
    node: true,
    es6: true
  },
  extends: ["plugin:prettier/recommended"],
  rules: {
    "prettier/prettier": "error",
    camelcase: [
      "error",
      {
        properties: "never"
      }
    ],
    curly: "error",
    "no-cond-assign": "error",
    "no-lonely-if": "error"
  },
  parser: "babel-eslint",
  parserOptions: {
     "sourceType": "module",
 }
};
