package org.codecraftlabs.idgenerator.id.series;

import javax.annotation.Nonnull;

enum SeriesToSequence {
    DEFAULT("default", "default_sequence"),
    PRODUCT("product", "product_sequence");

    private final String seriesName;
    private final String sequenceName;

    SeriesToSequence(@Nonnull String seriesName, @Nonnull String sequenceName) {
        this.seriesName = seriesName;
        this.sequenceName = sequenceName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public String getSequenceName() {
        return sequenceName;
    }
}
