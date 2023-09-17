
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
- 