package com.aries.tiger.concurrent.chapter1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aries.tiger.concurrent.common.GuardedBy;
import com.aries.tiger.concurrent.common.ThreadSafe;

@ThreadSafe
public class Sequence {

	static Logger log = LoggerFactory.getLogger(Sequence.class);

	@GuardedBy("this")
	private int value;

	public synchronized int getNext() {
		return value++;
	}

	public static void main(String[] args) {
		final Sequence seq = new Sequence();
		Thread tA = new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					log.debug("Thread A: {}", seq.getNext());
				}
			}
		};

		Thread tB = new Thread() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					log.debug("Thread B: {}", seq.getNext());
				}
			}
		};

		tA.start();
		tB.start();
	}
}
