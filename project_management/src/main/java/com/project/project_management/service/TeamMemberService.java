package com.project.project_management.service;

import com.project.project_management.Storage.JsonFileStorage;
import com.project.project_management.model.TeamMember;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class TeamMemberService {

    private final JsonFileStorage jsonFileStorage;

    public TeamMemberService(JsonFileStorage jsonFileStorage) {
        this.jsonFileStorage = jsonFileStorage;
    }


    public List<TeamMember> getAllTeamMembers() throws IOException {
        return jsonFileStorage.readList(
                "team-members.json", TeamMember.class);
    }


    public TeamMember getTeamMemberById(String id) throws IOException {
        return getAllTeamMembers()
                .stream()
                .filter(member -> member.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public TeamMember createTeamMember(TeamMember member)
            throws IOException {

        List<TeamMember> members = getAllTeamMembers();

        member.setId(UUID.randomUUID().toString());
        members.add(member);

        jsonFileStorage.writeList("team-members.json", members);

        return member;
    }


    public TeamMember updateTeamMember(
            String id, TeamMember updatedMember) throws IOException {

        List<TeamMember> members = getAllTeamMembers();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(id)) {
                updatedMember.setId(id);
                members.set(i, updatedMember);

                jsonFileStorage.writeList("team-members.json", members);

                return updatedMember;
            }
        }

        return null;
    }

    public boolean deleteTeamMember(String id) throws IOException {
        List<TeamMember> members = getAllTeamMembers();

        boolean removed = members.removeIf(
                member -> member.getId().equals(id));

        if (removed) {
            jsonFileStorage.writeList("team-members.json", members);
        }

        return removed;
    }
}