package ICE.encounters;

import ICE.game.GameManager;
import ICE.player.Player;

import java.util.Scanner;

import static ICE.player.EntityNames.*;

public class CharacterSelection implements Encounter {

    //sets player to chosen roll when added to the queue
    @Override
    public void runGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose character");
        System.out.println("Type B for Barbarian" +
                "\nType K for Knight" +
                "\nType M for Mercenary");
        String input = scan.nextLine();

        while (true)
            if (input.equalsIgnoreCase("B")) {
                Player player = new Player(Barbarian, 100, 10, 4, 0,0,0);
                System.out.println("You have chosen Barbarian");
                System.out.println(player);
                GameManager.getInstance().player = player;
                break;
            } else if (input.equalsIgnoreCase("K")) {
                Player player = new Player(Knight, 100, 8, 6, 0,0,0);
                System.out.println("You have chosen Knight");
                System.out.println(player);
                GameManager.getInstance().player = player;
                break;
            } else if (input.equalsIgnoreCase("M")) {
                Player player = new Player(Mercenary, 100, 9,5 , 0,0,0);
                System.out.println("You have chosen Mercenary");
                System.out.println(player);
                GameManager.getInstance().player = player;
                break;
            } else {
                System.out.println("That is not an option");
                System.out.println("\nType B for Barbarian" +
                        "\nType K for Knight" +
                        "\nType M for Mercenary");
                input = scan.nextLine();
            }
    }
    }
