package com.stjia.javabase.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 构建一棵二叉查找树  泛型二叉查找树以适应各种类型 ， 所传key需要实现Comparable<Key>明确比较大小的方法用来判断加入节点放左子树还是右子树
 * @author stjia
 * @time 2018年4月3日
 */
public class BST<Key extends Comparable<Key>, Value> {
	/**
	 * 根节点
	 */
	private Node root;
	
	private class Node {
		private Key key;
		private Value value; //键值对
		private Node left, right; //左右子树
		private int N; //节点总数
		
		/**
		 * 内部类构造函数，初始化时赋值
		 */
		public Node (Key key, Value val, int N) {
			this.key = key; this.value = val; this.N = N;
		}
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node root) {
		if (root == null) {
			return 0;
		} else {
			return root.N;
		}
	}
	
	/**
	 * 添加节点
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value) {
		
	}
	
	/**
	 * 利用根节点往下递归，直到找到合适的位置
	 * @param root 初始为根节点，往下递归到最后找到合适位置即为插入后节点的父节点
	 * @param key 
	 * @param value 
	 * @return 从上往下查找，从下往上更新，返回更新后的root
	 */
	public Node put(Node root, Key key, Value value) {
		if(root == null) {
			return new Node(key, value, 1); //若根节点为空则以此及节点为根并返回，  也是递归结束条件
		} 
		int cmp = key.compareTo(root.key); //一般比较为 第一减去第二： key-root.key; 返回比较结果
		//从左右子树往下递归找，当root为null时就为此节点插入位置
		if (cmp > 0) { //若大于节点从右子树往下递归查找
			root.right = put(root.right, key, value);
		} else if (cmp < 0) { //若比此根节点小，从左子树递归往下找
			root.left = put(root.left, key, value);
		} else {
			root.value = value; //若key一样则用新值覆盖旧值
		} 
		
		//递归到最深层找到位置后，开始从下往上修改节点下节点总数
		root.N = size(root.left) + size(root.right) + 1;
		//传下来的是root的最终传上去的依然是root，不过是更新后的
		return root;
	}
	
	/**
	 * 查找及节点
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		return get(root, key);
	}
	
	/**
	 * 查找也一样，从根节点往下查找
	 * @param root
	 * @param key
	 * @param value
	 * @return
	 */
	private Value get(Node root, Key key) {
		if (root == null) { //先考虑特殊情况
			return null;
		}
		int cmp = key.compareTo(root.key);
		if (cmp == 0) { //匹配到就返回该root的值
			return root.value;
		} else if (cmp > 0) {
			return get(root.right, key);
		} else {
			return get(root.left, key);
		}
			
	}

	
	private void delete() {
		
	}
}
