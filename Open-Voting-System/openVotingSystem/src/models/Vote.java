package models;

public class Vote  {


    private boolean voted;
    private String rankedVote1, rankedVote2, rankedVote3;

   
    
    public Vote( boolean voted, String rankedVote1, String rankedVote2, String rankedVote3) {

        this.voted = voted;
        this.rankedVote1 = rankedVote1;
        this.rankedVote2 = rankedVote2;
        this.rankedVote3 = rankedVote3;
    }

    public String getRankedVote1() {
        return rankedVote1;
    }

    public void setRankedVote1(String rankedVote1) {
        this.rankedVote1 = rankedVote1;
    }

    public String getRankedVote2() {
        return rankedVote2;
    }

    public void setRankedVote2(String rankedVote2) {
        this.rankedVote2 = rankedVote2;
    }

    public String getRankedVote3() {
        return rankedVote3;
    }

    public void setRankedVote3(String rankedVote3) {
        this.rankedVote3 = rankedVote3;
    }


    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    @Override
    public String toString() {
       
        return super.toString();
    }
}
