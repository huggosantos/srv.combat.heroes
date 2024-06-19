package com.orange.blood.heroes.srv.combat.heroes.port;

import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;

public interface IPartyService {
    void restHeroes();
    void restParty();
    Party getParty();
    void updatePartyMorale(int amount);

}
