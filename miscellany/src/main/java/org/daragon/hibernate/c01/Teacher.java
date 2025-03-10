package org.daragon.hibernate.c01;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Teacher {

	private int id;
	private String name;
	private String title;	@Enumerated(EnumType.ORDINAL)
	private ZhiCheng zhicheng;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ZhiCheng getZhicheng() {
		return zhicheng;
	}

	public void setZhicheng(ZhiCheng zhicheng) {
		this.zhicheng = zhicheng;
	}

}
