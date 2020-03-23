package net.thumbtack.school.competition;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestApplications {
    private String token1, token2, token3, tokenExpert1, tokenExpert2;
    private ApplicationDto appError1, appError2, app1, app2, app3, app4, app5, app6;
    private String[] apps123;
    private List<String> subjects2;
    private Gson json = new Gson();
    private Server server = new Server();

    @BeforeEach
    void setUp() {
        server.startServer(null);
        RegisterMemberDto member1 = new RegisterMemberDto("Олег", "Сухоруков",
                "ООО \"Газнефть\"", "@leg", "123456");
        RegisterMemberDto member2 = new RegisterMemberDto("Валентин", "Агапкин",
                "ООО \"Газнефть-торг\"", "AgaPkin213", "qwerty");
        RegisterMemberDto member3 = new RegisterMemberDto("Александр", "Золотарев",
                "ООО \"San-Bro\"", "Zolotarev@less", "654321");

        List<String> subjects3 = new ArrayList<>(); //Для экспертов
        subjects3.add("Математика");
        subjects3.add("Английский язык");
        List<String> subjects4 = new ArrayList<>(); //Для экспертов
        subjects4.add("Информатика");
        subjects4.add("Физика");

        RegisterExpertDto expert1 = new RegisterExpertDto("Эксперт1", "Эксперт", subjects3,
                "expert1@", "123456");
        RegisterExpertDto expert2 = new RegisterExpertDto("Эксперт2", "Эксперт", subjects4,
                "expert2@", "654321");

        token1 = server.registerMember(json.toJson(member1));
        token2 = server.registerMember(json.toJson(member2));
        token3 = server.registerMember(json.toJson(member3));
        tokenExpert1 = server.registerExpert(json.toJson(expert1));
        tokenExpert2 = server.registerExpert(json.toJson(expert2));
    }

    private void forAddApp() {
        List<String> subjects1 = new ArrayList<>(); //Для заявок
        subjects1.add("Математика");
        subjects1.add("Физика");
        subjects2 = new ArrayList<>(); //Для заявок
        subjects2.add("Английский язык");
        subjects2.add("Русский язык");

        appError1 = new ApplicationDto("Грант Олега Сухорукова 1", "",
                subjects1, 40000);
        app1 = new ApplicationDto("Грант Олега Сухорукова 1", "Описание гранта 1",
                subjects1, 40000);
        appError2 = new ApplicationDto("Грант Олега Сухорукова 1", "Описание гранта 1",
                subjects1, 40000);
        app2 = new ApplicationDto("Грант Олега Сухорукова 2", "Описание гранта 2",
                subjects2, 70000);
        app3 = new ApplicationDto("Грант Олега Сухорукова 3", "Описание гранта 3",
                subjects2, 23000);
        app4 = new ApplicationDto("Грант Валентина Агапкина", "Описание гранта",
                subjects1, 50000);
        app5 = new ApplicationDto("Грант Александра Золотарева 1", "1 Описание гранта",
                subjects1, 60000);
        app6 = new ApplicationDto("Грант Александра Золотарева 2", "2 Описание гранта",
                subjects2, 465000);

        apps123 = new String[3];
        apps123[0] = json.toJson(app1);
        apps123[1] = json.toJson(app2);
        apps123[2] = json.toJson(app3);
    }

    private void addApp() {
        forAddApp();
        server.addApplication(token1, apps123);
        server.addApplication(token2, json.toJson(app4));
        server.addApplication(token3, json.toJson(app5));
        server.addApplication(token3, json.toJson(app6));
    }

    private void rateApp() {
        addApp();
        server.rateApplication(tokenExpert1, json.toJson(app1), 5);
        server.rateApplication(tokenExpert1, json.toJson(app2), 3);
        server.rateApplication(tokenExpert2, json.toJson(app2), 4);
    }


    @Test
    void testAddApplications() {
        forAddApp();
        /*Добавление заявок на сервер*/
        assertNotEquals("{\"response\":\"ok\"}", server.addApplication(token1, json.toJson(appError1)));
        assertEquals("{\"error\":\"invalid request\"}", server.addApplication(token1, json.toJson(null)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token1, apps123));
        assertNotEquals("{\"response\":\"ok\"}", server.addApplication(token1, json.toJson(appError2)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token2, json.toJson(app4)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token3, json.toJson(app5)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token3, json.toJson(app6)));
    }

    @Test
    void testShowApplications() {
        addApp();
        assertEquals(485, server.memberShowApplications(token1).length());
        assertEquals(163, server.memberShowApplications(token2).length());
        assertEquals(336, server.memberShowApplications(token3).length());
        assertEquals(952, server.expertShowApplications(tokenExpert1).length());
        assertEquals(466, server.expertShowApplications(tokenExpert2).length());
        assertEquals("{\"error\":\"Invalid request\"}", server.expertShowApplications(tokenExpert1, subjects2));
        List<String> list = new ArrayList<>();
        list.add("Математика");
        assertEquals(466, server.expertShowApplications(tokenExpert1, list).length());
    }

    @Test
    void testRateApplications() {
        addApp();
        /*Оценка заявок*/
        assertEquals("{\"error\":\"invalid rating\"}", server.rateApplication(tokenExpert1, json.toJson(app1), 6));
        assertEquals("{\"response\":\"You rated application: Грант Олега Сухорукова 1\"}",
                server.rateApplication(tokenExpert1, json.toJson(app1), 5));
        assertEquals("{\"response\":\"You rated application: Грант Олега Сухорукова 2\"}",
                server.rateApplication(tokenExpert1, json.toJson(app2), 3));
        assertEquals("{\"error\":\"Such application not exist\"}",
                server.rateApplication(tokenExpert2, json.toJson(app2), 4));
        assertEquals(181, server.showRatedApplications(tokenExpert1).length());
        assertEquals("{\"error\":\"There are no rated applications\"}", server.showRatedApplications(tokenExpert2));
    }

    @Test
    void testChangeRatingApplications() {
        rateApp();
        assertEquals("{\"response\":\"ok\"}", server.changeRating(tokenExpert1, json.toJson(app1), 1));
        assertEquals(181, server.showRatedApplications(tokenExpert1).length());

    }

    @Test
    void testDeleteRatingApplications() {
        rateApp();
        assertEquals("{\"response\":\"ok\"}", server.deleteRating(tokenExpert1, json.toJson(app2)));
        assertEquals("{\"error\":\"There are no rated applications\"}", server.showRatedApplications(tokenExpert2));
    }

    @Test
    void testDeleteApplications() {
        rateApp();
        /*Проверка результатов удаления заявок одного пользователя*/
        server.memberDeleteApplication(token1, json.toJson(app1));
        assertEquals(336, server.memberShowApplications(token1).length());
        assertEquals("{\"error\":\"Such application not exist\"}",
                server.memberDeleteApplication(token1, json.toJson(app5)));
        assertEquals(104, server.showRatedApplications(tokenExpert1).length());
        server.memberDeleteApplication(token1, json.toJson(app2));
        assertEquals("{\"error\":\"There are no rated applications\"}", server.showRatedApplications(tokenExpert1));
        assertEquals(176, server.memberShowApplications(token1).length());
        server.memberDeleteApplication(token1, json.toJson(app3));
        assertEquals("{\"response\":\"[]\"}", server.memberShowApplications(token1));
    }

}
