/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;

/**
 *
 * @author abhi
 */
class Queue
{
    DNODE cdlist = null;
    int count = 0;
    DNODE front;
    DNODE rear;
    public void enque(String x)
    {
        DNODE dnewnode = new DNODE(x);
        ++count;
        DNODE temp = cdlist;
        if(cdlist == null)
        {
            cdlist = dnewnode;
            front = cdlist;
            rear = cdlist;
        }
        else
        {
            while(temp.next != null)
            {
                temp = temp.next;
            }
            temp.next = dnewnode;
            rear = dnewnode;
        }
    }
    public String deque()
    {
        String info;
        if(cdlist == null)
        {
            info=null;
        }
        else
        {
            --count;
            info=cdlist.info;
            cdlist = cdlist.next;
            front = cdlist;
        }
        return info;
    }
    public int getCount()
    {
        return count;
    }
}
