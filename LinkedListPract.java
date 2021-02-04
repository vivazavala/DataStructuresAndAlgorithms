import java.util.*;

import javax.swing.JOptionPane;

import java.io.*;

public class LinkedListPract {
	
	Node head;
	
	static class Node{
		int data; 
		Node next;
		
		Node(){
		}
		
		Node(int d){
			this.data=d;
			next=null;
		}
	}
	
	public static LinkedListPract insert(LinkedListPract list, int number) {
		Node newNode = new Node(number);
		newNode.next=null;
		
		if(list.head==null) {
			list.head=newNode;
		}
		else {
			Node last= list.head;
			while(last.next!=null) {
				last=last.next;
			}
			last.next=newNode;
			
		}
		return list;
	}
	
	public static LinkedListPract deleteKey(LinkedListPract list, int key) {
		Node currNode=list.head, prev=null;
		
		if(currNode!=null && currNode.data==key) {
			list.head=currNode.next;    	//list.head
			System.out.println("Your key "+ key+" was deleted");
			
			return list;
		}
		
		while(currNode!=null && currNode.data!=key) {
			prev=currNode;
			currNode=currNode.next;
		}
		if(currNode!=null) {
			prev.next=currNode.next;
			System.out.println("\nYour key "+key+" was deleted");
		}
		if(currNode==null) {
			System.out.println("\nYour key "+key+" was not found");
		}	
		return list;
	}
	
	public static void printList(Node node) {
		Node currNode=node;
		System.out.print("Your linked list holds: ");
		while(currNode!=null) {
			System.out.print(currNode.data+" ");		
			currNode=currNode.next;
		}
		
	}
	
	public static Node getMiddleNode(Node node) {
		if(node==null)
			return null;
		
		Node slow= node; 
		Node fast=node.next;
		
		while(fast!=null && fast.next!=null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		
		return slow;
	}
	
	public static Node mergeSort(Node head) {
		if(head==null || head.next==null)
			return head;
		
		Node middle= getMiddleNode(head);
		Node secondHalf = middle.next;
		
		middle.next=null;

		
		return merge(mergeSort(head), mergeSort(secondHalf));
	}
	
	public static Node merge(Node firstList, Node secondList) {
		Node temp = new Node();
		Node finalList=temp;
		
		while(firstList != null && secondList!=null) {
			if(firstList.data< secondList.data) {
				temp.next=firstList;
				firstList=firstList.next;
			}else{
				temp.next=secondList;
				secondList=secondList.next;
			}
			temp=temp.next;			
		}
			
		temp.next = (firstList==null) ? secondList: firstList;
				
		return finalList.next;
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("How many different numbers would you like to input into your linked list?");
		int numNodes=in.nextInt();
		LinkedListPract list = new LinkedListPract();
		
		for(int i=0 ; i< numNodes; i++) {
			System.out.println("What is your number for index "+i+"?");
			int number=in.nextInt();
			list=insert(list, number);
		}
		
		printList(list.head);
		deleteKey(list, 3);
		System.out.print("Your updated list is: ");
		printList(list.head);
		System.out.println("\nNow we will sort your list.");
		//mergeSort(list.head);
		printList(mergeSort(list.head));

	}

}
