package com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest;

import com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.dto.PartyResponse;
import com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.mapper.PartyMapper;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatType;
import com.orange.blood.heroes.srv.combat.heroes.port.ICombatService;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")

public class CombatHeroController {

    private final ICombatService combatService;
    private final IPartyService partyService;

    @Autowired
    public CombatHeroController(ICombatService combatService, IPartyService partyService) {
        this.combatService = combatService;
        this.partyService = partyService;
    }

    @GetMapping("/combat")
    public ResponseEntity<String> getCombat(@RequestParam int heroId, ECombatType combatType, ECombatResult combatResult) {
        try {
            combatService.processCombat(heroId, combatType, combatResult);
            return ResponseEntity.accepted().body("O combate é válido e foi computado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/party")
    public ResponseEntity<PartyResponse> getParty() {
        PartyResponse response = PartyMapper.INSTANCE.toPartyResponse(partyService.getParty());
        response.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}
