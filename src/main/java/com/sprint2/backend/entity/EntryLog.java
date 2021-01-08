package com.sprint2.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "entry_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "enter_date", columnDefinition = "DATETIME")
    private LocalDateTime enterDate;

    @Column(name = "exit_date", columnDefinition = "DATETIME")
    private LocalDateTime exitDate;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_card_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
    private MemberCard memberCard;
}
