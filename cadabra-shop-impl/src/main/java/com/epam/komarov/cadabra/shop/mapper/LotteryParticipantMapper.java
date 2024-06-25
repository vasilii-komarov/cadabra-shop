package com.epam.komarov.cadabra.shop.mapper;

import com.epam.komarov.cadabra.shop.entity.LotteryParticipantEntity;
import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LotteryParticipantMapper {

    LotteryParticipantDto lotteryParticipantToLotteryParticipantDto(LotteryParticipantEntity entity);
    List<LotteryParticipantDto> lotteryParticipantsToLotteryParticipantDtos(List<LotteryParticipantEntity> entities);
    LotteryParticipantEntity lotteryParticipantDtoToLotteryParticipant(LotteryParticipantDto dto);
    List<LotteryParticipantEntity> lotteryParticipantDtosToLotteryParticipants(List<LotteryParticipantDto> dtos);

}
