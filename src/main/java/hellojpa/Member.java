package hellojpa;


import jakarta.persistence.*;


@Entity //JPA를 사용해서 테이블과 매핑할 클래스를 지정해주는것
//@Entity(name ="mem) 이런식으로 JPA에서 사용할 엔티티 이름을 지정한다.  -JPA 쿼리에서 엔티티 이름을 지정
//@Table(name = "MBR") // Entity클래스의 세부설정을 위함  -데이터베이스의 테이블 이름을 지정
//@SequenceGenerator(name = "member_seq_generator",sequenceName = "member_seq")
//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ",
//initialValue = 1,allocationSize = 50)
public class Member {

    @Id
    @GeneratedValue//(strategy = GenerationType.SEQUENCE)//,generator = "member_seq_generator")
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        team.getMembers().add(this);
    }
}
