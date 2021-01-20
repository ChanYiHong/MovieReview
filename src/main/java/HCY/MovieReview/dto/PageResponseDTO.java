package HCY.MovieReview.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<EN, DTO> {

    private List<DTO> dtoList;

    private int totalPage;
    private int page;
    private int size;
    private int start;
    private int end;
    private boolean prev, next;

    private List<Integer> pageList;

    public PageResponseDTO (Page<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();

        makePage(result.getPageable());
    }

    private void makePage(Pageable pageable) {
        page = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        int tempEnd = (int) Math.ceil(pageable.getPageNumber() / 10.0) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}
