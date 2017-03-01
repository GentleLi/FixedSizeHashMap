package com.android.tao.fixedsizehashmap.util;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 具有固定长度的HashMap 初始设置固定长度为 2
 * 实现 LRU 算法
 * 用于 存储宠物的 图片资源 BitmapRegionDecoder
 * 使用时 要调用 preGet(K key) 方法来代替 get(K key) 方法
 * Created by Jiantao on 2017/2/27.
 */
public class FixedSizeHashMap<K, V> extends LinkedHashMap<K, V> {

    private static final String TAG = FixedSizeHashMap.class.getSimpleName();
    private int mMaxSize = 2;//默认的容量
    private K mLastKey;

    /**
     * 设置容量大小
     */
    public synchronized void setMaxSize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Oops!maxSize must be a positive Integer!");
        }
        while (size() > maxSize) {
            removeFirstKey();
        }
        this.mMaxSize = maxSize;
    }

    @Override
    public V put(K key, V value) {
        if (size() >= mMaxSize) {
            if (containsKey(key)) {//首先从Map中删除此对象，再重新添加即可
                return super.put(key, value);
            } else {//删除第一个元素,之后再插入元素
                removeFirstKey();
            }
        }
        return super.put(key, value);
    }

    /**
     * 删除HashMap的收个元素
     */
    private void removeFirstKey() {
        Object firstKey = null;
        Iterator iter = entrySet().iterator();
        if (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            firstKey = entry.getKey();
            remove(firstKey);
        }
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    /**
     * 获取Value 自定义get(K key)方法
     *
     * @param key
     * @return
     */
    public V preGet(K key) {
        if (mLastKey != key) {//优化，当上一个Key值与现在的key值不同的时候在进行换位置操作
            V value = get(key);
            remove(key);
            if (value != null) {
                Log.e(TAG, "value!=null,rePutValue");
                put(key, value);
            }
            mLastKey = key;
        }
        return get(key);
    }
}
