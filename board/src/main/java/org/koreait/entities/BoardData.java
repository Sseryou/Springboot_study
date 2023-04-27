package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardData extends BaseUserEntity{
    @Id @GeneratedValue
    private Long id; //게시글 번호
    @Column(nullable = false)
    private String subject; //제목

    @Lob
    @Column(nullable = false)
    private String content; //내용


}
