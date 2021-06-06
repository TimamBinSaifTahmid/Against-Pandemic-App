const express = require("express");
const app = express();
const PORT = 3000;
const knex = require("knex");
const postgres = knex({
  client: "pg",
  connection: {
    host: "127.0.0.1",
    user: "postgres",
    password: "tahmid",
    database: "AgainstPandemic",
  },
});
postgres.select("*").from("Users");
app.get("/", (req, res) => {
  res.send("This is woring");
});
app.post("/signin", (req, res) => {
  res.send("sign in ");
});
app.post("/register", (req, res) => {
  const { nid, name, email, location, financial_condition } = req.body;
  postgres("Users").insert({
    nid: nid,
    name: name,
    email: email,
    location: location,
    financial_condition: financial_condition,
  });
  res.send("sign in ");
});
app.listen(PORT, () => {
  console.log(`server is running on :${PORT}`);
});
