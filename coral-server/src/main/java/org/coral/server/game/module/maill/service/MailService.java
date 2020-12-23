package com.maya.game.modules.maill.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.maya.config.ConfigTipsMgr;
import com.maya.core.Context;
import com.maya.core.SendMessageUtil;
import com.maya.core.config.ConfigManager;
import com.maya.data.config.pojo.ConfigMail;
import com.maya.game.modules.item.proto.AckRewardsResp;
import com.maya.game.modules.maill.domain.Mail;
import com.maya.game.modules.maill.repository.MailRepository;
import com.maya.game.modules.maill.resp.AckDeleteEmailResp;
import com.maya.game.modules.maill.resp.AckUpdateEmailResp;
import com.maya.game.modules.player.domain.Player;
import com.maya.utils.CollectionUtil;
import com.maya.utils.StringUtils;
import com.maya.utils.log.NatureEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * 邮件系统服务
 * @author kdiller
 */
@Service
public class MailService {

	/**玩家邮件缓存*/
//	private final static Map<Long, MailBox> PLAYER_MAILBOX = new ConcurrentHashMap<Long, MailBox>();

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(MailService.class);

	@Autowired
	private MailRepository mailRepository;


	public void onLogin(long playerId) {
	}

//	public void onLogout(long playerId) {
//		PLAYER_MAILBOX.remove(playerId);
//	}

//	/**
//	 * 获取邮箱
//	 */
//	public MailBox getMailBox(long playerId) {
//		MailBox mailBox = PLAYER_MAILBOX.get(playerId);
//		if (mailBox == null) {
//			List<Mail> list = mailRepository.getMailsByPlayerId(playerId);
//			List<Mail> mails = initMailsList(list);
//			mailBox = new MailBox();
//			mailBox.initMailBox(mails);
//			PLAYER_MAILBOX.put(playerId, mailBox);
//		}
//		return mailBox;
//	}

//	/**
//	 * 初始化邮件列表
//	 *
//	 * @param mailList
//	 * @return
//	 */
//	public List<Mail> initMailsList(List<Mail> mailList) {
//		List<Mail> result = Lists.newArrayList();
//		for (Mail mail : mailList) {
//			if (mail == null)
//				continue;
//			result.add(mail);
//		}
//		// 删除非法数据
////		if (!deleteList.isEmpty()) {
////			itemDAO.setUsage2False(deleteList);
////		}
//		return result;
//	}



//	/***
//	 * 根据id获取邮件列表
//	 * @param playerId
//	 */
//	public Collection<Mail> getMails(long playerId) {
//		MailBox mailBox = getMailBox(playerId);
//		if (mailBox == null) {
//			return new ArrayList<Mail>();
//		}
//		return mailBox.getMails();
//	}


	/***
	 * 请求阅读邮件
	 * @param playerId
	 */
	public int read(long playerId, long mailId) {
		Mail mail = Mail.load(Mail.class, mailId);
		if(mail == null){
			return ConfigTipsMgr.Mail_400;
		}
		if (mail.getState() == ConfigMail.NONE) {
			mail.setState(ConfigMail.READ);
			mail.update();
		}
		responseEmail(playerId, Lists.newArrayList(mailId));
		return 0;
	}


	/***
	 * 请求领取奖励
	 * @param playerId
	 */
	public int receive(long playerId, long mailId) {
		if (mailId < 0) {
			return receiveAll(playerId, mailId);
		}else {
			return receiveOne(playerId, mailId);
		}
	}
	/**
	 * 领取一封邮件
	 * @param playerId
	 * @param mailId
	 * @return
	 */
	private int receiveOne(long playerId, long mailId) {
		Mail mail = Mail.load(Mail.class, mailId);
		if (mail == null) {
			return ConfigTipsMgr.Mail_401;
		}
		else if (mail.isRewarded()) {
			return ConfigTipsMgr.Mail_402;
		}else if (mail.isExpired()) {
			return ConfigTipsMgr.Mail_403;
		}
		mail.setState(ConfigMail.REWARD);
		mail.update();
		//领取附件
		Context.getItemService().addNewItem(playerId, mail.getRewardMap(), NatureEnum.MAIL, ""+mailId);
		//更新状态
		responseEmail(playerId, Lists.newArrayList(mailId));
		//更新奖励
		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(mail.getRewardMap());
		SendMessageUtil.sendResponse(playerId, resp);
		return 0;
	}

	/**
	 * 一键领取
	 * @param playerId
	 * @param mailId
	 * @return
	 */
	private int receiveAll(long playerId, long mailId) {
//		MailBox mailBox = getMailBox(playerId);
//		if (mailBox == null) {
//			return ConfigTipsMgr.Mail_400;
//		}
		//List<IItem> items = Lists.newArrayList();
		Map<Integer, Integer> rewardMap = Maps.newHashMap();
		List<Long> mailIds = Lists.newArrayList();
//		Collection<Mail> mails = mailBox.getMailsCanReward();
		Collection<Mail> mails = Mail.load(Mail.class, "receiver", playerId);
		for (Mail mail : mails) {
			if (mail == null || mail.isExpired() || mail.isRewarded())
				continue;

			mail.setState(ConfigMail.REWARD);
			mail.update();
			//领取附件
			Context.getItemService().addNewItem(playerId, mail.getRewardMap(), NatureEnum.MAIL, StringUtils.toLogString("mailId", mail.getId()));
			CollectionUtil.mergeToMap(mail.getRewardMap(), rewardMap);
			//items.addAll(temp);
			mailIds.add(mail.getId());
		}
		//更新状态
		responseEmail(playerId, mailIds);
		//更新奖励
		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(rewardMap);
		SendMessageUtil.sendResponse(playerId, resp);
		return 0;
	}

	/***
	 * 请求删除邮件
	 * @param playerId
	 */
	public int delete(long playerId, long mailId, AckDeleteEmailResp ack) {
		if (mailId < 0) {
			return deleteAll(playerId, mailId, ack);
		}else {
			return deleteOne(playerId, mailId, ack);
		}
	}

	/**
	 * 删除一封邮件
	 * @param playerId
	 * @param mailId
	 * @return
	 */
	private int deleteOne(long playerId, long mailId, AckDeleteEmailResp ack) {
		Mail mail = Mail.load(Mail.class, mailId);
		if (mail == null) {
			return ConfigTipsMgr.Mail_400;
		}
		if (mail.canDel()) {
			ack.addMailId(mailId);
			mail.remove();
		}
		return 0;
	}

	/**
	 * 删除多封邮件
	 * @param playerId
	 * @param mailId
	 * @return
	 */
	private int deleteAll(long playerId, long mailId, AckDeleteEmailResp ack) {
//		MailBox mailBox = getMailBox(playerId);
//		if (mailBox == null) {
//			return ConfigTipsMgr.Mail_400;
//		}
		List<Long> dels = Lists.newArrayList();
		Collection<Mail> mails = Mail.load(Mail.class, "receiver", playerId);
		for (Mail mail : mails) {
			if (mail.canDel()) {
				dels.add(mail.getId());
				ack.addMailId(mail.getId());
			}
		}
		for (Long del : dels) {
			Mail mail = Mail.load(Mail.class, del);
			if(mail != null)
				mail.remove();
		}
		return 0;
	}

	///////

	/**
	 * 根据邮件配置, 发送一封邮件
	 */
	public void sendMail(long playerId, int configID, Map<Integer, Integer> rewards, Object... args) {
		ConfigMail config = ConfigManager.get(ConfigMail.class, configID);
		if(config==null)
			return;
		String text = config.getMailContent();
		List<Object> textArgs = Lists.newArrayList();
		for(Object arg : args)
			textArgs.add(arg);
		String content = StringUtils.formatString(text, textArgs);
		Map<Integer, Integer> rewardMap = null;
		if (rewards != null) {
			rewardMap = CollectionUtil.mergeMap(config.getRewardItemMap(), rewards);
		}else {
			rewardMap = config.getRewardItemMap();
		}
		this.sendMail(playerId, config.getMailSubject(), content, config.getStorageTime(), rewardMap);
	}

	/**
	 * 发送邮件
	 */
	public void sendMail(long playerId, String title, String content, int expiredDays, Map<Integer, Integer> rewards) {
		//创造一封邮件
		Mail mail = Mail.loadOrCreate(Mail.class,Mail.incrementId(Mail.class), e->{
			Mail.create(e,playerId, title, content, expiredDays);
		});
//		Mail mail = Mail.create(playerId, title, content, expiredDays);
//		mail.setRewardMap(rewards);
//		mail.save();
		if(!Player.isOnline(playerId))
			return;
		//通知玩家
		this.responseEmail(playerId, Lists.newArrayList(mail.getId()));
	}
	////////////////////////////////////////////////////
	/**
	 * 更新单邮件信息
	 * @return void
	 * @date 2019年6月18日下午1:50:42
	 */
	public void responseEmail(long playerId, List<Long> mailIds)
	{
//		MailBox mailBox = getMailBox(playerId);
//		if (mailBox == null) {
//			return;
//		}
		AckUpdateEmailResp ack = AckUpdateEmailResp.newInstance();
		for (Long mailId : mailIds) {
			Mail mail = Mail.load(Mail.class, mailId);
			if (mail != null) {
				ack.addMails(mail);
			}
		}
		SendMessageUtil.sendResponse(playerId, ack);
	}
	//////////////////////分割///////////////////////////

}
