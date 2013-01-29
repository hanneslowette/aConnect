package org.hannes.concurrency;

/**
 * 
 * @author red
 *
 * @param <T> The returntype of the Future that is being listened to
 */
public interface FutureListener<T> {
	
	/**
	 * Called when the future has been 
	 * 
	 * @param object
	 */
	public abstract void listen(T object) throws Exception;
	
	/**
	 * Called when an exception has occurred during the execution
	 * of the process.
	 * 
	 * @param t
	 */
	public abstract void exceptionCaught(Throwable t);
	
}