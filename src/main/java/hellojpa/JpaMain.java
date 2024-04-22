package hellojpa;

import jakarta.persistence.*;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("bin");
            em.persist(member);
            Member member2 = new Member();
            member2.setId(2L);
            member2.setName("sol");
            em.persist(member2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
