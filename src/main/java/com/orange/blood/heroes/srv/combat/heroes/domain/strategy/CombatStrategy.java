package com.orange.blood.heroes.srv.combat.heroes.domain.strategy;

import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;

public abstract class CombatStrategy {
    public abstract void execute(Hero hero, ECombatResult combatResult);
}
