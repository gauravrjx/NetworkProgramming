package chapter9;
import java.nio.ByteBuffer;

public class BufferTwo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(11);

        String str = "Hellothere";
        byte[] data = str.getBytes();

        buffer.put(data, 2, 4);

        buffer.flip();

        byte[] result = new byte[5];

        buffer.get(result, 1, 4);

        System.out.println("Result: " + new String(result));
        
    }
}
