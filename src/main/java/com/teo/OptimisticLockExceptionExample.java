package com.teo;

import com.teo.entities.ItemPhoto;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class OptimisticLockExceptionExample {
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction transaction;

    @Getter
    @Setter
    private String message;

    public void causeOptLockException() {
        ExecutorService es = Executors.newFixedThreadPool(2);
        AtomicReference<String> msg = new AtomicReference<>();

        es.execute(() -> {
            try {
                ItemPhoto photo = em.find(ItemPhoto.class, 2L);
                transaction.begin();

                photo.setName("The best!");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                em.merge(photo);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                msg.set(e.getMessage());
            }
        });

        es.execute(() -> {
            try {
                ItemPhoto photo = em.find(ItemPhoto.class, 2L);
                transaction.begin();

                photo.setName("The even better!");
                em.merge(photo);

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        es.shutdown();

        try {
            es.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ItemPhoto photo = em.find(ItemPhoto.class, 2L);
        System.out.println("ItemPhoto loaded: " + photo.getName());
        message = msg.get();
    }
}
