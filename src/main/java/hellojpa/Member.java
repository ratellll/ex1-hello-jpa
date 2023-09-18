package hellojpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // name이라는 속성이 있는데 클래스 이름을 웬만하면 따라감
//@Table(name = "MB") 만약 Member말고 다른 테이블과 매핑하고싶을경우 name을 지정해줌 , catalog, schema로 설정가능
public class Member {


    @Id // PK가 뭔ㄴ지 선언해야함
    private Long id;
    private String name;
    private int size;


    public Member (){

    }


    public Member(Long id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
