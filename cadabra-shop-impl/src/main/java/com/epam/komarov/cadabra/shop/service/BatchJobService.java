package com.epam.komarov.cadabra.shop.service;

public interface BatchJobService {

    String startUsersImport();

    String startUserSpendingImport();

    String startLotteryParticipantsSelection();

}
