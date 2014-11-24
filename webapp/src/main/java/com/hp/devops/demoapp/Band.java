package com.hp.devops.demoapp;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: gullery
 * Date: 24/11/14
 * Time: 10:03
 * To change this template use File | Settings | File Templates.
 */
public class Band {
	public int id;
	public String name;
	public String logo;
	public String song;
	public int votes;

	public Band(JSONObject json) {
		id = json.getInt("id");
		name = json.getString("name");
		logo = json.getString("logo");
		song = json.getString("song");
		votes = json.getInt("votes");
	}

	public JSONObject toJSON() {
		JSONObject r = new JSONObject();
		r.put("id", id);
		r.put("name", name);
		r.put("logo", logo);
		r.put("song", song);
		r.put("votes", votes);
		return r;
	}
}
