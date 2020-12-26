package org.coral.server.disruptor.middle;

import org.coral.server.game.module.item.event.ItemAddEvent;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 
 * @author Administrator
 *
 */
public class C1EventHandler implements WorkHandler<TaskEvent>,EventHandler<TaskEvent>{

	@Override
	public void onEvent(TaskEvent event) throws Exception {
		if (event.isSameEvent(ItemAddEvent.NAME)) {
			System.out.println("threadName:"+Thread.currentThread().getName()+", ======111 C1EventHandler========");
		}
	}

	@Override
	public void onEvent(TaskEvent event, long sequence, boolean endOfBatch) throws Exception {
		if (event.isSameEvent(ItemAddEvent.NAME)) {
			ItemAddEvent e = (ItemAddEvent)event.getEvent();
			e.setCount(200);
			System.out.println("threadName:"+Thread.currentThread().getName()+", ======222 C1EventHandler========"+e.getCount());
		}
	}

}
