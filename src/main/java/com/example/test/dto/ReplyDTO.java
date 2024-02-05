package com.example.test.dto;

import java.time.LocalDateTime;

public class ReplyDTO {
	
	private int rep_idx;
	private int src_idx;
	private int step;
	private int ref;
	private int depth;
	
	
	private String reg_name;
	private String content;
	private LocalDateTime w_date;
	
	
	public int getRep_idx() {
		return rep_idx;
	}
	public void setRep_idx(int rep_idx) {
		this.rep_idx = rep_idx;
	}
	public int getSrc_idx() {
		return src_idx;
	}
	public void setSrc_idx(int src_idx) {
		this.src_idx = src_idx;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
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
	
	@Override
	public String toString() {
		return "ReplayDTO [rep_idx=" + rep_idx + ", src_idx=" + src_idx + ", step=" + step + ", ref=" + ref + ", depth="
				+ depth + ", reg_name=" + reg_name + ", content=" + content + ", w_date=" + w_date + "]";
	}
	
	

}
