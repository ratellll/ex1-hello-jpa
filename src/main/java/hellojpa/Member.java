package hellojpa;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity // name이라는 속성이 있는데 클래스 이름을 웬만하면 따라감
//@Table(name = "MB") 만약 Member말고 다른 테이블과 매핑하고싶을경우 name을 지정해줌 , catalog, schema로 설정가능
//persist들어가기전(영속성컨텍스트로들어가기전) 미리 시퀀스를 배당해줌
//allocationSize = 50 미리 50개를 셋팅해두고 차례대로쓰는것
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq",initialValue = 1,allocationSize = 50)
public class Member {


    @Id //직접할당할때사용
    //@GeneratedValue(strategy = GenerationType.AUTO) // db에맞춰서 자동으로 생성해줌
    //@GeneratedValue(strategy = GenerationType.SEQUENCE) // 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트(예: 오라클 시퀀스)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "member_seq_generator" ) //db에게 위임 시퀀스를 미리 얻어오는것 insert가 되기전에

    private String id;
    @Column(name = "name")
    //insertble,updateble 은 insert나 update를할지 설정해놓는것
    //nullable은 false를 주면 notNull로 들어가줌
    //unique는 true로 설정시 유니크로 넣어줌 이 설정을 넣어주려면 해당 클래스에 주는게 나음
    //columnDefinition은 내가 직접 설정을할수있음 ex)varchar(100)
    private String username;
    private Integer size;


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
