package com.sinaukoding.perpustakaan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@SuppressWarnings("unchecked")
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 5522962771614054312L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @PrePersist
    protected void onCreate() {
        setCreatedTime(new Date());
    }

    @PostPersist
    protected void onUpdate() {
        setUpdatedTime(new Date());
    }
}
