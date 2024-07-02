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

            Member member = new Member(4L, "뚱뚱뚱냥이");
            em.persist(member);

            System.out.println("=============");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
