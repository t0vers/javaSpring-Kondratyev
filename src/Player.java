import java.util.Random;

enum VARIANTS {
    ROCK,
    PAPER,
    SCISSORS
}
public class Player {
    private VARIANTS variant;
    private String name;

    public Player() {
        this.variant = getRandomVariant();
        this.name = "Bot";
    }
    public Player(VARIANTS variant, String name) {
        this.variant = variant;
        this.name = name;
    }

    private VARIANTS getRandomVariant() {
        Random random = new Random();
        return VARIANTS.values()[random.nextInt(VARIANTS.values().length)];
    }

    public VARIANTS getVariant() {
        return variant;
    }

    public String getName() {
        return name;
    }
    public String whoWins(Player player1, Player player2) {
        if (player1.getVariant() == player2.getVariant()) {
            return "Ничья";
        } else {
            switch (player1.getVariant()) {
                case ROCK:
                    if (player2.getVariant() == VARIANTS.SCISSORS){
                        return player1.getName();
                    }
                    break;
                case PAPER:
                    if (player2.getVariant() == VARIANTS.ROCK) {
                        return player1.getName();
                    }
                    break;
                case SCISSORS:
                    if (player2.getVariant() == VARIANTS.PAPER) {
                        return player1.getName();
                    }
                    break;
            }
            return player2.getName();
        }
    }
}
