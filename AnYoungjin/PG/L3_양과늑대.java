import java.util.*;

class Solution {
    ArrayList<Integer>[] tree;
    int[] info;
    int answer;

    public int solution(int[] info, int[][] edges) {
        answer = 0;
        this.info = info;
        tree = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int parentNode = edges[i][0];
            int childNode = edges[i][1];
            tree[parentNode].add(childNode);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        move(0, 0, 0, nextNodes);

        return answer;
    }

    public void move(int sheep, int wolf, int curNode, List nextNodes) {
        if (info[curNode] == 0) ++sheep;
        else ++wolf;
        if (sheep <= wolf) return;
        answer = Math.max(answer, sheep);

        ArrayList<Integer> newList = new ArrayList<>();
        newList.addAll(nextNodes);
        newList.addAll(tree[curNode]);
        newList.remove(Integer.valueOf(curNode));

        for (int node : newList) {
            move(sheep, wolf, node, newList);
        }
    }
}
