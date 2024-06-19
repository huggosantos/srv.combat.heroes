package com.orange.blood.heroes.srv.combat.heroes.domain.usecase;

import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PartyServiceImpl implements IPartyService {

    private static final Logger logger = LoggerFactory.getLogger(PartyServiceImpl.class);
    private static PartyServiceImpl instance;
    private Party party;

    private PartyServiceImpl() {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            heroes.add(new Hero(i));
        }
        this.party = new Party(heroes);
    }

    public static synchronized PartyServiceImpl getInstance() {
        if (instance == null) {
            instance = new PartyServiceImpl();
        }
        return instance;
    }

    @Override
    public void restHeroes() {
        party.getHeroes().forEach(hero -> {
            if (!hero.isInCombat()) {
                hero.setHealth(Math.min(hero.getHealth() + 2, 100));
                hero.setMana(Math.min(hero.getMana() + 2, 100));
                logger.info("Recovered Hero {}: health={}, mana={}", hero.getId(), hero.getHealth(), hero.getMana());
            }
        });
    }

    @Override
    public void restParty() {
        party.setMorale(Math.min(party.getMorale() + 20, 1000));
        logger.info("Party morale has been increased");
    }

    @Override
    public Party getParty() {
        return party;
    }

    @Override
    public void updatePartyMorale(int amount) {
        party.setMorale(Math.max(0, Math.min(party.getMorale() + amount, 1000)));
        logger.info("Party morale updated by {}", amount);
    }

}
