package com.aries.tiger.concurrent.chapter2;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aries.tiger.concurrent.common.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
	
	static Logger log = LoggerFactory.getLogger(LazyInitRace.class);
	private Object instance = null;
	
	public Object getInstance(){
		if(instance == null){
			instance = new Object();
		}
		return instance;
	}
	
	public static void main(String[] args){
		final LazyInitRace lazy = new LazyInitRace();
		final Set<Object> set = new HashSet<Object>();
		Thread ta = new Thread(){
			
			@Override
			public void run(){
				set.add(lazy.getInstance());
			}
		};
		
		Thread tb = new Thread(){
			
			@Override
			public void run(){
				set.add(lazy.getInstance());
			}
		};
		ta.start();
		tb.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.debug("Sleep error: {}", e);
		} finally {
			log.debug("Set size: {}", set.size());
		}
	}
}
