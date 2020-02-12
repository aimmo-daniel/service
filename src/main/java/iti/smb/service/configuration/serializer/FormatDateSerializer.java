package iti.smb.service.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import iti.smb.service.domain.dto.FormatDate;

import java.io.IOException;
import java.time.LocalDate;

public class FormatDateSerializer extends JsonSerializer<FormatDate> {

    @Override
    public void serialize(FormatDate formatDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(formatDate != null) {
            jsonGenerator.writeObject(LocalDate.of(formatDate.getYearOfDate(), formatDate.getMonthOfDate(), formatDate.getDayOfDate()));
        }
    }

}
