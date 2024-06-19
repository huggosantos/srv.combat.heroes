package com.orange.blood.heroes.srv.combat.heroes.port;

import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatType;

public interface ICombatService {
    void processCombat(int heroId, ECombatType combatType, ECombatResult combatResult);

}
