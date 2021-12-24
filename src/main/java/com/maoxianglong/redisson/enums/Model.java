package com.maoxianglong.redisson.enums;

/**
 * redis的集群模式
 *
 * @author maoxianglong
 * @since 2021/12/20
 */
public enum Model {
    //哨兵
    SENTINEL,
    //主从
    MASTERSLAVE,
    //单例
    SINGLE,
    //集群
    CLUSTER,
    //云托管模式
    REPLICATED
}
