package com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.mapper;

import com.orange.blood.heroes.srv.combat.heroes.adapter.inbound.rest.dto.PartyResponse;
import com.orange.blood.heroes.srv.combat.heroes.domain.model.Party;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartyMapper {
    PartyMapper INSTANCE = Mappers.getMapper(PartyMapper.class);

    PartyResponse toPartyResponse(Party party);
}
