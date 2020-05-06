package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.daoimpl.AdminDaoImpl;
import net.thumbtack.school.competition.dto.DtoError;
import net.thumbtack.school.competition.dto.DtoResponse;
import net.thumbtack.school.competition.dto.SummarizeDto;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminService {
    AdminDaoImpl adminDaoImpl;

    public AdminService() {
        adminDaoImpl = new AdminDaoImpl();
    }

    private List<Map.Entry<Application, Float>> sortMapAppFloat(Map<Application, Float> mapAverage) {
        return mapAverage.entrySet().stream()
                .sorted((e1, e2) -> {
                    if (e1.getValue().equals(e2.getValue())) {
                        if (e1.getKey().getAmountRequested() <= e2.getKey().getAmountRequested()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        return -e1.getValue().compareTo(e2.getValue());
                    }
                }).collect(Collectors.toList());
    }

    public String summarize(String summarizeDtoJson) {
        Gson json = new Gson();
        try {
            SummarizeDto summarizeDto = json.fromJson(summarizeDtoJson, SummarizeDto.class);
            int minRating = summarizeDto.getMinRate();
            int fund = summarizeDto.getFund();
            if (summarizeDto.getMinRate() > 0 && summarizeDto.getMinRate() < 6) {
                List<Application> result = new ArrayList<>();
                try {
                    Map<Application, Float> appWithAvgRating = adminDaoImpl.summarize();
                    if (appWithAvgRating.size() > 0) {
                        List<Map.Entry<Application, Float>> sortedAppList = sortMapAppFloat(appWithAvgRating);
                        for (Map.Entry<Application, Float> item : sortedAppList) {
                            if (item.getValue() > minRating && item.getKey().getAmountRequested() <= fund) {
                                fund -= item.getKey().getAmountRequested();
                                result.add(item.getKey());
                            }
                        }
                        if (result.size() == 0) {
                            return json.toJson(new DtoError(
                                    new CompetitionException(ErrorCode.SUMMARIZE_ERROR).getErrorCode().getErrorString()));
                        }
                        return json.toJson(new DtoResponse(json.toJson(result)));
                    } else {
                        return json.toJson(new DtoError(
                                new CompetitionException(ErrorCode.SUMMARIZE_ERROR).getErrorCode().getErrorString()));
                    }
                } catch (CompetitionException e) {
                    return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
                }
            } else {
                return json.toJson(new DtoError("invalid request"));
            }
        } catch (JsonSyntaxException e) {
            return json.toJson(new DtoError("invalid request"));
        }
    }
}
