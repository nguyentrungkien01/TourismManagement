package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Storage", schema = "TourismManagement")
public class StorageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stor_id")
    private int storId;
    @Basic
    @Column(name = "stor_name")
    private String storName;
    @Basic
    @Column(name = "stor_slug")
    private String storSlug;
    @Basic
    @Column(name = "stor_is_active")
    private byte storIsActive;

    public int getStorId() {
        return storId;
    }

    public void setStorId(int storId) {
        this.storId = storId;
    }

    public String getStorName() {
        return storName;
    }

    public void setStorName(String storName) {
        this.storName = storName;
    }

    public String getStorSlug() {
        return storSlug;
    }

    public void setStorSlug(String storSlug) {
        this.storSlug = storSlug;
    }

    public byte getStorIsActive() {
        return storIsActive;
    }

    public void setStorIsActive(byte storIsActive) {
        this.storIsActive = storIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageEntity that = (StorageEntity) o;
        return storId == that.storId && storIsActive == that.storIsActive && Objects.equals(storName, that.storName) && Objects.equals(storSlug, that.storSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storId, storName, storSlug, storIsActive);
    }
}
