package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponse <S>{
    List<S> responseList;
    private int totalPage;
    private int currentPage;
}
