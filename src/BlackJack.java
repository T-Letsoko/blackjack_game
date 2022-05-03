import java.util.*;

public class BlackJack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game;

        System.out.println("\nDo you want play BLACK JACK");
        String userInput = scanner.nextLine();

        while (userInput.equalsIgnoreCase("y")){
            game = new Game();
            game.playGame();
            System.out.println("Do you want play BLACK JACK\n yes = y or n = no");
            userInput = scanner.nextLine();
        }
    }
}
