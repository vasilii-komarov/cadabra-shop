package com.epam.komarov.cadabra.shop.service.impl;

import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import com.epam.komarov.cadabra.shop.mapper.LotteryParticipantMapper;
import com.epam.komarov.cadabra.shop.repository.LotteryParticipantRepository;
import com.epam.komarov.cadabra.shop.repository.UserSpendingRepository;
import com.epam.komarov.cadabra.shop.service.LotteryService;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotteryServiceImpl implements LotteryService {

    private final UserSpendingRepository userSpendingRepository; //todo switch on service
    private final LotteryParticipantRepository lotteryParticipantRepository;
    private final LotteryParticipantMapper lotteryParticipantMapper;

    private final Random random = new Random();

    @Value("${batch.lottery-participant.spending-threshold:20.00}")
    private Double spendingThreshold;

    @Override
    public List<LotteryParticipantDto> selectLotteryParticipants() {
        List<UserSpendingEntity> participants = userSpendingRepository.findBySpendingGreaterThan(spendingThreshold);
        Long winnerUserId = getWinnerUserId(participants);
        return participants.stream()
            .map(participant -> buildLotteryParticipantDto(participant, isWinner(participant, winnerUserId)))
            .toList();
    }

    @Override
    public List<LotteryParticipantDto> getLotteryParticipants() {
        return lotteryParticipantMapper.lotteryParticipantsToLotteryParticipantDtos(
            lotteryParticipantRepository.findAll());
    }

    @Override
    public List<LotteryParticipantDto> getLotteryWinners() {
        return lotteryParticipantMapper.lotteryParticipantsToLotteryParticipantDtos(
            lotteryParticipantRepository.findByGiftCardIdIsNotNull());
    }

    private Long getWinnerUserId(List<UserSpendingEntity> participants) {
        return participants.isEmpty()
            ? null
            : participants.get(random.nextInt(participants.size()))
            .getUserId();
    }

    private boolean isWinner(UserSpendingEntity participant, Long winnerUserId) {
        return Objects.nonNull(winnerUserId) && winnerUserId.equals(participant.getUserId());
    }

    private LotteryParticipantDto buildLotteryParticipantDto(UserSpendingEntity participant, boolean isWinner) {
        return LotteryParticipantDto.builder()
            .userId(participant.getUserId())
            .giftCardId(isWinner ? assignGiftCard() : null)
            .build();
    }

    private UUID assignGiftCard() {
        return UUID.randomUUID();
    }

}
