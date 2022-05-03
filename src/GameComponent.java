import java.util.ArrayList;

public interface GameComponent {
     void compareScore(int playerScore, int dealerScore);
     String dealCard();
     int calculateScore(ArrayList<String> cards);
     void playGame();

     void showCards(ArrayList<String> cards);
}
