package com.zaijiadd.app.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Date 2015.02.26
 * @author Chenzq 时间工具类
 */

public class DateUtils {
	
	public final static String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public final static String FULL_DATE_TIME2 = "yyyyMMddHHmmss";
	public final static String SHORT_DATE_TIME = "yyyy-MM-dd";
	public final static String SIMPLE_DATE_TIME = "yyyyMM";
	public final static String TRANS_DATE_TIME = "yyyyMMdd";
	public final static String INTEREST_DISPLAY_TIME = "yyyy.MM.dd";
	
	/**
	 * @Date 2015.02.26
	 * @author Chenzq
	 * @param pattern
	 *            (日期格式)
	 * @return
	 */
	public static String getSysDate(String pattern) {
		
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		return sdf.format(curDate);
		
	}
	
	public static String getSysDate(Date curDate, String pattern) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		return sdf.format(curDate);
		
	}
	
	public static Date getCurrentDate(String pattern) {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String strDate = sdf.format(curDate);
		return transStringToDate(strDate, pattern);
		
	}
	
	public static Date getDateWithPattern(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String strDate = sdf.format(date);
		return transStringToDate(strDate, pattern);
	}
	
	public static String transDateToString(Date date, String pattern) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr = sdf.format(date);
		
		return dateStr;
		
	}
	
	public static Date transStringToDate(String dateStr, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		
	}
	
	public static Date addCurrentDate(int periodDay) {
		String dateStr = getSysDate(SHORT_DATE_TIME);
		Date date = transStringToDate(dateStr, SHORT_DATE_TIME);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, periodDay);
		return calendar.getTime();
		
	}
	
	public static Date addDay(Date date, int periodDay) {
		SimpleDateFormat sdf = new SimpleDateFormat(SHORT_DATE_TIME);
		String strDate = sdf.format(date);
		Date shortDate = transStringToDate(strDate, SHORT_DATE_TIME);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(shortDate);
		calendar.add(Calendar.DAY_OF_MONTH, periodDay);
		return calendar.getTime();
		
	}
	
	public static long dayDiff(Date date1, Date date2) {
		long diff = 0;
		if (date1.compareTo(date2) >= 0) {
			diff = date1.getTime() - date2.getTime();
		} else {
			diff = date2.getTime() - date1.getTime();
		}
		return diff / (1000 * 24 * 60 * 60);
	}
	
	/**
	 * 
	 * @Description: 判断是否周末
	 * @param date
	 * @return
	 * @Return: boolean
	 */
	public static boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (Calendar.SATURDAY == dayOfWeek || Calendar.SUNDAY == dayOfWeek) {
			return true;
		} else {
			return false;
		}
		// return 0;
	}
	
	public static Date getLastDate(Date date) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		
		return date;
		
	}
	
	public static int getDiffDays(Date endDate, Date startDate) {
		long endTime = endDate.getTime();
		long startTime = startDate.getTime();
		long days = (endTime - startTime) / (1000 * 60 * 60 * 24);
		return (int) days;
	}
	
	/**
	 * 根据日获取年月日
	 */
	public static Date getDateByDays(Integer days, Date nowDate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_TIME);
		String date = sdf.format(nowDate) + days;
		Date curDate = transStringToDate(date, TRANS_DATE_TIME);
		
		return curDate;
		
	}
	
	/**
	 * 判断某个日期是否在指定日期范围内
	 */
	public static Boolean isBetweenDates(Date startDate, Date endDate, Date date) {
		
		if (date.after(startDate) && date.before(endDate)
		        || date.equals(startDate)) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * 根据日获取上月的年月日
	 */
	public static Date getLastDateByDays(Integer days, Date nowDate) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.MONTH, -1);
		
		return getDateByDays(days, calendar.getTime());
		
	}
	
	public static Date addMinute(Date date, int miniute) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, miniute);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		// Date d = transStringToDate("2015-05-29 00:00:00", FULL_DATE_TIME);
		// Date d2Date = transStringToDate("2015-05-25 20:03:41",
		// FULL_DATE_TIME);
		// System.err.println(DateUtils.getDiffDays(d2Date, d));
		//
		// System.err.println("end: " + d2Date);
		// oo
		// Date endDate = transStringToDate("2015-05-28", SHORT_DATE_TIME);
		
		System.err.println(addMinute(new Date(), -10));
	}
	
}
