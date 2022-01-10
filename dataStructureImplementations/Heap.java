package dataStructureImplementations;

import java.util.Arrays;

public class Heap {
	 int size,capacity;
	 int[] items;
	 public Heap(int c) {
		 capacity=c;
		 items=new int[capacity];
	 }
	 
	
	private int getLeftChildIndex(int parent) {
		return (2*parent+1);
	}
	private int getRightChildIndex(int parent) {
		return (2*parent+2);
	}
	private int getParentIndex(int child) {
		return (child-1)/2;
	}
	private boolean hasLeftChild(int parent) {
		return getLeftChildIndex(parent)<size;
	}
	private boolean hasRightChild(int parent) {
		return getRightChildIndex(parent)<size;
	}
	private boolean hasParent(int child) {
		return getParentIndex(child)>=0;
	}
	private int leftChild(int index) {
		return items[getLeftChildIndex(index)];
	}
	private int rightChild(int index) {
		return items[getRightChildIndex(index)];
	}
	private int parent(int index) {
		return items[getParentIndex(index)];
	}
	private void swap(int i,int j) {
		int temp=items[i];
		items[i]=items[j];
		items[j]=temp;
	}
	private void ensureExtraCapacity() {
		if(size==capacity) {
			items = Arrays.copyOf(items, capacity*2);
			capacity=capacity*2;
		}
	}
	public int peek() {
		if(size==0) return Integer.MAX_VALUE;
		return items[0];
	}
	public int poll() {
		if(size==0) return Integer.MAX_VALUE;
		int item=items[0];
		items[0]=items[size-1];
		size--;
		heapifyDown();
		return item;
	}
	public void add(int item) {
		ensureExtraCapacity();
		items[size]=item;
		size++;
		heapifyUp();
	}
	private void heapifyUp() {
		int index=size-1;
		while(hasParent(index)&& parent(index)>items[index]) {
			swap(getParentIndex(index),index);
			index=getParentIndex(index);
		}
	}
	private void heapifyDown() {
		int index=0;
		while(hasLeftChild(index)) {
			int smallerChildIndex=getLeftChildIndex(index);
			if(hasRightChild(index)&& rightChild(index)<leftChild(index))
				smallerChildIndex=getRightChildIndex(index);
			if(items[index]<items[smallerChildIndex])
				break;
			else {
				swap(index,smallerChildIndex);
			}
			index=smallerChildIndex;
		}
	}
	private boolean contains(int x) {
		for(int i=0;i<size;i++) {
			if(items[i]==x)
				return true;
		}
		return false;
	}

}
