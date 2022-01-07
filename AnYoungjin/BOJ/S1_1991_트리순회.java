package BOJ.silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제링크 https://www.acmicpc.net/problem/1991
 * 메모리 11684 kb
 * 시간 80 ms
 * 분류 트리, 재귀
 */
public class S1_1991_트리순회 {
    private static class Node {
        char data;
        Node left, right;

        public Node(char data) {
            this.data = data;
        }
    }

    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(input.readLine());

        Node[] tree = new Node[N];
        for (int n = 0; n < N; n++) {
            tree[n] = new Node((char) (n + 'A'));
        }

        for (int n = 0; n < N; n++) {
            char[] nodes = input.readLine().replace(" ", "").toCharArray();
            // A노드 -> tree[0], B노드 -> tree[1]
            char pNode = nodes[0];  // parent node
            char lNode = nodes[1];  // left node
            if (lNode != '.') {
                // 자식 노드가 있다면
                tree[pNode - 'A'].left = tree[lNode - 'A'];
            }
            char rNode = nodes[2];  // right node
            if (rNode != '.') {
                tree[pNode - 'A'].right = tree[rNode - 'A'];
            }
        }

        preOrder(tree[0]);
        output.append("\n");
        inOrder(tree[0]);
        output.append("\n");
        postOrder(tree[0]);
        output.append("\n");

        System.out.println(output);
    }

    // 전위 순회 : root -> left -> right
    public static void preOrder(Node root) {
        output.append(root.data);
        if (root.left != null) preOrder(root.left);
        if (root.right != null) preOrder(root.right);
    }

    // 중위 순회 : left -> root -> right
    public static void inOrder(Node root) {
        if (root.left != null) inOrder(root.left);
        output.append(root.data);
        if (root.right != null) inOrder(root.right);
    }

    // 후위 순회 : left -> right -> root
    public static void postOrder(Node root) {
        if (root.left != null) postOrder(root.left);
        if (root.right != null) postOrder(root.right);
        output.append(root.data);
    }

}



/*

이진 트리를 입력받아 전위/중위/후위 순회한 결과 출력

[input]
7       // 노드의 개수 N
A B C   // 노드, 왼쪽 자식 노드, 오른쪽 자식 노드
B D .   // . : 자식 노드 없음
C E F
E . .
F . G
D . .
G . .


[output]
ABDCEFG
DBAECFG
DBEGFCA

 */