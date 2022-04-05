package com.team7.classes;

public class Member {
	
	private int id;
	private String image;
	private String name;
	private String birthday;
	private String gender;
	private String job;
	
	public Member(int id, String image, String name, String birthday, String gender, String job) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.job = job;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
