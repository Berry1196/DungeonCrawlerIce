package ICE.player;

import ICE.roles.EntityTypes;

public class Player extends EntityTypes {

    //The player class extends the abstract class EntityType
    public Player(EntityNames playerRoles, int HP, int attack, int defense, int gold, int bombs, int healthPotion) {
        super(playerRoles, HP, attack, defense, gold, bombs, healthPotion);
    }
}



