const Patient = require("../models/patient");
const { check, validationResult } = require('express-validator');
var jwt = require('jsonwebtoken');
var expressJwt = require('express-jwt');





exports.signup = (req, res) => {

    const error = validationResult(req);

    if(!error.isEmpty()){
        return res.status(422).json({
            error: error.array()[0].msg
        });
    };

    const patient = new Patient(req.body)
    patient.save((err, patient) => {
            if(err || !patient){
                return res.status(400).json({
                    err: "NOT able to save patient in DB"
                });
            }
            res.json({
                f_name: patient.f_name,
                l_name: patient.l_name,
                email: patient.email,
                id: patient._id
            });
        });
};





exports.signin = (req, res) => {
    const error = validationResult(req);
    const { email, password } = req.body;

    if(!error.isEmpty()){
        return res.status(422).json({
            error: error.array()[0].msg
        });
    };

    Patient.findOne({email}, (err, patient) =>{
        if(err || !patient){
            return res.status(400).json({
                error: "Patient email does not exists"
            })
        }

        if(!patient.authenticate(password)){
            return res.status(401).json({
                error: "Email and password do not match"
            })
        }

        //Create token
        const token = jwt.sign({_id: patient._id}, process.env.SECRET_Key);
        //put token in cookie
        res.cookie("token", token, {expire: new Date() + 9999});

        //send response on frontend
        const {_id, f_name, l_name, email, role} = patient;
        return res.json({ token, patient: {_id, f_name, l_name, email, role}})
    })

}




exports.signout = (req, res) => {
    res.clearCookie("token");
    res.json({
        message: "User signout successfully"
    });
};