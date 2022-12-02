package controller;

import models.Candidate;
import views.CandidateView;

public class CandidateController {
    private Candidate model;
    private CandidateView view;
    public CandidateController(Candidate model, CandidateView view){
        this.model=model;
        this.view=view;
    }
    public void setCandidateNationalID(String nationalID){
        model.setNationalID(nationalID);
    }
    public String getCandidateNationalID(){
        return model.getNationalID();
    }
    public void setCandidateName(String name){
        model.setName(name);
    }
    public String getCandidateName(){
        return model.getName();
    }
    public void setCandidateEmail(String email){
        model.setEmail(email);
    }
    public String getCandidateEmail(){
        return model.getEmail();
    }
    public void setCandidateMobileNumber(String mobileNumber){
        model.setMobileNumber(mobileNumber);
    }
    public String getCandidateMobileNumber(){
        return model.getMobileNumber();
    }
    public void setCandidatePassword(String password){
        model.setPassword(password);
    }
    public String getCandidatePassword(){
        return model.getPassword();
    }
    public void setCandidateAddress(String address){
        model.setAddress(address);
    }
    public String getCandidateAddress(){
        return model.getAddress();
    }
    public void setCandidateGroup(String group){
        model.setGroup(group);
    }
    public String getCandidateGroup(){
        return model.getGroup();
    }
    public void setCandidateZone(String zone){
        model.setZone(zone);
    }
    public String getCandidateZone(){
        return model.getZone();
    }
    public void setCandidateValid(boolean valid){
        model.setValid(valid);
    }
    public boolean isCandidateValid(){
        return model.isValid();
    }
    public void updateView(){
        view=new CandidateView();
        view.displayOneCandidate(model);
    }
}
