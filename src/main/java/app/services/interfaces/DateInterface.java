package app.services.interfaces;

import java.time.*;

public interface DateInterface {
    LocalDate convertStr2Date(String string);

    String convertDate2Str(LocalDate fecha);
}
