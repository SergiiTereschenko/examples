package st.more.dns;

import org.jboss.netty.buffer.ChannelBuffer;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DnsQuery implements Encodable {
    private final QType qtype;
    private final QClass qclass;

    private final DnsName name;

    public DnsQuery(DnsName name, QType qtype, QClass qclass) {
        this.name = name;
        this.qtype = qtype;
        this.qclass = qclass;
    }

    public DnsName getName() {
        return name;
    }

    public QType getQtype() {
        return qtype;
    }

    public QClass getQclass() {
        return qclass;
    }

    @Override
    public void write(ChannelBuffer buffer) {
        name.write(buffer);

        buffer.writeShort(this.qtype.getIntCode());
        buffer.writeShort(this.qclass.getIntCode());
    }


    @Override
    public int getEncodedLength() {
        return name.getEncodedLength() + 4;
    }
}
