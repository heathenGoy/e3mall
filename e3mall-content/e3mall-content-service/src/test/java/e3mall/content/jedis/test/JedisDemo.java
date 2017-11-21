package e3mall.content.jedis.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
	
	public void jedisDemo() {
		
		Jedis jedis = new Jedis("192.168.139.128",6379);
		jedis.set("name", "你好!");
		String result = jedis.get("name");
		
		System.out.println(result);
		
	}
	
	public void jedisPoolDemo() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(2);
		config.setMaxTotal(30);
		
		JedisPool pool = new JedisPool(config, "192.168.139.128",6379);
		Jedis resource = pool.getResource();
		
		String result = resource.get("name");
		
		System.out.println(result);
		
		pool.close();
		
	}
	
	public void clusterJedisPoolDemo() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(2);
		config.setMaxTotal(30);
		
		Set<HostAndPort> servers = new HashSet<>();
		for (int i = 1 ; i <= 8; i ++ ) {
			servers.add(new HostAndPort("192.168.139.128", 7000 + i));
		}
		JedisCluster cluster = new JedisCluster(servers, config);
		//给redis集群设置值
		cluster.set("address", "beijing");
		//获取redis集群中值
		String address = cluster.get("address");
		System.out.println(address);
		
		
	}
	
}
