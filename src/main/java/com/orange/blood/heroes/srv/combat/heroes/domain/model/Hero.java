package com.orange.blood.heroes.srv.combat.heroes.domain.model;

public class Hero {

    private int id;
    private int health;
    private int mana;
    private boolean inCombat;

    public Hero(int id) {
        this.id = id;
        this.health = 100;
        this.mana = 100;
        this.inCombat = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isInCombat() {return inCombat; }

    public void setInCombat(boolean inCombat) { this.inCombat = inCombat; }
}
