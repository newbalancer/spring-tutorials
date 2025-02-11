package com.example.demo.domain;

import com.example.demo.QuerydslApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@SpringBootTest(classes = QuerydslApplication.class)
class ItemTest {

  @Autowired EntityManagerFactory entityManagerFactory;

  @Nested
  // 영속성 ex
  @DisplayName("영속성 hashcode 관계")
  class HashCode {

    @Test
    @DisplayName("영속성 hashcode & equals 테스트")
    void findId() {

      EntityManager entityManager = entityManagerFactory.createEntityManager();

      EntityTransaction transaction = entityManager.getTransaction();

      transaction.begin();

      Item mock = Item.builder().name("test1").build();

      entityManager.persist(mock);

      transaction.commit();

      entityManager.clear();

      Item entity = entityManager.find(Item.class, mock.getId());

      Assertions.assertEquals(entity, mock);
      // hashCode 비교
      Assertions.assertEquals(entity.hashCode(), mock.hashCode());
    }
  }

  @Nested
  @DisplayName("비영속성")
  class New {

    @Test
    void newEntity() {

      // 그냥 new 생성 연산자를 사용을 하면 비 영속성 상태가 된다.
      Item mock = Item.builder().name("test1").build();
    }
  }

  @Nested
  @DisplayName("준영속성")
  class Detached {

    @Test
    @DisplayName("준영속성 테스트 케이스")
    void detached() {

      EntityManager entityManager = entityManagerFactory.createEntityManager();

      EntityTransaction transaction = entityManager.getTransaction();

      Item mock = Item.builder().name("test1").build();

      transaction.begin();

      entityManager.detach(mock);

      transaction.commit();

      entityManager.clear();
    }
  }
}
