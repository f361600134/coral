package org.coral.server.game.module.chatplus.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.coral.net.core.annotation.Cmd;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IHandler;
import org.coral.server.game.data.proto.PBDefine.PBProtocol;
import org.coral.server.game.data.proto.PBPlayer.ReqChat;
import org.coral.server.game.module.chatplus.service.ChatService;
import org.coral.server.game.module.chatplus.service.CommandService;
import org.coral.server.game.module.player.proto.AckTipsResp;
import org.coral.server.game.module.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 聊天控制器
 *
 * @author Jeremy
 */
@Controller
public class ChatController implements IHandler{


    private static final Log log = LogFactory.getLog(ChatController.class);

    @Autowired
    private ChatService chatServicePlus;
    
    @Autowired
    private CommandService commandService;
    
    @Autowired
    private PlayerService playerService;

    @Cmd(id = PBProtocol.ReqChat_VALUE, mustLogin = false)
    public void chat(GameSession session, ReqChat req) {
        long playerId = session.getPlayerId();
        
        boolean isGm = this.commandService.isGmCommand(req.getContent());
        if (isGm) {
            this.commandService.gmFromClient(session, req);
        } else {
            //聊天
            int code = this.chatServicePlus.chat(req, playerId);
            AckTipsResp ack = AckTipsResp.newInstance().setTipsId(code);
//            playerService.sendMessage(playerId, ack);
            session.send(ack);
        }
    }

}
