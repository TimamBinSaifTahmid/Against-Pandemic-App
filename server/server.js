const express = require("express");
const bodyPerser = require("body-parser");
const bcrypt = require("bcrypt-nodejs");
const nodemailer = require("nodemailer");
const sendMail = require("./controller/userController.controller");
const {
  user,
  userCreation,
  getVerificationCode,
  setVerificationCode,
} = require("./model/userModel.model");
const { google } = require("googleapis");
const random = require("random");
const app = express();
const PORT = 3000;
const verificationCode = random.int((min = 1111111), (max = 9999999));
console.log(verificationCode);
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
      console.log(req.body.password);
      if (isValid) {
        postgres
          .select("*")
          .from("users")
          .where("email", "=", req.body.email)
          .then((user) => {
            if (user.verified) {
              userCreation(
                user[0].nid,
                user[0].name,
                user[0].email,
                user[0].password,
                user[0].location,
                user[0].contact_info,
                user[0].financial_condition
              );
              console.log(user[0].email);
              res.status(200).json(user[0]);
            } else res.status(405).json("email not varified");
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
          nid: nid,
          email: email,
          hash: hash,
          verificationcode: verificationCode,
        })
        .into("login")
        .returning("email")
        .then((loginEmail) => {
          sendMail(loginEmail[0], verificationCode)
            .then((result) => {
              console.log("EMAIL SENT ...", result);
            })
            .catch((error) => {
              console.log(error);
            });
            console.log( nid,
              name,
              email,
              password,
              location,
              contact_info,
              financial_condition)
          userCreation(
            nid,
            name,
            email,
            password,
            location,
            contact_info,
            financial_condition
          );
          setVerificationCode(verificationCode);
          console.log("vrify kaj orse");
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

app.post("/emailVerification", (req, res) => {
  const verficationCode = req.body.verificationCode;
  console.log(user,getVerificationCode(),verficationCode);
  if (verficationCode == getVerificationCode()) {
    res.status(200).send("success");
  } else res.status(400).send("failed");
});

app.post("/helpForm", (req, res) => {
  const reason = req.body.reason;
  const condition = req.body.condition;
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
