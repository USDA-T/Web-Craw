package gov.sba.utils.helpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import com.esotericsoftware.yamlbeans.YamlReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.esotericsoftware.yamlbeans.YamlReader;

public class Load_Yaml {
	private static final Logger logger = LogManager.getLogger(Load_Yaml.class.getName());
	String file_Name;
	
	public Load_Yaml(String file_Name_Passed){ 
		this.file_Name = file_Name_Passed;	
	}

	public Map Load_Values() throws Exception{
		logger.info(file_Name);
		YamlReader stream_Reader = new YamlReader(new FileReader(file_Name));
	    Object json_Object = stream_Reader.read();
	    Map map_Json = (Map)json_Object;
	    System.out.println(map_Json);	    
	    return map_Json;
	    //return map_Json;
	}
}
