package com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest;

import com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.dto.PartyResponse;
import com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.mapper.PartyMapper;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatType;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import com.orange.blood.heroes.srv.combat.heroes.port.ICombatService;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CombatHeroControllerTest {

    @Mock
    private ICombatService combatService;

    @Mock
    private IPartyService partyService;

    @InjectMocks
    private CombatHeroController restController;

    private Party party;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        party = new Party(null);
        when(partyService.getParty()).thenReturn(party);
    }

    @Test
    void testGetCombat() {
        ResponseEntity<String> response = restController.getCombat(1, ECombatType.MELEE, ECombatResult.WIN);
        assertEquals(202, response.getStatusCodeValue());
        verify(combatService, times(1)).processCombat(1, ECombatType.MELEE, ECombatResult.WIN);
    }

    @Test
    void testGetParty() {
        PartyResponse partyResponse = PartyMapper.INSTANCE.toPartyResponse(party);
        ResponseEntity<PartyResponse> response = restController.getParty();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(partyResponse.getMorale(), response.getBody().getMorale());
    }
}

