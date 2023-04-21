package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
//@IdClass(Category.class)
public class Category {
    @Id //과거에는, id를 2개 이상 만들면 에러가 발생했다.
        //이를 해결 하기 위해 @idClass를 사용했었다. (기본 키를 여러개 조합해서 사용)
    private String cateCd;

    @Id
    private String subCateCd;

    private String cateNm;

}
