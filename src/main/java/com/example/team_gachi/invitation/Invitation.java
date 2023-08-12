package com.example.team_gachi.invitation;

import com.example.team_gachi.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "invitation")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User invitingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User invitedUser;

    public Invitation(User invitingUser, User invitedUser) {
        this.invitingUser = invitingUser;
        this.invitedUser = invitedUser;
    }
}
