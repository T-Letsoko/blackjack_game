import java.util.*;

public class Game implements GameComponent {

    private final String[] cards;

    public Game(){
        this.cards = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    }

    /**
     * This method will pick a random card by generating a random index
     *
     * @return card
     */
    @Override
    public String dealCard(){
        Random random = new Random();
        int cardIndex = random.nextInt(cards.length);
        return cards[cardIndex];
    }

    /**
     * This method will compare player score and computer score, then it will
     * display message according accumulated score
     *
     * @param playerScore
     * @param computerScore
     */
    @Override
    public void compareScore(int playerScore, int computerScore){
        if (playerScore > 21 && computerScore > 21)
            System.out.println("You both went over! No Winner");
        else if(computerScore > 21 && playerScore <= 21)
            System.out.println("You Win. Dealer lost!");
        if(playerScore > computerScore && playerScore <= 21)
            System.out.println("You Win!");
        else if(computerScore > playerScore && computerScore <=21)
            System.out.println("Bust. Dealer Wins!");
        else if (playerScore == 21)
            System.out.println("Black Jack. You Win!");
        else if (computerScore == 21)
            System.out.println("Black Jack. Dealer Wins!");
        else if (playerScore == computerScore && computerScore <= 21 && playerScore <=21)
            System.out.println("It's a draw!");
        else if (playerScore > 21)
            System.out.println("You went over. Bust. Dealer Wins!");

    }

    /**
     * This method will calculate both the player and computer's scores according to
     * collected cards.
     *
     * @param cards
     * @return sum
     */
    @Override
    public int calculateScore(ArrayList<String> cards){
        int sum = 0;
        Map<String, Integer> cardNumbers = new HashMap<>()
        {{
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("10", 10);
            put("J", 10);
            put("Q", 10);
            put("K", 10);
            put("A", 11);
        }};

        /**
         * Iterate through the cards and get card value according to card face value.
         */
        for(String card: cards){
            sum += cardNumbers.get(card);
        }
        return sum;
    }

    /**
     * This method will display a list of card on a single line
     *
     * @param cards
     */
    @Override
    public void showCards(ArrayList<String> cards){
        for(String card: cards){
            System.out.print(card + " ");
        }
    }

    /**
     * This method will run the game by picking cards for both player and computer, calculate score
     * and determine the winner.
     */
    @Override
    public void playGame(){
        ArrayList<String> playerCards = new ArrayList<>(); // store player's cards
        ArrayList<String> computerCards = new ArrayList<>(); // store computer's cards
        boolean isGameOn = true;
        int playerScore = 0;
        int computerScore = 0;
        Scanner scanner = new Scanner(System.in);

        // Pick two cards each for player and computer
        for(int i = 0; i<2; i++){
            playerCards.add(dealCard());
            computerCards.add(dealCard());
        }

        while (isGameOn){
            // Calculate score
            playerScore = calculateScore(playerCards);
            computerScore = calculateScore(computerCards);

            // Display player's cards and score
            System.out.print("\nYour cards: ");
            showCards(playerCards);
            System.out.println("\n Your current score: " + playerScore);
            System.out.println("\nComputer first card: " + computerCards.get(0));


            /**
             * Check if player or computer has a score of 21 or has gone over 21 then
             * stop the game, else ask player to pick a card.
             */
            if(playerScore == 21 || computerScore == 21 || playerScore > 21){
                isGameOn = false;
            } else {
                System.out.println("Do you want to pick a card: yes = 'y' or no = 'n'");
                String userInput = scanner.nextLine();
                if(userInput.equalsIgnoreCase("y")){
                    playerCards.add(dealCard());
                } else {
                    isGameOn = false;
                }
            }
        }

        // Computer must pick cards until score is greater than 17
        while (computerScore < 17){
            computerCards.add(dealCard());
            computerScore = calculateScore(computerCards);
        }

        System.out.print("\nYour cards: ");
        showCards(playerCards);
        System.out.println("\n Your final score: " + playerScore);
        System.out.print("\nComputer's final cards: ");
        showCards(computerCards);
        System.out.println("\n Computer's final score: " + computerScore);

        // Compare scores and display correct message
        compareScore(playerScore, computerScore);

    }
}
