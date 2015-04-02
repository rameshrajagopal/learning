public class BigIntegerTest {
    public static void main(String[] args) {
        testBigInteger1(1, "representation");
        testBigInteger2(2, "representation");
        testBigIntegerAdd(3, "add two simple numbers");
        testBigIntegerMul(4, "Multiply simple two numbers");
    }
    private static void testBigInteger1(int testNo, String help) {
        System.out.print("Running test # " + testNo + " : " + help);
        byte[] array = new byte[8];
        int start = 48;
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte) (start + i);
        }
        BigInteger be = new BigInteger(array);

        if (!(be.getNumber().equals("01234567"))) {
            System.out.println("FAIL");
        }
        System.out.println("\t.....PASS");
    }
    private static void testBigInteger2(int testNo, String help) {
        System.out.print("Running test # " + testNo + " : " + help);
        byte[] array = new byte[8];
        int start = 65;
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte) (start + i);
        }
        BigInteger be = new BigInteger(array);

        if (!(be.getNumber().equals("ABCDEFGH"))) {
            System.out.println("FAIL");
        }
        System.out.println("\t.....PASS");
    }
    private static void testBigIntegerAdd(int testNo, String help) {
        System.out.print("Running test # " + testNo + " : " + help);
        byte[] array = new byte[8];
        int start = 65;
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte) (start + i);
        }
        BigInteger be1 = new BigInteger(array);
        start = 0;
        for (int i = array.length-1; i >= 0; --i) {
            array[i] = (byte) (start + i);
        }
        BigInteger be2 = new BigInteger(array);
        be1.add(be2);
        System.out.println(be1.getNumber());
    }
    private static void testBigIntegerMul(int testNo, String help) {
        System.out.println("Running test # " + testNo + " : " + help);
        BigInteger be1 = new BigInteger();
        BigInteger be2 = new BigInteger();

        byte[] array  = new byte[4];
        array[0] = 0xA;
        array[1] = 0x0;
        array[2] = 0x0;
        array[3] = 0x0;
        be1.setNumber(array);
        array[0] = 0xB;
        be2.setNumber(array);

        BigInteger mul;
        be1.dumpInteger();
        be2.dumpInteger();
        mul = be1.multiply(be2);
        mul.dumpInteger();
    }
}
