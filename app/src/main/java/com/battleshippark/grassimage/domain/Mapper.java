package com.battleshippark.grassimage.domain;

import com.annimon.stream.Stream;
import com.battleshippark.grassimage.data.NaverResultItem;
import com.battleshippark.grassimage.data.ReposNaverResult;

import java.util.List;

/**
 */

class Mapper {
    DomainResult from(ReposNaverResult reposResult) {
        return new DomainResult(
                reposResult.total, from(reposResult.items)
        );
    }

    private List<DomainResultItem> from(List<NaverResultItem> items) {
        return Stream.of(items).map(this::from).toList();
    }

    private DomainResultItem from(NaverResultItem item) {
        return new DomainResultItem(item.title, item.thumbnail);
    }
}
