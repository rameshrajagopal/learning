import java.io.IOException;

public class BigInteger {
    private int numBytes;
    private byte[] number;
    private int curIdx;
    /* methods */
    public BigInteger() {
        this.numBytes = 128;
        this.number = new byte[numBytes];
        this.curIdx = 0;
    }

    public BigInteger(byte[] byteArray) {
        numBytes = 128;
        number = new byte[numBytes];
        /* parse String and store into number[] */
        for (int i = 0; i < byteArray.length; ++i) {
            number[i] = byteArray[i];
        }
        curIdx = byteArray.length;
    }

    public BigInteger(byte[] byteArray, int length) {
        numBytes = 128;
        number = new byte[numBytes];
        /* parse String and store into number[] */
        for (int i = 0; i < length; ++i) {
            number[i] = byteArray[i];
        }
        curIdx = length;
    }

    public String getNumber() {
        String str;
        try {
          str = new String(number, 0, curIdx, "US-ASCII");
        } catch(IOException ioe) {
            System.out.println("Exception: " + ioe);
            return null;
        }
        return str;
    }

    public void setNumber(byte[] num) {
        numBytes = 128;
        number = new byte[numBytes];
        if (num.length > numBytes) {
            System.out.println("Too big number, will not be able to set it");
            return;
        }
        /* parse String and store into number[] */
        for (int i = 0; i < num.length; ++i) {
            number[i] = num[i];
        }
        this.curIdx = num.length;
    }

    public void add(BigInteger be) {
        int carry = 0, sum = 0;
        int idx = (this.curIdx < be.curIdx) ? this.curIdx : be.curIdx;
        int i = 0;
        for (; i < idx; ++i) {
            sum = this.number[i] + be.number[i] + carry;
            carry = sum / 256;
            this.number[i] = (byte)(sum % 256);
        }
        while ((i < this.curIdx) && (carry != 0)) {
            sum = this.number[i] + carry;
            carry = sum / 256;
            this.number[i] = (byte)(sum % 256);
            ++i;
        }
        while (i < be.curIdx) {
            sum = be.number[i] + carry;
            carry = sum / 256;
            this.number[i] = (byte)(sum % 256);
            ++i;
        }
        this.curIdx = i;
    }

    private static boolean isBitSet(BigInteger be, int bitnum) {
        int byteNum = bitnum / 8;
        int bit  = bitnum % 8;
        return ((be.number[byteNum] & (1 << bit)) != 0);
    }

    private static void shiftLeftByOne(BigInteger be) {
        int addbit = 0;
        int value;
        for (int i = 0 ; i < be.curIdx; ++i) {
            value = (be.number[i] << 1) | addbit;
            if (isBitSet(be, i * 8 + 7)) {
                addbit = 1;
            } else {
                addbit = 0;
            }
            be.number[i] = (byte)value;
        }
        if (addbit == 1) {
            if (be.curIdx == be.numBytes) {
                System.out.println("Overflow case...");
            } else {
                be.number[be.curIdx] = (byte)addbit;
                ++be.curIdx;
            }
        }
    }

    public void dumpInteger() {
        for (int i = this.curIdx-1; i > 0; --i) {
            System.out.print(this.number[i] + " ");
        }
        System.out.println(this.number[0]);
    }

    public BigInteger multiply(BigInteger be) {
        BigInteger sum = new BigInteger();
        BigInteger runner = new BigInteger(this.number, this.curIdx);
        int numBits = (be.curIdx) * 8;

        System.out.println("mul: " + this.curIdx + " " + be.curIdx + " numbits: " + numBits);
        for (int i = 0; i < numBits; ++i) {
            if (isBitSet(be, i)) {
                sum.add(runner);
            }
            shiftLeftByOne(runner);
        }
        return sum;
    }
}
