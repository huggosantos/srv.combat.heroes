package com.orange.blood.heroes.srv.combat.heroes.domain.usecase;

import com.orange.blood.heroes.srv.combat.heroes.domain.exception.HeroUnavailableException;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatResult;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.ECombatType;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Hero;
import com.orange.blood.heroes.srv.combat.heroes.domain.strategy.CombatStrategy;
import com.orange.blood.heroes.srv.combat.heroes.domain.strategy.MeleeCombatStrategy;
import com.orange.blood.heroes.srv.combat.heroes.domain.strategy.SpellCombatStrategy;
import com.orange.blood.heroes.srv.combat.heroes.port.ICombatService;
import com.orange.blood.heroes.srv.combat.heroes.port.IPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CombatServiceImpl implements ICombatService {

    private static final Logger logger = LoggerFactory.getLogger(CombatServiceImpl.class);

    private IPartyService partyService;

    public CombatServiceImpl(IPartyService partyService) {
        this.partyService = partyService;
    }

    @Override
    public void processCombat(int heroId, ECombatType combatType, ECombatResult combatResult) {
        Hero hero = findHeroById(heroId);
        if (hero == null || !canHeroFight(hero)) {
            throw new HeroUnavailableException("O herói não está em condições de combater no momento");
        }
        hero.setInCombat(true);
        logger.info("Hero {} is starting combat of type {}", heroId, combatType);

        CombatStrategy strategy;

        switch (combatType) {
            case MELEE:
                strategy = new MeleeCombatStrategy();
                break;
            case SPELL:
                strategy = new SpellCombatStrategy();
                break;
            default:
                throw new IllegalArgumentException("O combate é inválido e não foi computado");
        }
        strategy.execute(hero, combatResult);
        logger.info("Combat result for Hero {}: {}", heroId, combatResult);
        partyService.updatePartyMorale(combatResult == ECombatResult.WIN ? 1 : -10);
        validateHeroAttributes(hero);

        hero.setInCombat(false);
        logger.info("Hero {} has finished combat", heroId);
    }

    private Hero findHeroById(int heroId) {
        return partyService.getParty().getHeroes().stream()
                .filter(hero -> hero.getId() == heroId)
                .findFirst()
                .orElse(null);
    }

    private boolean canHeroFight(Hero hero) {
        return hero.getHealth() > 0 && hero.getMana() > 0;
    }

    private void validateHeroAttributes(Hero hero) {
        hero.setHealth(Math.max(0, Math.min(hero.getHealth(), 100)));
        hero.setMana(Math.max(0, Math.min(hero.getMana(), 100)));
    }
}
