package com.orange.blood.heroes.srv.combat.heroes.domain.model;

import java.util.List;

public class Party {

    private List<Hero> heroes;
    private int morale;

    public Party(List<Hero> heroes) {
        this.heroes = heroes;
        this.morale = 1000;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }
}
