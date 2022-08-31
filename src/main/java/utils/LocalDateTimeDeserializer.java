package utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class LocalDateTimeDeserializer extends JsonDeserializer <LocalDateTime> {

	@Override
	  public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
	    throws IOException, JsonProcessingException {
	    ObjectCodec oc = jp.getCodec();
	    TextNode node = (TextNode) oc.readTree(jp);
	    String dateString = node.textValue();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return LocalDateTime.parse(dateString, formatter);
	  }

}