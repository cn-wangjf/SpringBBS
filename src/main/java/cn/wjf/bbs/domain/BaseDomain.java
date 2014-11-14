package cn.wjf.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: wangjf
 * Date: 14-11-12
 * Time: 下午4:31
 */
public class BaseDomain implements Serializable {


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
