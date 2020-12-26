package org.coral.server.disruptor.middle;

import org.coral.server.game.module.item.event.ItemAddEvent;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 
 * @author Administrator
 *
 */
public class C2EventHandler implements WorkHandler<TaskEvent>, EventHandler<TaskEvent>{

	@Override
	public void onEvent(TaskEvent event) throws Exception {
		if (event.isSameEvent(ItemAddEvent.NAME)) {
			System.out.println("threadName:"+Thread.currentThread().getName()+", ======111 C2EventHandler========");
		}
	}

	@Override
	public void onEvent(TaskEvent event, long sequence, boolean endOfBatch) throws Exception {
		if (event.isSameEvent(ItemAddEvent.NAME)) {
			ItemAddEvent e = (ItemAddEvent)event.getEvent();
			e.setCount(100);
			System.out.println("threadName:"+Thread.currentThread().getName()+", ======222 C2EventHandler========"+e.getCount());
		}
	}

}
