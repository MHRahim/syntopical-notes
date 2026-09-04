package com.rahim.syntopicalnotes.mappers;

import java.util.List;

public interface Mapper<A, B> {
    public B mapTo(A a);
    public A mapFrom(B b);
    public List<A> mapFromMany(List<B> b);
}
