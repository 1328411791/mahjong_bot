package org.liahnu.bot.biz;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author lihanyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizServiceException extends RuntimeException {

    public BizFailCodeEnum type;

    public String message;

}
