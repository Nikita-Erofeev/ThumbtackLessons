package net.thumbtack.school.competition;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestApplications {

    @Test
    public void testApplicationFromUsers() {
        Gson json = new Gson();
        Server server = new Server();
        server.startServer(null);
        RegisterMemberDto member1 = new RegisterMemberDto("Олег", "Сухоруков",
                "ООО \"Газнефть\"", "@leg", "123456");
        RegisterMemberDto member2 = new RegisterMemberDto("Валентин", "Агапкин",
                "ООО \"Газнефть-торг\"", "AgaPkin213", "qwerty");
        RegisterMemberDto member3 = new RegisterMemberDto("Александр", "Золотарев",
                "ООО \"San-Bro\"", "Zolotarev@less", "654321");
        List<String> subjects1 = new ArrayList<>();
        subjects1.add("Математика");
        subjects1.add("Физика");
        List<String> subjects2 = new ArrayList<>();
        subjects2.add("Английский язык");
        subjects2.add("Русский язык");
        List<String> subjects3 = new ArrayList<>(); //Все заявки
        subjects3.add("Математика");
        subjects3.add("Английский язык");
        List<String> subjects4 = new ArrayList<>(); //Только 1 лист
        subjects4.add("Информатика");
        subjects4.add("Физика");

        RegisterExpertDto expert1 = new RegisterExpertDto("Эксперт1", "Эксперт", subjects3,
                "expert1@", "123456");
        RegisterExpertDto expert2 = new RegisterExpertDto("Эксперт2", "Эксперт", subjects4,
                "expert2@", "654321");

        ApplicationDto appError1 = new ApplicationDto("Грант Олега Сухорукова 1", "",
                subjects1, 40000);
        ApplicationDto app1 = new ApplicationDto("Грант Олега Сухорукова 1", "Описание гранта 1",
                subjects1, 40000);
        ApplicationDto appError2 = new ApplicationDto("Грант Олега Сухорукова 1", "Описание гранта 1",
                subjects1, 40000);
        ApplicationDto app2 = new ApplicationDto("Грант Олега Сухорукова 2", "Описание гранта 2",
                subjects2, 70000);
        ApplicationDto app3 = new ApplicationDto("Грант Олега Сухорукова 3", "Описание гранта 3",
                subjects2, 23000);
        ApplicationDto app4 = new ApplicationDto("Грант Валентина Агапкина", "Описание гранта",
                subjects1, 50000);
        ApplicationDto app5 = new ApplicationDto("Грант Александра Золотарева 1", "1 Описание гранта",
                subjects1, 60000);
        ApplicationDto app6 = new ApplicationDto("Грант Александра Золотарева 2", "2 Описание гранта",
                subjects2, 465000);
        String[] apps123 = new String[3];
        apps123[0] = json.toJson(app1);
        apps123[1] = json.toJson(app2);
        apps123[2] = json.toJson(app3);
        String token1 = server.registerMember(json.toJson(member1));
        String token2 = server.registerMember(json.toJson(member2));
        String token3 = server.registerMember(json.toJson(member3));
        String tokenExpert1 = server.registerExpert(json.toJson(expert1));
        String tokenExpert2 = server.registerExpert(json.toJson(expert2));
        assertNotEquals("{\"response\":\"ok\"}", server.addApplication(token1, json.toJson(appError1)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token1, apps123));
        assertNotEquals("{\"response\":\"ok\"}", server.addApplication(token1, json.toJson(appError2)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token2, json.toJson(app4)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token3, json.toJson(app5)));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(token3, json.toJson(app6)));
        assertEquals(485, server.memberShowApplications(token1).length());
        assertEquals(163, server.memberShowApplications(token2).length());
        assertEquals(336, server.memberShowApplications(token3).length());

        assertEquals(952, server.expertShowApplications(tokenExpert1).length());
        assertEquals(466, server.expertShowApplications(tokenExpert2).length());
        assertEquals("{\"error\":\"Invalid request\"}", server.expertShowApplications(tokenExpert1, subjects2)); //error
        List<String> list = new ArrayList<>();
        list.add("Математика");
        assertEquals(466, server.expertShowApplications(tokenExpert1, list).length());

        assertEquals("{\"error\":\"invalid rating\"}",server.rateApplication(tokenExpert1,json.toJson(app1),6));
        assertEquals("{\"response\":\"You rated application: Грант Олега Сухорукова 1\"}",server.rateApplication(tokenExpert1,json.toJson(app1),5));
        assertEquals("{\"response\":\"You rated application: Грант Олега Сухорукова 2\"}",server.rateApplication(tokenExpert1,json.toJson(app2),3));
        assertEquals("{\"error\":\"Such application not exist\"}",server.rateApplication(tokenExpert2,json.toJson(app2),4));
        assertEquals(181,server.showRatedApplications(tokenExpert1).length());
        assertEquals("{\"error\":\"There are no rated applications\"}",server.showRatedApplications(tokenExpert2));


        server.memberDeleteApplication(token1, json.toJson(app1));
        assertEquals(336, server.memberShowApplications(token1).length());
        assertEquals("{\"error\":\"Such application not exist\"}",
                server.memberDeleteApplication(token1, json.toJson(app5)));
        server.memberDeleteApplication(token1, json.toJson(app2));
        assertEquals(176, server.memberShowApplications(token1).length());
        server.memberDeleteApplication(token1, json.toJson(app3));
        assertEquals("{\"response\":\"[]\"}", server.memberShowApplications(token1));


//        Проверить как удаляются заявки и юзеры + изменение и удаление рейтинга
    }


}
