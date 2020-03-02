package net.thumbtack.school.competition.dto;

public class SummarizeDto {
    private int fund;
    private int minRate;

    public SummarizeDto(int fund, int minRate){
        this.fund = fund;
        this.minRate = minRate;
    }

    public int getFund() {
        return fund;
    }

    public int getMinRate() {
        return minRate;
    }
}
