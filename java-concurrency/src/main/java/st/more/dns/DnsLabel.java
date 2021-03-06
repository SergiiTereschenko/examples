package st.more.dns;

//import com.google.common.base.Charsets;
import org.apache.commons.codec.Charsets;
import org.jboss.netty.buffer.ChannelBuffer;

import javax.annotation.concurrent.Immutable;

@Immutable
public class DnsLabel implements Encodable {

    private final byte[] data;

    /**
     * @param data this data is now owned by the DnsLabel object.
     */
    public DnsLabel(byte[] data) {
        this.data = data;
    }

    @Override
    public int getEncodedLength() {
        return data.length + 1;
    }

    @Override
    public void write(ChannelBuffer buffer) {
        buffer.writeByte(data.length & 0xFF);
        buffer.writeBytes(data);
    }

    @Override
    public String toString() {
        return new String(data, Charsets.US_ASCII);
    }
}
