package ICE.roles;

import ICE.player.EntityNames;

public abstract class EntityTypes {
    private EntityNames roleName;
    private int HP;
    private int attack;
    private int defense;
    private int gold;
    private int bombs;
    private int healthPotion;

    
    //Constructor
    public EntityTypes(EntityNames playerRoles, int HP, int attack, int defense, int gold, int bombs, int healthPotion) {
        this.roleName = playerRoles;
        this.HP = HP;
        this.attack = attack;
        this.defense = defense;
        this.gold = gold;
        this.bombs = bombs;
        this.healthPotion = healthPotion;
    }
    //Whole bunch of setters and getters
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public EntityNames getRoleName() {
        return roleName;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public int getHealthPotion() {
        return healthPotion;
    }

    public void setHealthPotion(int healthPotion) {
        this.healthPotion = healthPotion;
    }

    //toString method
    @Override
    public String toString() {
        return
                 roleName +
                ": HP=" + HP +
                " Attack=" + attack +
                " Defense=" + defense;
    }
}

