const express = require("express");
const router = express.Router();
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
  getQrCodePoor,
  isValidQrCode,
  isAskHelp,
  getProfile,
  updateProfile,
  //sheetUrl,
  getSheetData,
} = require("../controller/userController.controller");
router.get("/", (req, res) => {
  res.send("This is woring");
});
router.post("/signin", postLogin);
router.post("/register", postRegister);

router.post("/emailVerification", isVerified);

router.post("/helpForm", postHelpRequest);

router.get("/helpSeekerList", getHelpRequestList);
router.post("/helpSeekerDetails", getHelpRequesterList);
router.post("/helpSeekerProfile", getHelpRequesterProfile);
router.post("/coronaResultUpdate", postCoronaResult);
router.post("/coronaResultshow", getCoronaResult);
router.post("/medicalRepresentativeLogin", postMedicalRepresentativeLogin);
router.post("/googleSheetUpdate", postGoogleSheet);
router.get("/generateQrCode", getQrCodePoor);
router.post("/isValidQrCode", isValidQrCode);
router.get("/isAskHelp", isAskHelp);
router.get("/getProfile", getProfile);
router.post("/updateProfile", updateProfile);
module.exports = router;
