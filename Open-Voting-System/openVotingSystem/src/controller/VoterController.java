package controller;

import models.Vote;
import models.Voter;
import views.VoterView;

public class VoterController {
        private Voter model;
        private VoterView view;
    
        public VoterController(Voter model, VoterView view){
            this.model=model;
            this.view=view;
        }
    
        public void setVoterNationalId(String nationalId){
            model.setNationalID(nationalId);
        }
    
        public String getNantionalId(){
            return model.getNationalID();
        }
    
        public void setFullname(String fullname){
            model.setFullName(fullname);
        }
    
        public String getFullname(){
            return model.getFullName();
        }
    
        public void setEmail(String email){
            model.setEmail(email);
        }
    
        public String getEmail(){
            return model.getEmail();
        }
        public void setZone(String zone){
            model.setZone(zone);
        }
        public String getZone(){
            return model.getZone();
        }
        public void setAddress(String address){
            model.setAddress(address);
        }
        public String getAddress(){
            return model.getAddress();
        }
        public void setMobileNumber(String mobileNumber){
            model.setMobileNumber(mobileNumber);
        }
        public String getMobileNumber(){
            return model.getMobileNumber();
        }
    
        public void setPassword(String password){
            model.setPassword(password);
        }
    
        public String getPassword(){
            return model.getPassword();
        }

        public void setVote(Vote vote){
            model.setVote(vote);
        }

        public Vote getVote(){
            return model.getVote();
        }
    
        public void updateView(){
            view =new VoterView();
            view.displayOneVoter(model);
        }
    
}
