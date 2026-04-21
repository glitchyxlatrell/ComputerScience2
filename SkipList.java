/*  Latrell Kong
    Fast Lanes: Skip List Implementation
    COP3503 Computer Science 2
    SkipList.java
*/

public class SkipList
{
    // creating node for skip list
    public class Node
    {
        // properties of ID, height, and pointer to next node
        int studentID;
        int height;
        Node[] next;

        // constructor for Node
        public Node(int ID, int level)
        {
            this.studentID = ID;
            this.height = level;
            this.next = new Node[3];
        }
    }

    // declaring head node, number of IDs, and highest level in use
    Node head;
    int count;
    int highestLevel;

    // initializing head
    public SkipList()
    {
        head = new Node(-1, 3);
        count = 0;
        highestLevel = 0;
    }

    // function to search for ID in skip list
    public boolean search(int studentID)
    {
        // initializing temp node to search for ID
        Node temp = head;

        // searching through list starting at top level
        for(int i = 2; i >= 0; i--)
        {   
            // while next node at level is not null and less than ID we are looking for
            while(temp.next[i] != null && temp.next[i].studentID < studentID)
            {   
                // increment temp
                temp = temp.next[i];
            }
        }

        // returning false if temp.next is null
        if(temp.next[0] == null) 
        {
            return false;
        }

        // incrementing temp to NULL, a value greater than ID, or the ID we are searching for
        temp = temp.next[0];

        // if ID in temp is the ID we are looking for, returning true
        if(temp.studentID == studentID)
        {
            return true;
        }

        // returning false otherwise 
        return false;
    }

    // helper function to find the height an ID should be placed at
    public int findHeight(int ID)
    {
        // height 3 if divisible by 4
        if(ID % 4 == 0)
        {
            return 3;
        }
        // height 2 if divisible by 2
        else if(ID % 2 == 0)
        {
            return 2;
        }
        // height 1 otherwise
        return 1;
    }

    // function to insert ID into skip list
    public void insert(int studentID)
    {   
        // if already in list, exit function
        if(search(studentID))
        {
            return;
        }

        // Node array to track predecessors
        Node[] predecessors = new Node[3];

        // getting height that ID should be placed at
        int height = findHeight(studentID);

        // initializing temp node to search for ID
        Node temp = head;

        // searching through list starting at top level
        for(int i = 2; i >= 0; i--)
        {   
            // while next node at level is not null and less than ID we are looking for
            while(temp.next[i] != null && temp.next[i].studentID < studentID)
            {   
                // increment temp
                temp = temp.next[i];
            }

            // putting temp into predecessors once traversed as much as possible in current level
            predecessors[i] = temp;
        }

        // creating new node with ID and height
        Node newNode = new Node(studentID, height);

        // for each level ID should be in, inserting ID in valid spot
        for(int i = 0; i < height; i++)
        {   
            // next node for new node is predecessors next node
            newNode.next[i] = predecessors[i].next[i];

            // making predecessor next node, new node
            predecessors[i].next[i] = newNode;
        }

        // updating highest level and count
        highestLevel = Math.max(highestLevel, height);
        count++;
    }

    // function to remove ID from skip list
    public void delete(int studentID)
    {

    }

    // function to return number of IDs currently stored
    public int size()
    {
        return count;
    }

    // function to return highest height currently in use
    public int height()
    {
        return highestLevel;
    }
}