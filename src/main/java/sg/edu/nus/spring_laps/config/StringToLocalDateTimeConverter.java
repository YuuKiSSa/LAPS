package sg.edu.nus.spring_laps.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDateTime convert(String source) {
        try {
            if (source.contains(":")) {
                return LocalDateTime.parse(source, dateTimeFormatter);
            } else {
                return LocalDateTime.parse(source + " 00:00", dateTimeFormatter);
            }
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
