package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends Item {

    private long id;
    private Item item;
    private ItemType itemType;

}
