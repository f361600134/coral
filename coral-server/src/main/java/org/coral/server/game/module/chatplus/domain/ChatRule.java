package org.coral.server.game.module.chatplus.domain;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.coral.server.game.data.config.ConfigChatMgr;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 玩家聊天约束
 * @author Jereme
 */
public class ChatRule{
	
	private static final Logger log = LoggerFactory.getLogger(ChatRule.class);
	
	//默认60s违规次数,超出延长时限
	private static final int DEFAULT_AGAINST_COUNT = 5;//连续5次违规
	private static final int DEFAULT_LIMIT_TIME = 60;//60s内
	
	private int channel; //频道
	private long lsatTime; //最后一次说话时间
	private long nextSpeakTime; //下次说话时间, 超过这个值可说话
	private Queue<Long> againstQueue;//违规时间列表
	
	private int againstCount; //自定义1分钟内违规次数
	private int limitTime;//自定义限制时间
//	private boolean against;//是否违规
	
	public ChatRule(int channel) {
		this.channel = channel;
		this.againstCount = DEFAULT_AGAINST_COUNT;
		this.limitTime = DEFAULT_LIMIT_TIME;
		this.lsatTime = System.currentTimeMillis();
		this.nextSpeakTime = lsatTime;
		this.againstQueue = new ArrayBlockingQueue<Long>(this.againstCount);
	}
	
	public ChatRule(int channel, int customAgainstCount, int limitTime) {
		this.channel = channel;
		this.againstCount = customAgainstCount;
		this.limitTime = limitTime;
		this.lsatTime = System.currentTimeMillis();
		this.nextSpeakTime = lsatTime;
		this.againstQueue = new ArrayBlockingQueue<Long>(this.againstCount);
	}
	
	public long getLsatTime() {
		return lsatTime;
	}
	public void setLsatTime(long lsatTime) {
		this.lsatTime = lsatTime;
	}
	public int getAgainstCount() {
		return againstCount;
	}
	public void setAgainstCount(int againstCount) {
		this.againstCount = againstCount;
	}
	
	public static ChatRule create(int channel) {
		return new ChatRule(channel);
	}
	
	public static ChatRule create(int channel, int customAgainstCount, int limitTime) {
		return new ChatRule(channel, customAgainstCount, limitTime);
	}

	public long getNextSpeakTime() {
		return nextSpeakTime;
	}
	public void setNextSpeakTime(long nextSpeakTime) {
		this.nextSpeakTime = nextSpeakTime;
	}
	
	public Queue<Long> getAgainstQueue() {
		return againstQueue;
	}

	public void setAgainstQueue(Queue<Long> againstQueue) {
		this.againstQueue = againstQueue;
	}

	/**
	 * 聊天成功, 设置最后聊聊天时间以及下次说话时间
	 */
	public void onChatSuccess() {
		long curTime = System.currentTimeMillis();
		this.lsatTime = curTime;//最后一次发言时间
		ConfigChat config = ConfigChatMgr.getConfig(channel);
		this.nextSpeakTime = lsatTime + ((config.getInterval()) * 1000);//下次发言间隔
		//log.debug("onChatSuccess this.lastTime:{}, , this.nextSpeakTime:{}", TimeUtils.format(this.lsatTime), TimeUtils.format(this.nextSpeakTime));
		checkAgainst();
		//log.debug("onChatSuccess , this.nextSpeakTime:{}, againstQueue:{}", TimeUtils.format(this.nextSpeakTime), againstQueue);
	}

	/**
	 * 当玩家违规
	 * 违规次数累加
	 */
	public void onTrigger() {
		againstQueue.offer(System.currentTimeMillis());
		//log.debug("onTrigger this.lastTime:{}, this.nextSpeakTime:{}", TimeUtils.format(this.lsatTime), TimeUtils.format(this.nextSpeakTime));
		checkAgainst();
		boolean bool = isAgainst();
		if (bool) {
			ConfigChat config = ConfigChatMgr.getConfig(channel);
			this.nextSpeakTime += (config.getSpeakLimitTime() * 1000);
		}
		//log.debug("onTrigger , this.nextSpeakTime:{}, againstQueue:{}", TimeUtils.format(this.nextSpeakTime), againstQueue);
	}
	
	public boolean isAgainst() {
		return againstQueue.size() >= this.againstCount ? true : false;
	}
	
	private void checkAgainst() {
		long curTime = System.currentTimeMillis();
		long expireTime = curTime - this.limitTime * 1000;
		Iterator<Long> iter =  againstQueue.iterator();
		while (iter.hasNext()) {
			if (iter.next() < expireTime) {
				iter.remove();
			}else {
				break;
			}
		}
	}
	

}