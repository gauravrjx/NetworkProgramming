
/* PART I: flip() method */

package chapter9;
import java.nio.ByteBuffer;

public class Buffer {
    public static void main(String[] args) {
        // Filling the buffer
        ByteBuffer buffer = ByteBuffer.allocate(7);
        System.out.println("Before FILLING:");
        printBufferState(buffer);
        String str = "Hello";
        byte [] data = str.getBytes();
        buffer.put(data);
        System.out.println("after FILLING the buffer:");
        printBufferState(buffer);

        // Flip the buffer before reading
        // flip() => this will set limit to current position and position to zero
        buffer.flip();
        System.out.println("after Flipping the buffer:");
        printBufferState(buffer);


        int limit = buffer.limit();
        byte [] output = new byte[limit];
        buffer.get(output);
        
        // Print the buffer data
        System.out.println(new String(output));
        System.out.println("after reading the buffer:");
        printBufferState(buffer);
    }

    private static void printBufferState(ByteBuffer buffer) {
        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());
        System.out.println("Capacity: " + buffer.capacity());
    }
    
}
