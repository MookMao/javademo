package com.mook.java.generic;

import java.time.LocalDate;

public class DateInterval extends Pair<LocalDate> {
    public DateInterval(LocalDate first, LocalDate second) {
        super(first, second);
    }

    @Override
    public void setFirst(LocalDate first) {
        super.setFirst(first);
    }

    @Override
    public LocalDate getFirst() {
        return super.getFirst();
    }
}
