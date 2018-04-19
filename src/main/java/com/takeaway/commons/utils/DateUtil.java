package com.takeaway.commons.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类
 * 
 * @author Administrator
 *
 */
public class DateUtil {
	private static final Pattern TIME_P = Pattern
			.compile("(\\d+)时(\\d+)分(\\d+)秒(\\d+)毫秒");
	static String[] parsePatterns = { "yyyy-MM-dd", "yyyy/MM/dd" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 * 
	 * @return
	 */
	public static String getDate() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 解析日期字符串，转换成相应的日期对象
	 * 
	 * @param dateString
	 *            日期格式字符串，支持格式：yyyy/MM/dd、yyyy-MM-dd、yyyy.MM.dd
	 * @return
	 */
	public static Date parseDateString(String dateString) {
		DateFormat format = null;
		try {
			if (dateString.indexOf("/") != -1) {
				// 时期格式为yyyy/MM/dd
				format = new SimpleDateFormat("yyyy/MM/dd");
				return format.parse(dateString);
			} else if (dateString.indexOf("-") != -1) {
				format = new SimpleDateFormat("yyyy-MM-dd");
				return format.parse(dateString);
			} else if (dateString.indexOf(".") != -1) {
				format = new SimpleDateFormat("yyyy.MM.dd");
				return format.parse(dateString);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析日期字符串，转换成相应的日期对象
	 * 
	 * @param dateString
	 *            日期格式字符串，支持格式：yyyy/MM/dd、yyyy-MM-dd、yyyy.MM.dd
	 * @return
	 */
	public static Date parseDatetimeString(String datetimeString) {
		DateFormat format = null;
		try {
			if (datetimeString.indexOf("/") != -1) {
				// 时期格式为yyyy/MM/dd
				format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				return format.parse(datetimeString);
			} else if (datetimeString.indexOf("-") != -1) {
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return format.parse(datetimeString);
			} else if (datetimeString.indexOf(".") != -1) {
				format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				return format.parse(datetimeString);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseTimeString(String datetimeString) {
		DateFormat format = null;
		try {
			format = new SimpleDateFormat("HH:mm:ss");
			return format.parse(datetimeString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将日期对象转换成相应的日期字符串,仅用于中文格式
	 * 
	 * @param date
	 *            日期对象
	 * @return
	 */
	public static String parseDateChineseString(Date date) {
		DateFormat format = null;
		try {
			format = new SimpleDateFormat("yyyy年MM月dd日");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将日期对象转换成相应的日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @param formatStr
	 *            日期字符串形式，支持格式：yyyy/MM/dd和yyyy-MM-dd 默认格式为：yyyy-MM-dd
	 * @return String
	 */
	public static String parseDate(Date date, String formatStr) {
		if (date == null) {
			return null;
		}

		if (StringUtils.isBlank(formatStr)) {
			formatStr = "yyyy-MM-dd";
		}

		DateFormat format = new SimpleDateFormat(formatStr);
		try {
			return format.format(date);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (null == date) {
			return formatDate;
		}

		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 获取前一天
	 * 
	 * @param cl
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("date11------" + date);
		date = getBeforeDay(date);
		System.out.println("date22------" + date);
	}
}
