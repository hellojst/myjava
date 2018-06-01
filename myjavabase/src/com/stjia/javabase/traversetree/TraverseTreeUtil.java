package com.stjia.javabase.traversetree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 * 深度优先遍历（先序，中序，后序）和广度优先遍历
 * 深度优先借助栈，广度优先借助队列
 * @author stjia
 * @time 2018年4月7日
 */
public class TraverseTreeUtil {
	//先是递归实现
	/**
	 * 先序  根--左--右；
	 * 还可以将符合条件的点放入orderlist中，List<Node<V>>返回值的作用就是递归到最后，要想把值保留下来就需要从下到上更新，
	 * 否则每层递归结束list的值就被丢弃了，最终list只会是第一层递归的值    所以每层递归完后都需要一个返回值来更新上一层的list；
	 */
	public static <V>  List<Node<V>> preOrderRecursion(Node<V> root, List<Node<V>> orderlist) {
		// TODO Auto-generated method stub
		if (root == null) {
			return new ArrayList<>();
		}
		System.out.print(root.getValue() + ",");
		orderlist.add(root);
		if (root.left != null) {
			orderlist = preOrderRecursion(root.left, orderlist);
		}
		if (root.right != null) {
			orderlist = preOrderRecursion(root.right, orderlist);
		}
		return orderlist;
	}
	
	public static <V> List<Node<V>> midOrderRecursion(Node<V> root, List<Node<V>> orderlist){
		if (root.left != null) {
			orderlist = midOrderRecursion(root.left, orderlist);
		}
		System.out.print(root.getValue().toString() + ",");
		orderlist.add(root);
		if (root.right != null) {
			orderlist = midOrderRecursion(root.right, orderlist);
		}
		
		return orderlist;
	}
	
	public static <V> List<Node<V>> postOrderRecursion(Node<V> root, List<Node<V>> orderlist){
		if (root.left != null) {
			orderlist = postOrderRecursion(root.left, orderlist);
		}
		if (root.right != null) {
			orderlist = postOrderRecursion(root.right, orderlist);
		}
		System.out.print(root.getValue().toString() + ",");
		orderlist.add(root);
		
		return orderlist;
	}
	
	
	//非递归实现  使用栈来实现
	
	/**
	 * 非递归先序遍历
	 * @param root
	 * @param orderlist
	 * @return
	 */
	public static <V> void preOrderStack(Node<V> root, List<Node<V>> orderlist){
		Stack<Node<V>> stack = new Stack<>();
		while(root != null || stack.size() > 0) {
			if (root != null) {
				//压栈前先访问   先序 什么都不管直接取根
				orderlist.add(root);
				System.out.print(root.getValue().toString() + ",");
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				root = root.right;
			}
		}
	}
	
	public static <V> void midOrderStack(Node<V> root, List<Node<V>> orderlist) {
		Stack<Node<V>> stack = new Stack<>();
		while(root != null || !stack.isEmpty()) {
			if (root != null) {
				//中序遍历，先将左子树一直压栈，从最后一个左子树开始遍历
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				//中序：只有左节点为null 才轮到取根
				System.out.print(root.getValue().toString() + ",");
				orderlist.add(root);
				root = root.right;
			}
		}
	}
	
	/**
	 * 后续遍历  非递归实现：若从左节点弹出到根需先判断根是否有右节点，若有右节点同样方法遍历右节点； 若从右节点弹出到根节点则只需输出根节点；
	 * @param root
	 * @param orderlist
	 */
	public static <V> void postOrderStack(Node<V> root, List<Node<V>> orderlist) {
		Stack<Node<V>> stack = new Stack<>();
		Node<V> lastVisitNode = null; //表示根节点是否已访问过，
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
		
		while (!stack.isEmpty()) {
				root = stack.pop();
				//后序：先左后右左右都为null，才取根
				if (root.right != null && lastVisitNode != root.right) { //先判断是否有右节点即该节点是否已处理过；
					stack.push(root);
					root = root.right;
					while (root != null) {
						stack.push(root);
						root = root.left;
					}
				} else {
					orderlist.add(root);
					System.out.print(root.getValue().toString() + ",");	
					lastVisitNode = root; //以输出过的打上表示；
					
				}
			}
	}
	
	
	//广度优先遍历利用队列的先进先出的特性
	public static <V> void breadthFirst(Node<V> root, List<Node<V>> orderlist) {
		Queue<Node<V>> queue = new ArrayDeque<>();
		Node<V> currentNode = null;
		queue.offer(root);
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			System.out.print(currentNode.getValue().toString() + ",");
			orderlist.add(currentNode);
			if (currentNode.left != null) {
				queue.offer(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.offer(currentNode.right);
			}
		}
	}
	
	
	
	public static <V> void printArray(List<Node<V>> nodes) {
		System.out.println();
		for (Node<V> node : nodes) {
			System.out.print(node.getValue().toString() + ";");
		}
		System.out.println("  ---  from orderlist!");
	}
}
