import java.util.Random;

public class RockPaperScissorsGame {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove == null || computerMove == null) {
            return "Invalid Move";
        }

        String player = normalizeMove(playerMove);
        String computer = normalizeMove(computerMove);
        if (player == null || computer == null) {
            return "Invalid Move";
        }
        if (player.equals(computer)) {
            return "Draw";
        }
        if (("Rock".equals(player) && "Scissors".equals(computer))
                || ("Paper".equals(player) && "Rock".equals(computer))
                || ("Scissors".equals(player) && "Paper".equals(computer))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    private static String normalizeMove(String move) {
        for (String validMove : MOVES) {
            if (validMove.equalsIgnoreCase(move.trim())) {
                return validMove;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        Random random = new Random();
        String[][] table = new String[playerMoves.length][4];
        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int round = 0; round < playerMoves.length; round++) {
            String computerMove = MOVES[random.nextInt(MOVES.length)];
            String result = playRound(playerMoves[round], computerMove);
            table[round][0] = String.valueOf(round + 1);
            table[round][1] = playerMoves[round];
            table[round][2] = computerMove;
            table[round][3] = result;

            if ("Player Wins".equals(result)) {
                wins++;
            } else if ("Computer Wins".equals(result)) {
                losses++;
            } else if ("Draw".equals(result)) {
                draws++;
            }
        }

        System.out.println("Round | Player Move | Computer Move | Result");
        for (String[] row : table) {
            System.out.printf("%s | %s | %s | %s%n", row[0], row[1], row[2], row[3]);
        }
        double winPercentage = wins * 100.0 / playerMoves.length;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage);
    }
}