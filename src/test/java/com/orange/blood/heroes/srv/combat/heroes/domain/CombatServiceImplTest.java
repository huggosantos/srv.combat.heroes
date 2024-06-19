package com.orange.blood.heroes.srv.combat.heroes.domain;

import com.orange.blood.heroes.srv.combat.heroes.domain.exception.HeroUnavailableException;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatType;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import com.orange.blood.heroes.srv.combat.heroes.domain.usecase.CombatServiceImpl;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CombatServiceImplTest {

    @Mock
    private IPartyService partyService;

    @InjectMocks
    private CombatServiceImpl combatService;

    private Hero hero;
    private Party party;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hero = new Hero(1);
        party = new Party(Arrays.asList(hero));
        when(partyService.getParty()).thenReturn(party);
    }

    @Test
    void testProcessCombatMeleeWin() {
        combatService.processCombat(1, ECombatType.MELEE, ECombatResult.WIN);
        assertEquals(100, hero.getHealth());
        verify(partyService, times(1)).updatePartyMorale(1);
    }

    @Test
    void testProcessCombatMeleeLose() {
        combatService.processCombat(1, ECombatType.MELEE, ECombatResult.LOSE);
        assertEquals(90, hero.getHealth());
        verify(partyService, times(1)).updatePartyMorale(-10);
    }

    @Test
    void testProcessCombatSpellWin() {
        combatService.processCombat(1, ECombatType.SPELL, ECombatResult.WIN);
        assertEquals(100, hero.getMana());
        verify(partyService, times(1)).updatePartyMorale(1);
    }

    @Test
    void testProcessCombatSpellLose() {
        combatService.processCombat(1, ECombatType.SPELL, ECombatResult.LOSE);
        assertEquals(90, hero.getMana());
        verify(partyService, times(1)).updatePartyMorale(-10);
    }

    @Test
    void testHeroCannotFight() {
        hero.setHealth(0);
        assertThrows(HeroUnavailableException.class, () -> {
            combatService.processCombat(1, ECombatType.MELEE, ECombatResult.WIN);
        });
    }
}
