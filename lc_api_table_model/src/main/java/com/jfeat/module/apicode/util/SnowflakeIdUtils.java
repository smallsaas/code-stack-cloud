package com.jfeat.module.apicode.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @description: 雪花id工具
 * @project: auto-api
 * @date: 2024/4/23 11:50
 * @author: hhhhhtao
 *
 * <p>
 *     这里使用单例模式来确保全局只有一个雪花id的生成工具，这种只适合在一台机器上使用，
 *     如果需要生成多台机器的雪花id，则该模式则不适用，应该将单例子模式去掉。
 * </p>
 */
public class SnowflakeIdUtils {

    /**
     * 这里使用单例模式来确保全局只有一个雪花id的生成工具
     */
    private static volatile SnowflakeIdUtils snowflakeIdUtils;

    private final long epoch = 1622505600000L; // 起始时间戳，这里使用2021-06-01 00:00:00的时间戳
    private final long machineIdBits = 1L; // 机器ID所占的位数，目前不需要分布式使用，所以设置为1L
    private final long datacenterIdBits = 1L; // 数据中心ID所占的位数，目前不需要分布式使用，所以设置为1L
    private final long maxMachineId = -1L ^ (-1L << machineIdBits); // 最大机器ID
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 最大数据中心ID
    private final long sequenceBits = 12L; // 序列号所占的位数
    private final long machineIdShift = sequenceBits; // 机器ID左移位数
    private final long datacenterIdShift = sequenceBits + machineIdBits; // 数据中心ID左移位数
    private final long timestampLeftShift = sequenceBits + machineIdBits + datacenterIdBits; // 时间戳左移位数
    private final long sequenceMask = -1L ^ (-1L << sequenceBits); // 序列号的掩码
    private long lastTimestamp = -1L;
    private long sequence = 0L;
    @Value("#{T(Long).valueOf('${SnowflakeIdParam.machineId}'.replace('L', ''))}")
    private long machineId; // 机器id
    @Value("#{T(Long).valueOf('${SnowflakeIdParam.datacenterId}'.replace('L', ''))}")
    private long datacenterId; // 数据中心id

    /**
     * 获取雪花id生成工具
     *
     * @return SnowflakeIdUtils
     */
    public static SnowflakeIdUtils getInstance() {
        if (snowflakeIdUtils == null) {
            synchronized (SnowflakeIdUtils.class) {
                if (snowflakeIdUtils == null) {
                    snowflakeIdUtils = new SnowflakeIdUtils();
                }
            }
        }
        return snowflakeIdUtils;
    }

    private SnowflakeIdUtils() {}

    /**
     * 单例模式中，这个构造方法不开放
     *
     * @param machineId 机器id
     * @param datacenterId 数据中心id
     */
    private SnowflakeIdUtils(long machineId, long datacenterId) {
        if (machineId > maxMachineId || machineId < 0) {
            throw new IllegalArgumentException("Machine ID must be between 0 and " + maxMachineId);
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("Datacenter ID must be between 0 and " + maxDatacenterId);
        }
        this.machineId = machineId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个雪花id
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID for " + (lastTimestamp - timestamp) + " milliseconds");
        }
        // 即使是同一时间生成也能进行毫秒内序列
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 统一毫秒内序列用完则等待下一毫秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - epoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (machineId << machineIdShift) |
                sequence;
    }

    /**
     * 等待下一毫秒，直到时间戳大于上一次生成ID的时间戳
     * @param lastTimestamp 上一次生成的时间
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
