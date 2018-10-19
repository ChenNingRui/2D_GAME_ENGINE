package basic.system;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import basic.component.JsonComponent;

public class JsonSystem implements System {

	private JsonComponent component;
	
	public JsonSystem() {
		instantiation();
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		component = new JsonComponent();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public void loadJson(String path) throws Exception 
	{
		component.setPath(path);
		Object obj = new JSONParser().parse(new FileReader(component.getPath()));
		JSONObject jsonObj = (JSONObject)obj;
		component.setJsonArr((JSONArray)jsonObj.get("SubTexture")); 
	}
	
	public JSONObject getObjByName(String name) {
		for(int i = 0; i < component.getJsonArr().size(); i++)
		{
			JSONObject obj = (JSONObject) component.getJsonArr().get(i);
			if(obj.containsValue(name)) {
				//java.lang.System.out.print(obj.get("x"));
				return obj;
			}
		}
		return null;
	}

}
