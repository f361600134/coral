package org.coral.server.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 * 
 * @author hdh
 *
 */
public class TimeUtil {

	private final static LocalTime ZERO_TIME = LocalTime.of(0, 0);

	public final static long SECOND_MILLISECONDS = TimeUnit.SECONDS.toMillis(1);
	public final static long MINIUTE_MILLISECONDS = TimeUnit.MINUTES.toMillis(1);
	public final static long HOUR_MILLISECONDS = TimeUnit.HOURS.toMillis(1);
	public final static long DAY_MILLISECONDS = TimeUnit.DAYS.toMillis(1);
	public final static long WEEK_MILLISECONDS = TimeUnit.DAYS.toMillis(7);
	/**
	 * 30天的毫秒数<br>
	 */
	public final static long MONTH_MILLISECONDS = TimeUnit.DAYS.toMillis(30);
	public final static long YEAR_MILLISECONDS = TimeUnit.DAYS.toMillis(365);
	public static final DateTimeFormatter FULL_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	/**
	 * 1天开始的时间点<br>
	 * 用于判断是否同1天<br>
	 * 跨天重置 不一定是0点 而以该时间点为准<br>
	 * FIXME 目前主策结论是 0点跨天 不是0点跨天的 让那个功能相关的策划找他 20200817
	 */
	public final static LocalTime DAY_BEGIN_TIME = LocalTime.of(0, 0);
	
	/**
	 * 一周的开始时间<br>
	 * 根据语言版本设置, 默认只有中国周一作为一周的开始,中文则设置为周一
	 *  日本欧美默认周天作为一周第一天.韩国泰国不知
	 *  如果不能用语言作为区分的话,需要修改成用类型区分
	 */
	public static WeekFields WEEK_BEGIN_DATE = WeekFields.ISO;
	

	/**
	 * 充值（月卡，年卡）等计算，一天开始的时间
	 */
	public static LocalTime PAY_BEGIN_TIME = LocalTime.of(0, 0);

	/**
	 * 时间偏移量<br>
	 * 为了给测试方便测试
	 */
	private static volatile long OFFSET_TIME = 0;

	public static void setOffsetTime(long offsetTime) {
		OFFSET_TIME = offsetTime;
	}

	public static long getOffsetTime() {
		return OFFSET_TIME;
	}
	
	/**
	 * 根据语言设置周一开始时间
	 * @param language
	 */
	public static void setWeekBeginDate(int language) {
		//不是中文的默认都以周天作为一周第一天
		if (language != 1) {
			WEEK_BEGIN_DATE = WeekFields.SUNDAY_START;
		}
	}

	
	/**
	 * 获取当前时间<br>
	 * 该方式有偏移量 方便测试调时间<br>
	 * 玩法功能代码 获取当前时间 使用该方法<br>
	 * 
	 * @return 毫秒
	 */
	public static long now() {
		long now = System.currentTimeMillis();
		return now + OFFSET_TIME;
	}

	/**
	 * 获取当前时间<br>
	 * 
	 * @return 秒
	 */
	public static int nowSecond() {
		return milliseconds2Seconds(now());
	}

	/**
	 * 获取该日期的时间戳<br>
	 * 该日期0点0分0秒
	 * 
	 * @param date
	 * @return
	 */
	public static long getTimestamp(LocalDate date) {
		ZonedDateTime zoneDateTime = ZonedDateTime.of(date, ZERO_TIME, ZoneId.systemDefault());
		long timestamp = TimeUnit.SECONDS.toMillis(zoneDateTime.toEpochSecond());
		return timestamp;
	}

	/**
	 * 获取该时间的时间戳<br>
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static long getTimestamp(LocalDate date, LocalTime time) {
		ZonedDateTime zoneDateTime = ZonedDateTime.of(date, time, ZoneId.systemDefault());
		long timestamp = TimeUnit.SECONDS.toMillis(zoneDateTime.toEpochSecond());
		return timestamp;
	}

	/**
	 * 获取该时间的时间戳<br>
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long getTimestamp(LocalDateTime dateTime) {
		ZonedDateTime zoneDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
		long timestamp = TimeUnit.SECONDS.toMillis(zoneDateTime.toEpochSecond());
		return timestamp;
	}

	/**
	 * 毫秒转为秒
	 * 
	 * @param milliseconds
	 * @return
	 */
	public static int milliseconds2Seconds(long milliseconds) {
		if (milliseconds == 0) {
			return 0;
		}
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
		return (int) seconds;
	}

	/**
	 * 毫秒转为秒
	 * 
	 * @param milliseconds
	 * @return
	 */
	public static int ms2s(long milliseconds) {
		if (milliseconds == 0) {
			return 0;
		}
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
		return (int) seconds;
	}

	public static ZonedDateTime timestamp2DateTime(long time) {
		Instant instant = Instant.ofEpochMilli(time);
		ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
		return dateTime;
	}

	/**
	 * 是否同1天<br>
	 * 该判断不使用自然天 而是使用自定义的某个时间点作为1天的分割点<br>
	 * {@link TimeUtil#DAY_BEGIN_TIME}
	 * 
	 * @param time1 毫秒
	 * @param time2 毫秒
	 * @return
	 */
	public static boolean isSameDay(long time1, long time2) {
		long diff = Math.abs(time1 - time2);
		if (diff >= DAY_MILLISECONDS) {
			return false;
		}
		// 获取其中1个时间的所在天的开始时间点
		long dayBeginTime = getDayBeginTime(time1);
		if (time2 < dayBeginTime || time2 >= dayBeginTime + DAY_MILLISECONDS) {
			return false;
		}
		return true;
	}

	/**
	 * 是否同1周<br>
	 * 该判断不使用自然天 而是使用自定义的某个时间点作为1天的分割点<br>
	 * {@link TimeUtil#DAY_BEGIN_TIME}
	 * 
	 * @param time1 毫秒
	 * @param time2 毫秒
	 * @return
	 */
	public static boolean isSameWeek(long time1, long time2) {
		long diff = Math.abs(time1 - time2);
		if (diff >= WEEK_MILLISECONDS) {
			return false;
		}
		// 获取其中1个时间的所在天的开始时间点
		long mondayBeginTime = getWeekBeginTime(time1);
		if (time2 < mondayBeginTime || time2 >= mondayBeginTime + WEEK_MILLISECONDS) {
			return false;
		}
		return true;
	}

	/**
	 * 获取两天时间戳的相差天数<br>
	 * 跨天 即使只有1秒 也视为1天<br>
	 * 
	 * @param startTime 起始时间（毫秒）
	 * @param endTime   结束时间（毫秒）
	 * @return 天数 >=0
	 */
	public static int getDifferDay(long startTime, long endTime) {
		if (endTime <= startTime) {
			return 0;
		}
		long diffTime = endTime - startTime;
		int day = (int) (diffTime / DAY_MILLISECONDS);
		long remainMs = diffTime % DAY_MILLISECONDS;
		if (remainMs > 0) {
			// 结束时间的当天起始时间
			long endTimeDayBeginTime = getDayBeginTime(endTime);
			if (endTimeDayBeginTime > endTime - remainMs) {
				day += 1;
			}
		}
		return day;
	}

	/**
	 * 获取现在到某个时间相差的天数 不支持过去的时间
	 * 
	 * @param time 目标时间时间戳（毫秒）， time > now
	 * @return 天数 >=0
	 */
	public static int getNowDifferDay(long time) {
		return getDifferDay(now(), time);
	}

	/**
	 * 获取这天的开始时间点<br>
	 * 不使用自然天 而是使用自定义的某个时间点作为1天的分割点<br>
	 * {@link TimeUtil#DAY_BEGIN_TIME}
	 * 
	 * @param time 毫秒
	 * @return 毫秒
	 */
	public static long getDayBeginTime(long time) {
		ZonedDateTime dateTime = timestamp2DateTime(time);
		LocalDate date = dateTime.toLocalDate();
		ZonedDateTime beginDateTime = ZonedDateTime.of(date, DAY_BEGIN_TIME, ZoneId.systemDefault());
		long beginTimestamp = TimeUnit.SECONDS.toMillis(beginDateTime.toEpochSecond());
		if (time < beginTimestamp) {
			beginTimestamp -= DAY_MILLISECONDS;
		}
		return beginTimestamp;
	}

	/**
	 * 获取本周第一天的0点开始时间点<br>
	 * 目前周一作为一周第一天<br>
	 * TODO 这里要确定清楚, 欧美地区以周天作为第一天, 中国以周一作为每周第一天. 不同地区的重置时间是否都设置为周一
	 * {@link TimeUtil#DAY_BEGIN_TIME}
	 * 
	 * @param time 毫秒
	 * @return 毫秒
	 */
	public static long getWeekBeginTime(long time) {
		ZonedDateTime dateTime = timestamp2DateTime(time);
		LocalDate date = dateTime.toLocalDate();

		TemporalField dayOfWeekTemporalField = WEEK_BEGIN_DATE.dayOfWeek();// 以周一作为每周第一天
		
		//t.with(dayOfWeekTemporalField, x) 表示获取第几天. 
		TemporalAdjuster temporalAdjuster = t -> t.with(dayOfWeekTemporalField, 1);

		LocalDate mondayDate = date.with(temporalAdjuster);

		ZonedDateTime beginDateTime = ZonedDateTime.of(mondayDate, DAY_BEGIN_TIME, ZoneId.systemDefault());
		long beginTimestamp = TimeUnit.SECONDS.toMillis(beginDateTime.toEpochSecond());
		if (time < beginTimestamp) {
			beginTimestamp -= DAY_MILLISECONDS;
		}
		return beginTimestamp;
	}

//	public static void main(String[] args) {
//		ZonedDateTime dateTime = timestamp2DateTime(TimeUtil.now());
//		LocalDate date = dateTime.toLocalDate();
//		TemporalField dayOfWeekTemporalField = WeekFields.SUNDAY_START.dayOfWeek();// 以周一作为每周第一天
//		TemporalAdjuster temporalAdjuster = t -> t.with(dayOfWeekTemporalField, 1);
//		LocalDate mondayDate = date.with(temporalAdjuster);
//
//		ZonedDateTime beginDateTime = ZonedDateTime.of(mondayDate, DAY_BEGIN_TIME, ZoneId.systemDefault());
//		long beginTimestamp = TimeUnit.SECONDS.toMillis(beginDateTime.toEpochSecond());
//		System.out.println(beginTimestamp);
//	}

	/**
	 * 获取充值时间计算，这天的开始时间点<br>
	 * 不使用自然天 而是使用自定义的某个时间点作为1天的分割点<br>
	 * {@link TimeUtil#PAY_BEGIN_TIME}
	 *
	 * @param time 毫秒
	 * @return 毫秒
	 */
	public static long getPayDayBeginTime(long time) {
		ZonedDateTime dateTime = timestamp2DateTime(time);
		LocalDate date = dateTime.toLocalDate();
		ZonedDateTime beginDateTime = ZonedDateTime.of(date, PAY_BEGIN_TIME, ZoneId.systemDefault());
		long beginTimestamp = TimeUnit.SECONDS.toMillis(beginDateTime.toEpochSecond());
		if (time < beginTimestamp) {
			beginTimestamp -= DAY_MILLISECONDS;
		}
		return beginTimestamp;
	}

	/**
	 * 获取下一天的 0 点
	 * 
	 * @return
	 */
	public static long getNextDayBeginTime() {
		long now = TimeUtil.now();
		return getDayBeginTime(now) + DAY_MILLISECONDS;
	}

	public static String getFullTimeStr(long time) {
		ZonedDateTime dateTime = timestamp2DateTime(time);
		return dateTime.format(FULL_TIME_FORMATTER);
	}

	/**
	 * 格式化时间
	 *
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String getTimeStr(long time, String pattern) {
		ZonedDateTime dateTime = timestamp2DateTime(time);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String timeStr = dateTime.format(formatter);
		return timeStr;
	}

	/**
	 * 获取现在是当天的第几个小时 1-24
	 * 
	 * @return
	 */
	public static int getTodayHour() {
		ZonedDateTime dateTime = timestamp2DateTime(now());
		return dateTime.getHour() + 1;
	}

}
