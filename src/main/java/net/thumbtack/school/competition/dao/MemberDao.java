package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Member;

public interface MemberDao {
    String insertMember(Member member) throws CompetitionException;
    String insertApplication(TokenDtoResponse token, Application application) throws CompetitionException;
    String memberShowApplications(TokenDtoResponse token) throws CompetitionException;
    String memberDeleteApplication(String token, Application application) throws CompetitionException;
}
