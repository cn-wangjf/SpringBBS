package cn.wjf.bbs.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


/**
 * User: wangjf
 * Date: 14-11-13
 * Time: 上午10:40
 *<br>
 * <b>类描述:</b>
 *
 * <pre>
 * 主题对应的主题帖
 * </pre>
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("2")
public class MainPost extends Post {
}
