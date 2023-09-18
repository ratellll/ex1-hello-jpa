package hellojpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
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
