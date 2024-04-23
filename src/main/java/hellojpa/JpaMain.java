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
           /** //비영속
            Member member = new Member();
            member.setId(1L);
            member.setName("bin");
            // setName까지 비영속상태 jpa랑 관계가 없기때문

            // persist를 시작하며 영속성 이때 db에 저장되어지진않음
            em.persist(member);

            Member member2 = new Member();
            member2.setId(2L);
            member2.setName("sol");
            em.persist(member2);

            tx.commit();**/

            Member member = new Member(200L,"member1");
            em.persist(member);
            //db에 미리 반영하거나 쿼리를 확인하고싶으면 flush를 사용하여 강제호출
            em.flush();

            System.out.println("================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }



}
