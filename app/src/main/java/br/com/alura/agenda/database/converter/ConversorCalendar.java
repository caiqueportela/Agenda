package br.com.alura.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

    @TypeConverter
    public Long toLong(Calendar valor) {
        if (valor == null) {
            valor = Calendar.getInstance();
        }
        return valor.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long valor) {
        Calendar momentoAtual = Calendar.getInstance();
        if (valor != null) {
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }

}
