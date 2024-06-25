package com.epam.komarov.cadabra.shop.api;

public interface BatchJobApi {

    String startUsersImport();

    String startUserSpendingImport();

    String startLotteryParticipantsSelection();

}
