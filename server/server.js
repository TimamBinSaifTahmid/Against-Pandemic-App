const express = require("express");
const bodyPerser = require("body-parser");

const {
  postRegister,
  postLogin,
  isVerified,
  postHelpRequest,
} = require("./controller/userController.controller");
let {
  user,
  userCreation,
  getVerificationCode,
  setVerificationCode,
} = require("./model/userModel.model");

const app = express();
const PORT = 3000;

app.use(bodyPerser.json());
app.get("/", (req, res) => {
  res.send("This is woring");
});
app.post("/signin", postLogin);
app.post("/register", postRegister);

app.post("/emailVerification", isVerified);

app.post("/helpForm", postHelpRequest);

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
