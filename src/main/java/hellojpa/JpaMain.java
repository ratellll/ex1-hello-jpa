package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // EMF는 애플리케이션 로딩시점에 딱 하나를 만들어놔야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try { // 정석코드는 try catch해주는것 ,트랜잭션을 try안에다가 넣어주기

            Model model = new Model();
            model.setModel("dunkhigh");
            model.setName("panda");
            model.setPrice(150000);
            model.setSize(270);

            em.persist(model);

            tx.commit();
            //em.persist(member); //  트랜잭션부분 트랜잭션은 db상태를 변화시키기는 수행작업단위
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
