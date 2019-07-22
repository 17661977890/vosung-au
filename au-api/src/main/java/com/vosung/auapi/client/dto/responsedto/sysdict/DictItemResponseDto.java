package com.vosung.auapi.client.dto.responsedto.sysdict;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @Author 彬
 * @Date 2019/6/17
 */
@Data
public class DictItemResponseDto implements Serializable {
    private static final long serialVersionUID = -5332688439034229595L;

    private Integer id;
    @FiledMessage(name = "编码")
    private String itemCode;
    @FiledMessage(name = "名称")
    private String itemName;

    private String dictCode;
    @FiledMessage(name = "描述")
    private String summary;

    private String status;

    private String isSystemInit;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
