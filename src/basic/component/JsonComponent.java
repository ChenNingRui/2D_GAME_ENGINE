package basic.component;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import basic.component.manager.ComponentType;

public class JsonComponent extends Component {
	private String path;
	private JSONObject jsonObj;
	private JSONArray jsonArr;
	
	public JsonComponent() {
		type = ComponentType.json;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public JSONArray getJsonArr() {
		return jsonArr;
	}
	public void setJsonArr(JSONArray object) {
		this.jsonArr = object;
	}
}
