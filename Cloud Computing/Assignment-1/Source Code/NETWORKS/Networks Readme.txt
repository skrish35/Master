Network Benchmark

This folder consists of 12 files :
•	TCP
o	ctcp.java : Client 1 byte
o	stcp.java : Server 1 byte
o	ctcpkb : client 1 kilo byte
o	stcpkb : server 1 kilo byte
o	ctcp64kb : client 64 kilobyte
o	stcp64kb : server 64 kilobyte

•	UDP

o	cudp.java : Client 1 byte
o	sudp.java : Server 1 byte
o	cudpkb : client 1 kilo byte
o	sudpkb : server 1 kilo byte
o	cudp64kb : client 64 kilobyte
o	sudp64kb : server 64 kilobyte

Instructions :
•	You need to write the client in one instance and server in another instance.
•	You need to enter the “IP Address of the server” in each of the client program before running.

For TCP :
Socket socket=new Socket(“52.36.11.80”,port++);


For UDP :
InetAddress host=InetAddress getbyName(“52.36.11.89”);

•	Compilation : javac filename.java (on both client and server side)

•	Running : java filename ( on server side first and then on client side )
