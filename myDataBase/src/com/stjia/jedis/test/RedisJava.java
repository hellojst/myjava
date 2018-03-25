package com.stjia.jedis.test;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * redis连接测试
 * 
 * @author stjia
 * @time 2018年3月25日
 */
public class RedisJava {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379, 3000);
		System.out.println(jedis.auth("admin"));
		System.out.println("连接测试");
		System.out.println(jedis.ping());
		System.out.println("连接结果:" + ("pong".equals(jedis.ping().toLowerCase()) ? "success!" : "failed"));
		if (!"pong".equals(jedis.ping().toLowerCase())) {
			return;
		}
		jedis.bgsave();
		jedis.setnx("a", "b");
		jedis.set("d", "3");
		System.out.println(jedis.set("e", "5"));
		int i = Integer.parseInt(jedis.get("d"));
		System.out.println(i);
		Object string = jedis.eval("return {KEYS[1],KEYS[2],KEYS[3],ARGV[1],ARGV[2],ARGV[3]}", 3, "KEY", "KEY2", "KEY3",
				"VALUE1", "V2", "V3");
		System.out.println(string);
		List<String> values = jedis.lrange("mlist", 0, 10);
		System.out.println(values);
	}
}
