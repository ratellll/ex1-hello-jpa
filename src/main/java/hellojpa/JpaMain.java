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

            Member member1 = new Member();
            member1.setUserName("현빈쓰");

            Member member2 = new Member();
            member2.setUserName("한솔쓰");

            Member member3 = new Member();
            member3.setUserName("솔빈쓰");
            System.out.println("=============");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1 == " + member1.getId());
            System.out.println("member2 == " + member2.getId());
            System.out.println("member3 == " + member3.getId());

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
