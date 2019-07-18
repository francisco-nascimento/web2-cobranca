package br.ifpe.web2.util;

import java.util.Calendar;
import java.util.Date;

public class Datas {

	public static Date calcularData(Date data, int numDias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, numDias);
		return cal.getTime();
	}

	public static Date criarData(int dia, int mes, int ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(ano, mes, dia);
		return cal.getTime();

	}
}
