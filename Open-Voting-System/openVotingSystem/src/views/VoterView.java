package views;
import java.util.ArrayList;

import models.Voter;
public class VoterView {
        public static void displayVoters(ArrayList<Voter> voterList){
            for(Voter voter:voterList){
                String id= voter.getNationalID();
                String name= voter.getFullName();
                String age= voter.getAge();
                String zone= voter.getZone();
                String address= voter.getAddress();
                String Email= voter.getEmail();
                String MobileNumber= voter.getMobileNumber();
                String Password= voter.getPassword();
                
                System.out.println(
                "ID: "+id+
                "\nName: " +name+
                "\nAge: " +age+
                "\nZone: " +zone+
                "\nAddress: " +address+
                "\nEmail: "+Email+
                "\nMobile Number: "+MobileNumber+
                "\nPassword: "+Password+
                "\n");
              }
        
    }

    public void displayOneVoter(Voter voter){
            String id= voter.getNationalID();
            String name= voter.getFullName();
            String age= voter.getAge();
            String zone= voter.getZone();
            String address= voter.getAddress();
            String Email= voter.getEmail();
            String MobileNumber= voter.getMobileNumber();
            String Password= voter.getPassword();
            
            System.out.println(
            "ID: "+id+
            "\nName: " +name+
            "\nAge: " +age+
            "\nZone: " +zone+
            "\nAddress: " +address+
            "\nEmail: "+Email+
            "\nMobile Number: "+MobileNumber+
            "\nPassword: "+Password+
            "\n");
    }
    
}
