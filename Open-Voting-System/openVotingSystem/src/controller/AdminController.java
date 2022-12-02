package controller;

import models.Admin;
import views.AdminView;

public class AdminController {
    private Admin model;
    private AdminView view;

    public AdminController(Admin model, AdminView view){
        this.model=model;
        this.view=view;
    }

    public void setAdminNationalId(String nationalId){
        model.setNationalId(nationalId);
    }

    public String getNantionalId(){
        return model.getNationalId();
    }

    public void setFullname(String fullname){
        model.setFullname(fullname);
    }

    public String getFullname(){
        return model.getFullname();
    }

    public void setEmail(String email){
        model.setEmail(email);
    }

    public String email(){
        return model.getEmail();
    }

    public void setPhoneNo(String phoneNo){
        model.setPhoneNo(phoneNo);
    }

    public String getPhoneNo(){
        return model.getPhoneNo();
    }

    public void setPassword(String password){
        model.setPassword(password);
    }

    public String getPassword(){
        return model.getPassword();
    }

    public void updateView(){
        view=new AdminView();
        view.displayOneAdmin(model);
    }

}
