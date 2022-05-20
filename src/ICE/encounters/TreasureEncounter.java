package ICE.encounters;

import ICE.combat.Attack;
import ICE.combat.AttackType;
import ICE.game.GameManager;

import java.util.Random;
import java.util.Scanner;

public class TreasureEncounter implements Encounter {
    @Override
    public void runGame() {
        treasure();
    }

    /* Create a method that makes the player able to interact with a treasure chest.
    The treasure chest should be able to be treasure or a trap.
    */
    private void treasure() {
        //Scanner used for player input
        Scanner scan = new Scanner(System.in);
        //Singleton from GameManager
        var player = GameManager.getInstance().player;
        System.out.println("You've found a treasure chest");
        System.out.println("Do you dare open it?");
        System.out.println("Type yes to open\nNo to leave it alone");
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("Yes")) {
            //With the help of the in built Random() we have been able to make the chest give "random" rewards or release a trap.
            //To be balanced later.
            Random rnd = new Random();
            var r = rnd.nextInt(100);
            if (r < 60) {
                System.out.println("The treasure chest contains 100 gold!");
                player.setGold(player.getGold() + 100);
                System.out.println("your current gold is now " + player.getGold());
            } else if (r < 80) {
                player.setGold(player.getGold() + 200);
                System.out.println("your current gold is now " + player.getGold());
            } else {
                System.out.println("The chest explodes and shrapnel strafes your arm!");
                player.setHP(player.getHP() - 10);
                System.out.println("your current hp is now " + player.getHP());
            }
        } 
        if(input.equalsIgnoreCase("No")) {
            System.out.println("You left the treasure alone");
        }
    }
}
