package com.maya.game.modules.maill.domain;

import com.maya.common.persistence.AbstractEntity;
import com.maya.common.persistence.annotation.CacheField;
import com.maya.common.persistence.annotation.Column;
import com.maya.common.persistence.annotation.Index;
import com.maya.common.persistence.annotation.Table;
import com.maya.data.config.pojo.ConfigMail;
import com.maya.game.modules.item.proto.ItemInfoBuilder;
import com.maya.game.proto.PBPlayer;
import com.maya.utils.TimeUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Table
@CacheField("receiver")
@Index(name = "receiver",columns = "receiver")
public class Mail extends AbstractEntity {

	/** 收件人Id*/
	@Column
	protected long receiver;
	/** 标题*/
	@Column
	protected String title;
	/** 内容*/
	@Column
	protected String content;
	/** 状态*/
	@Column
	protected int state;
	/** 邮件类型*/
	@Column
	protected int type;
	/** 创建时间*/
	@Column
	protected int createAt;
	/** 过期时间*/
	@Column
	protected int endTime;
	/**
	 * 奖励内容
	 */
	@Column
	protected Map<Integer, Integer> rewardMap;
	
	public Mail() {
		this.rewardMap = new HashMap<Integer, Integer>();
	}
	
	public Map<Integer, Integer> getRewardMap() {
		return rewardMap;
	}
	
	public void setRewardMap(Map<Integer, Integer> rewardMap) {
		this.rewardMap = rewardMap;
	}
	
	/**
	 * 创建一个邮件对象
	 */
	public static Mail create(	Mail mail ,long receiverId, String title, String content, int expiredDays) {
//		Mail mail = Mail.loadOrCreate(Mail.class, Mail.incrementId(Mail.class));
		mail.setReceiver(receiverId);
		mail.setTitle(title);
		mail.setContent(content);
		mail.setState(ConfigMail.NONE);
		mail.setType(0);
		long curTime = System.currentTimeMillis();
		int createAt = (int)(curTime / 1000);
		mail.setCreateAt(createAt);//创建时间
		Date endDate = TimeUtils.addDateTime(new Date(curTime), Calendar.DAY_OF_YEAR, expiredDays);
		int endTime = (int)(endDate.getTime() / 1000);
		mail.setEndTime(endTime);//过期时间
		return mail;
	}
	
	/**
	 * 是否已经领取
	 * @return
	 */
	public boolean isRewarded() {
		if (this.getState() == ConfigMail.REWARD) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否过期
	 * @return
	 */
	public boolean isExpired() {
		if ((System.currentTimeMillis()/1000) >= this.getEndTime()) {//截止时间大于当前时间,表示过期
			return true;
		}
		return false;
	}
	
	/**
	 * 是否可以删除
	 * @return
	 */
	public boolean canDel() {
		if (this.getState() == ConfigMail.READ && this.getRewardMap().isEmpty()) {
			//状态为已读,并且没有奖励配置 可以删除
			return true;
		}else if (this.isRewarded()) {
			//领过奖,可以删除
			return true;
		}else if (this.isExpired()) {
			//过期可以删除
			return true;
		}
		return false;
	}
	
	/**
	 * 实体对象转协议对象
	 * @return
	 */
	public PBPlayer.EmailInfo toProto() {
		PBPlayer.EmailInfo.Builder builder = PBPlayer.EmailInfo.newBuilder();
		builder.setId(this.getId());
		builder.setEmailTitle(this.getTitle());
		builder.setContent(this.getContent());
		int beginTime = TimeUtils.currentTimeSeconds() >= this.getCreateAt() ? TimeUtils.currentTimeSeconds() - this.getCreateAt() : 0;
		builder.setBeginTime(beginTime+"");
		int endTime = this.getEndTime() > TimeUtils.currentTimeSeconds() ? this.getEndTime() - TimeUtils.currentTimeSeconds() : 0;
		builder.setEndTime(endTime+"");
		builder.setState(this.getState());
		
		ItemInfoBuilder infoBuilder = null;
		for (Integer key : this.getRewardMap().keySet()) {
			infoBuilder = ItemInfoBuilder.newInstance();
			infoBuilder.addReward(key, this.getRewardMap().get(key));
			builder.addRewardList(infoBuilder.build());
		}
		return builder.build();
	}

	public long getReceiver() {
		return receiver;
	}

	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCreateAt() {
		return createAt;
	}

	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
