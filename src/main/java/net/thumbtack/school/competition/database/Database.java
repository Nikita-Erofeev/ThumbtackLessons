package net.thumbtack.school.competition.database;

import com.google.gson.Gson;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;
import net.thumbtack.school.competition.model.User;

import java.io.*;
import java.util.*;

public class Database implements Serializable {
    private static Database instance;
    private byte rating;
    private List<Member> members = new ArrayList<>();
    private List<Expert> experts = new ArrayList<>();
    private Map<Application,Member> applicationsWithMembers= new TreeMap<>();
    private Map<Application,Map<Expert,Byte>> applicationsWithRating = new TreeMap<>();
    private Map<UUID,User> usersOnline = new HashMap<>();

    private Database() {
    }

    public static Database getInstance() {
        /*if (instance == null) {
            instance = new Database();
        }*/
        return instance;
    }

    public static boolean uploadDatabase(String savedDataFileName) throws CompetitionException{
        if(savedDataFileName == null || savedDataFileName.equals("")){
            instance = new Database();
            return true;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(savedDataFileName)))) {
                Gson json = new Gson();
                instance = json.fromJson(br, Database.class);
                return true;
            } catch (IOException e) {
                throw new CompetitionException(ErrorCode.ERROR_UPLOAD_DATABASE);
            }
        }
    }

    public static boolean saveDatabase(String savedDataFileName) throws CompetitionException{
        if(savedDataFileName == null || savedDataFileName.equals("")){
            return true;
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(savedDataFileName)))) {
                Gson json = new Gson();
                json.toJson(instance,bw);
                return true;
            } catch (IOException e) {
                throw new CompetitionException(ErrorCode.ERROR_SAVING_DATABASE);
            }
        }
    }

    public String insert(Member member) throws CompetitionException {
        if(!members.contains(member)){
            members.add(member);
        } else {
            throw  new CompetitionException(ErrorCode.DUPLICATE_USER);
        }
        UUID token = UUID.randomUUID();
        usersOnline.put(token,member);
        return token.toString();
    }

    public String insert(Expert expert) throws CompetitionException{
        if(!experts.contains(expert)){
            experts.add(expert);
        } else {
            throw new CompetitionException(ErrorCode.DUPLICATE_USER);
        }
        UUID token = UUID.randomUUID();
        usersOnline.put(token,expert);
        return token.toString();
    }

    public String deleteUser(String token) throws CompetitionException{
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                User toDelete = usersOnline.get(key);
                usersOnline.remove(key, toDelete);
                if (toDelete.getClass() == Member.class) {
                    members.remove((Member) toDelete);
                } else {
                    experts.remove((Expert) toDelete);
                }
            } else {
                throw new CompetitionException(ErrorCode.ERROR_DELETING_USER);
            }
        } catch (ClassCastException e){
        throw new CompetitionException(ErrorCode.ERROR_DELETING_USER);
    }
        return "User successfully deleted from the server";
    }
    /*Остальные методы*/
}
