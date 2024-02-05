package com.example.test.dto;

import java.time.LocalDateTime;

public class BoardDTO {
	
	private String title;
	
	private String reg_name;
	private String content;
	private LocalDateTime w_date;
	private int hit;
	
	private int idx;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getW_date() {
		return w_date;
	}
	public void setW_date(LocalDateTime w_date) {
		this.w_date = w_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@Override
	public String toString() {
		return "BoardDAO [title=" + title + ", reg_name=" + reg_name + ", content=" + content + ", w_date=" + w_date
				+ ", hit=" + hit + ", idx=" + idx + "]";
	}

}
