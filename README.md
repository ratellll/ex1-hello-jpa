
엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유하기

엔티티매니저는 쓰레드간에 공유 x (사용하고 버려야댐)

JPA 모든 데이터 변경은 트랜잭션 안에서 실행하기 

JPA에서 가장 중요한것은 
객체와 관계형 데이터베이스 매핑 (정적인부분 )

영속성 컨텍스트 (엔티티매니저를 통해서 접근)
- JPA이해하는데 가장 중요한 용어
-  엔티티를 영구 저장하는 환경이라는뜻
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