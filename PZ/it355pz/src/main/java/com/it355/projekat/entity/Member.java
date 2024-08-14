package com.it355.projekat.entity;

import com.it355.projekat.entity.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "member_id")
    private Integer id;

    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    @OneToOne
    private UserEntity user;

    @Column(name = "member_number")
    private Integer memberNumber;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MemberType type;

    public Member(UserEntity user, Integer memberNumber, Integer discount, MemberType type) {
        this.user = user;
        this.memberNumber = memberNumber;
        this.discount = discount;
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }
}
