import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MaxSumPath {
    public static final int SIZE = 15;
    private static int LINE_COUNT=0;
    private static int[][] arr=new int[SIZE][SIZE];


    public static void main(String[] args) {

        int result=MaxSumPath.findMaxSum("example1.txt");
        System.out.println("Example 1 - Result:"+result);

        result=MaxSumPath.findMaxSum("example2.txt");
        System.out.println("Example 2 - Result:"+result);
    }

    /**
     * Init method
     * @param fileName
     * @return
     */
    public static int findMaxSum(String fileName){
        LINE_COUNT=0;
        readDataFromFile(fileName);
        if (arr[0][0]==-1) return 0;
        int result=findMaxPath(0,0);
        if (result<0) result=0;
        return result;
    }

    /**
     * Finds the path with the highest sum in the given data
     * @param row Row index
     * @param col Column index
     * @return maximum sum
     */
    private static int findMaxPath(int row, int col){
        if (row>=LINE_COUNT ||col>=LINE_COUNT) return 0;
        else if (row == (LINE_COUNT - 1)) {
            return arr[row][col];
        }
        // End of the path. Because left and right is prime number.
        if (arr[row][col]==-1 || (arr[row + 1][col]==-1 && arr[row + 1][col + 1]==-1)) return Integer.MIN_VALUE;

        return arr[row][col] + Math.max(findMaxPath(row + 1, col),findMaxPath(row + 1, col+1));
    }

    private static boolean isPrime(int number) {
        if (number<2) return false;
        for (int i = 2; i < number; ++i) {
            if ((number % i) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Reads number from the given file and puts the array.
     * @param fileName
     */
    private static void readDataFromFile(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (int i = 0; i < numbers.length; ++i) {
                    int temp=Integer.parseInt(numbers[i]);
                    if (!isPrime(temp))
                        arr[LINE_COUNT][i] = Integer.parseInt(numbers[i]);
                    else arr[LINE_COUNT][i] = -1;
                }
                LINE_COUNT++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input/Output error!");
            e.printStackTrace();
        }
    }

    private static void printData(){
        for (int i=0;i<LINE_COUNT;i++){
            for (int j=0;j<LINE_COUNT;j++){
                System.out.format("%2d ",arr[i][j]);
            }
            System.out.println();
        }
    }


}
