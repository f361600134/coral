package org.coral.server.game.helper.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * base on Twitter <b>Snowflake</b>.
 * 
 * java edition of Twitter <b>Snowflake</b>, a network service for generating
 * unique ID numbers at high scale with some simple guarantees.
 * 
 * https://github.com/twitter/snowflake
 * 
 * <p>
 * 雪花生成器,用于生成id, 每个服务器唯一, 结构如下
 * 
 * <br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 0000000000 00 <br>
 * <p>
 * 
 * 1位标识,long基本类型在Java中是带符号的,最高位是符号位,正数是0,负数是1,id一般是正数,最高位是0.
 * <p>
 * 
 * 41位时间截(毫秒级),注意41位时间截不是存储当前时间的时间截,而是存储时间截的差值(当前时间截 - 开始时间截)
 * <p>
 * 
 * 10位的数据机器位,可以部署在1024个节点,包括5位datacenterId和5位workerId
 * <p>
 * 
 * 12位序列,毫秒内的计数,12位的计数顺序号支持每个节点每毫秒(同一机器,同一时间截)产生4096个ID序号
 * <p>
 * 
 * 加起来刚好64位,为一个Long型.
 * <p>
 * 
 * SnowFlake的优点是,整体上按照时间自增排序,并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)
 * 并且效率非常高,经测试,SnowFlake每秒能够产生400万ID左右。
 * 
 */
public class SnowflakeGenerator {

	/*
	 * bits allocations for timeStamp, datacenterId, workerId and sequence
	 */
	private final long unusedBits = 1L; // 未使用的标识
	/**
	 * 'time stamp' here is defined as the number of millisecond that have
	 * elapsed since the {@link #epoch} given by users on
	 * {@link SnowflakeGenerator} instance initialization
	 */
//	private final long timestampBits = 41L; // 时间戳,差值
//	private final long datacenterIdBits = 5L; // 数据中心
//	private final long workerIdBits = 5L;
//	private final long sequenceBits = 12L; // 序列
	 private final long timestampBits = 40L; //时间戳,差值,理论使用34年
	 private final long datacenterIdBits = 1L; //数据中心 1, 可不用, 用workId区分
	 private final long workerIdBits = 10L; //1<<10 == 1024个子服务器
	 private final long sequenceBits = 12L; //序列

	/*
	 * max values of timeStamp, workerId, datacenterId and sequence
	 */
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 2^5-1
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits); // 2^5-1
	private final long maxSequence = -1L ^ (-1L << sequenceBits); // 2^12-1

	/**
	 * left shift bits of timeStamp, workerId and datacenterId
	 */
	private final long timestampShift = sequenceBits + datacenterIdBits + workerIdBits;
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	private final long workerIdShift = sequenceBits;

	/*
	 * object status variables
	 */

	/**
	 * reference material of 'time stamp' is 'current time'. its value can't be
	 * modified after initialization.
	 * 
	 * @change Set initial value at startup. This value can be used for 8 years
	 *         if the server does not restart.
	 * @editor Jeremy
	 */
	private final long epoch = 1546272000000L;

	/**
	 * data center number the process running on, its value can't be modified
	 * after initialization.
	 * <p>
	 * max: 2^5-1 range: [0,31]
	 */
	private final long datacenterId;

	/**
	 * machine or process number, its value can't be modified after
	 * initialization.
	 * <p>
	 * max: 2^5-1 range: [0,31]
	 * 
	 */
	private final long workerId;

	/**
	 * the unique and incrementing sequence number scoped in only one
	 * period/unit (here is ONE millisecond). its value will be increased by 1
	 * in the same specified period and then reset to 0 for next period.
	 * <p>
	 * max: 2^12-1 range: [0,4095]
	 */
	private long sequence = 0L;

	/** the time stamp last snowflake ID generated */
	private long lastTimestamp = -1L;

	/**
	 * generate an unique and incrementing id
	 *
	 * @return id
	 */
	public synchronized long nextId() {
		long currTimestamp = timestampGen();

		if (currTimestamp < lastTimestamp) {
			throw new IllegalStateException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - currTimestamp));
		}

		if (currTimestamp == lastTimestamp) {
			sequence = (sequence + 1) & maxSequence;
			if (sequence == 0) { // overflow: greater than max sequence
				currTimestamp = waitNextMillis(currTimestamp);
			}

		} else { // reset to 0 for next period/millisecond
			sequence = 0L;
		}

		// track and memo the time stamp last snowflake ID generated
		lastTimestamp = currTimestamp;

		return ((currTimestamp - epoch) << timestampShift) | //
				(datacenterId << datacenterIdShift) | //
				(workerId << workerIdShift) | // new line for nice looking
				sequence;
	}

	/**
	 * @param datacenterId
	 *            data center number the process running on, value range: [0,1]
	 * @param workerId
	 *            machine or process number, value range: [0,1024]
	 */
	public SnowflakeGenerator(long datacenterId, long workerId) {
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}

		this.datacenterId = datacenterId;
		this.workerId = workerId;
	}

	/**
	 * @param workerId
	 *            machine or process number, value range: [0,1024]
	 */
	public SnowflakeGenerator(long workerId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		this.datacenterId = 1L;
		this.workerId = workerId;
	}
	
	/**
	 * track the amount of calling {@link #waitNextMillis(long)} method
	 */
	private final AtomicLong waitCount = new AtomicLong(0);

	/**
	 * @return the amount of calling {@link #waitNextMillis(long)} method
	 */
	public long getWaitCount() {
		return waitCount.get();
	}

	/**
	 * running loop blocking until next millisecond
	 * 
	 * @param currTimestamp
	 *            current time stamp
	 * @return current time stamp in millisecond
	 */
	protected long waitNextMillis(long currTimestamp) {
		waitCount.incrementAndGet();
		while (currTimestamp <= lastTimestamp) {
			currTimestamp = timestampGen();
		}
		return currTimestamp;
	}

	/**
	 * get current time stamp
	 * 
	 * @return current time stamp in millisecond
	 */
	protected long timestampGen() {
		return System.currentTimeMillis();
	}

	/**
	 * show settings of Snowflake
	 */
	@Override
	public String toString() {
		return "Snowflake Settings [timestampBits=" + timestampBits + ", datacenterIdBits=" + datacenterIdBits + ", workerIdBits=" + workerIdBits + ", sequenceBits=" + sequenceBits + ", epoch=" + epoch + ", datacenterId=" + datacenterId + ", workerId=" + workerId + "]";
	}

	public long getEpoch() {
		return this.epoch;
	}

	/**
	 * extract time stamp, datacenterId, workerId and sequence number
	 * information from the given id
	 * 
	 * @param id
	 *            a snowflake id generated by this object
	 * @return an array containing time stamp, datacenterId, workerId and
	 *         sequence number
	 */
	public long[] parseId(long id) {
		long[] arr = new long[5];
		arr[4] = ((id & diode(unusedBits, timestampBits)) >> timestampShift);
		arr[0] = arr[4] + epoch;
		arr[1] = (id & diode(unusedBits + timestampBits, datacenterIdBits)) >> datacenterIdShift;
		arr[2] = (id & diode(unusedBits + timestampBits + datacenterIdBits, workerIdBits)) >> workerIdShift;
		arr[3] = (id & diode(unusedBits + timestampBits + datacenterIdBits + workerIdBits, sequenceBits));
		return arr;
	}

	/**
	 * extract and display time stamp, datacenterId, workerId and sequence
	 * number information from the given id in humanization format
	 * 
	 * @param id
	 *            snowflake id in Long format
	 * @return snowflake id in String format
	 */
	public String formatId(long id) {
		long[] arr = parseId(id);
		String tmf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(arr[0]));
		return String.format("%s, #%d, @(%d,%d)", tmf, arr[3], arr[1], arr[2]);
	}

	/**
	 * a diode is a long value whose left and right margin are ZERO, while
	 * middle bits are ONE in binary string layout. it looks like a diode in
	 * shape.
	 * 
	 * @param offset
	 *            left margin position
	 * @param length
	 *            offset+length is right margin position
	 * @return a long value
	 */
	private long diode(long offset, long length) {
		int lb = (int) (64 - offset);
		int rb = (int) (64 - (offset + length));
		return (-1L << lb) ^ (-1L << rb);
	}
	
	/**
	 * @方法名称 parseUID
	 * @功能描述
	 * 
	 *       <pre>
	 *       反解析UID
	 * 		</pre>
	 */
	private final static String MSG_UID_PARSE = "{\"UID\":\"%s\",\"timestamp\":\"%s\",\"workerId\":\"%d\",\"dataCenterId\":\"%d\",\"sequence\":\"%d\"}";

	/**
	 * @param uid
	 * @return  
	 * @return String  
	 * @date 2019年5月13日下午12:47:55
	 */
	public String parseUID(long uid) {
		// 解析Uid：标识 -- 时间戳 -- 数据中心 -- 机器码 --序列
		long sequence = (uid & diode(unusedBits + timestampBits + datacenterIdBits + workerIdBits, sequenceBits));
		long dataCenterId = (uid & diode(unusedBits + timestampBits, datacenterIdBits)) >> datacenterIdShift;
		long workerId = (uid & diode(unusedBits + timestampBits + datacenterIdBits, workerIdBits)) >> workerIdShift;
		long deltaSeconds = ((uid & diode(unusedBits, timestampBits)) >> timestampShift);
		// 时间处理(补上开始时间戳)
		Date thatTime = new Date(epoch + deltaSeconds);
		// 格式化输出
		return String.format(MSG_UID_PARSE, uid, thatTime, workerId, dataCenterId, sequence);
	}
	
//	/**
//	 * 测试 1s 400万
//	 */
//	public static void main(String[] args) {
//		long startTime = System.currentTimeMillis();
//		SnowflakeGenerator snowFlake = new SnowflakeGenerator(3);
//		for (int i = 0; i < 4000000; i++) {
//			long id = snowFlake.nextId();
//			// System.out.println(id);
//		}
//		System.out.println((System.currentTimeMillis() - startTime) + "ms");
//	}

}