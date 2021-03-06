package st.more.dns;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class DnsRblServerMain {

    public static void main(String[] args) {

        ChannelFactory factory =
                new NioDatagramChannelFactory(Executors.newCachedThreadPool());

        ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(factory);

        bootstrap.setPipelineFactory(() -> Channels.pipeline(new DnsRequestHandler()));


        bootstrap.bind(new InetSocketAddress(53535));
    }
}
