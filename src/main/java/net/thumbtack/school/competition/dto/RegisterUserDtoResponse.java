package net.thumbtack.school.competition.dto;

public class RegisterUserDtoResponse {
    private String token;

    public RegisterUserDtoResponse(String response){
        token = response;
    }

    public String getToken() {
        return token;
    }
}
