package com.aries.tiger.concurrent.chapter1;

import com.aries.tiger.concurrent.common.GuardedBy;
import com.aries.tiger.concurrent.common.ThreadSafe;

@ThreadSafe
public class Sequence {

	@GuardedBy("this")
	private int value;
	
	public synchronized int getNext(){
		return value++;
	}
}
