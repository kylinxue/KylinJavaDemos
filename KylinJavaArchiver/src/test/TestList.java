package com.it18zhang.archive.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestList {
	
	@Test
	public void testArrayList(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < 1000000 ; i ++){
			list.add(0, i);
		}
		
		long start = System.nanoTime();
		list.get(500000);
		System.out.println(System.nanoTime() - start);
	}
	
	@Test
	public void testLinkedList(){
		List<Integer> list = new LinkedList<Integer>();
		for(int i = 0 ; i < 1000000 ; i ++){
			list.add(0, i);
		}
		
		long start = System.nanoTime();
		list.get(500000);
		System.out.println(System.nanoTime() - start);
	}
}
