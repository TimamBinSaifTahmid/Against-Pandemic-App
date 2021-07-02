const express = require("express");
const bodyPerser = require("body-parser");
const bcrypt = require("bcrypt-nodejs");
const nodemailer = require("nodemailer");
const { google } = require("googleapis");
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
      console.log(req.body.password);
      if (isValid) {
        postgres
          .select("*")
          .from("users")
          .where("email", "=", req.body.email)
          .then((user) => {
            console.log(user[0].email);
            res.status(200).json(user[0]);
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

const CLIENT_ID =
  "358902421082-ce0l9jgsqadt2285f6u00gu4k8l1cp9q.apps.googleusercontent.com";
const CLIENT_SECRET = "RznHbAnsFyvAIpipn17SIVwM";
const REDIRECT_URI = "https://developers.google.com/oauthplayground";
const REFRESH_TOKEN =
  "1//0469Vb33R7EzQCgYIARAAGAQSNwF-L9Ir1rqTpxqGesmkHnrsB2dJ7iKYKkaBELI4jRKOycX3w2FrKN2ZxS5-BCmRVGVl3XK2fHY";

const oAuth2Client = new google.auth.OAuth2(
  CLIENT_ID,
  CLIENT_SECRET,
  REDIRECT_URI
);
oAuth2Client.setCredentials({ refresh_token: REFRESH_TOKEN });

async function sendMail() {
  try {
    const accessToken = await oAuth2Client.getAccessToken();
    const transport = nodemailer.createTransport({
      service: "gmail",
      auth: {
        type: "OAuth2",
        user: "timambinsaif462@gmail.com",
        clientId: CLIENT_ID,
        clientSecret: CLIENT_SECRET,
        refreshToken: REFRESH_TOKEN,
        accessToken: accessToken,
      },
      tls: {
        rejectUnauthorized: false,
      },
    });

    const mailOptions = {
      from: "timambinsaif462@gmail.com",
      to: "timam@iut-dhaka.edu",
      subject: "Verification Code",
      text: "",
      html: " <b>Hi!<br></b> <p> You are registering in Against Pandemic app.Your <b>verification code<b> is</p>:<h1> 922845.</h1> <br><p>Againstg Pandemic</p><br><p>This is an automated email. Please do not reply to this email</p>",
    };
    const result = await transport.sendMail(mailOptions);
    return result;
  } catch (error) {
    return error;
  }
}
sendMail()
  .then((result) => {
    console.log("EMAIL SENT ...", result);
  })
  .catch((error) => {
    console.log(error);
  });
