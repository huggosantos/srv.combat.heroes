package com.orange.blood.heroes.srv.combat.heroes.config;

import com.orange.blood.heroes.srv.combat.heroes.domain.usecase.CombatServiceImpl;
import com.orange.blood.heroes.srv.combat.heroes.domain.usecase.PartyServiceImpl;
import com.orange.blood.heroes.srv.combat.heroes.domain.usecase.RecoveryServiceImpl;
import com.orange.blood.heroes.srv.combat.heroes.port.ICombatService;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import com.orange.blood.heroes.srv.combat.heroes.port.IRecoveryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public IPartyService partyService() {
        return PartyServiceImpl.getInstance();
    }

    @Bean
    public ICombatService combatService(IPartyService partyService) {
        return new CombatServiceImpl(partyService);
    }

    @Bean
    public IRecoveryService recoveryService(IPartyService partyService) {
        return new RecoveryServiceImpl(partyService);
    }

}
