package ICE.enemy;

import ICE.combat.Attack;
import ICE.combat.AttackType;
import ICE.player.EntityNames;
import ICE.roles.EntityTypes;

import java.util.Random;

public class Enemy extends EntityTypes {
    

    public Enemy(EntityNames playerRoles, int HP, int attack, int defense, int gold, int bombs, int healthPotion) {
        super(playerRoles, HP, attack, defense, gold, bombs, healthPotion);
    }

    //a method for the monsters attack, in this case a physical attack
    public Attack monsterAttack(){
            return new Attack(AttackType.Physical, getAttack());
        }
    }

