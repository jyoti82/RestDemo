package com.rest.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.rest.model.CatlogItem;
@Path("/catlog")
public class Catlog {
	@GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON) 
    public String getCatlogItems(){
        String response = "";
        final OutputStream out = new ByteArrayOutputStream();
        JsonFactory jfactory = new JsonFactory();
        /*** Write JSON TO FILE ***/
		JsonGenerator jGenerator;
		try {
			jGenerator = jfactory.createJsonGenerator(out, JsonEncoding.UTF8);
			jGenerator.setCodec(new ObjectMapper());
			jGenerator.writeStartObject(); // {
			jGenerator.writeObjectFieldStart("status");
			jGenerator.writeStringField("code", "200");
			jGenerator.writeStringField("message", "success");
			jGenerator.writeEndObject();
			jGenerator.writeObjectFieldStart("data");
			//jGenerator.writeObjectFieldStart("catlogItems");
			List<CatlogItem> catlogItems = getCatlogItemDetails();
			jGenerator.writeArrayFieldStart("catlogItems");
		    for(CatlogItem aCatlog : catlogItems){
		    	jGenerator.writeObject(aCatlog);
		    }
		    //mapper.writeValue(out, catlogList);
		    jGenerator.writeEndArray();
			//jGenerator.writeArrayFieldStart("catlogItems");
			//jGenerator.writeObject(catlogItems);
			//jGenerator.writeEndArray();
			//jGenerator.writeEndObject();
			jGenerator.writeEndObject();
			jGenerator.writeEndObject();
			jGenerator.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("output "+out.toString());
		response = out.toString();
    return response;       
    }

	private List<CatlogItem> getCatlogItemDetails() {
		 byte[] data = null;
		// TODO Auto-generated method stub
		final OutputStream out = new ByteArrayOutputStream();
		JsonFactory jfactory = new JsonFactory();
        JsonGenerator jGenerator;
		final ObjectMapper mapper = new ObjectMapper();
	    List<CatlogItem> catlogList = null;
		try {
			jGenerator = jfactory.createJsonGenerator(out, JsonEncoding.UTF8);
			jGenerator.setCodec(new ObjectMapper());
		    catlogList = Database.getCatlogItemDetails();
		    jGenerator.writeStartArray();
		    for(CatlogItem aCatlog : catlogList){
		    	jGenerator.writeObject(aCatlog);
		    }
		    //mapper.writeValue(out, catlogList);
		    jGenerator.writeEndArray();
		    jGenerator.close();
		    System.out.println(out.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catlogList;
	}
}
