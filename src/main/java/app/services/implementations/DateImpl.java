package app.services.implementations;

import org.springframework.stereotype.Service;

import app.services.interfaces.DateInterface;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.*;

@Service
public class DateImpl implements DateInterface {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convertStr2Date(String fecha) {
        LocalDate fechaDate = null;
        try {
            fechaDate = LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return fechaDate;
    }

    @Override
    public String convertDate2Str(LocalDate fecha) {
        String fechaDate = null;
        try {
            fechaDate = fecha.format(formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return fechaDate;
    }
}
