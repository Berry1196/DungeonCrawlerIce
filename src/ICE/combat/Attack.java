package ICE.combat;

import ICE.roles.EntityTypes;

public class Attack {
    private AttackType attackType;
    private int damage;

    //Constructor
    public Attack(AttackType attackType, int damage) {
        this.attackType = attackType;
        this.damage = damage;
    }

    //Getter
    public int getDamage()
    {
        return damage;
    }



}
