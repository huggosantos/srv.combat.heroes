package com.orange.blood.heroes.srv.combat.heroes.domain;


import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import com.orange.blood.heroes.srv.combat.heroes.domain.usecase.RecoveryServiceImpl;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecoveryServiceTest {

    @Mock
    private IPartyService partyService;

    @InjectMocks
    private RecoveryServiceImpl recoveryService;

    private Hero hero;
    private Party party;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hero = new Hero(1);
        party = new Party(Arrays.asList(hero));
        when(partyService.getParty()).thenReturn(party);
        doAnswer(invocation -> {
            party.getHeroes().forEach(hero -> {
                hero.setHealth(Math.min(hero.getHealth() + 2, 100));
                hero.setMana(Math.min(hero.getMana() + 2, 100));
            });
            return null;
        }).when(partyService).restHeroes();

        doAnswer(invocation -> {
            party.setMorale(Math.min(party.getMorale() + 20, 1000));
            return null;
        }).when(partyService).restParty();
    }

    @Test
    void testRecoverHeroesAndParty() {
        hero.setHealth(98);
        hero.setMana(98);
        party.setMorale(980);

        recoveryService.recoverHeroesAndParty();

        assertEquals(100, hero.getHealth());
        assertEquals(100, hero.getMana());
        assertEquals(1000, party.getMorale());

        verify(partyService, times(1)).getParty();
    }
}
