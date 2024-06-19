package com.orange.blood.heroes.srv.combat.heroes.domain.strategy;

import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpellCombatStrategy extends CombatStrategy{
    private static final Logger logger = LoggerFactory.getLogger(SpellCombatStrategy.class);

    @Override
    public void execute(Hero hero, ECombatResult combatResult) {
        logger.info("Executing spell combat strategy for hero {}", hero.getId());
        if (ECombatResult.WIN.equals(combatResult)) {
            hero.setMana(hero.getMana() + 1);
        } else if (ECombatResult.LOSE.equals(combatResult)) {
            hero.setMana(hero.getMana() - 10);
        }
        logger.info("Spell combat result for hero {}: mana={}", hero.getId(), hero.getMana());

    }
}
