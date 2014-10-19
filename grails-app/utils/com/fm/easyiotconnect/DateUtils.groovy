/**
 *  Lacuna 2012
 */
package com.fm.easyiotconnect

/**
 * @author Fabio Marini
 *
 */
class DateUtils {

	/**
	 * Create a new calendar with the given values
	 * 
	 * @param day the day of month
	 * @param month the month (1=January..12=December)
	 * @param year the year
	 * 
	 * @return the calendar object
	 */
	public static Calendar createCalendar(int day, int month, int year)
	{
		Calendar calendar = Calendar.getInstance()
		
		calendar.set(Calendar.DAY_OF_MONTH, day)
		calendar.set(Calendar.MONTH, month - 1)
		calendar.set(Calendar.YEAR, year)
		
		return calendar
	}
	
	/**
	 * Create a new date with the given values
	 * 
	 * @param day the day of month
	 * @param month the month (1=January..12=December)
	 * @param year the year
	 * 
	 * @return the date object
	 */
	public static Date createDate(int day, int month, int year)
	{
		Calendar calendar = createCalendar(day, month, year)
		
		return calendar.getTime()
	}
}
