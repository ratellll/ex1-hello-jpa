package hellojpa;

import jakarta.persistence.*;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = createEntityManagerFactory("hello");// db당 하나 서버실행시 딱하나만 실행된다고 보면됨
        EntityManager em = emf.createEntityManager(); // 기능을 하나끝낼때마다 close
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team team = new Team();
            team.setName("빈이팀");
            em.persist(team);

            Member member = new Member();
            member.setUserName("솔이");
            member.setTeam(team);
            em.persist(member);

            //쿼리보고싶을때
            em.flush();
            em.clear();

            Member findMem = em.find(Member.class, member.getId());
            Team team1 = findMem.getTeam();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
