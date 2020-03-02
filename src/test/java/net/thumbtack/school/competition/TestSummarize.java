package net.thumbtack.school.competition;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.server.Server;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSummarize {
    @Test
    void testSummarizeApp() {
        Gson json = new Gson();
        Server server = new Server();
        server.startServer(null);
        RegisterMemberDto member1 = new RegisterMemberDto("Member1", "Member11",
                "ООО \"Member\"", "member1", "member1");
        RegisterMemberDto member2 = new RegisterMemberDto("Member2", "Member22",
                "Teo.com", "member2", "member2");

        List<String> subjects1 = new ArrayList<>(); //Для заявок + эксперт 1
        subjects1.add("Математика");
        subjects1.add("Физика");
        List<String> subjects2 = new ArrayList<>(); //Для заявок + эксперт 2
        subjects2.add("Английский язык");
        subjects2.add("Русский язык");
        List<String> subjects3 = new ArrayList<>(); //Для заявок + эксперт 3
        subjects3.add("Математика");
        subjects3.add("Русский язык");

        RegisterExpertDto expert1 = new RegisterExpertDto("Эксперт1", "Эксперт", subjects1,
                "expert1", "123456");
        RegisterExpertDto expert2 = new RegisterExpertDto("Эксперт2", "Эксперт", subjects2,
                "expert2", "654321");
        RegisterExpertDto expert3 = new RegisterExpertDto("Эксперт3", "Эксперт", subjects3,
                "expert3", "123456");

        ApplicationDto app1 = new ApplicationDto("Грант member1 (1)", "Эксперт 1,3",
                subjects1, 10000); //Средн оценка: 1.5
        ApplicationDto app2 = new ApplicationDto("Грант member1 (2)", "Эксперт 1,3",
                subjects1, 10000); //Средн оценка: 2.5
        ApplicationDto app3 = new ApplicationDto("Грант member1 (3)", "Эксперт 2,3",
                subjects2, 40000); //Средн оценка: 3.5
        ApplicationDto app4 = new ApplicationDto("Грант member1 (4)", "Эксперт 1,2,3",
                subjects3, 40000); //Средн оценка: 3.67
        ApplicationDto app5 = new ApplicationDto("Грант member2 (5)", "Эксперт 2,3",
                subjects2, 40000); //Средн оценка: 4
        ApplicationDto app6 = new ApplicationDto("Грант member2 (6)", "Эксперт 1,2,3",
                subjects3, 50000); //Средн оценка: 3
        ApplicationDto app7 = new ApplicationDto("Грант member2 (7)", "Эксперт 1,2,3",
                subjects3, 50000); //Средн оценка: 4
        ApplicationDto app8 = new ApplicationDto("Грант member2 (8)", "Эксперт 1,3",
                subjects1, 300000); //Средн оценка: 5
        ApplicationDto app9 = new ApplicationDto("Грант member2 (9)", "Эксперт 2,3",
                subjects2, 10000); //Средн оценка: 3

        String tokenMember1 = server.registerMember(json.toJson(member1));
        String tokenMember2 = server.registerMember(json.toJson(member2));
        String tokenExpert1 = server.registerExpert(json.toJson(expert1));
        String tokenExpert2 = server.registerExpert(json.toJson(expert2));
        String tokenExpert3 = server.registerExpert(json.toJson(expert3));

        String[] member1Apps = new String[4];
        member1Apps[0] = json.toJson(app1);
        member1Apps[1] = json.toJson(app2);
        member1Apps[2] = json.toJson(app3);
        member1Apps[3] = json.toJson(app4);
        String[] member2Apps = new String[5];
        member2Apps[0] = json.toJson(app5);
        member2Apps[1] = json.toJson(app6);
        member2Apps[2] = json.toJson(app7);
        member2Apps[3] = json.toJson(app8);
        member2Apps[4] = json.toJson(app9);

        assertEquals("{\"response\":\"ok\"}", server.addApplication(tokenMember1, member1Apps));
        assertEquals("{\"response\":\"ok\"}", server.addApplication(tokenMember2, member2Apps));

        /*Эксперт 1 оценивает заявки: 1,2,4,6,7,8*/
        assertEquals(55, server.rateApplication(tokenExpert1, json.toJson(app1), 2).length());
        assertEquals(55, server.rateApplication(tokenExpert1, json.toJson(app2), 3).length());
        assertEquals(55, server.rateApplication(tokenExpert1, json.toJson(app4), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert1, json.toJson(app6), 3).length());
        assertEquals(55, server.rateApplication(tokenExpert1, json.toJson(app7), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert1, json.toJson(app8), 5).length());

        /*Эксперт 2 оценивает заявки: 3,4,5,6,7,9*/
        assertEquals(55, server.rateApplication(tokenExpert2, json.toJson(app3), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert2, json.toJson(app4), 3).length());
        assertEquals(55, server.rateApplication(tokenExpert2, json.toJson(app5), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert2, json.toJson(app6), 3).length());
        assertEquals(55, server.rateApplication(tokenExpert2, json.toJson(app7), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert2, json.toJson(app9), 3).length());

        /*Эксперт 3 оценивает все заявки*/
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app1), 1).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app2), 2).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app3), 3).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app4), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app5), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app6), 3).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app7), 4).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app8), 5).length());
        assertEquals(55, server.rateApplication(tokenExpert3, json.toJson(app9), 3).length());

        SummarizeDto summarizeDto1 = new SummarizeDto(180000,3);
        SummarizeDto summarizeDto2 = new SummarizeDto(130000,3);
        SummarizeDto summarizeDto3 = new SummarizeDto(100000,5);
        SummarizeDto summarizeDto4 = new SummarizeDto(10000,1);
        SummarizeDto summarizeDto5 = new SummarizeDto(1,1);
        SummarizeDto summarizeDto6 = new SummarizeDto(0,0);
        assertEquals(591,server.summarize(json.toJson(summarizeDto1)).length());
        assertEquals(361,server.summarize(json.toJson(summarizeDto2)).length());
        assertEquals("{\"error\":\"There are no matching entries\"}",server.summarize(json.toJson(summarizeDto3)));
        assertEquals(131,server.summarize(json.toJson(summarizeDto4)).length());
        assertEquals("{\"error\":\"There are no matching entries\"}",server.summarize(json.toJson(summarizeDto5)));
        assertEquals("{\"error\":\"invalid request\"}",server.summarize(json.toJson(summarizeDto6)));
    }
}
