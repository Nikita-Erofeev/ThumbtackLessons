package net.thumbtack.school.competition.server;

import com.google.gson.Gson;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.DtoError;
import net.thumbtack.school.competition.dto.DtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.service.UserService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Server {
    private boolean run = false;

    private boolean isEmptyString(String string){
        return string == null || string.equals("");
    }

    public String startServer(String savedDataFileName){
        Gson json = new Gson();
        try {
            run = Database.uploadDatabase(savedDataFileName);
            return json.toJson(new DtoResponse("Database uploaded"));
        } catch (CompetitionException e){
            return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
        }

    }

    public String stopServer(String savedDataFileName){
        Gson json = new Gson();
        try{
            run = !Database.saveDatabase(savedDataFileName);
            return json.toJson(new DtoResponse("Database saved"));
        } catch (CompetitionException e){
            return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
        }
    }

    public String registerMember(String requestJsonString){
        Gson json = new Gson();
        if(run){
            UserService userService = new UserService();
            return userService.registerMember(requestJsonString);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String registerExpert(String requestJsonString){
        Gson json = new Gson();
        if (run){
            UserService userService = new UserService();
            return userService.registerExpert(requestJsonString);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String deleteUser(String token){
        Gson json = new Gson();
        if(run){
            UserService userService = new UserService();
            return userService.deleteUser(token);
        }
        return json.toJson(new DtoError("Database is not available"));
    }



}
