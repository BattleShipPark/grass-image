package com.battleshippark.grassimage.domain;

import com.annimon.stream.Stream;
import com.battleshippark.grassimage.data.ReposNaverResultItem;
import com.battleshippark.grassimage.data.ReposNaverResult;

import java.util.List;

/**
 */

public class NaverMapper {
    DomainResult from(ReposNaverResult reposResult) {
        return new DomainResult(
                reposResult.total, from(reposResult.items)
        );
    }

    private List<DomainResultItem> from(List<ReposNaverResultItem> items) {
        return Stream.of(items).map(this::from).toList();
    }

    private DomainResultItem from(ReposNaverResultItem item) {
        return new DomainResultItem(item.title, item.thumbnail);
    }
}
