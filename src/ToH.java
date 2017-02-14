import java.util.*;

public class ToH
{

    public static void main(String[] args)
    {

        int numDisks = 0;
        int fromPeg = 0; //Since we are using Stack and arrays we are going to
        int toPeg = 2;  // use 3 pegs indexed at 0 - 2
        
        
        if (args.length > 0)
        {
            try
            {
                numDisks = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e)
            {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        Disk myToH = new Disk(numDisks);

        myToH.printInitStatus();

        myToH.moveTowers(numDisks,fromPeg,toPeg);

    }


    private static class Disk {

        final int NUMPEGS = 3;
        
        ArrayList<Stack<Integer>> pegs = new ArrayList();
        Stack<Integer> pegA = new Stack<>();
        Stack<Integer> pegB = new Stack<>();
        Stack<Integer> pegC = new Stack<>();


        Disk(int numDisks)
        {
            setUpTower(numDisks);
        }

        void setUpTower(int numDisks)
        {
            for (int i = numDisks; i > 0; i--)
            {

                this.pegA.add(i);
            }
            
            pegs.add(0, pegA);
            pegs.add(1, pegB);
            pegs.add(2, pegC);
        }


        public void moveTowersSingleDisk(int from, int to)
        {
            Stack<Integer> pegFrom = pegs.get(from);
            Stack<Integer> pegTo = pegs.get(to);
            if (pegs.get(to).empty() || pegFrom.peek() < pegTo.peek())
            {
                pegTo.push(pegFrom.pop());
            }
        }

        public void moveTowers(int disk, int from, int to)
        {
            if (disk == 1)
            {
                moveTowersSingleDisk(from, to);
                printMove(disk, from, to);
                printAllPegStatus(pegA, pegB, pegC);
            }
            else
            {
                int auxPeg = NUMPEGS - from - to;
                moveTowers(disk - 1, from, auxPeg);

                moveTowersSingleDisk(from, to);

                printMove(disk, from, to);

                printAllPegStatus(pegA, pegB, pegC);

                moveTowers(disk - 1, auxPeg, to);
            }

        }


        String printSinglePeg(Stack<Integer> peg)
        {
            String pegHaveDisk = "";
            for (int i = 0; i < peg.size(); i++)
            {
                if (peg.isEmpty())
                {
                    pegHaveDisk = " ";
                }
                else
                {
                    pegHaveDisk += peg.get(i);
                }

            }

            return pegHaveDisk;

        }

        void printAllPegStatus(Stack<Integer> a, Stack<Integer> b, Stack<Integer> c)
        {
            System.out.print(String.format("%-10s", printSinglePeg(a)));
            System.out.print(String.format("%-8s",  printSinglePeg(b)));
            System.out.print(String.format("%8s\n", printSinglePeg(c)));
        }

        void printMove(int n, int from, int to)
        {
            System.out.printf("%d From %d To %d ", n, from + 1, to + 1);
        }

        void printInitStatus()
        {
            System.out.printf("%40s", "Peg Configuration\n");
            System.out.printf("%15s %10s %10s\n", "A", "B", "C");
            System.out.print("Move\n");
            System.out.printf("Init %12s\n", printSinglePeg(pegA));
        }

    }
}




