package com.it355.projekat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "shopping")
public class Shopping extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "shopping_id")
    private Integer id;

    @JoinColumn(name = "watch_fk", referencedColumnName = "watch_id")
    @ManyToOne
    private Watch watch;

    @JoinColumn(name = "member_fk", referencedColumnName = "member_id")
    @ManyToOne
    private Member member;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private Double price;

    public Shopping(Watch watch, Member member, LocalDate date, Double price) {
        this.watch = watch;
        this.member = member;
        this.date = date;
        this.price = price;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
