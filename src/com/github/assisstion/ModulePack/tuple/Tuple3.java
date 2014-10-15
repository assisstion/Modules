package com.github.assisstion.ModulePack.tuple;

import com.github.assisstion.ModulePack.annotation.LimitedImmutable;

@LimitedImmutable({})
public interface Tuple3<T, S, R> extends Tuple<Object>,
Value1<T>, Value2<S>, Value3<R>{

}
