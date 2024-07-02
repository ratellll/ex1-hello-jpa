

엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유하기

엔티티매니저는 쓰레드간에 공유 x (사용하고 버려야댐)

JPA 모든 데이터 변경은 트랜잭션 안에서 실행하기 

JPA에서 가장 중요한것은 
객체와 관계형 데이터베이스 매핑 (정적인부분 )

영속성 컨텍스트 (엔티티매니저를 통해서 접근)
- JPA이해하는데 가장 중요한 용어
- 엔티티를 영구 저장하는 환경이라는뜻
- EntityManager.persist(entity); (DB에 저장하는ㄴ게아니라 영속성 컨텍스트에 저장하는것)
- 논리적인 개념 (눈에보이지않음)

엔티티매니저를 생성하면 영속성 컨텍스트가 생성이됨 (1:1)

엔티티의 생명주기
- 비영속(new/transient) 영속성 컨텍스트와  전혀 관계가 없는 새로운상태
- //객체를 생성한상태 (비영속)
- Member member = new Member();
- member.setId("gg");
- -----------------------------------
- 영속(managed) 영속성 컨텍스트에 관리되는 상태
- - //객체를 생성한상태 (비영속)
- Member member = new Member();
- member.setId("gg");
- EntityManager em = emf.createEntityManager();
- em.getTransaction().begin();
- //객체를 저장한상태 (영속) 커밋을해야지 db에 저장됨
- em.persist(member);
- ------------------------------------
- 준영속(detached) 영속성 컨텍스트에 저장되었다가 분리된 상태


영속엔티티의 동일성 보장
1차캐시로 반복 가능한 읽기 등급의 트랜잭션 격리수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공해줌(a는 1차캐시 b는 2는 1차캐시에 없어도 동일함)


엔티티는 변경감지를한다 
Set을 해주고 update를 안쳐줘도 commit할때 알아서 처리해줌

플러시는 
영속성 컨텍스트의 변경내용을 데이터베이스에 반영하는것
데이터 트랜잭션이 시작되면 플러시가 발동됨
- 변경감지 -> 수정된 엔티티 쓰기 지연 SQL저장소에 등록 -> 쓰기지연 SQL저장소의 쿼리를 데이터베이스에 전송 (등록 수정 삭제쿼리)
직접호출하거나 트랜잭션을 커밋, 아니면 JPQL실행시 호출됨
플러시를 사용해도 1차캐시가 지워지지않음 
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화 하는작업
- 트랜잭션이라는 작업단위가 중요@ 커밋직전에만 동기화를하면됨

-----------------------------------------------

@Entity
@Entity가 붙은 클래스는 JPA가 관리, 엔티티라 한다
JPA를 사용해서 테이블과 매핑할 클래스는 Entity 필수 

주의
- 기본생성자 필수 (파라미터가 없는 public 이나 protected 생성자)
- final클래스 , enum, interface, inner클래스 사용x
- 저장할 필드에 final 사용x

생성된 DDL은 불안할수있으니 운영에서 웬만하면 사용 x 다듬어서 사용해야함
DDL종류
create :  기존테이블 삭제후 다시 생성
create-drop : 종료시점에서 테이블 드랍
update : 번경분만 반영 (운영db사용 xxxx)
validate : 엔티티와 테이블이 정상 매핑되었는지만 확인
none : 사용 x

개발초기에는 create 또는 update (웬만하면 vaildate만 개발서버에서도 x)
테스트서버는 UPDATE 또는 vaildate
스테이징과 운영서버는 vaildate or noe



기본매핑시----
시간찍을땐 편하게 java LocalDateTime사용하기
Enum사용시 String 꼭붙여주기 ORDINAL사용시 꼬일수있음
Column매핑시 nullable이나 insertlable,updateable설정하기

객체의 양방향관계는 사실 양방향관계가 아니라 서로다른 단방향관계 2개이다.
객체를 양방향으로 참조하려면 단방향 연관관계를 2개만들어야한다.

ex) A- > B (a.getB())       class A {
                                    B b;
                                        }
ex) B- > A (b.getA())       class B {
                                    A a;
                                        }

테이블은 외래키 하나로 두 테이블의 연관관계를 관리

ex ) join
 

연관 관계는 외래키가 항상 주인 
Many쪽이 항상 주인이라고생각한다


다대일 단방향
- 항상 다 쪽에 외래키가 가야함

다대일 양방향
- 양방향을 한다고해도 테이블에 영향은 없음 (mappedBy 사용필수)

일대다 단방향
- 일대다 단방향은 일대다 에서 1이 연관 관계의주인
- 테이블 일대다 관계는 항상 다 쪽에 외래키가있다
- 객체와 테이블 차이떄문에 *반대편 테이블의 외래키를 관리하는 특이한구조*
- JoinColumn 을 꼭 사용해야함  그렇지않으면 조인테이블 방식을 사용함 ( 중간에 테이블을 하나 추가함)

일대다 단방향 매핑의 단점
- 엔티티가 관리하는 외래 키가 다른 테이블에있음
- 연관관계 관리를 위해 추가로 UPDATE SQL실행
- 일대다 단방향 매핑보다는 양방향 매핑을 사용하자


일대일 관계
- 일대일 관계는 그 반대도 일대일
- 주테이블이나 대상테이블중에 외래키 선택가능
- 외래 키에 데이터베이스 유니크 (UNI) 제약조건 추가
----------------------------------------------------------------------------------------
JPA를 사용해야하는 이유
-SQL 중심적인 개발에서 객체 중심으로 개발
-생산성
-유지보수
-패러다임의 불일치 해결
-성능
-데이터접근 추상화와 벤더 독립성
- 표준
------------------------------------------------------------------------------------------------
주의사항 
- 엔티티매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서공유
- 엔티티매니저는 쓰레드간에 공유 x (사용하고 버려야함 , 로직이돌때 한번사용하고버리는것
- ***** JPA의 모든 데이터 변경은 트랙잭션 안에서 실행 ****


    /**
     * Member member = new Member();
     * member.setid("mem");
     * member.setUserName("bin");
     * 
     * //1차캐시에 저장됨
     * em.persist(member);
     * 
     * //1차캐시에서 조회
     * Member findMember = em.find(Member.class, "mem");
     */

플러시  

플러시가 발생될때  변경감지(더티체킹) , 수정된 엔티티 쓰기지연SQL저장소에 등록
쓰기지연 SQL 저장소의 쿼리를 데이터베이스에 전송  ---- 
- 하지만 플러시가 발생한다고 데이터베이스 트랜잭션이 커밋되는건아님

영속성 컨텍스트를 플러시하는 방법
-em.flush() - 직접호출
트랜잭션 커밋 - 플러시 자동 호출
 
플러시는 
- 영속성 컨텍스트를 비우지않음 
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 - > 커밋직전에만 동기화 하면됨




- ------------------------------------------------------------------------------------------------
@Entity
- @Entity가 붙은 클래스는 JPA가 관리, 엔티티라고한다.
- JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
***주의***
- 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자)
- final 클래스 , enum,interface,inner클래스 사용x
- 저장할 필드에 final사용x

*속성 : name
- JPA에서 사용할 엔티티 이름을 지정한다
- 기본값: 클래스 이름을 그대로 사용(ex:Member)
- 같은 클래스 이름이 없으면 가급적 기본값을 사용한다


@Column
-nullable(DDL) null값의 허용 여부를 설정, false로 설정하면 DDL생성시에 not null제약이붙는다
-unique(DDL)  @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제 약조건을 걸 때 사용한다.
-columnDefinition (DDL)   데이터베이스 컬럼 정보를 직접 줄 수 있다.   ex) varchar(100) default ‘EMPTY'

id 생성 전략중 
TABLE 전략 이라는게 있음
키 생성 전용 테이블을 하나 만들어서 DB시퀀스를 흉내내는 전략
장점은 모든 DB에 다 적용을할수있음 하지만 단점은 테이블을 직접사용하다보니 성능이슈가있음 

권장하는 식별자 전략 
- 기본키 제약조건: null아님, 유일해야함,변하면안됌
만일 IDENTITY 전략을 사용한다면 DB에 값이 들어가봐야 키를 확인할수있다.