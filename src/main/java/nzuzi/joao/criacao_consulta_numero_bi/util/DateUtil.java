package nzuzi.joao.criacao_consulta_numero_bi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date stringToDate(String date) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			simpleDateFormat.setLenient(false);
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date stringToDate1 (String date) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			simpleDateFormat.setLenient(false);
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String regularFormat(String date)
	{
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			simpleDateFormat.setLenient(false);
			Date newDate = simpleDateFormat.parse(date);
			return new SimpleDateFormat("dd-MM-yyyy").format(newDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String regularFormatEn(String date)
	{
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			simpleDateFormat.setLenient(false);
			Date newDate = simpleDateFormat.parse(date);
			return new SimpleDateFormat("yyyy-MM-dd").format(newDate);
		} catch (Exception e) {
			return null;
		}
	}

	public static String dateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	public static String formatDate(Date date){
		if(date != null){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.format(date);
		}
		return null;
	}

	public static String currentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	public static String dataActual() {
		Date todayDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(todayDate);
	}
}
