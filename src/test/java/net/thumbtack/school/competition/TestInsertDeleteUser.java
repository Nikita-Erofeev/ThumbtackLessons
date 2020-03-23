package net.thumbtack.school.competition;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.LoginDto;
import net.thumbtack.school.competition.dto.RegisterExpertDto;
import net.thumbtack.school.competition.dto.RegisterMemberDto;
import net.thumbtack.school.competition.server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestInsertDeleteUser {
    private RegisterMemberDto memberError1, member2, member3, memberSameLogin;
    private RegisterExpertDto expertSameLogin, expert2, expert3, expert4;
    private List<String> subjects2 = new ArrayList<>();
    private String memberToken3, expertToken3;
    private Gson json = new Gson();
    private Server server = new Server();

    @BeforeEach
    private void setUp() {
        server.startServer(null);
        member3 = new RegisterMemberDto("Александр", "Золотарев",
                "ООО \"San-Bro\"", "Zolotarev@less", "222222");
        subjects2.add("Английский язык");
        subjects2.add("Русский язык");
        expert3 = new RegisterExpertDto("Валерия", "Ким", subjects2,
                "Valeria32", "333333");
        memberToken3 = server.registerMember(json.toJson(member3));
        expertToken3 = server.registerExpert(json.toJson(expert3));
    }

    void prepareToTestRegisterUser() {
        memberError1 = new RegisterMemberDto("Игорь", "Шестакович",
                "ООО \"Газнефть\"", "Shestakovich@@", "ds");
        member2 = new RegisterMemberDto("Валентин", "Агапкин",
                "ООО \"Газнефть-торг\"", "AgaPkin213", "111111");
        memberSameLogin = new RegisterMemberDto("Виталий", "Милонов",
                "ООО \"Subway\"", "Zolotarev@less", "333333");

        List<String> subjects1 = new ArrayList<>();
        subjects1.add("Математика");
        subjects1.add("Физика");

        expertSameLogin = new RegisterExpertDto("Виталий", "Меньшиков", subjects1,
                "Zolotarev@less", "111111");
        expert2 = new RegisterExpertDto("Семен", "Климов", subjects2,
                "SemeN", "222222");
        expert4 = new RegisterExpertDto("Валерия", "Ким", subjects2,
                "Va", "333333");
    }

    @Test
    void testRegisterUser() {
        prepareToTestRegisterUser();
        server.stopServer(null);
        assertEquals("{\"error\":\"Database is not available\"}", server.registerMember(json.toJson(memberSameLogin)));
        assertEquals("{\"error\":\"Database is not available\"}", server.registerExpert(json.toJson(expertSameLogin)));
        server.startServer(null);
        String memberToken1 = server.registerMember(json.toJson(member2));
        String memberToken2 = server.registerMember(json.toJson(member3));
        String expertToken1 = server.registerExpert(json.toJson(expert2));
        String expertToken2 = server.registerExpert(json.toJson(expert3));
        assertEquals("{\"error\":\"invalid request\"}", server.registerMember(json.toJson(memberError1)));
        assertEquals(48, memberToken1.length());
        assertEquals(48, memberToken2.length());
        assertEquals("{\"error\":\"This user already exist\"}", server.registerMember(json.toJson(memberSameLogin)));
        assertEquals("{\"error\":\"This user already exist\"}", server.registerExpert(json.toJson(expertSameLogin)));
        assertEquals(48, expertToken1.length());
        assertEquals(48, expertToken2.length());
        assertEquals("{\"error\":\"invalid request\"}", server.registerExpert(json.toJson(expert4)));
    }

    @Test
    void testLogInUser() {
        assertEquals(48, server.loginUser(json.toJson(new LoginDto("Zolotarev@less", "222222"))).length());
        assertEquals(48, server.loginUser(json.toJson(new LoginDto("Valeria32", "333333"))).length());
    }

    @Test
    void testLogOutUser() {
        assertEquals("{\"response\":\"You are logged out\"}", server.logoutUser(memberToken3));
        assertEquals("{\"response\":\"You are logged out\"}", server.logoutUser(expertToken3));
    }

    @Test
    void testDeleteUser() {
        String memberToken2 = server.loginUser(json.toJson(new LoginDto("Zolotarev@less", "222222")));
        String expertToken2 = server.loginUser(json.toJson(new LoginDto("Valeria32", "333333")));
        assertEquals("{\"response\":\"User successfully deleted from the server\"}", server.deleteUser(memberToken2));
        assertEquals("{\"response\":\"User successfully deleted from the server\"}", server.deleteUser(expertToken2));
        assertEquals("{\"error\":\"invalid request\"}", server.loginUser(memberToken2));
    }

}
