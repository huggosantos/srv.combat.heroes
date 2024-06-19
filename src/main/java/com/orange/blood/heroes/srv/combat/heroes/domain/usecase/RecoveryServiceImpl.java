package com.orange.blood.heroes.srv.combat.heroes.domain.usecase;

import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import com.orange.blood.heroes.srv.combat.heroes.port.IRecoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;



public class RecoveryServiceImpl implements IRecoveryService {
    private static final Logger logger =  LoggerFactory.getLogger(RecoveryServiceImpl.class);

    private final IPartyService partyService;

    public RecoveryServiceImpl(IPartyService partyService) {
        this.partyService = partyService;
    }

    @Override
    @Scheduled(fixedRateString = "${hero.recovery.interval:10000}")
    public void recoverHeroesAndParty() {
        logger.info("Starting recovery process for heroes and party");

        Party party = partyService.getParty();
        partyService.restHeroes();

        int previousMorale = party.getMorale();
        partyService.restParty();
        logger.info("Recovered Party morale: previous={}, current={}", previousMorale, party.getMorale());

        logger.info("Finished recovery process for heroes and party");
    }
}
