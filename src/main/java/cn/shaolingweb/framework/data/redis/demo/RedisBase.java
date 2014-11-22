package cn.shaolingweb.framework.data.redis.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 基础类，业务类直接集成，可直接使用redis模板类
 * @author  shaoling@shaolingweb.cn
 */
public abstract class RedisBase {
	@Autowired
	private StringRedisTemplate template;
	public StringRedisTemplate getTemplate() {
		return template;
	}
        public void setTemplate(StringRedisTemplate template) {
                this.template = template;
        }

}