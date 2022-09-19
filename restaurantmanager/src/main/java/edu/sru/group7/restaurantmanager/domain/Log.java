package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int location;
	
	private String date;

	private String time;

	private long userId;

	private String action;

	private long actionId;
	
	public Log() {
		super();
	}

	public Log(long id, int location, String date, String time, long userId, String action, long actionId) {
		super();
		this.id = id;
		this.location = location;
		this.date = date;
		this.time = time;
		this.userId = userId;
		this.action = action;
		this.actionId = actionId;
	}

	public long GetId() {
		return id;
	}

	public void SetId(long id) {
		this.id = id;
	}

	public int GetLocation() {
		return location;
	}

	public void SetLocation(int location) {
		this.location = location;
	}

	public String GetDate() {
		return date;
	}

	public void SetDate(String date) {
		this.date = date;
	}

	public String GetTime() {
		return time;
	}

	public void SetTime(String time) {
		this.time = time;
	}

	public long GetUserId() {
		return userId;
	}

	public void SetUserId(long userId) {
		this.userId = userId;
	}

	public String GetAction() {
		return action;
	}

	public void SetAction(String action) {
		this.action = action;
	}

	public long GetActionId() {
		return actionId;
	}

	public void SetActionId(long actionId) {
		this.actionId = actionId;
	}
}