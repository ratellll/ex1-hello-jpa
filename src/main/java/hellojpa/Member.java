package hellojpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //JPA를 사용해서 테이블과 매핑할 클래스를 지정해주는것
//@Entity(name ="mem) 이런식으로 JPA에서 사용할 엔티티 이름을 지정한다.  -JPA 쿼리에서 엔티티 이름을 지정
//@Table(name = "MBR") // Entity클래스의 세부설정을 위함  -데이터베이스의 테이블 이름을 지정
public class Member {

    @Id
    private Long id;

    private String name;

    public Member() {

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
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
