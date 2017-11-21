package cn.e3mall.freemarker.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.weaver.patterns.PatternNode;

public class Person {
	
	private String name;
	private String address;
	private Integer age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	//验证是否符合:
	//Pattern.matches(String regex,String target);
	//等价于 str.matches(String regex)
	//matcher.matches(), 尝试匹配整个字符串
	//matcher.lookingAt() 代表从头开始匹配, 代表从第几个字符开始匹配 , 不全部匹配到最后,只要开头满足即可 
	//matcher.find(), 从头到尾仔细寻找字符串, 相当于lookingAt从0-lenth
	//find方法查找下一个匹配的字符串, 如果想让查询的指针返回, 则使用reset方法 , 其中reset(String newTarget), 把数据源都改变了
	
	//分割字符串, 使用pattern对象进行分割
	//pattern.split(String str, int SliceNum), 第二个参数可以省略, 表示能分多少是多少, 注意最后可能会分出空字符
	//matcher.groupCount() find方法可以查找捕获分组的数量 及()有几个
	
	//查找所有符合条件的字符串
	//一下需要与find()进行配合
	//matcher.start() 第一组的起始位置, 
	//matcher.end() 第一组的结束位置
	//matcher.group() 返回符合条件的字符串
	//matcher.start(int groupNum) 返回第n组的起始位置, n从1开始
	//matcher.end(int groupNum) 返回第n组的结束位置, n从1开始
	//matcher.group(int groupNum) 返回第n组的字符串, n从1开始
	
	//替换目标字符串
	//matcher.replaceAll(String replacement) 替换每一个
	//matcher.prelaceFirst(String replacement) 替换第一个
	//matcher.appendReplacement(StringBuffer sb , String replacement) 将第一次替换的group及之前的
	
	//matcher.replaceAll的底层, 一次取代,一次添加, 最后把尾巴加上
	//添加到StringBuffer之中, 配合每一次find, 一次次添加查询结果
	//matcher.appendTail(sb); 把剩余没有匹配的也加进来
	
	
	public static void main(String[] args) {
	/*
		Pattern p = Pattern.compile("((?<=\\()).*(?=\\))");
		String target = "find(sss);";
		
		Matcher m = p.matcher(target);
		int count = m.groupCount();
		System.out.println(count);

		System.out.println(m.group(1));*/
		
	   Pattern p = Pattern.compile(".*?(?=abc)");
	   
	   //没有匹配到的, 就会让指针向前走一位, 匹配到了, 就会让指针走一个n位置
	   Matcher matcher = p.matcher("3eg3dcabcgfabc");
	   while(matcher.find()) {
		   String result = matcher.group();
		   System.out.println(result);
	   }
	   
	
	}
}
