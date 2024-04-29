package hellojpa;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {

    @Id //Id를 직접할당 (db에서 사용되는id)
    //@GeneratedValue Id를 자동생성시켜줌
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 기본 키 생성을 데이터베이스에 위임하는것
    //@GeneratedValue(strategy = GenerationType.SEQUENCE) 시퀀스사용 (자동생성되는것)
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 기본 키 생성을 데이터베이스에 위임하는것
    //@GeneratedVㅁalue(strategy = GenerationType.IDENTITY) 기본 키 생성을 데이터베이스에 위임하는것
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 기본 키 생성을 데이터베이스에 위임하는것
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING) //db에서 Enum을 쓰고싶을때 사용
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;


    public Member() {

    }


    public Member(Long id, String name, Integer age) {
        this.id = id;
        this.username = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
