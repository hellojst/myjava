package com.stjia.javabase.tree;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.xml.soap.Node;

/**
 * 红黑树 2-3树的升级优化版，更少的代码实现与2-3树相同的功能
 * @author stjia
 * @time 2018年4月3日
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		Key key;
		Value value;
		Node left, right; //左右子树
		int N;
		//与二叉查找树相比仅多了一个color
		boolean color; //由该结点的父节点指向该结点的链接颜色；root节点的父节点为null； 叶子节点的子节点为null；
		Node(Key key, Value val, int N, boolean color){
			this.key = key;
			this.value = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node node) {
		//先考虑特殊情况
		if (node == null) {
			return false; //约定空连接为黑色
		}
		return node.color == RED;
	}
	
	private int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.N;
	}
	
	/**
	 *左旋原因是:新插入的红链接出现了红色右链接；--- 即新节点出现在了右子树
	 * 默认新插入的节点都是红链接，参考2-3树，先往根上放的 2->3,3拆成2；
	 * 左旋   ： 将右红链接移到左， 实际就是 ：： 有子树变为根，根变成其左子树
	 * @param h 旋转前局部树根节点
	 * @return 旋转后的新根节点
	 */
	private Node rotateLeft(Node hNode) {
		Node xNode = hNode.right;
		//开始根与根右子树的交换
		hNode.right = xNode.left;
		xNode.left = hNode;
		hNode.color = RED;
		xNode.N = hNode.N; //只是左右旋 节点总数目不变
		hNode.N = 1 + size(hNode.left) + size(hNode.right);
		return xNode;
	}
	
	/**
	 * 右旋原因:新插入点会导致两条连续的红链接
	 * 右旋 -- 
	 * @param hNode 
	 * @return
	 */
	private Node rotateRight(Node hNode) {
		Node xNode = hNode.left;
		xNode.right = hNode;
		hNode.left = xNode.right;
		hNode.color = RED;
		xNode.N = hNode.N;
		hNode.N = 1 +size(hNode.left) + size(hNode.right);
		return xNode;
	}
	
	/**
	 * 新建插入有三种情况p279:
	 * 1.新键最大，2新键最小 3，新键位于两者间
	 */
	private void flipColor(Node hNode) {
		hNode.color = RED;
		hNode.left.color = BLACK;
		hNode.right.color = BLACK;
	}
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = BLACK; // 因为插入新节点都为红节点，所以根节点显示设为黑色
	}
	
	/**
	 * 
	 * @param root 尝试插入位置，通过相对大小来判断是左、右还是刚好在此位置；
	 * @param key
	 * @param value
	 * @return
	 */
	private Node put(Node root, Key key, Value value) {
		if (root == null) {
			return new Node(key, value, 1, RED); //标准插入所有的插入节点都为红节点，也是递归的结束标志
		}
		int cmp = key.compareTo(root.key);
		if (cmp > 0) {
			root.right = put(root.right, key, value);
		} else if (cmp < 0) {
			root.left = put(root.left, key, value);
		} else {
			root.value = value;
		}
		
		//对所有插入情况（3种）进行分别处理 ： 新键最大，新键最小，新键介于两者间；
		//复杂关系依次递增，介于两者之间右旋后 变为 新键最小，新建最小左旋转后变为新键最大，新键最大就需要颜色转换
		//root 为带插入点的父节点
		//左旋 ---> 右旋 ---> 颜色转换  这三个操作会从左到右执行，不管从哪个地方切入，一定会执行下面的所有if
		if (isRed(root.right) && !isRed(root.left)) rotateLeft(root); //红色右节点，左旋
		if (isRed(root.left) && !isRed(root.right)) rotateRight(root);
		if (isRed(root.left) && isRed(root.right)) flipColor(root); 
		//旋转后总结点数虽然不变但被旋转的节点下数目会被调整；
		root.N = 1 + size(root.left) + size(root.right);
		return root;
	}
	
	//红黑树本身就是一个平衡二叉查找树，因此get方法与二叉查找树并无区别
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
	
	/**
	 * 删除最小值删除通过返回null来删除及节点，一般删除改变互相连接状态来删除，因为只有删除的点是红链接删除后所有空链接到root的
	 * 黑链接数量才不变，所以一定要先将删除点置为红
	 * 思想删除的决不能是2-链接，所以要引入临时的4-链接
	 * @param key
	 */
//	public void delete(Key key) {
//		//对左右都是黑的要构造临时4-链接，使删除后依然平衡
//		if (!isRed(root.left) && !isRed(root.right)) {
//			root.color = RED;
//		}
//		root = delete(root, key);
//	}
	
//	private Node delete(Node node, Key key) {
//		if (key.compareTo(node.key) < 0) {
//			if (!isRed(node.left) && !isRed(node.left.left)) {
//				node = moveRedLeft(node);
//			}
//			node.left = delete(node.left, key);
//		} else {
//			if (isRed(node.left)) {
//				node = rotateRight(node);
//			}
//			if (key.compareTo(node.key) == 0 && node.right == null) {
//				return null;
//			}
//			if (!isRed(node.right) && !isRed(node.right.left)) {
//				node = moveRedLeft(node);
//			}
//			
//			if (key.compareTo(node.key) == 0) {
//				node.value = get(node.key, min)
//			}
//		}
//	}
	
	/**
	 * 子节点不为红时用来将子节点变红
	 * @param node
	 * @return
	 */
	private Node moveRedLeft(Node node) {
		//父节点上提 （置红）
		flipColor(node);
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
		}
		return node;
	}
	

}
