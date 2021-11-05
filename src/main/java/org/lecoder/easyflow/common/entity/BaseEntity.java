package org.lecoder.easyflow.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 基础实体类
 *
 * @author: lijile
 * @date: 2021/10/22 19:39
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

}
