package com.battleshippark.grassimage.domain;

import java.util.List;

/**
 */

public class DomainResult {
    public int total;
    public List<DomainResultItem> items;

    public DomainResult(int total, List<DomainResultItem> items) {
        this.total = total;
        this.items = items;
    }
}
