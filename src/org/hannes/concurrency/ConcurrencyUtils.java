package org.hannes.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Contains a class with utils for concurrency
 * 
 * @author red
 */
public class ConcurrencyUtils {

	/**
	 * Only 1 thread can be available at any given time
	 */
	private static final ExecutorService service = Executors.newSingleThreadExecutor();

	/**
	 * Execute a new system command on a separate thread.
	 * 
	 * TODO: Proper exception handling
	 * 
	 * @param command
	 * @return
	 */
	public static <T> Future<T> execute(final FutureListener<T> listener, final Callable<T> callable) {
		return service.submit(new Callable<T>() {

			@Override
			public T call() throws Exception {
				T object = null;
				try {
					object = callable.call();
					listener.listen(object);
				} catch (Exception ex) {
					listener.exceptionCaught(ex);
				}
				return object;
			}
			
		});
	}

}