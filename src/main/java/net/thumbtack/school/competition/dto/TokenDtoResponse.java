package net.thumbtack.school.competition.dto;

public class TokenDtoResponse {
    private String token;

    public TokenDtoResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
