/*
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

*/
class MedianFinder {
    
    private int arrSize = 500;
    private int actualSize = 0;
    private Integer[] arr;

    /** initialize your data structure here. */
    public MedianFinder() {
        arr = new Integer[arrSize];
    }
    
    public void addNum(int num) {
        // first check if arr is full, if so, double size and copy contents
        if (arr.length == actualSize) {
            Integer[] newArr = new Integer[actualSize * 2];
            System.arraycopy(arr, 0, newArr, 0, actualSize);
        }
        
        // array is empty
        if (actualSize == 0) {
            arr[actualSize++] = num;
        } else if (actualSize == 1) {
            int existing = arr[0];
            
            if (num < existing) {
                arr[0] = num;
                arr[1] = existing;
            } else {
                arr[1] = num;
            }
            
            actualSize++;
        } else {
            
            int ptr = 0;
            while (ptr < actualSize && arr[ptr] != null && num >= arr[ptr]) {
                ptr++;
            }
            
            if (ptr == actualSize) {        // append
                arr[ptr] = num;
            } else {                        // insert
                
                Integer[] firstHalf = new Integer[ptr];
                System.arraycopy(arr, 0, firstHalf, 0, ptr);
                
                Integer[] secondHalf = new Integer[actualSize - ptr];
                System.arraycopy(arr, ptr, secondHalf, 0, actualSize - ptr);
                
                Integer[] resultArr = new Integer[arr.length + 1]; // add room for num
                
                System.arraycopy(firstHalf, 0, resultArr, 0, firstHalf.length);
                resultArr[ptr] = num;
                System.arraycopy(secondHalf, 0, resultArr, ptr + 1, secondHalf.length);
                
                arr = resultArr;
            }
            
            actualSize++;
        }
    }
    
    public double findMedian() {
        if (actualSize == 0) {
            return -1;
        } else if (actualSize == 1) {
            return arr[0];
        } else if (actualSize % 2 == 0) {   // is even
            return isEvenFindMean();
        } else {                            // is odd
            return isOddFindMedian();
        }
    }
    
    private double isEvenFindMean() {
        int upperMid = actualSize / 2;
        int lowerMid = upperMid - 1;
        
        return (arr[upperMid] + arr[lowerMid]) / 2.0;
    }
    
    private double isOddFindMedian() {
        int middle = actualSize / 2;
        return arr[middle];
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
