const express = require("express");
const bodyPerser = require("body-parser");

const {
    postRegister,
    postLogin,
    isVerified,
    postHelpRequest,
    getHelpRequestList,
    getHelpRequesterList,
    getHelpRequesterProfile,
    postCoronaResult,
    getCoronaResult,
    postMedicalRepresentativeLogin,
    postGoogleSheet,
    //sheetUrl,
    getSheetData,
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

app.get("/helpSeekerList", getHelpRequestList);
app.post("/helpSeekerDetails", getHelpRequesterList);
app.post("/helpSeekerProfile", getHelpRequesterProfile);
app.post("/coronaResultUpdate", postCoronaResult);
app.post("/coronaResultshow", getCoronaResult);
app.post("/medicalRepresentativeLogin", postMedicalRepresentativeLogin);
app.post("/googleSheetUpdate",postGoogleSheet);
//app.get("https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1wyWse6RwXHx8L8xB4yfC_HcVADCtEivhIlx7l30kX5k&sheet=Sheet1", getSheetData);
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
