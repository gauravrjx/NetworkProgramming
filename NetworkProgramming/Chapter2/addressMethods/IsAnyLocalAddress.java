package Chapter2.addressMethods;

import java.net.*;
class IsAnyLocalAddress
{
        public static void main(String[] args) throws Exception
        {
                byte[] b = {0, 0, 0, 0};
                String s = "abc";
                InetAddress in = InetAddress.getByAddress(b);
                boolean b1 = in.isAnyLocalAddress();
                System.out.println(in);
                System.out.println(b1);
        }
}
