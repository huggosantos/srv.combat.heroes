package com.orange.blood.heroes.srv.combat.heroes.domain;


import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import com.orange.blood.heroes.srv.combat.heroes.domain.usecase.PartyServiceImpl;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;

class PartyServiceImplTest {

    private IPartyService partyService;

    private Hero hero;
    private Party party;

    @BeforeEach
    void setUp() {
        partyService = PartyServiceImpl.getInstance();
        party = partyService.getParty();
        hero = party.getHeroes().get(1);
    }

    @Test
    void testRestHeroes() {
        hero.setHealth(98);
        hero.setMana(98);
        partyService.restHeroes();
        assertEquals(100, hero.getHealth());
        assertEquals(100, hero.getMana());
    }

    @Test
    void testRestParty() {
        party.setMorale(980);
        partyService.restParty();
        assertEquals(1000, party.getMorale());
    }

    @Test
    void testUpdatePartyMorale() {
        party.setMorale(500);
        partyService.updatePartyMorale(10);
        assertEquals(510, party.getMorale());
        partyService.updatePartyMorale(-20);
        assertEquals(490, party.getMorale());
    }
}
