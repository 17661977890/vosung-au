package com.vosung.auapi.client.dto.responsedto.orgrelationscheme;

import com.vosung.auapi.client.entity.TAuOrganization;
import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询方案入参
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class GetSchemeInfoResponseDto implements Serializable {
    private static final long serialVersionUID = -7694733978892275088L;
    //方案id
    private Integer id;
    //方案编码
    @FiledMessage(name = "方案编码")
    private String schemeCode;
    //方案名
    @FiledMessage(name = "方案名称")
    private String schemeName;
    //组织职能类型
    private String orgFunctionType;

    @FiledMessage(name = "职能类型")
    private String orgFunctionTypeName;
    //顶层组织
    private Integer topOrgId;
    @FiledMessage(name = "顶层组织")
    private String topOrgName;
    //是否默认方案
    @FiledMessage(name = "默认方案")
    private String isDefaultScheme;
    //生效日期
    @FiledMessage(name = "启用日期")
    private Date enableDate;
    //失效日期
    @FiledMessage(name = "截至日期")
    private Date expiryDate;

    private String dataState;
    @FiledMessage(name = "数据状态")
    private String dataStateName;
    @FiledMessage(name = "禁用状态")
    private String prohibitState;
    //描述
    private String summary;
    //组织关系树
    private List<Object> treeList;
    //可选组织列表（已关联方案后）
    private List<TAuOrganization> selectAbleOrgList;

}
