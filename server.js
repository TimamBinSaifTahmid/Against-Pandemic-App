const express = require("express");
const bodyPerser = require("body-parser");
const bcrypt = require("bcrypt-nodejs");
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
postgres
  .select("*")
  .from("users")
  .then((data) => {
    console.log(data);
  });
app.use(bodyPerser.json());
app.get("/", (req, res) => {
  res.send("This is woring");
});
app.post("/signin", (req, res) => {
  postgres
    .select("email", "hash")
    .from("login")
    .where("email", "=", req.body.email)
    .then((data) => {
      const isValid = bcrypt.compareSync(req.body.password, data[0].hash);

      if (isValid) {
        postgres
          .select("*")
          .from("users")
          .where("email", "=", req.body.email)
          .then((user) => {
            console.log(user[0].email);
            res.status(200).json(user[0].email);
          })
          .catch((err) => res.status(400).json("cant find user"));
      } else {
        res.status(400).json("wrong credential");
      }
    })
    .catch((err) => res.status(400).json("wrong credential"));
});
app.post("/register", (req, res) => {
  const {
    nid,
    name,
    email,
    password,
    location,
    contact_info,
    financial_condition,
  } = req.body;
  const hash = bcrypt.hashSync(password);
  postgres
    .transaction((trx) => {
      trx
        .insert({
          hash: hash,
          email: email,
        })
        .into("login")
        .returning("email")
        .then((loginEmail) => {
          return trx("users").insert({
            nid: nid,
            name: name,
            email: loginEmail[0],
            location: location,
            contact_info: contact_info,
            financial_condition: financial_condition,
          });
        })
        .then(trx.commit)
        .catch(trx.rollback);
    })
    .catch((err) => res.status(400).json("unable to register"));
  res.send("sign in ");
});
app.listen(PORT, () => {
  console.log(`server is running on :${PORT}`);
});
// bcrypt.hash("bacon", null, null, function(err, hash) {
//   // Store hash in your password DB.
// });

// // Load hash from your password DB.
// bcrypt.compare("bacon", hash, function(err, res) {
//   // res == true
// });
// bcrypt.compare("veggies", hash, function(err, res) {
//   // res = false
// });
