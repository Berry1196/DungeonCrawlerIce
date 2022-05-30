package ICE.game;

import ICE.encounters.*;
import ICE.enemy.Enemy;
import ICE.player.EntityNames;
import ICE.player.Player;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameManager {

    //Queue system using LinkedList
     public Player player;
    Queue<Encounter> q = new LinkedList<>();

    //Singleton that instantiates the gameManager
            private static GameManager instance;
            public static GameManager getInstance() {
                return instance;
            }
    // GameManger method that starts the queue
    public GameManager() {
        instance = this;

        buildGameQueue();
        runTheGame();
    }


    //The Queue system that runs all the different encounter methods
    private void buildGameQueue() {
        q.add(new CharacterSelection());
        q.add(new StoryEncounter("\nWelcome to the world of Calderan." +
            "\nIn the recent years, Calderan has been plagued by disappearances, the usage of dark magic has been on the rise." +
            "\nDragons have been seen flying more frequently and monsters have begun to roam new parts of the land." +
            "\nThe world of Calderan is faced with an ever growing threat" +
            "\nTalks of a dark sorcerer being behind it all is starting to spread from town to town and city to city." +
            "\n Noble knights, mercenaries, sorcerers have all set out to explore but none have returned."));
        q.add(new StoryEncounter("Now adventurer it is time to set off and find the mysterious sorcerer." +
            "\nAs you leave the capital city of Calagar, the guards closes the gates behind you."));
        q.add(new StoryEncounter("As you wander the road, you are suddenly ambushed by two Goblins"));


            var goblin1 = new Enemy(EntityNames.Goblin,14,4,1,0,0,1);
            var goblin1Encounter = new MonsterEncounter(goblin1);
        q.add((goblin1Encounter));
        q.add(new StoryEncounter("As you defeat the goblin it drops a health potion." +
            "\nHealth potions are used to heal yourself during battle." +
            "\nYou can get more if monsters drop them or by finding a shop to purchase them."));
            var goblin2 = new Enemy(EntityNames.Goblin,20,4,1,25,0,1);
            var goblin2Encounter = new MonsterEncounter(goblin2);
        q.add((goblin2Encounter));
        q.add(new StoryEncounter("Gold can be used to buy items and upgrades at a merchant."));
        q.add(new StoryEncounter("One of the goblin drops a map, on the map is a red  circlearound a nearby orc camp." +
                "\nYou decide to head towards the camp to find answers." +
                "\nAs you change directions towards the orc camp you see a treasure chest behind a tree"));
        q.add(new TreasureEncounter());
        q.add(new StoryEncounter("As you leave the treasure chest, you hear a voice calling you from the road."));
        q.add(new TraderEncounter());
        q.add(new StoryEncounter("As you leave the trader you start nearing the orc camp." +
            "\nThis is the orc camp Ziruk, to enter here you must proof your strength to enter." +
            "\nYou are attacked by one of the orcs, if you defeat it you are allowed to enter their camp"));
        var orc1 = new Enemy(EntityNames.Orc,40,8,2,50,1,0);
        var orc1Encounter = new MonsterEncounter(orc1);
        q.add(orc1Encounter);
        q.add(new StoryEncounter("As you defeat the orc it drops a bomb." +
            "\nBombs are used to instantly defeat monsters but not bosses." +
            "\nYou can get more if monsters drop them."));
        q.add(new StoryEncounter("As you complete the orcs test of strength you are allowed into their camp." +
        "\nAs you enter you see nothing but brute orc fighting eachother in the camp you approach their leader." +
        "\nOrc Leader: What do you want human." +
        "\nPlayer: I need to know the location of the sorcerer thats been tormenting these lands." +
        "\nOrc Leader: If we are to give you this information, you will do something for us first." +
        "\nOrc Leader: Travel north and slay the giant that's been attacking our camp and you shall have your information"));

        q.add(new StoryEncounter("As you accept the orcs offer, you head north in search of this giant." +
        "\nYou reach an enourmos grotto entrance and get ready for battle with your new acquired bomb"));
            var giant1 = new Enemy(EntityNames.Giant,1000,50,50,50,0,2);
            var giant1Encounter = new MonsterEncounter(giant1);
        q.add(giant1Encounter);
        q.add(new StoryEncounter("After you've slain the giant, you travel back to the orc camp" +
            "\nOrc Leader: Good human, you are useful to us. Now as promised i will mark the sorceres location on your map" +
            "\nAs you leave the camp to head towards the sorcerer your path once again intertwines with the merchants."));
        q.add(new TraderEncounter());
        q.add(new StoryEncounter("After having stocked up for the upcoming fight you head towards the sorceres location"));
            var sorcerer = new Enemy(EntityNames.Sorcerer,100,500,15,10000,100,100);
            var sorcererEncounter = new MonsterEncounter(sorcerer);
        q.add(sorcererEncounter);
        q.add(new StoryEncounter("You saved the world of Calderan thank you hero."));
    }
    //This method takes the header of the queue pulls it out, runs it and prompts the user for an input to continue
    private void runTheGame() {

        Scanner scan = new Scanner(System.in);
        var firstElement = q.poll();
        firstElement.runGame();

        while (!q.isEmpty()) {
            q.poll().runGame();

            System.out.println(("Press ANY LETTER to continue"));
            scan.next();
        }

    }
}