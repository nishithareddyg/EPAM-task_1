//Glowing Bulbs
/*There is an infinite series of bulbs indexed from 1. And there are 40 switches indexed from 1 to 40. Switch with index x is connected to the bulbs with indexes that are multiple of x. i.e switch 2 is connected to bulb 4, 6, 8 ....

You can easily observe that some bulbs are connected to multiple switches and some are not connected to any switch.

Chotu is playing with these switches, he wants to know the Kth glowing bulb from the start if certain switches are in ON state. If any of the switch is ON, connected to any bulb then bulb starts glowing. But chotu has special fond of prime numbers so he only puts prime indexed switches ON.

*/


    import java.util.*;
     
    class GlowingBulbs {
        public static void main(String args[] ) throws Exception {
            Scanner s = new Scanner(System.in);
            long testCases = Long.parseLong(s.nextLine());
            for (long testCase = 1; testCase <= testCases; testCase++) {
                String[] splitted = s.nextLine().split("");
                long searchedBulb = Long.parseLong(s.nextLine());
                List<Queue> queues = new ArrayList<>();
                List<Double> factors = new ArrayList<>();
                for (int i = 0; i < 40; i++)
                    if (splitted[i].equals("1"))
                        factors.add(1. / (i + 1));
                double sum = 0;
                for (Double f: factors)
                    sum += f;
     
                for (int i = 0; i < 40; i++)
                    if (splitted[i].equals("1"))
                        queues.add(new Queue(i + 1, (1. / (i + 1)) / sum));
     
     
                long sElement = queues.get(queues.size()-1).startElement(searchedBulb);
                long startElement = sElement;
                long cElement = 0;
                while (cElement < searchedBulb) {
                    startElement = sElement;
                    sElement = Math.round(sElement * 1.05 + 1);
                    cElement = 0;
                    for (int i = 0; i < queues.size(); i++)
                        cElement += X(sElement, reverse(queues.subList(0, i + 1)));
                }
     
                startElement = getStartElement(startElement, sElement, queues, 30, searchedBulb);
     
                long currentElement = 0;
                for (int i = 0; i < queues.size(); i++) {
                    queues.get(i).setStart(startElement);
                    currentElement += X(startElement, reverse(queues.subList(0, i + 1)));
                }
     
                Set<Integer> queuesWithLowestElement = new HashSet<>();
                long latestElement = startElement;
                for (long i = currentElement; i < searchedBulb;  i++) {
                    queuesWithLowestElement.clear();
                    long smallestIndex = Long.MAX_VALUE;
                    for (int q = 0; q < queues.size(); q++) {
                        long peek = queues.get(q).peek();
                        if (peek < smallestIndex) {
                            smallestIndex = peek;
                            queuesWithLowestElement.clear();
                            queuesWithLowestElement.add(q);
                        } else if (peek == smallestIndex) {
                            queuesWithLowestElement.add(q);
                        }
                        latestElement = smallestIndex;
                    }
                    queuesWithLowestElement.forEach(q -> queues.get(q).remove());
                }
                System.out.println(latestElement);
            }
        }
     
        private static long getStartElement(long startElement, long sElement, List<Queue> queues, int count, long searchedBulb) {
            if (count == 1)
                return startElement;
            long middle = (startElement + sElement) / 2;
     
            long cElement = 0;
            for (int i = 0; i < queues.size(); i++)
                cElement += X(middle, reverse(queues.subList(0, i + 1)));
            if (cElement < searchedBulb)
                return getStartElement(middle, sElement, queues, count - 1, searchedBulb);
            else
                return getStartElement(startElement, middle, queues, count - 1, searchedBulb);
        }
     
        static class Queue {
            long base;
            long multiplier;
            double fraction;
     
            public Queue(long base, double fraction) {
                this.base = base;
                this.fraction = fraction;
                this.multiplier = 1;
            }
     
            long peek() {
                return base * multiplier;
            }
     
            void remove() {
                this.multiplier++;
            }
     
            void setStart(long startElement) {
                this.multiplier = startElement / this.base + 1;
            }
     
            public long startElement(long searchedBulb) {
                double numberOfBulbsToAccountFor = fraction * searchedBulb - 0.5;
                return Math.round(numberOfBulbsToAccountFor * this.base);
            }
        }
     
        private static long X(long val, List<Long> toCut) {
            if (toCut.size() == 1)
                return val / toCut.get(0);
            List<List<Long>> list = new ArrayList<>();
            long x = val / toCut.get(0);
            for (int i = 1; i < toCut.size();  i++)
                list.add(toCut.subList(i, toCut.size()));
            long result = x;
            for (List<Long> l:list)
                result -= X(x, l);
            return result;
     
        }
     
        private static List<Long> reverse(List<Queue> orig) {
            List<Long> result = new ArrayList();
            for (int i = orig.size() - 1; i >= 0; i--)
                result.add(orig.get(i).base);
            return result;
        }
    }


