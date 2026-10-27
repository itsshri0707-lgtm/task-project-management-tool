package com.project.project_management.controller;

import com.project.project_management.model.TeamMember;
import com.project.project_management.service.TeamMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }


    @GetMapping
    public List<TeamMember> getAllTeamMembers() throws IOException {
        return teamMemberService.getAllTeamMembers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeamMember> getTeamMemberById(
            @PathVariable String id) throws IOException {

        TeamMember member =
                teamMemberService.getTeamMemberById(id);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(member);
    }


    @PostMapping
    public ResponseEntity<TeamMember> createTeamMember(
            @RequestBody TeamMember member) throws IOException {

        TeamMember createdMember =
                teamMemberService.createTeamMember(member);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdMember);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TeamMember> updateTeamMember(
            @PathVariable String id,
            @RequestBody TeamMember member) throws IOException {

        TeamMember updatedMember =
                teamMemberService.updateTeamMember(id, member);

        if (updatedMember == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(
            @PathVariable String id) throws IOException {

        boolean deleted =
                teamMemberService.deleteTeamMember(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}