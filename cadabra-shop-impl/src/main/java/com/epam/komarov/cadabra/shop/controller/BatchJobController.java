package com.epam.komarov.cadabra.shop.controller;

import com.epam.komarov.cadabra.shop.api.BatchJobApi;
import com.epam.komarov.cadabra.shop.service.BatchJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatchJobController implements BatchJobApi {

    private final BatchJobService batchJobService;

    @Override
    @PostMapping("/batch/job/startUsersImport")
    public String startUsersImport() {
        return batchJobService.startUsersImport();
    }

    @Override
    @PostMapping("/batch/job/startUserSpendingImport")
    public String startUserSpendingImport() {
        return batchJobService.startUserSpendingImport();
    }

    @Override
    @PostMapping("/batch/job/startLotteryParticipantsSelection")
    public String startLotteryParticipantsSelection() {
        return batchJobService.startLotteryParticipantsSelection();
    }

}
