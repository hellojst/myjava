package com.stjia.javabase.traversetree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 
 * @author stjia
 * @time 2018年4月7日
 */
class Node<V>{
	V value;
	Node<V> left, right;
	/**
	 * 
	 */
	public Node(V value, Node<V> left, Node<V> right) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public Node<V> getLeft() {
		return left;
	}
	public void setLeft(Node<V> left) {
		this.left = left;
	}
	public Node<V> getRight() {
		return right;
	}
	public void setRight(Node<V> right) {
		this.right = right;
	}
	
}
