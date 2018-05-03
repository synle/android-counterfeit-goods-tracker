package com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao;

/**
 * Created by syle on 5/2/18.
 */

public class Item {
    String id;
    String name;
    String nextpubkey;
    String payload;

    // last block hash
    String hash;


    @Override
    public String toString() {
        return getId() + " : " + getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextpubkey() {
        return nextpubkey;
    }

    public void setNextpubkey(String nextpubkey) {
        this.nextpubkey = nextpubkey;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
