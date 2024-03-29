package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Log POJO
 */
@Entity
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String location;
	
	private String date;

	private String time;

	private long userId;

	private String action;

	private long actionId;
	
	/**
	 * Default Constructor
	 */
	public Log() {
		super();
	}
	
	/**
	 * Parameterized Constructor
	 * @param id
	 * @param location
	 * @param date
	 * @param time
	 * @param userId
	 * @param action
	 * @param actionId
	 */
	public Log(long id, String location, String date, String time, long userId, String action, long actionId) {
		super();
		this.id = id;
		this.location = location;
		this.date = date;
		this.time = time;
		this.userId = userId;
		this.action = action;
		this.actionId = actionId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

}