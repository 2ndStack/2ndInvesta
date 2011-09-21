package org.jasoet.mandiri.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: Deny Prasetyo, S.T.
 * http://www.jasoet.com
 */

@Entity
@Table(name = "email")
public class Email implements DomainObject {


    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "destination", length = 155, nullable = false)
    private String to;
    @Column(name = "subj", length = 155, nullable = false)
    private String subject;
    @Column(name = "txt", length = 1024, nullable = false)
    private String text;
    private boolean delivered = false;
    private int priority;
    @Temporal(value = TemporalType.DATE)
    private Date created = new Date();


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
