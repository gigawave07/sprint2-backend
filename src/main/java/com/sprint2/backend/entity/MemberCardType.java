package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "member_card_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "member_type_name", columnDefinition = "VARCHAR(50)")
    private String memberTypeName;

    // relationship

    @OneToMany(mappedBy = "memberCardType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MemberCard> memberCardList;
}
