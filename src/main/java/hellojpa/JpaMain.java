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
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("빈이짱");
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("빈이");
//            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
