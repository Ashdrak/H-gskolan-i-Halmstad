public class ListNode<T> {
    
    T value;
    ListNode<T> next;
    ListNode<T> prev;

    public ListNode(T value, ListNode<T> prev, ListNode<T> next){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public ListNode(T value){
        this.value = value;
        this.next = null;  
        this.prev = null;
        
    }

    public ListNode(){
        this.value = null;
        this.next = null;
        this.prev = null;
    }
}
