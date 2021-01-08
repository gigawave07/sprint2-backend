package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "member_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "start_date", columnDefinition = "DATETIME")
    private LocalDateTime startDate;

    @Column(name = "end_date", columnDefinition = "DATETIME")
    private LocalDateTime endDate;

    @Column(name = "price", columnDefinition = "DOUBLE")
    private Double price;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_card_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private MemberCardType memberCardType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
    private Car car;

    @OneToMany(mappedBy = "memberCard", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
    private List<EntryLog> entryLogList;
}
