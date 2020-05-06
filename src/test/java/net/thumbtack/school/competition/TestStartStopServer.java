/*package net.thumbtack.school.competition;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestStartStopServer {
    private static String tokenMember1, tokenMember2, tokenExpert1, tokenExpert2;
    private Gson json = new Gson();
    private Server server = new Server();

    private void prepareToSave() {
        RegisterMemberDto member1 = new RegisterMemberDto("Member1", "Member11",
                "ООО \"Member\"", "member1", "member1");
        RegisterMemberDto member2 = new RegisterMemberDto("Member2", "Member22",
                "Teo.com", "member2", "member2");
        List<String> subjects1 = new ArrayList<>(); //Для заявок
        subjects1.add("Математика");
        subjects1.add("Физика");
        List<String> subjects2 = new ArrayList<>(); //Для заявок
        subjects2.add("Английский язык");
        subjects2.add("Русский язык");
        RegisterExpertDto expert1 = new RegisterExpertDto("Эксперт1", "Эксперт", subjects1,
                "expert1", "123456");
        RegisterExpertDto expert2 = new RegisterExpertDto("Эксперт2", "Эксперт", subjects2,
                "expert2", "654321");
        ApplicationDto app1 = new ApplicationDto("Грант member1", "Описание гранта 1",
                subjects1, 10000);
        ApplicationDto app2 = new ApplicationDto("Грант member1", "Описание гранта 2",
                subjects2, 20000);
        ApplicationDto app3 = new ApplicationDto("Грант member2", "3 Описание гранта",
                subjects1, 30000);
        ApplicationDto app4 = new ApplicationDto("Грант member2", "4 Описание гранта",
                subjects2, 40000);

        server.startServer(null);

        tokenMember1 = server.registerMember(json.toJson(member1));
        tokenMember2 = server.registerMember(json.toJson(member2));
        tokenExpert1 = server.registerExpert(json.toJson(expert1));
        tokenExpert2 = server.registerExpert(json.toJson(expert2));

        server.addApplication(tokenMember1, json.toJson(app1));
        server.addApplication(tokenMember1, json.toJson(app2));
        server.addApplication(tokenMember2, json.toJson(app3));
        server.addApplication(tokenMember2, json.toJson(app4));
        server.rateApplication(tokenExpert1, json.toJson(app1), 3);
        server.rateApplication(tokenExpert1, json.toJson(app3), 1);
        server.rateApplication(tokenExpert2, json.toJson(app2), 5);
        server.rateApplication(tokenExpert2, json.toJson(app4), 5);
    }

    @Test
    void testStopServer() {
        prepareToSave();
        *//*Данные ниже нужны для сравнения*//*
        assertEquals(303, server.memberShowApplications(tokenMember1).length());
        assertEquals(303, server.memberShowApplications(tokenMember2).length());
        assertEquals(148, server.showRatedApplications(tokenExpert1).length());
        assertEquals(170, server.showRatedApplications(tokenExpert2).length());
        assertEquals("{\"response\":\"Database saved\"}", server.stopServer("database"));
    }

    @Test
    void testStartServer() {
        assertEquals("{\"response\":\"Database uploaded\"}", server.startServer("database"));

        *//*Проверка наличия авторизированних пользователей*//*
        assertEquals("{\"error\":\"Wrong user profile\"}", server.memberShowApplications(tokenMember1));
        assertEquals("{\"error\":\"Wrong user profile\"}", server.showRatedApplications(tokenExpert1));

        *//*Проверка состояния заявок на сервере*//*
        String newTokenMember1 = server.loginUser(json.toJson(new LoginDto("member1", "member1")));
        String newTokenMember2 = server.loginUser(json.toJson(new LoginDto("member2", "member2")));
        String newTokenExpert1 = server.loginUser(json.toJson(new LoginDto("expert1", "123456")));
        String newTokenExpert2 = server.loginUser(json.toJson(new LoginDto("expert2", "654321")));

        assertEquals(303, server.memberShowApplications(newTokenMember1).length());
        assertEquals(303, server.memberShowApplications(newTokenMember2).length());
        assertEquals(148, server.showRatedApplications(newTokenExpert1).length());
        assertEquals(170, server.showRatedApplications(newTokenExpert2).length());
        server.stopServer(null);
    }

}*/
