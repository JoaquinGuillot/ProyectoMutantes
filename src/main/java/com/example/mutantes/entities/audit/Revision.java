package com.example.mutantes.entities.audit;

import com.example.mutantes.config.CustomRevisionListener;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@RevisionEntity(CustomRevisionListener.class)
@Data
public class Revision implements Serializable {
     private static final Long serialVersionUID =1L;

     @Id
    @GeneratedValue
    @SequenceGenerator(
            name = "revision_seq",
            sequenceName = "rback.seq_revision_id"
    )
     @RevisionNumber
     private int id;
     @Column(name = "REVISION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @RevisionTimestamp
    private Date date;
}

