package views;

import java.util.ArrayList;

import models.Admin;

public class AdminView {
    public static void displayAdmin(ArrayList<Admin> adminList) {
        for(Admin admin:adminList){
            String ID= admin.getNationalId();
            String Name= admin.getFullname();
            String Email= admin.getEmail();
            String MobileNumber= admin.getPhoneNo();
            String Password= admin.getPassword();

        System.out.println("ID: "+ID+"\nName: "
        +Name+"\nEmail: "+Email+"\nMobile Number: "
        +MobileNumber+"\nPassword: "+Password+"\n");
    } 
 }
    public void displayOneAdmin(Admin admin) {
            String ID= admin.getNationalId();
            String Name= admin.getFullname();
            String Email= admin.getEmail();
            String MobileNumber= admin.getPhoneNo();
            String Password= admin.getPassword();

        System.out.println("ID: "+ID+"\nName: "
        +Name+"\nEmail: "+Email+"\nMobile Number: "
        +MobileNumber+"\nPassword: "+Password+"\n");
 }
}

