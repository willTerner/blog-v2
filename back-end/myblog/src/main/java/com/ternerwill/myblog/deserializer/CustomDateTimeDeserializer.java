package com.ternerwill.myblog.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomDateTimeDeserializer extends StdDeserializer<Date> {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public CustomDateTimeDeserializer(){
        this(null);
    }
    protected CustomDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        try {
            return format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
