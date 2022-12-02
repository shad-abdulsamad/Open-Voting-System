package views;

import java.util.ArrayList;

import models.Candidate;

public class CandidateView {
    public static void displayCandidates(ArrayList<Candidate> candidateList){
        for(Candidate candidate:candidateList){
            String ID= candidate.getNationalID();
            String Name= candidate.getName();
            String group= candidate.getGroup();
            String zone= candidate.getZone();
            String address= candidate.getAddress();
            String Email= candidate.getEmail();
            String MobileNumber= candidate.getMobileNumber();
            String Password= candidate.getPassword();
            
            System.out.println(
            "ID: "+ID+
            "\nName: " +Name+
            "\nGroup: " +group+
            "\nZone: " +zone+
            "\nAddress: " +address+
            "\nEmail: "+Email+
            "\nMobile Number: "+MobileNumber+
            "\nPassword: "+Password+
            "\n");
        }
    }

    public void displayOneCandidate(Candidate candidate){
        String ID= candidate.getNationalID();
        String Name= candidate.getName();
        String group= candidate.getGroup();
        String zone= candidate.getZone();
        String address= candidate.getAddress();
        String Email= candidate.getEmail();
        String MobileNumber= candidate.getMobileNumber();
        String Password= candidate.getPassword();
        
        System.out.println(
        "ID: "+ID+
        "\nName: " +Name+
        "\nGroup: " +group+
        "\nZone: " +zone+
        "\nAddress: " +address+
        "\nEmail: "+Email+
        "\nMobile Number: "+MobileNumber+
        "\nPassword: "+Password+
        "\n");
    }
}
