package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Member;

public interface MemberDao {
    String insertMember(Member member) throws CompetitionException;
}
