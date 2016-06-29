package com.movieapp.wrappers;

import java.util.List;

import com.movieapp.beans.Extra;

public class ExtraWrapper {
	private Extra extra;
	private List<Extra> extras;
	public Extra getExtra() {
		return extra;
	}
	public void setExtra(Extra extra) {
		this.extra = extra;
	}
	public List<Extra> getExtras() {
		return extras;
	}
	public void setExtras(List<Extra> extras) {
		this.extras = extras;
	}
	@Override
	public String toString() {
		return "ExtraWrapper [extra=" + extra + ", extras=" + extras + "]";
	}
	
	
}
