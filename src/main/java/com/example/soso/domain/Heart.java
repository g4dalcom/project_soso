package com.example.soso.domain;

import lombok.*;
import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "postId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column
    public Boolean isLike = false;


}
