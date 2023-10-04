package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity // name이라는 속성이 있는데 클래스 이름을 웬만하면 따라감
//@Table(name = "MB") 만약 Member말고 다른 테이블과 매핑하고싶을경우 name을 지정해줌 , catalog, schema로 설정가능
public class Member {


    @Id
    private Long id;
    @Column(name = "name")
    //insertble,updateble 은 insert나 update를할지 설정해놓는것
    //nullable은 false를 주면 notNull로 들어가줌
    //unique는 true로 설정시 유니크로 넣어줌 이 설정을 넣어주려면 해당 클래스에 주는게 나음
    //columnDefinition은 내가 직접 설정을할수있음 ex)varchar(100)
    private String username;
    private Integer size;
    @Enumerated(EnumType.STRING) //ENUM타입을 쓰고싶을때붙어준다 //ODINAL은 사용금지
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) // 날짜타입  DATE, TIME, DATETIME  3가지가 있음
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP) // 잘 사용하지않음
    private Date lastModifiedDate;
    private LocalDateTime localDateTime; //이런식으로씀
    @Lob //큰 컨텐츠를넣고싶을때 ex) varchar를 넘어선것 문자일경우 CLob  byte면 BLob
    private String description;

    public Member() {

    }


    public Member(Long id, String username, int size) {
        this.id = id;
        this.username = username;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String name) {
        this.username = username;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
