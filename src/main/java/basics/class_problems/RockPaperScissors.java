package basics.class_problems;

import java.util.Random;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();

        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};

        int wins = 0, losses = 0, draws = 0;
        int rounds = 5;

        String[] playerHistory = new String[rounds];
        String[] computerHistory = new String[rounds];
        String[] resultHistory = new String[rounds];

        for (int i = 0; i < rounds; i++) {
            String playerMove = playerMoves[i];
            String computerMove = moves[rand.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            playerHistory[i] = playerMove;
            computerHistory[i] = computerMove;
            resultHistory[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < rounds; i++) {
            System.out.println((i + 1) + "     | " + playerHistory[i] + "        | " + computerHistory[i] + "         | " + resultHistory[i]);
        }

        double winPercentage = ((double) wins / rounds) * 100;

        System.out.println();
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws + " | Win % = " + winPercentage + "%");
    }
}
