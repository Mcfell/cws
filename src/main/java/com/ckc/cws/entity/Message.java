package com.ckc.cws.entity;

import javax.swing.text.AbstractDocument.Content;

public class Message<T1,T> {
	
	public T1 statue;
	public T contenT;
	public T1 getStatue() {
		return statue;
	}
	public void setStatue(T1 statue) {
		this.statue = statue;
	}
	public T getContenT() {
		return contenT;
	}
	public void setContenT(T contenT) {
		this.contenT = contenT;
	}
	
}
