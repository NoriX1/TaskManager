package ru.compito.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="members")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    @JsonIgnore
    @EmbeddedId
    private MemberIdentity memberIdentity;

    @MapsId( "userId" )
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId( "boardId" )
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;

    public Member() {
    }

    public Member(User user, Board board, Role role) {
        this.user = user;
        this.board = board;
        this.role = role;
        this.memberIdentity = new MemberIdentity(user.getId(), board.getId());
    }

    public Member(MemberIdentity memberIdentity, Role role) {
        this.memberIdentity = memberIdentity;
        this.role = role;
    }

    public MemberIdentity getMemberIdentity() {
        return memberIdentity;
    }

    public void setMemberIdentity(MemberIdentity memberIdentity) {
        this.memberIdentity = memberIdentity;
    }

    public Member(Role role, User user, Board board) {
        this.role = role;
        this.user = user;
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member that = (Member) o;
        return (user.getId() == that.user.getId())&&(board.getId() == that.board.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + user.getId();
        result = 37*result + board.getId();
        return Long.valueOf(result).hashCode();
    }
}