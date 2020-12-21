package com.maya.game.modules.maill.repository;


import com.maya.common.persistence.repository.AbstractRepository;
import com.maya.game.modules.maill.domain.Mail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailRepository extends AbstractRepository<Mail> {


    public List<Mail> deleteByEndtime(long endTime) {
        return getList(Mail.class, "delete from mail where create_at >= ?", endTime);
    }

    public List<Mail> getMailsByPlayerId(long playerId) {
        return getList(Mail.class, "select * from mail where receiver = ? ORDER BY create_at DESC", playerId);
    }



}
