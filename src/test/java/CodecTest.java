import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Approach 1: Non-ASCII delimiter
 * Approach 2: Escaping
 * Approach 3: Chunked Transfer Encoding
 */
public class CodecTest {

    @Test
    void encodeAndDecodeStrings() {
        List<String> strs = List.of("Hello", "Java");
//        List<String> strs = List.of("", "");
        Codec codec = new Codec();
        String encoded = codec.encode(strs);
        assertThat(encoded).isEqualTo("5:Hello4:Java");

        List<String> decoded = codec.decode(encoded);
        assertThat(decoded).containsExactlyElementsOf(strs);
    }

    /**
     * Approach 3: Chunked Transfer Encoding
     */
    public class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str.length())
                  .append(':')
                  .append(str);
            }
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> decoded = new ArrayList<>();
            int i = 0;
            while (i < s.length()) {
                int searchIndex = s.indexOf(':', i);
                int chunkSize = Integer.parseInt(s.substring(i, searchIndex));
                i = searchIndex + chunkSize + 1;
                decoded.add(s.substring(searchIndex + 1, i));
            }
            return decoded;
        }
    }
}
