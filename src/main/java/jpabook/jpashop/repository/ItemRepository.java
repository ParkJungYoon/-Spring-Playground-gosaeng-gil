package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * lombok에 @AllArgsConstructor가 생성자 주입 코드를 직접 넣어준다.
 * @RequiredArgsConstructor는 final로 된 친구들 주입
 * --자세한 내용은 MemberService에
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    //save()
    //id 가 없으면 신규로 보고 persist() 실행
    //id 가 있으면 이미 데이터베이스에 저장된 엔티티를 수정한다고 보고, merge() 를 실행
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
