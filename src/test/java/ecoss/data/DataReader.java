package ecoss.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// Read Json to String & import jackson-databind
		String JsonFileinString = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\ecoss\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
		
		// convert to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> JsonFileHashMap = mapper.readValue(JsonFileinString, new TypeReference<List<HashMap<String,String>>>(){});
		
		return JsonFileHashMap;
		
		
	}

}
