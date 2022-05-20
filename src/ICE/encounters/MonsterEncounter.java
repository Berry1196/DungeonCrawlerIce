package ICE.encounters;

import ICE.combat.Attack;
import ICE.combat.AttackType;
import ICE.combat.CombatAction;
import ICE.enemy.Enemy;
import ICE.game.GameManager;
import ICE.player.EntityNames;

import java.util.Random;
import java.util.Scanner;

public class MonsterEncounter implements Encounter {

    private Enemy enemy;
    private CombatAction action;
    private Attack attack;


    public MonsterEncounter(Enemy enemy) {
        this.enemy = enemy;

    }

    //switch case that runs through the different actions during combat
    @Override
    public void runGame() {
        System.out.println("You encounter a monster: ");
        System.out.println(enemy);

        while (enemy.getHP() > 0) {

            chooseCombatAction();

            switch (action) {
                case Attack:
                    this.chooseAttack();
                    this.executeCombat();
                    break;

                case UseItem:
                    this.usePotion();
                    break;

                case CheckStatsAndItems:
                    this.checkStatsAndItems();

            }
        }
    }
    // Allows the user to check their current stats and items
    private void checkStatsAndItems() {
        var player = GameManager.getInstance().player;
        System.out.println(
            "Your HP: " + player.getHP() +
            "\nYour Attack: " + player.getAttack() +
            "\nYour Defense: " + player.getDefense() +
            "\nYour Gold: " + player.getGold() +
            "\nYour Health potions: " + player.getHealthPotion() +
            "\nYour Bombs: " + player.getBombs()
        );
    }
    //The user via input takes a potion
    private void usePotion() {
        Scanner scan = new Scanner(System.in);
        var player = GameManager.getInstance().player;
        if(player.getHealthPotion() >= 1 ){
        System.out.println("Press 1 to use a potion");
        }
        System.out.println("Press 2 to return");

        while(true) {
                var input = scan.nextLine();
                //the user is allowed to use a health potion if his current amount of potions is bigger than 0 and his hp is lower or equal to 50
            if (input.equalsIgnoreCase("1") && player.getHealthPotion() > 0 && player.getHP() <= 50) {
                player.setHP(player.getHP()+50);
                System.out.println("Your health is now " + player.getHP());
                player.setHealthPotion(player.getHealthPotion()-1);
                System.out.println("Your amount of health potions is now " + player.getHealthPotion());
                break;
                }
                // the users current health potions is 0 and therefor can not use any.
            if (input.equalsIgnoreCase("1") && player.getHealthPotion() <= 0)  {
                System.out.println("You do not have any health potions");
                break;
            }
            // the user input 2 Returns him to the chooseCombatAction()
            if (input.equalsIgnoreCase("2")) {
                System.out.println("Return");
                break;
            } else {
            scan = new Scanner(System.in);
            System.out.println("Thats not a valid item, try again.");
            System.out.println();
            System.out.println("Press 1 to use a potion");
            System.out.println("Press 2 to use a TBD");

                }
            }
        }



    //playerattack method deals damage to the enemy, and if the enemy is still alive it will then do deal damage to the players
    //its possible to add a speed stat that determines if the player or the enemy attacks first
    private void executeCombat() {

        playerAttack();
        if(enemy.getHP() > 0) {
            var enemyAttack = enemy.monsterAttack();
            enemyAttack(enemyAttack);
        }
    }

    //This method takes the damage from the players and sets a new current health for the enemy
    //If the players attack finnishes the enemy, it will also get the enemies items and set the players items to the new total
    private void playerAttack() {
        int tempGold;
        int tempPotion;
        int tempBomb;
        System.out.println("");
        System.out.println("You attack and deal " + attack.getDamage() + " damage");
        var enemyHealth = enemy.getHP();
        var currentHealth = enemyHealth - attack.getDamage();
        enemy.setHP(currentHealth);

        //displays the enemies dropped items
        if (enemy.getHP() <= 0) {
            var player = GameManager.getInstance().player;
            System.out.println(enemy.getRoleName() + " is dead. You win!");
            System.out.println("The " + enemy.getRoleName() + " drops:");
            if(enemy.getGold() > 0){
                System.out.println(enemy.getGold() + " Gold.");
            }
            if(enemy.getHealthPotion() > 0){
                System.out.println(enemy.getHealthPotion() + " Health potions.");
            }
            if(enemy.getBombs() > 0){
                System.out.println(enemy.getBombs() + " Bombs.");
            }

           //sets the players items to the old sum + new sum
           tempGold = player.getGold() + enemy.getGold();
            player.setGold(tempGold);
            tempPotion = player.getHealthPotion() + enemy.getHealthPotion();
            player.setHealthPotion(tempPotion);
            tempBomb = player.getBombs() + enemy.getBombs();
            player.setBombs(tempBomb);
            System.out.println("\nYour current HP: " + player.getHP() +
                            "\nYour current amount bombs: " + player.getBombs() +
                    "\nYour current amount of health potions: " + player.getHealthPotion());

            action = CombatAction.Won;
        } else {
            System.out.println(enemy.getRoleName() + " health is now: " + enemy.getHP());
        }

    }

        // calculates enemy damage to player
        private void enemyAttack(Attack enemyAttack) {
            var player = GameManager.getInstance().player;
            int dmgTaken;
            Random rnd = new Random();
            var r = rnd.nextInt(100);
            // the enemy cannot do damage to the player if its attack is lower than the players defense
            if(player.getDefense() > enemy.getAttack()){
            player.setHP(player.getHP());
            dmgTaken = 0;
        }else{
            if(r<60){
            player.setHP(player.getHP()+(player.getDefense() - enemy.getAttack()));
            dmgTaken = (player.getDefense() - enemy.getAttack())*-1;
        }
        else if (r < 80){
            System.out.println("Enemy missed");
            dmgTaken = 0;
        }
        else{
            System.out.println("Enemy Critical hit!");
            player.setHP(player.getHP()+(player.getDefense() - enemy.getAttack())*2);
            dmgTaken = (player.getDefense() - enemy.getAttack())*-2;
        }

        }
        System.out.println((""));
        System.out.println("Enemy attacks and deals " + dmgTaken + " damage to player");

        if(player.getHP()<=0){
            System.out.println("Your current HP: 0");
            System.out.println("You failed your attempt to save the world of Caldaran, lets hope that your sucessor is more useful.");
            System.exit(0);
        }else{
        System.out.println("Your current HP: " + player.getHP());
        }
        }
        // chooses what kind of attack you want to use.
        private void chooseAttack () {
            Scanner scan = new Scanner(System.in);
            var player = GameManager.getInstance().player;
            System.out.println();
            System.out.println("Choose attack: ");
            System.out.println("1: Melee attack");
            System.out.println("2: Ranged attack");
            System.out.println("3: Throw bomb");
            // damage is calculated differently according to the enemy Entity.name
            while(true) {
                var input = scan.nextLine();
            if (input.equalsIgnoreCase("1")) {
                if(enemy.getRoleName() == EntityNames.Skeleton){
                  attack = new Attack(AttackType.Physical, player.getAttack()*2 - enemy.getDefense());
                }else{
                attack = new Attack(AttackType.Physical, player.getAttack() - enemy.getDefense());
                }
                break;
            }
            if (input.equalsIgnoreCase("2")) {
                if(enemy.getRoleName() == EntityNames.Harpy){
                    attack = new Attack(AttackType.Ranged, player.getAttack()*2 - enemy.getDefense());
                }else{
                attack = new Attack(AttackType.Ranged, player.getAttack() - enemy.getDefense());
                }
                break;
            } // bombs dont work on sorcerers
            if (input.equalsIgnoreCase("3") && player.getBombs() >= 1) {
                if(enemy.getRoleName() == EntityNames.Sorcerer){
                    attack = new Attack(AttackType.Explosive, 0);
                    player.setBombs(player.getBombs()-1);
                    System.out.println("Dragons are unaffected by bombs");
                    System.out.println("You throw a bomb and now have " + player.getBombs() + " left");
                }else {
                attack = new Attack(AttackType.Explosive, enemy.getHP());
                player.setBombs(player.getBombs()-1);
                System.out.println("You throw a bomb and now have " + player.getBombs() + " left");
                }
                break;
            }
            else {
            scan = new Scanner(System.in);
            System.out.println("Thats not a valid attack, try again.");
            System.out.println();
            System.out.println("Choose attack: ");
            System.out.println("1: Melee attack");
            System.out.println("2: Ranged attack");
            System.out.println("3: Throw bomb");
            }
        }
        }
        // chooses what the player wants to do before combat.
        private void chooseCombatAction() {
            Scanner scan = new Scanner(System.in);
            System.out.println("\nChoose your action: ");
            System.out.println("1: Attack");
            System.out.println("2: Use Item");
            System.out.println("3: Check stats and items");

            var input = scan.nextLine();

            if (input.equalsIgnoreCase("1")) {
                this.action = CombatAction.Attack;
            }
            if (input.equalsIgnoreCase("2")) {
                this.action = CombatAction.UseItem;
            }
            if (input.equalsIgnoreCase("3")) {
                this.action = CombatAction.CheckStatsAndItems;
            }
        }
    }


