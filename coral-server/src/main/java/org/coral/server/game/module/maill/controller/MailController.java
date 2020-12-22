package org.coral.server.game.module.maill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

/**
 * 邮件控制器
 * @author Jeremy
 *
 */
@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;

//	@Autowired
//	private SessionManager sessionManager;
	
//	/**
//	 * @param session
//	 * @return
//	 */
//	/**获取邮件列表*/
//	@Request(PBDefine.PBProtocol.ReqEmailList_VALUE)
//	public void reqEmailList(Session session, PBPlayer.ReqEmailList req) {
//		Player player = sessionManager.getPlayer(session);
//		Collection<Mail> mails = Mail.load(Mail.class, "receiver", player.getPlayerId());
//		AckEmailListResp resp = AckEmailListResp.newInstance();
//		resp.addEmailInfos(mails);
//		SendMessageUtil.sendResponse(player.getId(), resp);
//	}
//
//	/**
//	 * @param session
//	 * @return
//	 */
//	/**阅读邮件*/
//	@Request(PBDefine.PBProtocol.ReqReadEmail_VALUE)
//	public void reqReadEmail(Session session, PBPlayer.ReqReadEmail req) {
//		Player player = sessionManager.getPlayer(session);
//		int code = mailService.read(player.getId(), req.getId());
//		AckReadEmailResp resp = AckReadEmailResp.newInstance();
//		resp.setId(req.getId());
//		resp.setCode(code);
//		SendMessageUtil.sendResponse(player.getId(), resp);
//	}
//	
//	/** 
//	 * @param session
//	 * @return
//	 */
//	/**领取邮件*/
//	@Request(PBDefine.PBProtocol.ReqReceiveEmail_VALUE)
//	public void reqReceiveEmail(Session session, PBPlayer.ReqReceiveEmail req) {
//		Player player = sessionManager.getPlayer(session);
//		int code = mailService.receive(player.getId(), req.getId());
//		AckReceiveEmailResp resp = AckReceiveEmailResp.newInstance();
//		resp.setCode(code);
//		SendMessageUtil.sendResponse(player.getId(), resp);
//	}
//
//	
//	/**
//	 * @param session
//	 * @return
//	 */
//	/**删除邮件*/
//	@Request(PBDefine.PBProtocol.ReqDeleteEmail_VALUE)
//	public void reqDeleteEmail(Session session, PBPlayer.ReqDeleteEmail req) {
//		Player player = sessionManager.getPlayer(session);
//		AckDeleteEmailResp resp = AckDeleteEmailResp.newInstance();
//		int code = mailService.delete(player.getId(), req.getId(), resp);
//		resp.setCode(code);
//		SendMessageUtil.sendResponse(player.getId(), resp);
//	}

}
