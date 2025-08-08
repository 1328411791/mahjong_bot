package org.liahnu.bot.biz.handler.record;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.biz.BizFailCodeEnum;
import org.liahnu.bot.biz.BizServiceException;
import org.liahnu.bot.biz.BizServiceHandleInterface;
import org.liahnu.bot.biz.base.AbstractBizServiceHandler;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.request.record.AddContestRecordBizServiceRequest;
import org.liahnu.bot.biz.result.record.AddContestRecordBizServiceResult;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.domain.User;
import org.liahnu.bot.model.type.ContestStatus;

/**
 * @author lihanyu
 */
@Slf4j
@BizServiceHandleInterface(type = BizServiceTypeEnum.ADD_RECORD)
public class ContestAddRecordBizHandler
        extends AbstractBizServiceHandler<AddContestRecordBizServiceRequest, AddContestRecordBizServiceResult> {


    @Override
    public AddContestRecordBizServiceResult handle(AddContestRecordBizServiceRequest request) {

        Contest contest = contestService.getById(request.getContestId());
        if (contest == null){
            log.error("比赛不存在");
            throw new RuntimeException("比赛不存在");
        }
        // 获取用户
        User user = userService.queryByQQId(request.getUserId());


        // 检查是否是创建者所在的比赛
        Assert.equals(contest.getCreateGroupId(),request.getGroupId());

        ContestRecord cud = contestRecordService.queryRecordByCUD(request.getContestId(), user.getId(), request.getDirection());

        if (cud != null) {
            log.error("该记录已存在, ContestRecord: {}", cud.toString());
            throw new BizServiceException(BizFailCodeEnum.CONTEST_RECORD_EXIST, "该记录已存在");
        }

        ContestRecord record = new ContestRecord();
        record.setContestId(request.getContestId());
        record.setDirection(request.getDirection());
        record.setPoint(request.getScore());
        record.setRecordUserId(user.getId());

        transactionTemplate.execute((status -> {
            // 如果比赛未开始，设置为开始
            if (contest.getStatus() == ContestStatus.NOT_START) {
                contest.setStatus(ContestStatus.START);
                contestService.updateById(contest);
            }

            return contestRecordService.save(record);
        }));

        // TODO: 等待解耦此处
        contestRecordService.calculateScore(request.getContestId());

        AddContestRecordBizServiceResult result = new AddContestRecordBizServiceResult();
        result.setRecord(record);
        return result;
    }


}