//package com.maya.game.modules.maill.domain;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.maya.data.config.pojo.ConfigMail;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
///**
// * 玩家邮箱
// * @author Jeremy
// */
//public class MailBox {
//
//	//所有的物品 key:mailId(guid)
//	private Map<Long, Mail> mailMap;
//
//	public MailBox() {
//		mailMap = Maps.newConcurrentMap();
//	}
//
//	/**
//	 * 初始化邮箱
//	 */
//	public void initMailBox(List<Mail> mailList) {
//		//初始化背包
//		for (Mail mail : mailList) {
//			mailMap.put(mail.getId(), mail);
//		}
//	}
//
//	/**
//	 * 获取所有可领取邮件
//	 * @return 可领取邮件
//	 */
//	public Collection<Mail> getMailsCanReward(){
//		//初始化背包
//		List<Mail> ret = Lists.newArrayList();
//		Collection<Mail> mails = mailMap.values();
//		for (Mail mail : mails) {
//			if (!mail.isRewarded()) {
//				ret.add(mail);
//			}
//		}
//		return ret;
//	}
//
//	/**
//	 * @return 获取所有邮件
//	 */
//	public Collection<Mail> getMails(){
//		return mailMap.values();
//	}
//
//	/**
//	 * @return 根据id获取指定邮件
//	 */
//	public Mail getMail(long mailId){
//		return mailMap.get(mailId);
//	}
//
//	/**
//	 *  删除邮件, 先删除缓存, 再删除数据库
//	 */
//	public void deleteEmails(List<Long> eids) {
//		for (Long eid : eids) {
//			this.deleteEmail(eid);
//		}
//	}
//
//	/**
//	 *  删除邮件, 先删除缓存, 再删除数据库
//	 */
//	public void deleteEmail(Long eid) {
//		Mail mail = this.mailMap.remove(eid);
//		if (mail != null) {
//			mail.remove();
//		}
//	}
//
//	/**
//	 * 添加新邮件
//	 * @param mail 新邮件
//	 */
//	public void add(Mail mail) {
//		this.mailMap.put(mail.getId(), mail);
//	}
//
//	/**
//	 * 阅读邮件,改变已读状态
//	 * @param eid 邮件id
//	 */
//	public void read(long eid) {
//		Mail mail = this.mailMap.get(eid);
//		if (mail.getState() == ConfigMail.NONE) {
//			mail.setState(ConfigMail.READ);
//			mail.update();
//		}
//	}
//
//	/**
//	 * 领取邮件,改变为领取状态
//	 * @param eid 邮件id
//	 */
//	public void reward(long eid) {
//		Mail mail = this.mailMap.get(eid);
//		mail.setState(ConfigMail.REWARD);
//		this.mailMap.put(eid, mail);
//		mail.update();
//	}
//
//}
