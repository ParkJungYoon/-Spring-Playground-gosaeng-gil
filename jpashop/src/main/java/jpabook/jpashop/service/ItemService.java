package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 변경 감지 기능 사용
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // id를 기준으로 실제 영속 상태 엔티티를 가져옴
        Item findItem = itemRepository.findOne(itemId);
        // 나중에는 변경을 item 안에서 할 수 있도록 한다!! 그래야 추적하기 편함!
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // 이 상태에서는 save 같은 것이 필요없다. 트랜잭셔널 어노테이션에 의해서 커밋될 때 JPA 플러시로 반영된다.
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
