package djpiper28.randomcommander;

import forohfor.scryfall.api.Card;

import java.util.List;
import java.util.Random;

import static forohfor.scryfall.api.MTGCardQuery.search;

public class main {

    private static boolean has(int[] array, int number) {
        for (int elmenet : array) {
            if (elmenet == number) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        final String info = "Made by djpiper28, available at https://github.com/djpiper28/randomcommander";
        final String helpinfo = "Arguments (* means required) <numberOfPlayers*> <player 1 name> <player 2 name> ... <player n name>\ni.e: java -jar randomcommander.jar 4 Bob Bill Ben Jeb\n" + info;

        System.out.println(args.length == 0 || args[0].matches("-?-?help") ? helpinfo : info);

        if (args[0].matches("-?-?help")) {
            System.exit(0);
        }

        List<Card> cards = search("(t:creature and t:legend) or o:\"can be your commander\" and legal:commander");
        Random random = new Random();
        int[] indices = new int[args.length > 1 ? Integer.parseInt(args[0]) : 4]; //4 elements

        indices[0] = random.nextInt(cards.size());
        for (int i = 1; i < indices.length; i++) {
            int number = random.nextInt(cards.size());
            while (has(indices, number)) {
                number = random.nextInt(cards.size());
            }
            indices[i] = number; //VERY IMPORTANT
        }

        for (int i = 0; i < indices.length; i++) {
            System.out.println((args.length + 2 >= i ? args[i + 1] : i) + " commander: [[" + cards.get(indices[i]).getName()
                    + "]] Colours: " + cards.get(indices[i]).getColorIdentity() + " " + cards.get(indices[i]).getScryfallURI());
        }
    }

}
