package org.coral.server.game.module.item.handler;

import org.springframework.stereotype.Controller;

/**
 * 物品控制器
 */
@Controller
public class ItemHandler {
	
//	@Autowired
//	private ItemService itemService;
//	@Autowired
//	private SessionManager sessionManager;
//	
//	public void updateBagList(Session session) {
//		long playerId = sessionManager.getPlayerId(session);
//
//		//登陆成功,下发背包信息
//		Collection<IItem> items = itemService.getItems(playerId);
//		AckBagListResp ack = AckBagListResp.newInstance();
//		ack.addItem(items);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}
//	
//	/**
//	 * 操作道具1合成, 2出售
//	 * @param session
//	 * @return
//	 */
//	@Request(PBDefine.PBProtocol.ReqOperateItem_VALUE)
//	public void operateItem(Session session, PBBag.ReqOperateItem req) {
//		long playerId = sessionManager.getPlayerId(session);
//		int code = itemService.operateItem(playerId, req.getOpType(), req.getItemId(), req.getNum());
//		SendMessageUtil.sendResponse(playerId, AckOperateItemResp.newInstance().setCode(code));
//	}
//	
//	/**
//	 * @param session
//	 * @return
//	 */
//	@Request(PBDefine.PBProtocol.ReqBuyCount_VALUE)
//	public void reqBuyCount(Session session, PBBag.ReqBuyCount req) {
//		long playerId = sessionManager.getPlayerId(session);
//		int code = itemService.buyCount(playerId, req.getType());
//		AckBuyCountResp ack = AckBuyCountResp.newInstance();
//		ack.setCode(code);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}
//	
//	@Request(PBDefine.PBProtocol.ReqQuickSellItemEquip_VALUE)
//	public void reqQuickSellItemEquip(Session session, PBBag.ReqQuickSellItemEquip req) {
//		long playerId = sessionManager.getPlayerId(session);
//		AckQuickSellItemEquipResp ack = AckQuickSellItemEquipResp.newInstance();
//		int code = itemService.reqQuickSellItemEquip(playerId, req, ack);
//		ack.setCode(code);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}
//	
//	@Request(PBDefine.PBProtocol.ReqBagUsePackage_VALUE)
//	public void reqBagUsePackage(Session session, PBBag.ReqBagUsePackage req) {
//		long playerId = sessionManager.getPlayerId(session);
//		AckBagUsePackageResp ack = AckBagUsePackageResp.newInstance();
//		int code = itemService.reqBagUsePackage(playerId, req, ack);
//		ack.setCode(code);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}
	
}
