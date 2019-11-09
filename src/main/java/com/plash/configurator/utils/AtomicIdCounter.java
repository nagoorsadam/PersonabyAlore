/**
 * 
 */
package com.plash.configurator.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author satish
 *
 */
public class AtomicIdCounter {

	public static synchronized Long getRandomUID() {
		AtomicLong counter = new AtomicLong(System.currentTimeMillis());
		Random r = new Random();
		Long value = (counter.incrementAndGet() + r.nextInt());
		return value;
	}

	public static synchronized Long getUniqueID() {
		AtomicLong counter = new AtomicLong(System.currentTimeMillis());
		Long value = (counter.incrementAndGet());
		return value;
	}
}
