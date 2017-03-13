package com.battleshippark.grassimage.domain;

import com.annimon.stream.Stream;
import com.battleshippark.grassimage.data.DaumResultItem;
import com.battleshippark.grassimage.data.ReposDaumResult;

import java.util.List;

/**
 */

class DaumMapper {
    public DomainResult from(ReposDaumResult reposResult) {
        return new DomainResult(
                reposResult.channel.result, from(reposResult.channel.item)
        );
    }

    private List<DomainResultItem> from(List<DaumResultItem> item) {
        return Stream.of(item).map(this::from).toList();
    }

    private DomainResultItem from(DaumResultItem resultItem) {
        return new DomainResultItem(resultItem.title, resultItem.thumbnail);
    }
}
