package co.edu.cuc.onlinelibrary.auth.domain.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPage<T> extends PageImpl<T> {
    private final T footer;

    public CustomPage(List<T> content, Pageable pageable, long total, T footer) {
        super(content,pageable,total);
        this.footer = footer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomPage<?> that = (CustomPage<?>) o;
        return Objects.equals(footer, that.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), footer);
    }
}
