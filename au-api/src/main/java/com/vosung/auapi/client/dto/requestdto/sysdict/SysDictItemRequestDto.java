package com.vosung.auapi.client.dto.requestdto.sysdict;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 查询字典列表入参
 * @Author 彬
 * @Date 2019/6/17
 */
@Data
public class SysDictItemRequestDto implements Serializable {
    private static final long serialVersionUID = -5148791440308493442L;

    private Integer id;

    private String itemCode;

    private String itemName;
    @NotNull(message = "字典类型不能为空")
    private String dictCode;

    private String summary;

    private String status;

    private String isSystemInit;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
