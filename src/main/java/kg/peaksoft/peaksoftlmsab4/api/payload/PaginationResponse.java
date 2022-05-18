package kg.peaksoft.peaksoftlmsab4.api.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponse <S>{
    List<S> responseList;
    private int listSize;
    private int pageSize;
    private int currentPage;
    private int HowManyPages;
}
