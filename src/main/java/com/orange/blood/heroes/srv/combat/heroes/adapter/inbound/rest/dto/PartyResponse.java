package com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.dto;


import java.util.List;

public class PartyResponse {
    private List<HeroResponse> heroes;
    private int morale;
    private long timestamp;

    public List<HeroResponse> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroResponse> heroes) {
        this.heroes = heroes;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
