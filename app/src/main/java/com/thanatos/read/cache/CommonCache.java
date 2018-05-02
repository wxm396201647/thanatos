package com.thanatos.read.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by wang.ming5 on 2018/4/23.
 */

public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<String>> getUsers(Observable<List<String>> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);

}
