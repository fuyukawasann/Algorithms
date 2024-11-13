package P070_트리순회하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static Node[] tree;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/P070_트리순회하기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine());
		tree = new Node[N];
		
		for(int i = 0; i < N; i++)
		{
			char[] ch = br.readLine().toCharArray();
			tree[ch[0] - 'A'] = new Node(ch[0], ch[2], ch[4]);
		}
		
		// 전위 순회
		precycle(0);
		sb.append("\n");
		// 중위 순회
		incycle(0);
		sb.append("\n");
		// 후위 순회
		postcycle(0);
		sb.append("\n");
		System.out.println(sb.toString());
		
	}
	
	static void precycle(int node)
	{
		sb.append(tree[node].alphabet);
		
		if(tree[node].left != '.') {
			precycle(tree[node].left - 'A');
		}
		if(tree[node].right != '.') {
			precycle(tree[node].right - 'A');
		}
	}
	
	static void incycle(int node)
	{
		if(tree[node].left != '.') {
			incycle(tree[node].left - 'A');
		}
		sb.append(tree[node].alphabet);
		if(tree[node].right != '.') {
			incycle(tree[node].right - 'A');
		}
		
	}
	
	static void postcycle(int node)
	{
		
		
		if(tree[node].left != '.') {
			postcycle(tree[node].left - 'A');
		}
		if(tree[node].right != '.') {
			postcycle(tree[node].right - 'A');
		}
		sb.append(tree[node].alphabet);
	}
	
	

}

class Node {
	char alphabet;
	char left;
	char right;
	
	public Node(char alphabet, char left, char right) {
		this.alphabet = alphabet;
		this.left = left;
		this.right = right;
	}
}
