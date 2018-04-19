package com.oggtechnologies.creogi.inventory;

import com.oggtechnologies.creogi.items.Item;

public class ItemSlot {
    private Item item;

    ItemSlot(){

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
