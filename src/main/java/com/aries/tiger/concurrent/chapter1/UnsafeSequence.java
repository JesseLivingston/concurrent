package com.aries.tiger.concurrent.chapter1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aries.tiger.concurrent.common.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {

	static Logger log = LoggerFactory.getLogger(UnsafeSequence.class);
	
	private int value;
	
	public int getNext() {
		return value++;
	}
	
	public static void main(String[] args){
		final UnsafeSequence seq = new UnsafeSequence();
		
		Thread tA = new Thread(){
			@Override
			public void run(){
				for(int i = 0; i < 100; i++){
					log.debug("Thread A: {}", seq.getNext());
				}
			}
		};
		
		Thread tB = new Thread(){
			@Override
			public void run(){
				for(int i = 0; i < 100; i++){
					log.debug("Thread B: {}", seq.getNext());
				}
			}
		};
		
		tA.start();
		tB.start();
	}
}
