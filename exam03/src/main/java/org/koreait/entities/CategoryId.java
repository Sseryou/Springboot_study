package org.koreait.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryId implements Serializable {
    //동등성 체크 : equals(), hashCode

    private String cateCd;

    private String subCateCd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryId that = (CategoryId) o;
        return Objects.equals(cateCd, that.cateCd) && Objects.equals(subCateCd, that.subCateCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cateCd, subCateCd);
    }
}
