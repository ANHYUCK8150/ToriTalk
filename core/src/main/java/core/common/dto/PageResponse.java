package core.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

    private List<T> contents = new ArrayList<>();

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;

    @Builder
    public PageResponse(List<T> contents, Integer pageNumber, Integer pageSize, Integer totalPages,
                        Long totalElements) {
        this.contents = contents;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}

