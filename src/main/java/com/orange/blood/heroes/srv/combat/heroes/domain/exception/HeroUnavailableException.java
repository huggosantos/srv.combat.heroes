package com.orange.blood.heroes.srv.combat.heroes.domain.exception;

public class HeroUnavailableException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public HeroUnavailableException(String errMsg) {
        super(errMsg);
    }

}
