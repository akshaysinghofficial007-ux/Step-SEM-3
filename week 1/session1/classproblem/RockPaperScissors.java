import java.util.*;
class RockPaperScissors {
    public static void main(String[] args) {
        String[] moves = {"Rock","Paper","Scissors"};
        String[] playerMoves = {"Rock","Paper","Scissors","Rock","Paper"};
        int wins=0,losses=0,draws=0;
        Random r=new Random();
        for(int i=0;i<playerMoves.length;i++){
            String comp=moves[r.nextInt(3)];
            String player=playerMoves[i];
            if(player.equals(comp)) draws++;
            else if((player.equals("Rock")&&comp.equals("Scissors"))||
                    (player.equals("Paper")&&comp.equals("Rock"))||
                    (player.equals("Scissors")&&comp.equals("Paper"))) wins++;
            else losses++;
            System.out.println("Round "+(i+1)+": Player="+player+" Computer="+comp);
        }
        System.out.println("Wins="+wins+" Losses="+losses+" Draws="+draws+" Win%="+(wins*100.0/playerMoves.length));
    }
}