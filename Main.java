public class Main {
    public static void main(String[] args) {
        //дз1
        int sum = 0;
        for (int i = 0; i < 1001; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum = sum + i;
            }
        }
        System.out.print("Ответ на дз 1: ");
        System.out.println(sum);
        System.out.println("Ответ на дз 2: ");
        //дз 2
        int mn = 0;
        int[][] x = {{20, 34, 2}, {9, 12, 18}, {3, 4, 5}};
        for (int p = 0; p < x.length; p++) {
            for (int k = 0; k < x[p].length; k++) {
                if (mn == 0 || x[p][k] < mn) {
                    mn = x[p][k];
                }
            }
        }
        System.out.println(mn);

        Player bot = new Player();
        Player alex = new Player(VARIANTS.ROCK, "Alex");
        String result = bot.whoWins(bot, alex);
        if (!result.equals("Ничья")) {
            System.out.print("Победил игрок с именем: ");
        }
        System.out.print(result);
    }
}