package org.coral.server.disruptor.base;

import com.lmax.disruptor.EventTranslatorOneArg;

public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {
    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        event.setNumber(arg0);
    }
}