package ICE.encounters;

import ICE.combat.Attack;
import ICE.combat.AttackType;
import ICE.game.GameManager;

import java.util.Scanner;

public class TraderEncounter implements Encounter{

//runGame calls the trader method
    @Override
    public void runGame() {
        trader();
    }
    /*
    Implement a trader that can sell upgrades and items to the player.

     */
    private void trader() {
        Scanner scan = new Scanner(System.in);
        //Scanner for user input
        var player = GameManager.getInstance().player;
        //while loop is always true, so we always go into the loop
        while(true) {
            System.out.println();
            System.out.println("Merchant:Hello traveler, come take a look at my wares");
            System.out.println("What would you like to buy: ");
            System.out.println("1: Health Potion");
            System.out.println("2: Armor upgrade");
            System.out.println("3: Weapon upgrade");
            System.out.println("4: Exit trader");
            var input = scan.nextLine();
            // When the user types in 1 the trader takes 5 gold from the player and gives a health potion.
            if (input.equalsIgnoreCase("1") && player.getGold() >= 5) {
                player.setHealthPotion(player.getHealthPotion() + 1);
                System.out.println("You now have " + player.getHealthPotion() + " Health potions");
                player.setGold(player.getGold() - 5);
                System.out.println("Merchant: Here is your remaining gold " + player.getGold());
            }
            else if (input.equalsIgnoreCase("1") && player.getGold() < 5) {
                System.out.println("Merchant: Do I look like I cant count?\nYou do not have the money for that!");
            }
            // When the user types in 2 the trader takes 10 gold and upgrades the players defense by 1.
            else if (input.equalsIgnoreCase("2") && player.getGold() >= 10) {
                player.setDefense(player.getDefense() + 1);
                System.out.println("Upgraded Defense: " + player.getAttack());
                player.setGold(player.getGold() - 10);
                System.out.println("Merchant: Here is your remaining gold " + player.getGold());
            }
            else if (input.equalsIgnoreCase("2") && player.getGold() < 10) {
                System.out.println("Merchant: Do I look like I cant count?\nYou do not have the money for that!");
            }
            // When the user types in 2 the trader takes 10 gold and upgrades the players attack by 1.
            else if (input.equalsIgnoreCase("3") && player.getGold() >= 10) {
                player.setAttack(player.getAttack() + 1);
                System.out.println("Upgraded Attack: " + player.getAttack());
                player.setGold(player.getGold() - 10);
                System.out.println("Merchant: Here is your remaining gold " + player.getGold());
            }
            else if (input.equalsIgnoreCase("3") && player.getGold() < 10) {
                System.out.println("Merchant: Do I look like I cant count?\nYou do not have the money for that!");
            }
            // When the user types in 4 it breaks the while loop and continues the queue
            else if (input.equalsIgnoreCase("4")) {
                System.out.println("Merchant: Till we meet again traveler");
                break;
            } // if the player does not input one of the above inputs
            else{
                System.out.println("Merchant: I have not heard about that ware before, but I do not have.");
            }
        }
        System.out.println("You leave the merchant and continue your adventure");
    }
}
