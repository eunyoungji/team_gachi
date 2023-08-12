package com.example.team_gachi.invitation;

import com.example.team_gachi.invitation.Invitation;
import com.example.team_gachi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByInvitedUserAndInvitingUser(User invitedrUser, User invitingUser);

    List<Invitation> findAllByInvitedUser(User user);
}