package com.wuhj.code;

/**
 * @author wuhj
 * @date 2021/1/27 16:04
 */
public class ListNodeRemove {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        head = removeNthFromEnd(head, 2);
        System.out.println(head.toString());
    }
    
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //当链表为空或者要删除的节点小于等于0的时候，直接返回head
        if (head == null || n <= 0)
            return head;
        //建立一个虚拟的表头结点，因为需要删除的节点有可能是头结点，
        // 所以建立虚拟头结点可以不用分是否是头结点两种情况
        ListNode tempHead = new ListNode(0);
        tempHead.next = head;
        ListNode p = tempHead, q = tempHead;
        //p指针比q指针先跑n次
        for (int i = 0; i < n; i++) {
            //如果p为空的时候，说明这个节点的长度不足n，返回head
            if (p == null )
                return head;
            else {
                p = p.next;
            }
        }
        //p，q一起往前跑，直到p的next为空，
        // q所指向的下一个结点就是要删除的元素的位置
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        
        //删除q指向的节点的下一个元素
        q.next = q.next.next;
        //删除虚拟头结点
        return tempHead.next;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
        }
        
        public ListNode(int[] arr) {
            if (arr == null || arr.length == 0)
                throw new IllegalArgumentException("arr can to be empty");
            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }
        
        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                res.append(cur.val + "->");
                cur = cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }
}