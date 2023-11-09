package hellojpa;


import jakarta.persistence.*;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
 private String name;

 @OneToMany(mappedBy = "team") // 반대편 변수명 넣어주기
 private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
