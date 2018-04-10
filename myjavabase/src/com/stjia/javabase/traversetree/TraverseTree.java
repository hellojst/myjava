package com.stjia.javabase.traversetree;

import java.util.ArrayList;
import java.util.List;

/**
 * 遍历树的结构；
 * @author stjia
 * @time 2018年4月7日
 */
public class TraverseTree {
	public static void main(String[] args) {
		Node<Integer> root = initTree();
		
		//先序遍历
		System.out.println("先序遍历：");
		List<Node<Integer>> orderlist = new ArrayList<>();
		TraverseTreeUtil.preOrderRecursion(root, orderlist);
		TraverseTreeUtil.printArray(orderlist);
		
		List<Node<Integer>> orderlist1 = new ArrayList<>();
		TraverseTreeUtil.preOrderStack(root, orderlist1);
		TraverseTreeUtil.printArray(orderlist1);
		
		System.out.println("中序遍历：");
		List<Node<Integer>> orderlist2 = new ArrayList<>();		
		TraverseTreeUtil.midOrderRecursion(root, orderlist2);
		TraverseTreeUtil.printArray(orderlist2);
		
		List<Node<Integer>> orderlist22 = new ArrayList<>();		
		TraverseTreeUtil.midOrderStack(root, orderlist22);
		TraverseTreeUtil.printArray(orderlist22);
		
		System.out.println("后序遍历：");
		List<Node<Integer>> orderlist3 = new ArrayList<>();
		TraverseTreeUtil.postOrderRecursion(root, orderlist3);
		TraverseTreeUtil.printArray(orderlist3);
		
		List<Node<Integer>> orderlist33 = new ArrayList<>();
		TraverseTreeUtil.postOrderStack(root, orderlist33);
		TraverseTreeUtil.printArray(orderlist33);
		
		System.out.println("广度优先遍历：");
		List<Node<Integer>> orderlist4 = new ArrayList<>();
		TraverseTreeUtil.breadthFirst(root, orderlist4);
		TraverseTreeUtil.printArray(orderlist4);
		
	}
	
	/**
	 *                        6
	 *                       / \
	 *                      3   9
	 *                     / \    \
	 *                    1   5    7
	 *                    \   /      \
	 *                     2 4       8
	 */
	private static Node<Integer> initTree() {
		Node<Integer> J = new Node<Integer>(8, null, null);  
        Node<Integer> H = new Node<>(4, null, null);  
        Node<Integer> G = new Node<>(2, null, null);  
        Node<Integer> F = new Node<>(7, null, J);  
        Node<Integer> E = new Node<>(5, H, null);  
        Node<Integer> D = new Node<>(1, null, G);  
        Node<Integer> C = new Node<>(9, F, null);  
        Node<Integer> B = new Node<>(3, D, E);  
        Node<Integer> A = new Node<>(6, B, C);  
        return A;  //返回根节点  
	}
	
	private static void printNode(Node<Integer> node) {
		System.out.println("value = " + node.getValue() + ";");
	} 
	
}
